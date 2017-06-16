package top.wuchaofei.manager;

import org.springframework.stereotype.Service;
import top.wuchaofei.domain.Permission;
import top.wuchaofei.domain.PermissionVo;

import java.util.List;
import java.util.Map;

/**
 * Created by cofco on 2017/6/7.
 */
@Service
public interface PermissionManager {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> listByDataTable(Map<String, Object> map);

    long getTotalByDataTable(Map<String, Object> map);

    List<PermissionVo> selectPermissionByRoleId(Integer id);

    Map<String,Object> addPermission2Role(Integer roleId, String ids);

    void deleteCascade(Integer id);

    int saveOrUpdate(Permission permission);
}
