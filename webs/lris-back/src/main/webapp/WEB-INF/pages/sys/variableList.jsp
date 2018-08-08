<%@page import="com.lris.ain.back.common.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-平台常量列表</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body >
	<form class="form-inline" role="form" action="<%=rootpath%>/Variable/list" method="POST">
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	<input type="hidden" id="totalCount" name="totalCount" value="0" />
	<div class="container-fluid" id="searchpanel">
		<div class="form-group form-group-sm">
    		<label for="key">KEY:</label>
    		<input type="text" class="form-control" id="key" name="key" value="${param.key}" autocomplete="off">
  		</div>
		<div class="form-group form-group-sm">
    		<label for="desc">常量名称:</label>
    		<input type="text" class="form-control" id="desc" name="desc" value="${param.desc}" autocomplete="off">
  		</div>
  		<div class="form-group form-group-sm">
    		<label>常量类型:</label>
    		<select class="form-control" id="type" name="type"  >
    			<option value="">所有</option>
    			<c:forEach items="${types}" var="types" varStatus="sta" >
    			  <option id="type" name="type" value="${types.type}" ${types.type==param.type ? 'selected' : ''} >${types.type} </option>
    			</c:forEach>
    		</select>
  		</div>
		<div class="form-group form-group-sm">
			<button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-search"></i><span class="ml10">查 询</span></button>
		</div>
		<div id="toolspanel">
		</div>
	</div>
	</form>

	<div id="datapanel" class="table-responsive">
	<table class="table table-bordered table-hover">
	<thead>
		<tr>
		<th class="col-md-3">Key值</th>
		<th class="col-md-3">常量名称</th>
		<th class="col-md-2">常量类型</th>
		<th class="col-md-3">常量值</th>
		<th class="col-md-1">操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${requestScope.data}" var="obj" varStatus="sta" >
	<tr>
		<td>${obj.keyLimit}</td>
		<td>${obj.descLimit}</td>
		<td>${obj.type}</td>
		<td>${obj.valLimit}</td>
		<td>
			<%if(SessionManager.hasRight(request, "Variable.edit")) {%>
			<a href="<%=rootpath%>/Variable/edit?id=${obj.key}" class="mr10 blue"><span class="ml10">修改</span></a>
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