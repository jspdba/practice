package top.wuchaofei.domain;


import java.util.ArrayList;
import java.util.List;

public class Role {
    private Integer id;
    private String rolename;
    private String description;
    private List<Permission> permissionList;//一个角色对应多个权限
    private List<User> userList;//一个角色对应多个用户

    private int count=0;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<String> getPermissionsName(){
        List<String> list=new ArrayList<String>();
        List<Permission> perlist=getPermissionList();

        if(perlist==null){
            return list;
        }

        for (Permission per : perlist) {
            list.add(per.getPermissionname());
        }
        return list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", description='" + description + '\'' +
                ", permissionList=" + permissionList +
                ", userList=" + userList +
                ", count=" + count +
                '}';
    }
}