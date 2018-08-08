<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/js/messages_zh.js"></script>
<script src="<%=request.getContextPath()%>/js/template.js"></script>
<script src="<%=request.getContextPath()%>/js/pub.js"></script>
<script src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">
	var rootpath = "<%=rootpath%>";
	<c:if test="${fn:length(param.errormsg)>0}">
        alert('${param.errormsg}');
    </c:if>
</script>