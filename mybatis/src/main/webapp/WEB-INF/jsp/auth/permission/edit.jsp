<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    request.setAttribute("active_ul",3);
    request.setAttribute("active_li",0);
%>
<!DOCTYPE html>
<html>
<jsp:include page="../../common/head.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../../common/nav.jsp"/>
    <jsp:include page="../../common/sidebar.jsp"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                管理平台
                <small>权限管理</small>
            </h1>
        </section>
        <section class="content">
            <div class="box box-warning">
                <div class="box-header with-border">
                    <h3 class="box-title">添加角色</h3>
                </div>
                <form role="form" action="/auth/permission/save.action" method="post">
                    <input type="hidden" id="id" name="id" value="${permission.id}">
                    <div class="box-body">
                        <div class="form-group">
                            <input id="permissionname" name="permissionname" type="text" class="form-control" placeholder="角色名称" value="${permission.permissionname}">
                        </div>
                        <div class="form-group">
                            <textarea id="description" name="description" class="form-control" rows="3" placeholder="角色描述">${permission.description}</textarea>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="submit" class="btn btn-info">提交</button>
                    </div>
                </form>
            </div>
        </section>
    </div>
    <aside class="control-sidebar control-sidebar-dark">
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
    </aside>
    <div class="control-sidebar-bg"></div>
</div>
<jsp:include page="../../common/script.jsp"/>
</body>
</html>
