<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><s:message code="common.pageTitle" /></title>
<link href="css/sb-admin/bootstrap.min.css" rel="stylesheet">
<link href="css/sb-admin/metisMenu.min.css" rel="stylesheet">
<link href="css/sb-admin/sb-admin-2.css" rel="stylesheet">
<link href="css/sb-admin/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="js/dynatree/ui.dynatree.css" rel="stylesheet" id="skinSheet" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/dynatree/jquery.dynatree.js"></script>
<script src="css/sb-admin/bootstrap.min.js"></script>
<script src="css/sb-admin/metisMenu.min.js"></script>
<script src="css/sb-admin/sb-admin-2.js"></script>
<script src="js/project9.js"></script>

<script src="js/moment.min.js"></script>
<script>
	function fn_formSubmit() {
		document.form1.submit();
	}

</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
</head>

<body>

			<div class="panel-body">
				<c:forEach var="listview" items="${listview}" varStatus="status">
				<!-- 제목 --> <c:out value="${listview.schtitle}"/><br>
				<!-- 일시 --> <fmt:formatDate value="${listview.schstdt}" pattern="yyyy년 MM월 dd일" /><br>
				<!-- 내용 --> <c:out value="${listview.schcont}"/><br>
				<!-- 장소 --> <c:out value="${listview.schplace}"/><br>
				<!-- 참석자 --> <c:out value="${listview.schmember}"/><br>
				<!-- 공개여부--><c:out value="${listview.schopen}"/> <br>
				</c:forEach>
				</div>
			<!-- /.row -->



</body>

</html>
