package app.top.wuchaofei.interceptor;

import app.top.wuchaofei.utils.HttpClientUtil;
import app.top.wuchaofei.utils.UrlUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * serviceTicket拦截器，
 * 如果请求参数中没有serviceTicket代表没有通过身份认证
 * Created by cofco on 2017/6/26.
 */
@Component("serviceTicketInteceptor")
public class ServiceTicketInteceptor extends HandlerInterceptorAdapter{
    @Value("${sso.auth.url}")
    String authUrl;
    @Value("${sso.userInfo.url}")
    String userInfoUrl;

    Logger logger= LoggerFactory.getLogger(ServiceTicketInteceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //session若已登录，则不重复验证
        String username = (String) request.getSession().getAttribute("username");
        if(StringUtils.isNotEmpty(username)){
            return super.preHandle(request, response, handler);
        }

        //如果请求参数中包含serviceTicket，更新请求中的serviceTicket
        String serviceTicket = request.getParameter("serviceTicket");
        String redirectUrl = request.getRequestURL().toString();

        if(StringUtils.isNotBlank(serviceTicket)){
            //在获取到serviceTicket后从sso服务器获取获取资源
            Map<String, String> map = new HashMap<String, String>();
            map.put("serviceTicket",serviceTicket);
            String userInfo = HttpClientUtil.post(userInfoUrl,map);

            //验证 serviceTicket
            if(StringUtils.isNotEmpty(userInfo)){
                request.getSession().setAttribute("username",userInfo);
                //去掉serviceToken
                response.sendRedirect(UrlUtil.removeParameter(redirectUrl,"redirectUrl"));
                return super.preHandle(request, response, handler);
            }
        }

        if(authUrl.indexOf("?")>0){
            authUrl = authUrl +"&redirectUrl="+redirectUrl;
        }else{
            authUrl = authUrl +"?redirectUrl="+redirectUrl;
        }
        response.sendRedirect(authUrl);
        return super.preHandle(request, response, handler);
    }
}
