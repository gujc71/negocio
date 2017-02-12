<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

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

	function showBoardList(ev) {
		if ($('#boardlistDiv').is(':visible')) {
			$("#boardlistDiv").hide();
			return;
		}
		var pos = $("#boardlistBtn").position();
		$("#boardlistDiv").css({
			"top" : parseInt(pos.top) + 30 + "px",
			"left" : pos.left
		}).show();

		var node = $("#tree").dynatree("getRoot");

		if (node.childList)
			return;

		$.ajax({
			url : "boardListByAjax",
			type : "post",
			dataType : "json",
			success : function(result) {
				$("#tree").dynatree({
					children : result
				});
				$("#tree").dynatree("getTree").reload();
				$("#tree").dynatree("getRoot").visit(function(node) {
					node.expand(true);
				});
			}
		})

	}

	$(function() {
		$("#tree").dynatree({
			onActivate : TreenodeActivate
		});
	});

	function TreenodeActivate(node) {
		location.href = "boardList?bgno=" + node.data.key;
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
	<div id="wrapper">
		<jsp:include page="../common/navigation.jsp" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						<i class="fa fa-files-o fa-fw"></i> 스케줄
					</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- 스케쥴 버튼  -->
			<div class="row">
				<div class="col-lg-12">
					<button type="button" class="btn btn-default pull-left"
						onclick="location.href='scheduleDay'">
						<i class="fa fa-calendar-o"></i>
						<s:message code="sch.day" />
					</button>
					<button type="button" class="btn btn-default pull-left"
						onclick="location.href='scheduleMonth'">
						<i class="fa fa-calendar-o"></i>
						<s:message code="sch.month" />
					</button>
					<button type="button" class="btn btn-default pull-left"
						onclick="location.href='scheduleYear'">
						<i class="fa fa-calendar-o"></i>
						<s:message code="sch.year" />
					</button>
					<button type="button" class="btn btn-default pull-right"
						onclick="location.href='scheduleForm'">
						<i class="fa fa-plus"></i>
						<s:message code="common.add" />
					</button>

				</div>
			</div>

			<!-- 연도 선택 -->
			<div class="panel panel-default">
				<div class="day" style="text-align:center">
					<button type="button" class="btn btn-default"
						onclick="location.href=''">
						<i class="fa fa-caret-left"></i>
					</button>
					<font size ="6">&nbsp;2011&nbsp;</font>
					<button type="button" class="btn btn-default"
						onclick="location.href=''">
						<i class="fa fa-caret-right"></i>
					</button>
				</div>


				<div class="panel-body">
					<c:forEach var="listview" items="${listview}" varStatus="status">
						<!-- 스케쥴 번호 -->
						<c:url var="link" value="scheduleRead">
							<c:param name="schno" value="${listview.schno}" />
						</c:url>
						
						<!-- 스케쥴 시작일자 -->
						<div class="listBody">
							<div class="listHiddenField pull-left field200">
								<c:out value="${listview.schsttm}" /> ~ <c:out value="${listview.schedtm}" />
							</div>
							<div class="listDown pull-right field60">
								<i class="fa fa-chevron-down"></i>
							</div>
							<div class="listTitle" title="<c:out value="${listview.schtitle}"/>"><i class="fa fa-chevron-down"></i>
								<a href='javascript:void(0)' onclick="this.innerHTML=(this.nextSibling.style.display=='none')?'닫기':'${listview.schtitle}';this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'">
								<i class="fa fa-chevron-up"></i>
								</a><div style="display: none">
									내용 : <c:out value="${listview.schcont}" escapeXml="false" /> <br>
									장소 : <c:out value="${listview.schplace}" escapeXml="false" /> <br>
									참석자 : <c:out value="${listview.schmember}" escapeXml="false" /> <br>
									<button type="button" class="btn btn-default pull-right"
										onclick="location.href='scheduleForm'">
										<i class="fa fa-pencil-square-o "></i>
										<s:message code="common.btnUpdate" />
									</button>
									<button type="button" class="btn btn-default pull-right"
										onclick="location.href='scheduleForm'">
										<i class="fa fa-minus"></i>
										<s:message code="common.btnDelete" />
									</button>
								</div>
							</div>
						</div>

					</c:forEach>

				</div>



				<br />
				<form role="form" id="form1" name="form1" method="post">

					<div class="form-group">
						<div class="checkbox col-lg-3 pull-left">
							<label class="pull-right"> <input type="checkbox"
								name="searchType" value="schcont"
								<c:if test="${fn:indexOf(searchVO.searchType, 'schcont')!=-1}">checked="checked"</c:if> />
								<s:message code="board.contents" />
							</label> <label class="pull-right"> <input type="checkbox"
								name="searchType" value="schtitle"
								<c:if test="${fn:indexOf(searchVO.searchType, 'schtitle')!=-1}">checked="checked"</c:if> />
								<s:message code="board.title" />
							</label> <label class="pull-right"> <input type="checkbox"
								name="searchType" value="usernm"
								<c:if test="${fn:indexOf(searchVO.searchType, 'usernm')!=-1}">checked="checked"</c:if> />
								<s:message code="board.writer" />
							</label>
						</div>

						<div class="input-group custom-search-form col-lg-3">
							<input class="form-control" placeholder="Search..." type="text"
								name="searchKeyword"
								value='<c:out value="${searchVO.searchKeyword}"/>'> <span
								class="input-group-btn">
								<button class="btn btn-default" onclick="fn_formSubmit()">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</div>
				</form>

			</div>
			<!-- /.row -->

		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

</body>

</html>
