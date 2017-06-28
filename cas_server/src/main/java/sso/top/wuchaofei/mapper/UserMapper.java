package sso.top.wuchaofei.mapper;

import sso.top.wuchaofei.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    User findUserByName(String username);

    List<User> listByPager(Map map);
}