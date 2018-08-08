<%@page import="com.lris.ain.back.common.SessionManager"%>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-后台操作日志</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body>
	<form class="form-inline" role="form" action="<%=rootpath%>/BackGroundOperationLog/list" method="POST">
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	<input type="hidden" id="totalCount" name="totalCount" value="0" />
	<div class="container-fluid" id="searchpanel">
		<div class="form-group form-group-sm">
    		<label for="username">用户名:</label>
    		<input type="text" class="form-control" id="username" name="username" value="${param.username}" autocomplete="off" />
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="describe">描述:</label>
    		<input type="text" class="form-control" id="describe" name="describe" value="${param.describe}" autocomplete="off" />
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
			<th width="20%">用户名</th>
			<th width="30%">操作时间</th>
			<th width="50%">描述</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${data}" var="obj" varStatus="sta" >
			<tr>
				<td>${obj.username}</td>
				<td><fmt:formatDate value="${obj.f03}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<p class="moreinfo" style="width: 400px!important;" data-container="body" data-toggle="popover" data-placement="left" 
					data-content='${obj.f06 }'>${obj.f06}</p>
				</td>
			</tr>
		</c:forEach>
	</tbody>	
	</table>
	</div>
	<%@include file="/WEB-INF/include/pages.jsp" %>
	<%@include file="/WEB-INF/include/script.jsp" %>
	<script type="text/javascript">
		$(function () { 
			$("[data-toggle='popover']").popover();
		});
	</script>
</body>
</html>