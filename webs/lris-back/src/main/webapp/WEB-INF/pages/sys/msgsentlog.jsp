<%@page import="com.lris.ain.back.common.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-短信下发日志</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body>
	<form class="form-inline" role="form" action="<%=rootpath%>/MsgSentLog/list" method="POST">
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	<input type="hidden" id="totalCount" name="totalCount" value="0" />
	<div class="container-fluid" id="searchpanel">
		<div class="form-group form-group-sm">
    		<label for="content">短信内容:</label>
    		<input type="text" class="form-control" id="content" name="content" value="${param.content}" autocomplete="off" />
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="phone">手机号:</label>
    		<input type="text" class="form-control" id="phone" name="phone" value="${param.phone}" autocomplete="off" />
  		</div>
		<div class="form-group form-group-sm">
			<button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-search"></i><span class="ml10">查 询</span></button>
		</div>
	</div>
	</form>

	<div id="datapanel" class="table-responsive" >
	<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>发送类型</th>
			<th>短信内容</th>
			<th>手机号</th>
			<th>手机号数量</th>
			<th>发送时间</th>
			<th>短信返回参数</th>
			<th>短信反馈发送结果</th>
			<th>发送接口</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${data}" var="obj" varStatus="sta" >
			<tr>
				<td>${obj.f02 == 1?'验证码':'通知'}</td>
				<td>${obj.f03}</td>
				<td>${obj.f04}</td>
				<td>${obj.f05}</td>
				<td><fmt:formatDate value="${obj.f06}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${obj.f07}</td>
				<td>${obj.f08}</td>
				<td>${obj.f09}</td>
			</tr>
		</c:forEach>
	</tbody>	
	</table>
	</div>
	<%@include file="/WEB-INF/include/pages.jsp" %>
	<%@include file="/WEB-INF/include/script.jsp" %>
</body>
</html>