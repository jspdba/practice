<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
                <small>渠道列表</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-tools">
                        </div>
                        <div class="box-header">
                            <h3 class="box-title">分页信息</h3>
                        </div>
                        <div class="box-body">
                            <table class="gridtable" style="width:100%;text-align: center;">
                                <tr>
                                    <c:if test="${pageInfo.hasPreviousPage}">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/channel/list?pageNo=${pageInfo.prePage}&pageSize=${pageInfo.pageSize}">前一页</a>
                                        </td>
                                    </c:if>
                                    <c:forEach items="${pageInfo.navigatepageNums}" var="nav">
                                        <c:if test="${nav == pageInfo.pageNum}">
                                            <td style="font-weight: bold;">${nav}</td>
                                        </c:if>
                                        <c:if test="${nav != pageInfo.pageNum}">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/channel/list?pageNo=${nav}&pageSize=${pageInfo.pageSize}">${nav}</a>
                                            </td>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${pageInfo.hasNextPage}">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/channel/list?pageNo=${pageInfo.nextPage}&pageSize=${pageInfo.pageSize}">下一页</a>
                                        </td>
                                    </c:if>
                                </tr>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body">
                            <table id="dataTable" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>渠道号</th>
                                    <th>名称</th>
                                    <th>描述</th>
                                    <th>时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageInfo.list}" var="item">
                                    <tr>
                                        <td><a href="/channel/edit.action?id=${item.id}"><c:out value="${item.id}"></c:out></a></td>
                                        <td><c:out value="${item.channelId}"></c:out></td>
                                        <td><c:out value="${item.name}"></c:out></td>
                                        <td><c:out value="${item.description}"></c:out></td>
                                        <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <div class="btn-group">
                                                <a type="button" href="/channel/edit.action?id=${item.id}" class="btn btn-default" >编辑</a>
                                                <a type="button" class="btn btn-default delete" href="/channel/delete.action?id=${item.id}">删除</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
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
    //导入excel channel数据
    $(".import").bind("click",function () {
        if($("#file").val()){
            $("#uploadForm")[0].submit();
        }
    });

    $(function () {
        $('#dataTable').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": true,
            "ordering": true,
            "info": false,
            "autoWidth": false,
            "columnDefs": [
                {
                    "targets": [5],
                    "orderable": false,
                    "searchable": false
                }
            ]
        });
    });

    $('input[id=file]').change(function() {
        $('#upd').val($(this).val());
    });
</script>
</body>
</html>
