<%@page import="com.lris.ain.back.common.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-用户操作日志</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body>
	<form class="form-inline" role="form" action="<%=rootpath%>/UserOperationLog/list" method="POST">
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	<input type="hidden" id="totalCount" name="totalCount" value="0" />
	<div class="container-fluid" id="searchpanel">
		<div class="form-group form-group-sm">
    		<label for="username">用户名:</label>
    		<input type="text" class="form-control" id="username" name="username" value="${param.username}" autocomplete="off" />
  		</div>
		<div class="form-group form-group-sm">
    		<label for="f02">操作类型:</label>
    		<input type="text" class="form-control" id="f02" name="f02" value="${param.f02}" autocomplete="off" />
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="f03">验证依据:</label>
    		<input type="text" class="form-control" id="f03" name="f03" value="${param.f03}" autocomplete="off" />
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="f05">IP:</label>
    		<input type="text" class="form-control" id="f05" name="f05" value="${param.f05}" autocomplete="off" />
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="f11">失败原因:</label>
    		<input type="text" class="form-control" id="f11" name="f11" value="${param.f11}" autocomplete="off" />
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="f06">操作结果:</label>
    		<select class="form-control" id="f06" name="f06" >
    			<option value="">所有</option>
    			<option id="f06" name="f06" value="1" ${"1"==param.f06 ? 'selected' : ''} >成功</option>
    			<option id="f06" name="f06" value="0" ${"0"==param.f06 ? 'selected' : ''} >失败 </option>
    		</select>
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="timestart">操作时间:</label>
	    		<input type="text" onClick="WdatePicker();" class="form-control" id="timestart" name="timestart" value="${param.timestart}" autocomplete="off" />
	    		<label for="timeend">至:</label>
	    		<input type="text" onClick="WdatePicker();" class="form-control" id="timeend" name="timeend" value="${param.timeend}" autocomplete="off" />
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
			<th>用户名</th>
			<th>操作时间</th>
			<th>操作类型</th>
			<th>验证依据</th>
			<th>IP</th>
			<th>结果</th>
			<th>历史数据</th>
			<th>失败原因</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${data}" var="obj" varStatus="sta" >
			<tr>
				<td>${obj.username}</td>
				<td><fmt:formatDate value="${obj.f04}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${obj.f02}</td>
				<td>${obj.f03}</td>
				<td>${obj.f05}</td>
				<td>${obj.f06==0?"失败":"成功"}</td>
				<td>${obj.f07}</td>
				<td>${obj.f11}</td>
			</tr>
		</c:forEach>
	</tbody>	
	</table>
	</div>
	<%@include file="/WEB-INF/include/pages.jsp" %>
	<%@include file="/WEB-INF/include/script.jsp" %>
</body>
</html>