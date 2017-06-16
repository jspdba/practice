package top.wuchaofei.domain;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 角色选择对话框
 * Created by cofco on 2017/6/7.
 */
public class PermissionVo extends Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * roleID (用String， 考虑多个ID，现在只有一个ID)
     */
    private String roleId;
    /**
     * 是否勾选
     */
    private String marker;

    public boolean isCheck(){
        return StringUtils.equals(roleId,marker);
    }
    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
