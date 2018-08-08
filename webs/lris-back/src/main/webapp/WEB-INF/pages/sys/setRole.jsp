<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-授权</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<style type="text/css">
#rolepanel>label{margin-right:20px;}
</style>
</head>
<body>
	<div class="container" style="margin-top:20px;margin-bottom:20px;">
		<form class="form-horizontal" role="form" action="<%=rootpath%>/SysUser/setRole" method="post" id="form1">
			<input type="hidden" name="id" value="${param.id}" />
			<div class="col-md-3" id="rolepanel">
			<c:forEach items="${requestScope.obj}" var="obj" varStatus="sta">
				<c:if test="${obj.f06=='QY'}">
					<label><input type="checkbox" name="roleIds" value="${obj.f01}" ${obj.userId==0?'':'checked'} />${sta.index+1}.${obj.f02}</label>
				</c:if>
	       </c:forEach>
			</div>
	</div>

	<div class="form-group form-group-sm">
		<div class="col-sm-offset-2 col-sm-10">

			<button type="submit" class="btn btn-primary btn-sm mr10">
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
	</script>
</body>
</html>