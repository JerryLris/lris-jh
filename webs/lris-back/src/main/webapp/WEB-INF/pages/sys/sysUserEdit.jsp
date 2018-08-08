<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-帐号管理-编辑</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form"
			action="<%=rootpath%>/SysUser/${param.id > 0 ? 'update' : 'add'}"
			method="post" id="form1">
			<input type="hidden" name="id" value="${param.id}" />
			${formtoken }
			<div class="form-group form-group-sm">
				<label for="userName" class="col-sm-2 control-label"><span>*</span>用户名:</label>
				<div class="col-sm-4">
					<c:choose>
						<c:when test="${param.id > 0}">${obj.f02}</c:when>
						<c:otherwise>
							<input type="alnum" class="form-control" id="userName" autocomplete="off"
								name="userName" value="${obj.f02}" required />
							<span id="help_userName" style="color:red;"></span>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="realName" class="col-sm-2 control-label"><span>*</span>真实姓名:</label>
				<div class="col-sm-4">
					<c:choose>
						<c:when test="${param.id > 0}">${obj.f04}</c:when>
						<c:otherwise>
							<input type="chcharacter" class="form-control" id="realName" autocomplete="off"
							name="realName" value="${obj.f04}" required />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="password" class="col-sm-2 control-label">密码:</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password" 
						name="password" value="${param.id > 0 ? '':123456}" />
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="phone" class="col-sm-2 control-label"><span>*</span>手机:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="phone" autocomplete="off"
						name="phone" value="${obj.f09}" required />
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="f12" class="col-sm-2 control-label">座机分机:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="f12" autocomplete="off"
						name="f12" value="${obj.f12}" />
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label"><span>*</span>是否审单人员:</label>
				<div class="col-sm-4">
					<label class="radio-inline"><input type="radio" name="f11" value="0" checked="checked" />否</label>
				    <label class="radio-inline"><input type="radio" name="f11" value="1" <c:if test="${obj.f11 eq 1 }">checked="checked"</c:if>/>是</label>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" ><span>*</span>状态:</label>
				<div class="col-sm-4">
					<label class="radio-inline"><input type="radio" name="status" value="QY" ${obj.f05=='QY'?"checked":"checked"} />启用</label>
				    <label class="radio-inline"><input type="radio" name="status" value="TY" ${obj.f05=='TY'?"checked":""}/>停用</label>
				</div>
			</div>
		  <div class="form-group form-group-sm">
    		<label class="col-sm-2 control-label" >所属部门:</label>
    		<select id="emp" name="cuiShoueId" class="form-control" style="width:250px" value="${obj.f13}" >
    			<!-- <option value="">请选择</option> -->
    			<%-- <option value="A" <c:if test="${'A' eq param.dataSource }">selected="selected"</c:if>>河马</option> --%>
    			<%-- <option value="B" <c:if test="${'B' eq param.dataSource }">selected="selected"</c:if>>代理</option> --%>
    		</select>
  		</div>
			<div class="form-group form-group-sm">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-sm mr10" id="sbm">
						<i class="fa fa-save"></i><span class="ml10">保 存</span>
					</button>
					<a href="<%=rootpath%>/SysUser/list"
						class="btn btn-default btn-sm"><i class="fa fa-reply"></i><span
						class="ml10">返 回</span></a>
				</div>
			</div>
		</form>
	</div>
	<%@include file="/WEB-INF/include/script.jsp"%>
	<script type="text/javascript">
		$().ready(function(){
			var msg= '${errormsg}';
			if(msg!=null&&msg!=''){
				alert(msg);
			}
			$("#form1").validate({
		        submitHandler:function(form){
		        	$.ajax({
						type:"post"
						,url:"<%=rootpath%>/SysUser/docheckf02"
						,data:"f02="+$("#userName").val()
						,dataType:"text"
						,success:function(data){
							if(data=='YES'){
								$("#help_userName").html("");
								$("#help_userName").html("用户名重复");
							}else{
								$("#help_userName").html("");
								form.submit();
							}
						}
					});
		        }    
		    });
		});
		$(function () {

			var url = "<%=rootpath%>/CuiShouEmp/getList";
			$.post(url,{},function(data){
				var s = $("#emp").attr("value")
				var optionstring="";
				$(data).each(function(i,n){		
					if(s==n.F01){
						optionstring += "<option selected='selected' value=\"" +n.F01+ "\" >" + n.F02+ "</option>";
					}else{
						optionstring += "<option value=\"" +n.F01+ "\" >" + n.F02+ "</option>";
					} 
				});
				$("#emp").html("<option value='0'>请选择...</option> "+optionstring);  
		        
			},'json');
		})
	</script>
</body>
</html>