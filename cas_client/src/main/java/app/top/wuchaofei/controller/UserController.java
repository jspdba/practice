package app.top.wuchaofei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cofco on 2017/6/28.
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @RequestMapping(value = "my",method = RequestMethod.GET)
    public String my(){
        return "/user/my";
    }
}
