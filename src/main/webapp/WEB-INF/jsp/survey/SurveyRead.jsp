<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><s:message code="common.pageTitle"/></title>
    <link href="css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="css/sb-admin/metisMenu.min.css" rel="stylesheet">
    <link href="css/sb-admin/sb-admin-2.css" rel="stylesheet">
    <link href="css/sb-admin/font-awesome.min.css" rel="stylesheet" type="text/css">

    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
	<script src="js/project9.js"></script>    
<script>
function hideDiv(id){
	$(id).hide();
	$(id).appendTo(document.body);
}
</script>    
</head>
<body>
    <div id="wrapper">
		<jsp:include page="../common/navigation.jsp" />
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> <c:out value="${bgInfo.bgname}"/></h1>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
				<div class="panel panel-default">
                    <div class="panel-heading">
                        	<c:out value="${result.surtitle}"/> [ <c:out value="${result.usernm}"/> <c:out value="${result.surdate}"/>]
                    </div>
                    <div class="panel-body">
                        <p><c:out value="${result.surcontents}" escapeXml="false"/></p>
                    </div>
                </div>
                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('surveyList?bgno=<c:out value="${bgno}"/>')" ><s:message code="common.btnList"/></button>
				<c:if test='${result.userno==sessionScope.userno}' >
	                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('surveyDelete?bgno=<c:out value="${result.bgno}"/>&surno=<c:out value="${result.surno}"/>', '<s:message code="common.btnDelete"/>')" ><s:message code="common.btnDelete"/></button>
	                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('surveyForm?surno=<c:out value="${result.surno}"/>')" ><s:message code="common.btnUpdate"/></button>
				</c:if>
				<p>&nbsp;</p>
				<input type="hidden" id="surno" name="surno" value="<c:out value="${result.surno}"/>"> 								
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
</body>
</html>