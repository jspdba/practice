package top.wuchaofei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.wuchaofei.service.WechatService;

/**
 * Created by cofco on 2017/6/18.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    @Qualifier(value="tulingWechatServiceImpl")
    WechatService wechatService;
    @Value("qrPath")
    String qrPath;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        wechatService.login();
        return "wechat/login";
    }
}
