package top.wuchaofei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wuchaofei.service.WechatService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cofco on 2017/6/18.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    @Qualifier(value="basicWechatService")
    WechatService wechatService;
    @Value("qrPath")
    String qrPath;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(ModelMap modelMap){
        int status=wechatService.status();
        if(status!=0){
            wechatService.login();
        }
        modelMap.addAttribute("status",status);
        return "wechat/login";
    }
    @RequestMapping(value = "logout")
    @ResponseBody
    public Map logout(){
        int status=wechatService.status();
        if(status==0){
            wechatService.logout();
        }

        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("msg","");
        return resultMap;
    }

    @RequestMapping(value = "status")
    @ResponseBody
    public Map status(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("status", wechatService.status());
        return resultMap;
    }
}
