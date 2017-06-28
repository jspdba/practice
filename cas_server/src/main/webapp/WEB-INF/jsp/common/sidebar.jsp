<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu">
            <li class="header">操作</li>
            <shiro:hasAnyRoles name="admin,tinker:admin">
                <li class="${0==active_ul?'active':''} treeview">
                    <a href="#"><i class="fa fa-th"></i> <span>渠道管理</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                    <ul class="treeview-menu">
                        <li ${(0==active_ul && 2==active_li)?'class="active"':''}><a href="/channel/list.action"><i class="fa fa-circle-o text-aqua"></i> <span>渠道列表</span></a></li>
                    </ul>
                </li>
            </shiro:hasAnyRoles>
                <li class="${2==active_ul?'active':''} treeview">
                    <a href="#"><i class="fa fa-th"></i> <span>用户中心</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                    <ul class="treeview-menu">
                        <shiro:hasAnyRoles name="admin">
                        <li ${(2==active_ul && 0==active_li)?'class="active"':''}><a href="/user/edit.action"><i class="fa fa-circle-o text-aqua"></i> <span>添加</span></a></li>
                        <li ${(2==active_ul && 1==active_li)?'class="active"':''}><a href="/user/index.action"><i class="fa fa-circle-o text-aqua"></i> <span>用户列表</span></a></li>
                        </shiro:hasAnyRoles>
                        <li ${(2==active_ul && 2==active_li)?'class="active"':''}><a href="/user/my.action"><i class="fa fa-circle-o text-aqua"></i> <span>个人资料</span></a></li>
                    </ul>
                </li>
            <shiro:hasAnyRoles name="admin,auth:admin">
                <li class="${3==active_ul?'active':''} treeview">
                    <a href="#"><i class="fa fa-th"></i> <span>权限管理</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                    <ul class="treeview-menu">
                        <li ${(3==active_ul && 0==active_li)?'class="active"':''}><a href="/auth/role/index.action"><i class="fa fa-circle-o text-aqua"></i> <span>角色列表</span></a></li>
                        <li ${(3==active_ul && 1==active_li)?'class="active"':''}><a href="/auth/role/allocate.action"><i class="fa fa-circle-o text-aqua"></i> <span>角色分配</span></a></li>
                        <li ${(3==active_ul && 2==active_li)?'class="active"':''}><a href="/auth/permission/index.action"><i class="fa fa-circle-o text-aqua"></i> <span>权限列表</span></a></li>
                        <li ${(3==active_ul && 3==active_li)?'class="active"':''}><a href="/auth/permission/allocate.action"><i class="fa fa-circle-o text-aqua"></i> <span>权限分配</span></a></li>
                    </ul>
                </li>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="admin">
                <li class="${4==active_ul?'active':''} treeview">
                    <a href="#"><i class="fa fa-th"></i> <span>微信管理</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                    <ul class="treeview-menu">
                        <li ${(4==active_ul && 0==active_li)?'class="active"':''}><a href="/wechat/login.action"><i class="fa fa-circle-o text-aqua"></i> <span>个人微信登录</span></a></li>
                    </ul>
                </li>
            </shiro:hasAnyRoles>
        </ul>
    </section>
</aside>
