package app.top.wuchaofei.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cofco on 2017/6/26.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Value("${sso.logout.url}")
    String logout;

    @RequestMapping("")
    public String index(){
        return "index";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");

        StringBuilder sb=new StringBuilder(logout);
        sb.append("?redirectUrl=");
        sb.append(request.getScheme());
        sb.append("://");
        sb.append(request.getServerName());
        sb.append(":");
        sb.append(request.getServerPort());
        sb.append("/");
        System.out.println(sb.toString());
        return "redirect:"+sb.toString();
    }
}
