package sso.top.wuchaofei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cofco on 2017/6/25.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(){
        return "user/edit";
    }
}
