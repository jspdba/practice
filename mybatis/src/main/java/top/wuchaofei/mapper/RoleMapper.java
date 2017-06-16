package top.wuchaofei.mapper;


import top.wuchaofei.domain.PermissionVo;
import top.wuchaofei.domain.Role;
import top.wuchaofei.domain.RoleVo;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> listByPager(Map map);

    long getTotalByPager(Map map);

    List<PermissionVo> selectPermissionByRoleId(Integer id);

    List<RoleVo> selectRoleByUserId(Integer id);

    int saveOrUpdate(Role role);
}