package top.wuchaofei.mapper;


import top.wuchaofei.domain.Permission;
import top.wuchaofei.domain.PermissionVo;

import java.util.List;
import java.util.Map;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> listByPager(Map map);

    long getTotalByPager(Map map);

    List<PermissionVo> selectPermissionByRoleId(Integer id);

    int saveOrUpdate(Permission permission);
}