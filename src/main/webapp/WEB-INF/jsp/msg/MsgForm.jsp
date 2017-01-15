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
	<script src="js/ckeditor/ckeditor.js"></script>
	<script src="js/project9.js"></script>    
	<script src="js/jquery-ui.js"></script>
	<script src="js/dynatree/jquery.dynatree.js"></script>
<script>
window.onload =function() {
	  CKEDITOR.replace( 'brdmemo', { 'filebrowserUploadUrl': 'upload4ckeditor'});
}	  

function fn_formSubmit(){
	CKEDITOR.instances["brdmemo"].updateElement();
	
	if ( ! chkInputValue("#brdtitle", "<s:message code="board.title"/>")) return false;
	if ( ! chkInputValue("#brdmemo", "<s:message code="board.contents"/>")) return false;
	
	$("#form1").submit();
} 
////////////////////////////////////////////////////////
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
////////////////////////////////////////////////
function fn_searchUsers(){
    $.ajax({
    	url: "popupUsers",
		type: "post"    	
    }).success(function(result){
    			$("#popupUsers").html(result);
    			if ($("#usernos").val()!==""){
    				set_Users($("#usernos").val(), $("#usernms").val()); 
    			}
		}    		
    );
	$("#popupUsers").modal("show");
}

function deptTreeInUsersActivate(node) {
    if (node==null || node.data.key==0) return;
    
    $.ajax({
    	url: "popupUsers4Users",
		type:"post", 
    	data: { deptno : node.data.key }    	
    }).success(function(result){
    			$("#userlist4Users").html(result);
		}    		
    );
}

function fn_selectUsers(usernos, usernms) {
    $("#usernos").val(usernos);
    $("#usernms").val(usernms);
	$("#popupUsers").modal("hide");
}
</script>
    
</head>

<body>

    <div id="wrapper">

		<jsp:include page="../common/navigation.jsp" />
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> <s:message code="send.msg.new"/></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
            	<form id="form1" name="form1" role="form" action="boardSave" method="post" enctype="multipart/form-data" onsubmit="return fn_formSubmit();" >
					<div class="panel panel-default">
	                    <div class="panel-body">
	                    <!-- 사용자 추가 -->
	                    	<div class="row form-group">
								<label class="col-lg-1">사용자 선택</label>
									<div class="col-lg-9">
										<div class="input-group custom-search-form">
					               			<input type="hidden" name="usernos" id="usernos">
					                           <input class="form-control" type="text" name="usernms" id="usernms" readonly="readonly">
					                           <span class="input-group-btn">
					                               <button class="btn btn-default" type="button" onclick="fn_searchUsers()">
					                                   <i class="fa fa-search"></i>
					                               </button>
					                           </span>
					                     </div>
					                </div> 
					        </div>  
				            <!-- 사용자 추가 --> 
				            <!-- 부서선택 -->
				            <div class="row form-group">
								<label class="col-lg-1">부서선택</label>
							<div class="col-lg-9">
								<div class="input-group custom-search-form">
			               			<input type="hidden" name="deptno" id="deptno">
			                           <input class="form-control" type="text" name="deptnm" id="deptnm" readonly="readonly">
			                           <span class="input-group-btn">
			                               <button class="btn btn-default" type="button" onclick="fn_searchDept()">
			                                   <i class="fa fa-search"></i>
			                               </button>
			                           </span>
			                       </div>
			                   </div>  
			                </div>   
	                    	<div class="row form-group">
	                            <label class="col-lg-1"><s:message code="board.contents"/></label>
	                            <div class="col-lg-9">
	                            	<textarea id="brdmemo" class="form-control" name="brdmemo" rows="10" cols="60"></textarea>
	                            </div>
	                        </div>                        
	                    	<div class="row form-group">
	                            <label class="col-lg-1"><s:message code="board.file"/></label>
	                            <div class="col-lg-9">
									<input type="file" name="uploadfile" multiple="multiple" />
	                            </div>
	                        </div>  
	                    </div>
	                </div>
			        <button class="btn btn-outline btn-primary"><s:message code="send.msg.send"/></button>
					<input type="hidden" name="bgno" value="<c:out value="${bgno}"/>"> 
					<input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
				</form>	
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    <div id="popupUsers" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	</div>
	<div id="popupDept" style="display: none;" class="modal fade bs-example-modal-sm" role="dialog" tabindex="-1" aria-labelledby="mySmallModalLabel">
    </div>
</body>

</html>
