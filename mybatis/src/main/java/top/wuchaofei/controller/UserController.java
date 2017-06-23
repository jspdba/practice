package top.wuchaofei.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.wuchaofei.auth.PasswordHelper;
import top.wuchaofei.domain.DataTableParameter;
import top.wuchaofei.domain.User;
import top.wuchaofei.manager.UserManager;
import top.wuchaofei.utils.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解决用户登录注销等问题
 * Created by wuchaofei on 2017/3/29.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "login",method = {RequestMethod.GET})
    public String login_page(HttpServletRequest request){
        return "user/login";
    }

    @RequestMapping(value = "login",method = {RequestMethod.POST})
    public String login(HttpServletRequest request, RedirectAttributes redirectAttributes, String username, String passwd,boolean remberMe){

        if(StringUtils.isBlank(username)){
            redirectAttributes.addFlashAttribute("message", "用户名不能为空");
            return "redirect:/user/login";
        }
        if(StringUtils.isBlank(passwd)){
            redirectAttributes.addFlashAttribute("message", "密码不能为空");
            return "redirect:/user/login";
        }

        //先进行验证码检查
        String exception = (String) request.getAttribute("shiroLoginFailure");

        if(StringUtils.isNotBlank(exception)){
            if("Kaptcha.error".equals(exception)){
                redirectAttributes.addFlashAttribute("message", "验证码错误");
            }
            return "redirect:/user/login";
        }

        try {
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
//            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, MD5Util.md5(passwd)));
//            rememeberMe
            UsernamePasswordToken token = new UsernamePasswordToken(username, passwd);
            if(remberMe){
                token.setRememberMe(true);
            }
            SecurityUtils.getSubject().login(token);
            User user = new User();
            user.setUsername(username);
            request.getSession().setAttribute("user",user);

            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if(savedRequest!=null){
                String url = savedRequest.getRequestUrl();
                if(StringUtils.isNotBlank(url)){
                    if(!url.contains("/favicon.ico") && !url.contains("/user/login") && !url.contains("/user/logout")){
                        return "redirect:"+url;
                    }
                }
            }
            return "redirect:/";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/user/login";
        }
    }
    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");

        return "redirect:/user/login";
    }
    //用户信息
    @RequestMapping("my")
    public String my(ModelMap modelMap){
        String name=(String) SecurityUtils.getSubject().getPrincipal();
        if(name!=null && name.length()>0){
            User user=userManager.findUserByName(name);
            modelMap.addAttribute("user",user);
        }
        return "user/my";
    }

    @RequestMapping("index")
    public Object index(){
        return "user/index";
    }

    @RequestMapping("pager")
    @ResponseBody
    public Object pager(@RequestBody List<Object> param){
        DataTableParameter data =new DataTableParameter();
        Map<String,Object> map =convertToMap(param);
        List<User> list = userManager.listByDataTable(map);
        //强制转换为pageInfo
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        data.setAaData(pageInfo.getList());
        data.setiTotalDisplayRecords(pageInfo.getTotal());
        data.setiTotalRecords(pageInfo.getTotal());
        data.setsEcho(map.get("sEcho")+"");
        return data;
    }

    private static Map<String,Object>convertToMap(List<Object> list){
        Map<String,Object>map =new HashMap<String, Object>();
        for(Object ob:list){
            Map<String,Object> param =(Map<String, Object>) ob;
            map.put(param.get("name")+"",param.get("value"));
        }
        return map;
    }

    @RequestMapping(value = "edit",method = {RequestMethod.GET})
    public String edit(ModelMap modelMap, Integer id){
        User u = null;
        if(id!=null && id!=0){
            u = userManager.selectByPrimaryKey(id);
        }
        modelMap.addAttribute("u", u);
        return "user/edit";
    }

    @RequestMapping(value = "save",method = {RequestMethod.POST})
    public String save(User user, RedirectAttributes redirectAttributes){
        if(user.getId()==null || user.getId()==0){
            if(StringUtils.isNotBlank(user.getPassword())){
                //password加密
                user.setPassword(PasswordHelper.encryptPassword(user.getPassword(),user.getUsername()));
            }
            try {
                userManager.save(user);
            }catch (Exception e){
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("msg",e.getMessage());
                return "redirect:/user/edit.ation";
            }
        }else{
            userManager.updateByPrimaryKeySelective(user);
        }
        return "redirect:/user/index";
    }
}
