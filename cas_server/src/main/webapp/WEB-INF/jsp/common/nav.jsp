<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<header class="main-header">
    <a href="/user/my.action" class="logo">
        <span class="logo-mini">tinker</span>
        <span class="logo-lg"><b>管理平台</b></span>
    </a>
    <nav class="navbar navbar-static-top">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <shiro:user>
                    <li>
                        <a><shiro:principal/></a>
                    </li>
                    <li><a>|</a></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/user/logout.action"><span>退出</span></a>
                    </li>
                </shiro:user>
            </ul>
        </div>
    </nav>
</header>