package top.wuchaofei.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wuchaofei.domain.PermissionVo;
import top.wuchaofei.domain.Role;
import top.wuchaofei.domain.RoleVo;
import top.wuchaofei.domain.UserRole;
import top.wuchaofei.manager.RoleManager;
import top.wuchaofei.mapper.RoleMapper;
import top.wuchaofei.mapper.RolePermissionMapper;
import top.wuchaofei.mapper.UserRoleMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cofco on 2017/6/7.
 */
@Service
public class RoleManagerImpl implements RoleManager {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Role> listByDataTable(Map<String, Object> map) {
        return roleMapper.listByPager(buildPagerMap(map));
    }

    @Override
    public long getTotalByDataTable(Map<String, Object> map) {
        return roleMapper.getTotalByPager(buildPagerMap(map));
    }

    @Override
    public List<PermissionVo> selectPermissionByRoleId(Integer id) {
        return roleMapper.selectPermissionByRoleId(id);
    }


    @Override
    public List<RoleVo> selectRoleByUserId(Integer id) {
        return roleMapper.selectRoleByUserId(id);
    }

    @Override
    public Map<String, Object> addRole2User(Integer userId, String ids) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int count = 0;
        try {
            userRoleMapper.deleteByUserId(userId);
            //如果ids,role 的id 有值，那么就添加。没值象征着：把这个用户（userId）所有角色取消。
            if(StringUtils.isNotBlank(ids)){
                String[] idArray = null;

                //这里有的人习惯，直接ids.split(",") 都可以，我习惯这么写。清楚明了。
                if(StringUtils.contains(ids, ",")){
                    idArray = ids.split(",");
                }else{
                    idArray = new String[]{ids};
                }
                //添加新的。
                for (String rid : idArray) {
                    //这里严谨点可以判断，也可以不判断。这个{@link StringUtils 我是重写了的}
                    if(StringUtils.isNotBlank(rid)){
                        UserRole entity = new UserRole(userId,new Integer(rid));
                        count += userRoleMapper.insertSelective(entity);
                    }
                }
            }
            resultMap.put("code", 0);
            resultMap.put("msg", "操作成功");
        } catch (Exception e) {
            resultMap.put("code", -1);
            resultMap.put("msg", "操作失败，请重试！");
        }

        resultMap.put("count", count);
        return resultMap;
    }

    @Override
    public void deleteCasCade(Integer id) {
        rolePermissionMapper.deleteByRoleId(id);
        userRoleMapper.deleteByRoleId(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(Role role) {
        return roleMapper.saveOrUpdate(role);
    }

    /**
     * 转换为可接受的分页map
     * @param map
     * @return
     */
    private static Map buildPagerMap(Map map){
        Map<String,Object> map1=new HashMap<String,Object>();
        if(map==null){
            map.put("start",0);
            map.put("limit",10);
            return map1;
        }
        map1.put("start",map.get("iDisplayStart"));
        map1.put("limit",map.get("iDisplayLength"));
        //注意字段用sql关键字的情况
        String orderByClause;
        Object iSortCol_0 = map.get("iSortCol_0");
        Object sSortDir_0 = map.get("sSortDir_0");

        String order_colume= iSortCol_0==null?null:toCamels((String)map.get("mDataProp_"+iSortCol_0));
        orderByClause = (order_colume+" "+(sSortDir_0==null?"asc":(String)sSortDir_0));
        map1.put("orderByClause",orderByClause);
        return map1;
    }

    private static String toCamels(String str){
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(str),"_");
    }

}
