package top.wuchaofei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wuchaofei.domain.DataTableParameter;
import top.wuchaofei.domain.Role;
import top.wuchaofei.domain.RoleVo;
import top.wuchaofei.manager.RoleManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cofco on 2017/6/7.
 */
@Controller
@RequestMapping("/auth/role")
public class RoleController extends BaseController {
    @Autowired
    RoleManager roleManager;

    @RequestMapping("index")
    public String index(){
        return "auth/role/index";
    }

    @RequestMapping("pager")
    @ResponseBody
    public Object pager(@RequestBody List<Object> param){
        logger.info("params={}",param);
        DataTableParameter data =new DataTableParameter();
        Map<String,Object> map =convertToMap(param);
        List<Role> list = roleManager.listByDataTable(map);
        long totalCount =roleManager.getTotalByDataTable(map);
        data.setAaData(list);
        data.setiTotalDisplayRecords(totalCount);
        data.setiTotalRecords(totalCount);
        data.setsEcho(map.get("sEcho")+"");
        return data;
    }

    //添加或修改角色
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(Role role){
        if(role.getId()==null || role.getId()==0){
            roleManager.insertSelective(role);
        }else{
            roleManager.updateByPrimaryKeySelective(role);
        }
        return "redirect:/auth/role/index";
    }
    //添加或修改角色
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(Role role, ModelMap modelMap){
        if (role.getId()!=null){
            modelMap.addAttribute("role",roleManager.selectByPrimaryKey(role.getId()));
        }
        return "auth/role/edit";
    }
    //添加或修改角色
    @RequestMapping(value = "allocate",method = RequestMethod.GET)
    public String allocate(){
        return "auth/role/allocate";
    }



    /**
     * 根据用户ID查询权限
     * @param id
     * @return
     */
    @RequestMapping(value="selectRoleByUserId")
    @ResponseBody
    public List<RoleVo> selectRoleByUserId(Integer id){
        List<RoleVo> bos = roleManager.selectRoleByUserId(id);
        return bos;
    }

    /**
     * 操作用户的角色
     * @param userId 	用户ID
     * @param ids		角色ID，以‘,’间隔
     * @return
     */
    @RequestMapping(value="addRole2User")
    @ResponseBody
    public Map<String,Object> addRole2User(Integer userId,String ids){
        return roleManager.addRole2User(userId,ids);
    }

    private static Map<String,Object>convertToMap(List<Object>list){
        Map<String,Object>map =new HashMap<String, Object>();
        for(Object ob:list){
            Map<String,Object> param =(Map<String, Object>) ob;
            map.put(param.get("name")+"",param.get("value"));
        }
        return map;
    }

    //删除权限
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    @ResponseBody
    public Map delete(Role role){
        if(role.getId()!=null || role.getId()>0){
            roleManager.deleteCasCade(role.getId());
        }
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("msg", "删除成功");
        return resultMap;
    }
}
