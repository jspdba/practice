package top.wuchaofei.manager;

import org.springframework.stereotype.Service;
import top.wuchaofei.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */
@Service
public interface UserManager {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    User login(HttpServletRequest request, HttpServletResponse response, String username, String passwd);
    boolean logout(HttpServletRequest request, HttpServletResponse response);
    User selectByEntry(User record);

    User findUserByName(String loginName);

    List<User> listByDataTable(Map<String, Object> map);

    long getTotalByDataTable(Map<String, Object> map);
}
