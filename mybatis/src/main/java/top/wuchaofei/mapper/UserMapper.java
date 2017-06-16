package top.wuchaofei.mapper;


import top.wuchaofei.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    User selectByEntry(User record);

    User findUserByName(String username);

    List<User> listByPager(Map map);

    long getTotalByPager(Map map);
}