package top.wuchaofei.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wuchaofei.domain.Permission;
import top.wuchaofei.domain.PermissionVo;
import top.wuchaofei.domain.RolePermission;
import top.wuchaofei.manager.PermissionManager;
import top.wuchaofei.mapper.PermissionMapper;
import top.wuchaofei.mapper.RolePermissionMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cofco on 2017/6/7.
 */
@Service
public class PermissionManagerImpl implements PermissionManager {
    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    @Override
    public Permission selectByPrimaryKey(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Permission> listByDataTable(Map<String, Object> map) {
        return permissionMapper.listByPager(buildPagerMap(map));
    }

    @Override
    public long getTotalByDataTable(Map<String, Object> map) {
        return permissionMapper.getTotalByPager(buildPagerMap(map));
    }

    @Override
    public Map<String, Object> addPermission2Role(Integer roleId, String ids) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int count = 0;
        try {
            rolePermissionMapper.deleteByRoleId(roleId);
            if(StringUtils.isNotBlank(ids)){
                String[] idArray = null;

                if(StringUtils.contains(ids, ",")){
                    idArray = ids.split(",");
                }else{
                    idArray = new String[]{ids};
                }
                //添加新的。
                for (String rid : idArray) {
                    if(StringUtils.isNotBlank(rid)){
                        RolePermission entity = new RolePermission(roleId,new Integer(rid));
                        count += rolePermissionMapper.insertSelective(entity);
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
    public void deleteCascade(Integer id) {
        rolePermissionMapper.deleteByPermissionId(id);
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(Permission permission) {
        return permissionMapper.saveOrUpdate(permission);
    }

    @Override
    public List<PermissionVo> selectPermissionByRoleId(Integer id) {
        return permissionMapper.selectPermissionByRoleId(id);
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
