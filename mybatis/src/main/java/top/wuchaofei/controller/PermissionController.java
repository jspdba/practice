package top.wuchaofei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wuchaofei.domain.DataTableParameter;
import top.wuchaofei.domain.Permission;
import top.wuchaofei.domain.PermissionVo;
import top.wuchaofei.manager.PermissionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cofco on 2017/6/7.
 */
@Controller
@RequestMapping("/auth/permission")
public class PermissionController extends BaseController {
    @Autowired
    PermissionManager permissionManager;

    @RequestMapping("index")
    public String index(){
        return "auth/permission/index";
    }

    @RequestMapping("pager")
    @ResponseBody
    public Object pager(@RequestBody List<Object> param){
        logger.info("params={}",param);
        DataTableParameter data =new DataTableParameter();
        Map<String,Object> map =convertToMap(param);
        List<Permission> list = permissionManager.listByDataTable(map);
        long totalCount =permissionManager.getTotalByDataTable(map);
        data.setAaData(list);
        data.setiTotalDisplayRecords(totalCount);
        data.setiTotalRecords(totalCount);
        data.setsEcho(map.get("sEcho")+"");
        return data;
    }

    //添加或修改权限
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(Permission permission){
        if(permission.getId()==null || permission.getId()==0){
            permissionManager.insertSelective(permission);
        }else{
            permissionManager.updateByPrimaryKeySelective(permission);
        }
        return "redirect:/auth/permission/index";
    }
    //删除权限
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    @ResponseBody
    public Map delete(Permission permission){
        if(permission.getId()!=null && permission.getId()>0){
           permissionManager.deleteCascade(permission.getId());
        }
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("msg", "删除成功");
        return resultMap;
    }
    //添加或修改角色
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(Permission permission, ModelMap modelMap){
        if (permission.getId()!=null){
            permission = permissionManager.selectByPrimaryKey(permission.getId());
            modelMap.addAttribute("permission",permission);
        }
        return "auth/permission/edit";
    }
    //添加或修改角色
    @RequestMapping(value = "allocate",method = RequestMethod.GET)
    public String allocate(){
        return "auth/permission/allocate";
    }


    /**
     * 根据用户ID查询权限
     * @param id
     * @return
     */
    @RequestMapping(value="selectPermissionByRoleId")
    @ResponseBody
    public List<PermissionVo> selectPermissionByRoleId(Integer id){
        List<PermissionVo> bos = permissionManager.selectPermissionByRoleId(id);
        return bos;
    }

    /**
     * 操作用户的角色
     * @param roleId 	用户ID
     * @param ids		角色ID，以‘,’间隔
     * @return
     */
    @RequestMapping(value="addPermission2Role")
    @ResponseBody
    public Map<String,Object> addPermission2Role(Integer roleId,String ids){
        return permissionManager.addPermission2Role(roleId,ids);
    }
    private static Map<String,Object>convertToMap(List<Object>list){
        Map<String,Object>map =new HashMap<String, Object>();
        for(Object ob:list){
            Map<String,Object> param =(Map<String, Object>) ob;
            map.put(param.get("name")+"",param.get("value"));
        }
        return map;
    }
}
