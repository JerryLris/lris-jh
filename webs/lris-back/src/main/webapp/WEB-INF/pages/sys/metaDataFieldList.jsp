<%@page import="com.lris.ain.back.common.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-元数据标记</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<style type="text/css">
.moreinfo{text-align:left;width:300px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
</style>
</head>
<body>
	<form class="form-inline" role="form" action="<%=rootpath%>/MetaDataField/list" method="POST">
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	<input type="hidden" id="totalCount" name="totalCount" value="0" />
	<div class="container-fluid" id="searchpanel">
		<div class="form-group form-group-sm">
    		<label for="para">表名称:</label>
    		<input type="text" class="form-control" id="f02" name="f02" value="${param.f02}" autocomplete="off" />
  		</div>
  	   <div class="form-group form-group-sm">
    		<label for="para">主键:</label>
    		<input type="text" class="form-control" id="f03" name="f03" value="${param.f03}" autocomplete="off" />
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="para">表字段:</label>
    		<input type="text" class="form-control" id="f04" name="f04" value="${param.f04}" autocomplete="off" />
  		</div>
		<div class="form-group form-group-sm">
			<button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-search"></i><span class="ml10">查 询</span></button>
		</div>
	</div>
	</form>

	<div id="datapanel" class="table-responsive" >
	<table class="table table-bordered table-hover ">
	<thead>
		<tr>
			<th>表名称</th>
			<th>PK主键</th>
			<th>表字段</th>
			<th>标记状态</th>
			<th>创建时间</th>
			<th>修改时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${data}" var="obj" varStatus="sta" >
			<tr>
				<td>${obj.f02}</td>
				<td>${obj.f03}</td>
			    <td>${obj.f04}</td>
				<td>
				     <c:choose>
						<c:when test="${obj.f05 == 0}">未标记</c:when>
						<c:when test="${obj.f05 == 1}">已标记</c:when>
					</c:choose>
				</td>
				<td nowrap="nowrap"><fmt:formatDate value="${obj.f06}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td nowrap="nowrap"><fmt:formatDate value="${obj.f07}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</tbody>	
	</table>
	</div>
	<%@include file="/WEB-INF/include/pages.jsp" %>
	<%@include file="/WEB-INF/include/script.jsp" %>
</body>
</html>