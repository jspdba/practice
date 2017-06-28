<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    request.setAttribute("active_ul",1);
    request.setAttribute("active_li",0);
%>
<!DOCTYPE html>
<html>
<jsp:include page="common/head.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="common/nav.jsp"/>
    <jsp:include page="common/sidebar.jsp"/>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                管理平台
                <small>error</small>
            </h1>
        </section>
        <section class="content">
            <div class="callout callout-danger">
                <h4>Warning!</h4>
                <p>${error}</p>
                <p><a class="btn btn-warning btn-sm" href="javascript:window.history.back()">返回</a></p>
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
<jsp:include page="common/script.jsp"/>
</body>
</html>