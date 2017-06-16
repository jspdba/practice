<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
  request.setAttribute("active_ul",3);
  request.setAttribute("active_li",1);
%>
<!DOCTYPE html>
<html>
<jsp:include page="WEB-INF/common/head.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:include page="WEB-INF/common/nav.jsp"/>
  <jsp:include page="WEB-INF/common/sidebar.jsp"/>
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        管理平台
        <small>403</small>
      </h1>
    </section>
    <section class="content">
      <div class="error-page">
        <h2 class="headline text-yellow">403</h2>

        <div class="error-content">
          <h3><i class="fa fa-warning text-yellow"></i> 哇哦! 你没有权限访问</h3>

          <p>
           <a href="/user/my.action">返回个人中心</a> or 联系管理员添加权限.
          </p>
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
<jsp:include page="WEB-INF/common/script.jsp"/>
</body>
</html>
