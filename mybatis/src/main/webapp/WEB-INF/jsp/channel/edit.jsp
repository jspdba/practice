<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("active_ul",0);
    request.setAttribute("active_li",2);
%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/head.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../common/nav.jsp"/>
    <jsp:include page="../common/sidebar.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                管理平台
                <small>渠道号</small>
            </h1>
            <%--<ol class="breadcrumb">
                <li><a href="/user/my.action"><i class="fa fa-dashboard"></i>管理平台</a></li>
                <li class="active">编辑</li>
            </ol>--%>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <!-- left column -->
                <div class="col-md-12">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">添加</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form id="saveForm" role="form" action="/channel/save.action" method="post">
                            <input id="id" name="id" type="hidden" value="${channel.id}">
                            <div class="box-body">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="channelId" name="channelId" placeholder="渠道号" value="${channel.channelId}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="名称" value="${channel.name}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="description" name="description" placeholder="描述" value="${channel.description}">
                                </div>

                            </div>
                            <!-- /.box-body -->

                            <div class="box-footer">
                                <button type="button" class="btn btn-primary submit">提交</button>
                            </div>
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->

    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<jsp:include page="../common/script.jsp"/>
<script>
    $(".submit").bind("click",function () {
        $("#saveForm")[0].submit();
    });
    $(function () {
        //Initialize Select2 Elements
        $(".select2").select2();
    });
</script>
</body>
</html>

