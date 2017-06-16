<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    request.setAttribute("active_ul",2);
    request.setAttribute("active_li",0);
%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/head.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../common/nav.jsp"/>
    <jsp:include page="../common/sidebar.jsp"/>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                管理平台
                <small>用户管理</small>
            </h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">${u!=null?"编辑":"添加"}用户</h3>
                        </div>
                        <form id="editForm" role="form" action="/user/save.action" method="post">
                            <input name="id" type="hidden" value="${u.id}">
                            <div class="box-body">
                                <c:if test="msg">
                                    <div class="form-group">
                                        <label class="form-control label danger">${msg}</label>
                                    </div>
                                </c:if>
                                <c:choose>
                                    <c:when test="${empty u}">
                                        <div class="form-group">
                                            <input type="text" autocomplete="off" class="form-control" id="username" name="username" placeholder="用户名" value="${u.username}">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" autocomplete="off" class="form-control" id="password" name="password" placeholder="密码" value="${u.password}">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group">
                                            <label class="form-control" name="username">${u.username}</label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机号" value="${u.mobile}">
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-primary submit">提交</button>
                            </div>
                        </form>
                    </div>
                </div>
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
<jsp:include page="../common/script.jsp"/>
<script>
    $(".submit").bind("click",function () {

        <c:if test="u==null">
        var username=$("#username").val();
        if(!username){
            layer.msg("username 不能为空");
            return;
        }
        var password=$("#password").val();
        if(!password){
            layer.msg("password 不能为空");
            return;
        }
        </c:if>
        $("#editForm")[0].submit();
    });
</script>
</body>
</html>