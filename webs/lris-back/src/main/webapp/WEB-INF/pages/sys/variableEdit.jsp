<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-平台常量修改</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<style type="text/css">
</style>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form"
			action="<%=rootpath%>/Variable/update"
			method="post" id="form1">

			<input type="hidden" name="id" value="${param.id}" />
			<input type="hidden" name="type" value="${obj.type}"/>
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label">Key值:</label>
				<div class="col-sm-4">${obj.key}
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label">常量类型:</label>
				<div class="col-sm-4">${obj.type}</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="desc" class="col-md-2 control-label"><span>*</span>常量名称:</label>
				<div class="col-md-10">
					<input type="text" class="form-control" id="desc"
						name="desc" value="${obj.desc}" required autocomplete="off" />
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="val" class="col-md-2 control-label"><span>*</span>常量值:</label>
				<div class="col-md-10">
						<textarea id="val" name="val" cols="105" rows="3" required>${obj.val}</textarea>
				</div>
			</div>

			<div class="form-group form-group-sm">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-sm mr10">
						<i class="fa fa-save"></i><span class="ml10">保 存</span>
					</button>
					<button type="button" class="btn btn-default btn-sm mr10" onclick="javascript:history.go(-1);">
						<i class="fa fa-reply"></i><span class="ml10">返 回</span>
					</button>
				</div>
			</div>
		</form>
	</div>
	<%@include file="/WEB-INF/include/script.jsp"%>
</body>
</html>