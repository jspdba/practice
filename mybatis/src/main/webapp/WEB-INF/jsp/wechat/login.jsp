<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("active_ul",4);
    request.setAttribute("active_li",0);
    double number=Math.random();
%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/head.jsp"/>
<style>
    .status > .on{
        display: block;
    }
    .status > .off{
        display: none;
    }
</style>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../common/nav.jsp"/>
    <jsp:include page="../common/sidebar.jsp"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                管理平台
                <small>微信登录</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <!-- left column -->
                <div class="col-md-12">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <div class="status">
                                <c:choose>
                                    <c:when test="${0==status}">
                                        <div class="logout on">
                                            <h3 class="box-title">退出登录微信号</h3>
                                            <button onclick="wechatLogout()">退出</button>
                                        </div>
                                        <div class="login off">
                                            <h3 class="box-title">扫码登录微信号</h3>

                                            <img onclick="doRefresh()" src="/static/wechat/QR.jpg?r=<%=number%>" alt="wechat">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="logout off">
                                            <h3 class="box-title">退出登录微信号</h3>
                                            <button onclick="wechatLogout()">退出</button>
                                        </div>

                                        <div class="login on">
                                            <h3 class="box-title">扫码登录微信号</h3>
                                            <img onclick="doRefresh()" src="/static/wechat/QR.jpg?r=<%=number%>" alt="wechat">
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
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
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<jsp:include page="../common/script.jsp"/>
<script>
    function doRefresh() {
        var url="/static/wechat/QR.jpg?r="+Math.random();
        $(this).attr("src",url);
    }

    function wechatLogout() {
        var url="/wechat/logout.action";
        $.getJSON(url,function (result) {
            if(result && result.code==0){
                alert("退出成功");
                window.location.reload();
            }else{
                alert(result.msg);
            }
        })
    }

    /*var ihander=null;
    (function () {
        var url="/wechat/status.action";
        var status=${status};
        var count=0;
        //如果是未登录状态，一秒检测一次，登录成功刷新页面
        if(status!=0){
            ihander = setInterval(function () {
                $.getJSON(url,function (result) {
                    //登录成功
                    if(result && result.code==0 && result.status==0){
                        if(ihander){
                            clearInterval(ihander);
                            ihander=null;
                        }
                        window.location.reload();
                    }
                    count++;
                    if(count>10){
                        if(ihander){
                            clearInterval(ihander);
                            ihander=null;
                        }
                    }
                });
            },1000);
        }
    })();*/
</script>
</body>
</html>

