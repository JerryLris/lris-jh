<!DOCTYPE>
<html lang="zh-cn">
<head>
<title></title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<style type="text/css">
</style>
</head>
<body >
	<div style="width:100%;height:50%;background:url('<%=rootpath%>/images/errortip.png') center center no-repeat;">
	</div>
	<div style="margin:0 auto;text-align:center;padding-top:20px;color:#FF0000;font-size:24px;">
	<%=request.getParameter("errormsg")%>
	<div>
</body>
</html>