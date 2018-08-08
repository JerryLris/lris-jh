<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-角色管理编辑</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form"
			action="<%=rootpath%>/Role/${param.id > 0 ? 'update' : 'add'}"
			method="post" id="form1">

			<input type="hidden" name="id" value="${param.id}" />
			<div class="form-group form-group-sm">
				<label for="roleName" class="col-sm-2 control-label"><span>*</span>名称:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="roleName"
						name="roleName" value="${obj.f02}" required />
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="roleDes" class="col-sm-2 control-label">描述:</label>
				<div class="col-sm-4">
					<textarea rows="5" style="height: 150px"  class="form-control" id="roleDes" name="roleDes" >${obj.f03}</textarea>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" ><span>*</span>状态:</label>
				<div class="col-sm-4">
					<label class="radio-inline"> <input type="radio" name="status" id="status" value="QY" ${obj.f06=='QY'?"checked":"checked"} />启用</label>
				    <label class="radio-inline"> <input type="radio" name="status" id="status" value="TY" ${obj.f06=='TY'?"checked":""}/>停用</label>
				</div>
			</div>

			<div class="form-group form-group-sm">
				<div class="col-sm-offset-2 col-sm-10">

					<button type="submit" class="btn btn-primary btn-sm mr10">
						<i class="fa fa-save"></i><span class="ml10">保 存</span>
					</button>

					<a href="<%=rootpath%>/Role/list"
						class="btn btn-default btn-sm"><i class="fa fa-reply"></i><span
						class="ml10">返 回</span></a>
				</div>
			</div>
		</form>
	</div>
	<%@include file="/WEB-INF/include/script.jsp"%>
	<script type="text/javascript">
		$().ready(function(){
			$("#form1").validate();
		});
	</script>
</body>
</html>