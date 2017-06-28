package sso.top.wuchaofei.service;

import org.springframework.stereotype.Service;
import sso.top.wuchaofei.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by cofco on 2017/6/26.
 */
@Service
public interface UserService extends BaseService<User>{
    User login(HttpServletRequest request, HttpServletResponse response, String username, String passwd);

    boolean logout(HttpServletRequest request, HttpServletResponse response);

    User selectByEntry(User record);

    User findUserByName(String loginName);

    List<User> listByDataTable(Map<String, Object> map);

    String genAndStoreServiceTicket(String tgc);

    String addParameter(String redirectUrl, String key, String value);

    String grantTicketGrantedCookie(HttpServletResponse response, User user1);

    boolean isExpireOfServiceTicket(String serviceTicket);
    boolean isExpireOfTicketGrantCookie(String tgc);

    String getUserInfoByServiceTicket(String serviceTicket);

    String getTicketGrantCookieFromRequest(HttpServletRequest request);

    Object getUserInfoByTicketGrantCookie(String tgc);

    void deleteServiceTicket(String serviceTicket);
}
