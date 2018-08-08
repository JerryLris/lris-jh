<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-修改密码</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<style type="text/css">
</style>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form" method="post" id="form1" action="">
			<div class="form-group form-group-sm">
				<label for="name" class="col-sm-2 control-label">旧密码<span>*</span></label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="oldpwd" name="oldpwd"  />
					<span id="oldpwds" style="color: red;"></span>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="name" class="col-sm-2 control-label">新密码<span>*</span></label>
				<div class="col-sm-6">
					<input  type="password"  class="form-control" name="newpwd" id="newpwd" />
					<span id="newpwds" style="color: red;"></span>
				</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="name" class="col-sm-2 control-label">确认密码<span>*</span></label>
				<div class="col-sm-6">
					<input  type="password"  class="form-control" name="renewpwd" id="renewpwd"  />
					<span id="renewpwds" style="color: red;"></span>
				</div>
			</div>
			
			<div class="form-group form-group-sm">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary btn-sm mr10" id="mybtn" onclick="doSubmit()">
						<i class="fa fa-save"></i><span class="ml10">保 存</span>
					</button>
					<button type="button" class="btn btn-primary btn-sm mr10" id="reset">
						<i class="fa fa-save"></i><span class="ml10">重 置</span>
					</button>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="name" class="col-sm-2 control-label"></label>
				<span id="result" style="color: red;font-size:250%;"></span>
			</div>
		</form>
	</div>
	<%@include file="/WEB-INF/include/script.jsp"%>
	<script type="text/javascript">
		function check(){
			$("#result").html("");
			var flag=true;
			var oldpwd = $("#oldpwd").val();
			var newpwd = $("#newpwd").val();
			var renewpwd = $("#renewpwd").val();
			if(oldpwd==undefined || trim(oldpwd)==""){
				$("#oldpwds").html("请输入旧密码");
				flag = false;
			}else{
				$("#oldpwds").html("");
			}
			if(newpwd==undefined || trim(newpwd)==""){
				$("#newpwds").html("请输入新密码");
				flag = false;
			}else{
				$("#newpwds").html("");
			}
			if(renewpwd==undefined || trim(renewpwd)==""){
				$("#renewpwds").html("请输入确认密码");
				flag = false;
			}else{
				$("#renewpwds").html("");
			}
			if(renewpwd!=newpwd && trim(newpwd)!="" && trim(renewpwd)!=""){
				$("#renewpwds").html("新密码和确认密码不一致");
				flag = false;
			}else{
				$("#renewpwd").html("");
			}
			return flag;
			
		}
		function trim(str){  
		    return str.replace(/^\s+/g,"").replace(/\s+$/g,"");
		}
		function doSubmit(){
			if(check()){
			$.ajax({
				type:"post"
				,url:"<%=rootpath%>/SysUser/doresetpwd"
				,data:$("#form1").serialize()
				,dataType:"text"
				,success:function(data){
					$("#result").html("");
					$("#result").html(data);
				}
			});
			}
		}

		$("#reset").click(function(){
			$("input").val("");
		});
	</script>
</body>
</html>