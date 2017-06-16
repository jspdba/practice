package top.wuchaofei.domain;

public class RolePermission {
    private Integer id;

    private Integer role;

    private Integer permission;

    public RolePermission(Integer role, Integer permission) {
        this.role=role;
        this.permission=permission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}