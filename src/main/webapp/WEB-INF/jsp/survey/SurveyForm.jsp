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
	<link href="js/datepicker/datepicker.css" rel="stylesheet" type="text/css">
	<link href="js/dynatree/ui.dynatree.css" rel="stylesheet"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
	<script src="js/datepicker/bootstrap-datepicker.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/dynatree/jquery.dynatree.js"></script>
	<script src="js/project9.js"></script>    

<script>
window.onload =function() {
	  $('#term1').datepicker().on('changeDate', function(ev) {
			if (ev.viewMode=="days"){
				$('#term1').datepicker('hide');
			}
		});
		$('#term2').datepicker().on('changeDate', function(ev) {
			if (ev.viewMode=="days"){
				$('#term2').datepicker('hide');
			}
		});
}	  

function fn_formSubmit(){
	if ( ! chkInputValue("#surtitle", "<s:message code="survey.title"/>")) return false;
	if ( ! chkInputValue("#surcontents", "<s:message code="survey.contents"/>")) return false;
	
	$("#form1").submit();
}

function fn_searchDept(){
    $.ajax({
    	url: "popupDept",
		type: "post"    	
    }).success(function(result){
    			$("#popupDept").html(result);
		}    		
    );
	$("#popupDept").modal("show");
}

function deptTreeActivate(node) {
    if (node==null || node.data.key==0) return;
    
    $("#deptno").val(node.data.key);
    $("#deptnm").val(node.data.title);
	$("#popupDept").modal("hide");
}
</script>
    
</head>

<body>

    <div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> 설문 게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
            	<form id="form1" name="form1" role="form" action="surveySave" method="post" onsubmit="return fn_formSubmit();" >
					<div class="panel panel-default">
	                    <div class="panel-body">
	                    	<div class="row form-group">
	                            <label class="col-lg-1"><s:message code="board.title"/></label>
	                            <div class="col-lg-9">
	                            	<input type="text" class="form-control" id="surtitle" name="surtitle" size="70" maxlength="250" value="<c:out value="${boardInfo.brdtitle}"/>">
	                            	<c:if test="${bgInfo.bgnotice=='Y'}">
									 	<label>
				                        	<input type="checkbox" name="brdnotice" value="Y" <c:if test="${boardInfo.brdnotice=='Y'}">checked="checked"</c:if>/>
				                        	<s:message code="common.notice"/>
				                        </label>
	                            	</c:if>
	                            </div>
	                        </div>
	                    	<div class="row form-group">
	                            <label class="col-lg-1"><s:message code="survey.contents"/></label>
	                            <div class="col-lg-9">
	                            	<textarea id="surcontents" class="form-control" name="surcontents" rows="10" cols="60"><c:out value="${boardInfo.brdmemo}"/></textarea>
	                            </div>
	                        </div>                        
	                    	
	                        <div class="row form-group">
	                        	<label class="col-lg-1">
                    	 			<s:message code="survey.period"/>
								</label>
                 				<div class="col-lg-2">
									<input class="form-control" size="16" id="term1" name="surstartdate" type="text" value="<c:out value="${today}"/>" readonly>
                			 	</div>
                 				<div class="col-lg-2">
			  						<input class="form-control" size="16" id="term2" name="surenddate" type="text" value="<c:out value="${today}"/>" readonly>
                 				</div>
                 			</div> 
                 			<div class="row form-group">
                 				<label class="col-lg-1">
                    	 			<s:message code="survey.target"/>
								</label>
								<div class="col-lg-3">
									<div class="input-group custom-search-form">
               							<input type="hidden" name="deptno" id="deptno">
                           				<input class="form-control" type="text" name="surresponsor" id="deptnm" readonly="readonly">
                           				<span class="input-group-btn">
                               				<button class="btn btn-default" type="button" onclick="fn_searchDept()">
                                   				<i class="fa fa-search"></i>
                               				</button>
                           				</span>
                       				</div>
                   				</div>
                 			</div>
	                    </div>
	                </div>
			        <button class="btn btn-outline btn-primary"><s:message code="common.btnSave"/></button>
					<input type="hidden" name="bgno" value="<c:out value="${bgno}"/>"> 
					<input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
				</form>	
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    <div id="popupDept" style="display: none;" class="modal fade bs-example-modal-sm" role="dialog" tabindex="-1" aria-labelledby="mySmallModalLabel">
    </div>
</body>

</html>
