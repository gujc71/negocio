<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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

    <title><s:message code="common.pageTitle"/></title>
    <link href="css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="css/sb-admin/metisMenu.min.css" rel="stylesheet">
    <link href="css/sb-admin/sb-admin-2.css" rel="stylesheet">
    <link href="css/sb-admin/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="js/dynatree/ui.dynatree.css" rel="stylesheet" id="skinSheet"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/dynatree/jquery.dynatree.js"></script>    
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
	<script src="js/project9.js"></script>    
<script>


function fn_formSubmit(){
	document.form1.submit();	
}

function showBoardList(ev){
	if( $('#boardlistDiv').is(':visible') ) {
		$("#boardlistDiv").hide();
		return;
	}
	var pos = $( "#boardlistBtn" ).position();
	$("#boardlistDiv").css({
		   "top" : parseInt(pos.top)+30 + "px",
		   "left" : pos.left
	}).show();
	
	var node = $("#tree").dynatree("getRoot");
	
	if (node.childList) return;
	
	$.ajax({
		url: "boardListByAjax",
		type:"post", 
		dataType: "json",
		success: function(result){
			$("#tree").dynatree({children: result});
		    $("#tree").dynatree("getTree").reload();
		    $("#tree").dynatree("getRoot").visit(function(node){
		        node.expand(true);
		    });
		}
	})	
	
}

$(function(){
	$("#tree").dynatree({
		onActivate: TreenodeActivate
	});
});

function TreenodeActivate(node) {
	location.href = "boardList?bgno=" + node.data.key;
}

</script>
    
</head>

<body>

    <div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> <s:message code="send.msg.revtitle"/></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
			            <button type="button" class="btn btn-default pull-right" onclick="fn_moveToURL('msgForm')">
			            <i class="fa fa-edit fa-fw"></i> <s:message code="send.msg.new"/></button>      
                </div>
            </div>
            <!-- /.row -->
            <div class="panel panel-default">
            					
					<c:if test="${listview.size()==0}">
						<div class="listBody height200">
						</div>
					</c:if>					
					<c:forEach var="listview" items="${listview}" varStatus="status">
						<div class="listHead">
							<div class="listHiddenField pull-left field60"><s:message code="send.msg.no"/></div>
							<div class="listHiddenField pull-right field60"><s:message code="send.msg.file"/></div>
							<div class="listHiddenField pull-right field100"><s:message code="send.msg.date"/></div>
							<div class="listHiddenField pull-right field100"><s:message code="send.msg.senduser"/></div>
							<div class="listTitle"><s:message code="send.msg.cont"/></div>
						</div>
						<div class="listBody">
							<div class="listHiddenField pull-left field60"><c:out value="${searchVO.totRow-((searchVO.page-1)*searchVO.displayRowCount + status.index)}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${listview.sendate}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><a href="list4User?userno=<c:out value="${listview.userno}"/>"><c:out value="${listview.senuser}"/></a></div>
							<div class="listTitle" title="<c:out value="${listview.msgcontent}"/>">
								<a href="${link}" ><c:out value="${listview.msgcontent}"/></a>							
							</div>
							<!-- 작은창 -->
							<div class="showField text-muted small">
								<c:out value="${listview.senuser}"/>
								<c:out value="${listview.sendate}"/>
							</div>
						</div>
					</c:forEach>	
					
					<br/>
					<form role="form" id="form1" name="form1"  method="post">
					    <jsp:include page="../common/pagingforSubmit.jsp" />
				    
						<div class="form-group">
							<div class="checkbox col-lg-3 pull-left">
							 	<label class="pull-right">
		                        	<input type="checkbox" name="searchType" value="brdmemo" <c:if test="${fn:indexOf(searchVO.searchType, 'brdmemo')!=-1}">checked="checked"</c:if>/>
		                        	<s:message code="board.contents"/>
		                        </label>
							 	<%-- <label class="pull-right">
		                        	<input type="checkbox" name="searchType" value="brdtitle" <c:if test="${fn:indexOf(searchVO.searchType, 'brdtitle')!=-1}">checked="checked"</c:if>/>
		                        	<s:message code="board.title"/>
		                        </label> --%>
							 	<label class="pull-right">
							 		<input type="checkbox" name="searchType" value="usernm" <c:if test="${fn:indexOf(searchVO.searchType, 'usernm')!=-1}">checked="checked"</c:if>/>
		                        	<s:message code="board.writer"/>
		                        </label>
		                   </div>
		                   <div class="input-group custom-search-form col-lg-3">
	                                <input class="form-control" placeholder="Search..." type="text" name="searchKeyword" 
	                                	   value='<c:out value="${searchVO.searchKeyword}"/>' >
	                                <span class="input-group-btn">
	                                <button class="btn btn-default" onclick="fn_formSubmit()">
	                                    <i class="fa fa-search"></i>
	                                </button>
	                            </span>
	                       </div>
						</div>
					</form>	
            	</div>    
            </div>            
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>

</html>
