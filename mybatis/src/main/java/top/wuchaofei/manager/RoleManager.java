package top.wuchaofei.manager;

import org.springframework.stereotype.Service;
import top.wuchaofei.domain.PermissionVo;
import top.wuchaofei.domain.Role;
import top.wuchaofei.domain.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * Created by cofco on 2017/6/7.
 */
@Service
public interface RoleManager {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> listByDataTable(Map<String, Object> map);

    long getTotalByDataTable(Map<String, Object> map);

    List<PermissionVo> selectPermissionByRoleId(Integer id);


    List<RoleVo> selectRoleByUserId(Integer id);

    Map<String,Object> addRole2User(Integer userId, String ids);

    void deleteCasCade(Integer id);

    int saveOrUpdate(Role role);
}
