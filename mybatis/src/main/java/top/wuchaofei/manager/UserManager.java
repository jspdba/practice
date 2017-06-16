package top.wuchaofei.manager;

import org.springframework.stereotype.Service;
import top.wuchaofei.domain.User;
import top.wuchaofei.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */
@Service
public interface UserManager extends BaseService<User>{

    User login(HttpServletRequest request, HttpServletResponse response, String username, String passwd);

    boolean logout(HttpServletRequest request, HttpServletResponse response);

    User selectByEntry(User record);

    User findUserByName(String loginName);

    List<User> listByDataTable(Map<String, Object> map);
}
