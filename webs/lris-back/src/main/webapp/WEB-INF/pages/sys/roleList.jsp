<%@page import="com.lris.ain.back.common.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-角色管理列表</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body >
	<form class="form-inline" role="form" action="<%=rootpath%>/Role/list" method="POST">
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	<input type="hidden" id="totalCount" name="totalCount" value="0" />
	<div class="container-fluid" id="searchpanel">
		<div class="form-group form-group-sm">
    		<label for="name">名称:</label>
    		<input type="text" class="form-control" id="name" name="name" value="${param.name}">
  		</div> 
  		<div class="form-group form-group-sm">
    		<label>状态:</label>
    		<select class="form-control" id="status" name="status">
    			<option value="">所有</option>
    			<option value="QY" ${param.status == 'QY' ? 'selected' : ''}>启用</option>
    			<option value="TY" ${param.status == 'TY' ? 'selected' : ''}>停用</option>
    		</select>
  		</div>
		<div class="form-group form-group-sm">
			<button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-search"></i><span class="ml10">查 询</span></button>
		</div>
		<%if(SessionManager.hasRight(request, "Role.add")){%>
		<div class="form-group form-group-sm">
			<a class="btn btn-primary btn-sm" href="<%=rootpath%>/Role/edit"><i class="fa fa-plus"></i><span class="ml10">新 增</span></a>
		</div>
		<%}%>
		<div id="toolspanel">
		</div>
	</div>
	</form>

	<div id="datapanel" class="table-responsive">
	<table class="table table-bordered table-hover">
	<thead>
		<tr>
		<th class="col-sm-3">名称</th>
		<th class="col-sm-3">描述</th>
		<th class="col-sm-3">状态</th>
		<th class="col-sm-3">操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${requestScope.data}" var="obj" varStatus="sta" >
	<tr>
		<td>${obj.f02}</td>
		<td>${obj.f03}</td>
		<td>${obj.f06=='QY'?'启用':'停用'}</td>
		<td>
		<%if(SessionManager.hasRight(request, "Role.menuupdate")){%>
		<a href="<%=rootpath%>/Role/menuedit?id=${obj.f01}" class="mr10 blue"><span class="ml10">菜单设置</span></a>
		<%}%>
		<%if(SessionManager.hasRight(request, "Role.rightupdate")){%>
		<a href="<%=rootpath%>/Role/rightedit?id=${obj.f01}" class="mr10 blue"><span class="ml10">权限设置</span></a>
		<%}%>
		<%if(SessionManager.hasRight(request, "Role.update")){%>
		<a href="<%=rootpath%>/Role/edit?id=${obj.f01}" class="mr10 blue"><span class="ml10">修改</span></a>
		<%}%>
		</td>
	</tr>
	</c:forEach>
	</tbody>	
	</table>
	</div>
	<%@include file="/WEB-INF/include/pages.jsp" %>
	<%@include file="/WEB-INF/include/script.jsp" %>
</body>
</html>