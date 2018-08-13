<%@page import="java.util.Map"%>
<%@page import="com.lris.ain.back.sys.vo.Right"%>
<%{
Map<String,Right> list=(Map<String,Right>)request.getAttribute("rights");
int menuNum = 0;
%>
<li id="menuitem1">
<a href="#"><i class="fa fa-cog"></i> <span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
<ul class="nav nav-second-level">
<%if(list.containsKey("SYS.Role")){menuNum++;%><li><a href="javascript:gotoPage('<%=rootpath%>/Role/list','角色管理','SYS_Role')">角色管理</a></li><%}%>
<%if(list.containsKey("SYS.SysUser")){menuNum++;%><li><a href="javascript:gotoPage('<%=rootpath%>/SysUser/list?status=QY','账号管理','SYS_SysUser')">账号管理</a></li><%}%>
<%if(list.containsKey("SYS.Variable")){menuNum++;%><li><a href="javascript:gotoPage('<%=rootpath%>/Variable/list','平台常量','SYS_Variable')">平台常量</a></li><%}%>
<%if(list.containsKey("SYS.UserOperationLog")){menuNum++;%><li><a href="javascript:gotoPage('<%=rootpath%>/UserOperationLog/list','用户操作日志','SYS_Useroperationlog')">用户操作日志</a></li><%}%>
<%if(list.containsKey("SYS.BackGroundOperationLog")){menuNum++;%><li><a href="javascript:gotoPage('<%=rootpath%>/BackGroundOperationLog/list','后台操作日志','SYS_Backgroundoperationlog')">后台操作日志</a></li><%}%>
<%if(list.containsKey("SYS.MsgSentLog")){menuNum++;%><li><a href="javascript:gotoPage('<%=rootpath%>/MsgSentLog/list','短信下发日志','SYS_MsgSentLog')">短信下发日志</a></li><%}%>
</ul>
</li>
<%if(0==menuNum){%>
<script type="text/javascript">
hideMenu.push("menuitem1");
</script>	
<%}%>
<%} %>