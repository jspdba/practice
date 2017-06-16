package top.wuchaofei.mapper;

import tk.mybatis.mapper.common.Mapper;
import top.wuchaofei.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    User findUserByName(String username);

    List<User> listByPager(Map map);
}