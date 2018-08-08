<%@page import="com.lris.ain.back.common.SessionManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-帐号管理列表</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
</head>
<body >
	<form class="form-inline" role="form" action="<%=rootpath%>/SysUser/list" method="POST">
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	<input type="hidden" id="totalCount" name="totalCount" value="0" />
	<div class="container-fluid" id="searchpanel">
		<div class="form-group form-group-sm">
    		<label for="name">用户名:</label>
    		<input type="text" class="form-control" id="name" name="name" value="${param.name}">
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="realname">真实姓名:</label>
    		<input type="text" class="form-control" id="realName" name="realName" value="${param.realName }">
  		</div>
  		<div class="form-group form-group-sm">
    		<label for="createTimeStart">最后登录时间:</label>
    		<input type="text" class="form-control" id="createTimeStart" name="createTimeStart" value="${param.createTimeStart}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" autocomplete="off" >
  			<label for="createTimeEnd">至:</label>
    		<input type="text" class="form-control" id="createTimeEnd" name="createTimeEnd" value="${param.createTimeEnd}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" autocomplete="off" >
  		</div>
		<div class="form-group form-group-sm">
    		<label>状态:</label>
    		<select class="form-control" id="status" name="status">
    			<option value="">所有</option>
    			<c:forEach items="${useState}" var="useStatus">
    				<option value="${useStatus}" ${param.status == useStatus ? 'selected' : ''}>${useStatus.cname}</option>
    			</c:forEach>
    		</select>
  		</div>
		<div class="form-group form-group-sm">
			<button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-search"></i><span class="ml10">查 询</span></button>
		</div>
		<%if(SessionManager.hasRight(request, "SysUser.edit")){%>
		<div class="form-group form-group-sm">
			<a class="btn btn-primary btn-sm" href="<%=rootpath%>/SysUser/edit"><i class="fa fa-plus"></i><span class="ml10">新 增</span></a>
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
				<th>用户名</th>
				<th>真实姓名</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>最后登录时间</th>
				<th>最后登录IP</th>
				<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.data}" var="obj" varStatus="sta" >
				<tr>
					<td>${obj.f02}</td>
					<td>${obj.f04}</td>
					<td>${obj.f05cname}</td>
					<td><fmt:formatDate value="${obj.f06}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${obj.f07}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${obj.f08}</td>
					<td>
					<%if(SessionManager.hasRight(request, "SysUser.authEdit")){%>
						<a href="<%=rootpath%>/SysUser/authEdit?id=${obj.f01}" class="mr10 blue"><span class="ml10">授权</span></a>
					<%}%>
					<%if(SessionManager.hasRight(request, "SysUser.toupdate")){%>
						<a href="<%=rootpath%>/SysUser/toupdate?id=${obj.f01}" class="mr10 blue"><span class="ml10">修改</span></a>
					<%}%>
					<%if(SessionManager.hasRight(request, "SysUser.unlock")){%>
					 	<a href="" onclick="unlock(${obj.f01})" class="mr10 blue"><span class="ml10">解锁</span></a>
					<%}%>
					</td>
				</tr>
			</c:forEach>
			</tbody>	
		</table>
		</div>
		<%@include file="/WEB-INF/include/pages.jsp" %>
		<%@include file="/WEB-INF/include/script.jsp" %>
		<script type="text/javascript">
			function unlock(uid) {
				var data = {"id":uid};
				$.ajax({
					url:rootpath+'/SysUser/unlock',
					type:'POST',
					data:data,
					success:function(data){
						alert("解锁成功");
					}
				});
			}
		</script>
	</body>
</html>