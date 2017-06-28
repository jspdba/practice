package sso.top.wuchaofei.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sso.top.wuchaofei.domain.User;
import sso.top.wuchaofei.service.UserService;
import sso.top.wuchaofei.utils.PasswordHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static sso.top.wuchaofei.constant.Constant.ERROR;
import static sso.top.wuchaofei.constant.Constant.LOGIN;
import static sso.top.wuchaofei.constant.Constant.REGIST;

/**
 * TGC 关联用户信息(可以存放用户标识)
 * serviceTicket 关联TGC(可以存放TGC)
 *
 * Created by cofco on 2017/6/23.
 */
@Controller
@RequestMapping
public class LoginController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, ModelMap modelMap){
        String redirectUrl = request.getParameter("redirectUrl");

        modelMap.addAttribute("redirectUrl",redirectUrl);
        return LOGIN;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,User user, String redirectUrl, RedirectAttributes redirectAttributes){
        if(StringUtils.isBlank(redirectUrl)){
            redirectAttributes.addFlashAttribute(ERROR,"参数不能为空=redirectUrl");
            return ERROR;
        }
        if(StringUtils.isBlank(user.getUsername())|| StringUtils.isBlank(user.getPassword())){
            redirectAttributes.addFlashAttribute(ERROR,"用户名或密码不能为空");
            return ERROR;
        }

        User user1=userService.login(request,response,user.getUsername(), PasswordHelper.encryptPassword(user.getPassword(),user.getUsername()));
        if(user1!=null){
//            并为客户端浏览器设置一个 Ticket Granted Cookie （ TGC ）,并关联用户信息
            String tgc = userService.grantTicketGrantedCookie(response,user1);
            //登录成功添加一个serviceTicket参数
            redirectUrl = userService.addParameter(redirectUrl, "serviceTicket", userService.genAndStoreServiceTicket(tgc));
            return "redirect:" + redirectUrl;
        }
        redirectAttributes.addFlashAttribute(ERROR,"用户名或密码错误");
        return LOGIN;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response){
        userService.logout(request,response);
        String redirctUrl = request.getParameter("redirectUrl");
        // TODO: 2017/6/28 默认值需要添加
        return "redirect:"+redirctUrl;
    }

    @RequestMapping(value = "regist",method = RequestMethod.GET)
    public String regist(){
        return REGIST;
    }

    @RequestMapping(value = "userInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object userInfo(String serviceTicket){
        //检查serviceTicket是否失效
        boolean expired = userService.isExpireOfServiceTicket(serviceTicket);
        //过期
        if(expired){
            return null;
        }
        //获取serviceTicket对应的用户信息
        String username = userService.getUserInfoByServiceTicket(serviceTicket);
        userService.deleteServiceTicket(serviceTicket);
        return username;
    }

    /**
     * 应用检查
     * 1.获取TGC:如果有效，走step4(重定向到client service 带serviceTicket)
     * 2.如果失效，走step3(去登录接口)
     * @return
     */
    @RequestMapping("auth")
    public String auth(HttpServletRequest request,RedirectAttributes redirectAttributes){
        String redirectUrl = request.getParameter("redirectUrl");
        String serviceTicket = request.getParameter("serviceTicket");

        String TGC = userService.getTicketGrantCookieFromRequest(request);

        if(StringUtils.isNotBlank(TGC) && !userService.isExpireOfTicketGrantCookie(TGC)){
            //重新生成serviceTicket 走step5
            if(StringUtils.isNotBlank(serviceTicket)){
                userService.deleteServiceTicket(serviceTicket);
            }
            redirectUrl = userService.addParameter(redirectUrl, "serviceTicket", userService.genAndStoreServiceTicket(TGC));
            return "redirect:" + redirectUrl;
        }else{
            return "redirect:/login?redirectUrl="+redirectUrl;
        }
    }
}
