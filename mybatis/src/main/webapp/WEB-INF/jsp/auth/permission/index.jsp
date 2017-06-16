<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    request.setAttribute("active_ul",3);
    request.setAttribute("active_li",2);
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
                    <h3 class="box-title">添加权限</h3>
                </div>
                <form role="form" action="/auth/permission/save.action" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <input id="permissionname" name="permissionname" type="text" class="form-control" placeholder="权限名称">
                        </div>
                        <div class="form-group">
                            <textarea name="description"  class="form-control" rows="3" placeholder="权限描述"></textarea>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="submit" class="btn btn-info">提交</button>
                    </div>
                </form>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">权限列表</h3>
                        </div>
                        <div class="box-body">
                            <table id="dataTable" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>名称</th>
                                    <th>描述</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
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

    <!-- Modal -->
    <div class="modal modal-primary fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">删除</h4>
                </div>
                <div class="modal-body">
                    确定删除这个补丁吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">取消</button>
                    <button id="del_sure" type="button" class="btn btn-outline">删除</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../common/script.jsp"/>

<script>
    $(function () {
        function retrieveData(sSource, aoData, fnCallback) {
            $.ajax({
                "type" : "POST",
                "contentType" : "application/json",
                "url" : sSource,
                "dataType" : "json",
                "data" : JSON.stringify(aoData),
                "success" : fnCallback
            });
        }

        var oTable = $('#dataTable').dataTable(
            {
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "sDom": "<'row-fluid inboxHeader'<'span6'<'dt_actions'>l><'span6'f>r>t<'row-fluid inboxFooter'<'span6'i><'span6'p>>",
                "iDisplayLength": 10,//每页显示10条数据
                "bAutoWidth": false,//宽度是否自动，感觉不好使的时候关掉试试

                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,

                "bLengthChange": false,
                "bFilter": false,
                "oLanguage": {
                    "sSearch": "搜索",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
                    "sInfoFiltered": "(筛选自 _MAX_ 条数据)",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "末页"
                    }
                },
                "bProcessing": true,
                "bServerSide": true,
                "sAjaxSource":  "/auth/permission/pager?rand=" + Math.random(),
                "fnServerData" : retrieveData,
                "aoColumns": [
                    {"mData": 'id'},
                    {"mData": 'permissionname'},
                    {"mData": 'description'},
                    {"sDefaultContent": ''}
                ],
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [1,2,3]},
                    {"bSearchable": false, "aTargets": [1,2,3]},
                ],
                "aaSorting": [[0, "asc"]],
                "fnRowCallback": function(nRow, aData, iDisplayIndex) {
                    $('td:eq(0)', nRow).html("<div style='width: 10px'><a href='/auth/permission/edit.action?id="+aData.id+"'>"+(iDisplayIndex+1)+"</a></div>");
                    $('td:eq(1)', nRow).html("<div style='width: 70px'>"+aData.permissionname+"</div>");
                    $('td:eq(2)', nRow).html("<div style='width: 150px'>"+(aData.description?aData.description:"")+"</div>");
                    var btn='<button type="button" class="btn btn-default del" for="'+aData.id+'">删除</button>';
                    $('td:eq(3)', nRow).html("<div style='width: 70px'>"+btn+"</div>");
                    return nRow;
                },
                "fnInitComplete": function(oSettings, json) {
                }
            }
        );
    });

    //删除
    $(document).on("click",".del",function () {
        var id=$(this).attr("for");
        var url="/auth/permission/delete.action";

        var index = layer.confirm("确定操作？",function(){
            <%--loding--%>
//            var load = layer.load();
            $.getJSON(url,{id:id},function (result) {
                if(result && result.code != 0){
                    return layer.msg(result.msg,function () {
                    }),!1;
                }
//                $('#selectPermission').modal('hide');
                layer.msg('删除成功');
                window.location.reload();
            })
        });
    });
</script>
</body>
</html>
