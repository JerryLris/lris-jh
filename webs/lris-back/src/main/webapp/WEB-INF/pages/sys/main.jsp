<!DOCTYPE>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.lris.ain.back.sys.vo.Right"%>
<html lang="zh-cn">
<head>
<title>lris系统-运营平台</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/metisMenu/metisMenu.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/font-awesome.min.css" />
<style type="text/css">
	
	.user-nav {margin: 0;margin-right: -15px;}
	.container{ width: 100%;}
	.navbar{margin-bottom: 0px;height: 50px;border: none;}
	#head-nav{background-color: #23262E; /*#2494F2;*/height: 50px;}
	#cl-wrapper{position: absolute;left:0px;top:50px;right: 0px;bottom: 0px;overflow: hidden;}
	#side-menu{position: absolute;left:0;top: 0;width: 200px;bottom: 0;overflow-y:auto;overflow-x:hidden;background-color: 393D49;}
	#cotentpanel{position: absolute;left:200px;top: 0px;right: 0;bottom: 0;overflow:hidden;}
	#functab {position: absolute;left: 0;top: 0;right: 0;overflow: hidden;/* border: 1px solid #F0F0F0; */background-color: #F2F2F2;}
	#functab .menutab{float:left;height: 30px;line-height: 30px;overflow: hidden;width:100px;margin-left:1px;position: relative;}
	#functab .menutab:hover{background-color: #F4F4F4;cursor:pointer;}
	.menutab.normal{background-color: #DDDDDD;}
	.menutab.now{background-color: #FFFFFF;}
	.menutab .menutitle{position: absolute;left:0;top:0;height: 30px;line-height: 30px;width:80px;overflow: hidden;text-align: center;}
	.menutab .menucls{position: absolute;right:2;top:2;height: 12px;width:12px;background-image: url('<%=request.getContextPath()%>/images/tab-close.png');background-repeat: no-repeat;}
	.menutab .menucls:hover{background-position: -24px 0px;cursor:pointer;}
	#workcenter {position: absolute;left: 2px;top: 38px;right: 0;bottom: 0;overflow: hidden;/* border-top: 1px solid #F4F5F6 */}
	.nav > li > a {color: #a7b1c2;font-weight: 550;padding: 8px 20px 8px 25px;}
	.nav > li.active > a {color: #ffffff;}
	.nav > li > a:hover,.nav > li > a:focus {background-color: #4E5465;color: white;}
	.nav > li > a i {margin-right: 6px;}
	.nav > li.active {border-left: 4px solid #19aa8d;background: #282B33;}
	.nav.nav-second-level > li.active {border: none;}
	.nav.nav-second-level.collapse[style] {height: auto !important;}
	.arrow {float: right;}
	.fa.arrow:before {content: "\f104";}
	.active > a > .fa.arrow:before {content: "\f107";}
	.img-circle {border-radius: 50%;margin-right: 5px;max-height: 50px;max-width: 50px;}
	#usernav{height: 50px;padding: 0 10px;}
	#usernav >  a:hover,
	#usernav > a:link,
	#usernav > a:visited,
	#usernav >  a:focus {background-color: transparent;color: white;text-decoration: none;}
	.header-selected{background-color:#23262E;}
	.item-selected{background-color: #293846;color: white;}
	.dropdown-menu > li > a:hover,
	.dropdown-menu > li > a:focus,
	.dropdown-submenu:hover > a,
	.dropdown-menu li > a:hover {background-color: #2391ed;background-image: none;color: #FFF;}
	

</style>
<script type="text/javascript">
	var hideMenu = new Array();
</script>
</head>
<body >
	<div id="head-nav" >
		<div class="container-fluid" style="padding-left:0;">
	      <div class="navbar-header header-selected" style="width:200px;">
	        <a class="navbar-brand" href="#" style="color:#FFFFFF"><span>lris系统运营平台</span></a>
	      </div>
	      <div class="nav navbar-nav navbar-right " style="margin-right:20px">
	      	<div class="dropdown" id="usernav">
	      		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img class="img-circle" src="<%=request.getContextPath()%>/images/default_person.png" /><span>${sessionScope.name}</span> <b class="caret"></b></a>
	      		<ul class="dropdown-menu">
			          <li><a href="javascript:gotoPage('<%=request.getContextPath()%>/SysUser/resetpwd','修改密码')">修改密码</a></li>
			          <li class="divider"></li>
			          <li><a href="login/loginout">安全退出</a></li>
			        </ul>
	      	</div>
	      </div>
	    </div>
	</div>
	<div id="cl-wrapper" >
		<div id="side-menu">
			<ul class="nav" id="leftmenu">
			<%
				Map<String,Right> lists=(Map<String,Right>)request.getAttribute("rights");
			%>
				<%@include file="/WEB-INF/include/menu/sys.jsp" %>
				<%-- <%if(lists.containsKey("CUSTOMER.CustomerManager")){%>
					<%@include file="/WEB-INF/include/menu/customer.jsp" %>
				<%}%> --%>
			</ul>
		</div>
		<div id="cotentpanel">
			<div class="row clearfix" id="functab" >
			   <div id="tab0" class="menutab normal now" style="text-align:center;" onclick="showPage(0)">个人中心</div>
			   <%for(int i=1;i<=10;i++){%>
			   <div id="tab<%=i%>" class="menutab normal" style="display:none;">
					<div id="tabtitle<%=i%>" class="menutitle" onclick="showPage(<%=i%>)"></div>
					<div class="menucls" onclick="closePage(<%=i%>)"></div>
			   </div>
			   <%}%>
			</div>
			<div id="workcenter">
			<iframe id="if0" name="if0" src="<%=request.getContextPath() %>/usercenter" width="100%" height="100%" border="0" frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" allowtransparency="yes" ></iframe>
			<%for(int i=1;i<=10;i++){%>
			<iframe id="if<%=i%>" name="if<%=i%>" src="" width="100%" height="100%" border="0" frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" allowtransparency="yes" ></iframe>
			<%}%>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/include/script.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript">
	var totalpage = 10;
	var pages = ["home"];
	var menus = {};
	$(function(){
		for(var i=0;i<hideMenu.length;i++){
			$("#"+hideMenu[i]).hide();
		}

		$("#leftmenu").metisMenu({toggle: true});

		$("#usernav").on({
			'mouseenter':function(){
				$(this).addClass('header-selected');
			},
			'mouseleave':function(){
				$(this).removeClass('header-selected');
			}
		});

		for(var i=1;i<=totalpage;i++){
			pages[i] = "";
		}
	});

	function doHome(){
		
	}

	function hideAllPage(){
		for(var i=0;i<=totalpage;i++){
			$("#if"+i).hide();
		}
	}

	function gotoPage(url,title,id){
		
		if(menus[id]){
			showPage(menus[id]);
			$("#if"+menus[id]).attr({"src":url});
		}else{
			for(var i=1;i<=totalpage;i++){
				if(pages[i]==''){
					hideAllPage();
					$("#if"+i).attr({"src":url});
					$("#if"+i).show();
					$(".now").removeClass('now');
					$("#tabtitle"+i).html(title);
					$("#tab"+i).addClass('now');
					$("#tab"+i).show();
					menus[id] = i;
					pages[i] = id;
					return;
				}
			}
			alert('最多能打开'+totalpage+'个窗口');
		}
	}

	function showPage(index){
		hideAllPage();
		$(".now").removeClass('now');
		$("#tab"+index).addClass('now');
		$("#if"+index).show();
	}

	function closePage(index){
		//增加关闭贷款申请审核页面定时器的功能 开始
		try {
			if("贷款申请" == $("#tabtitle"+index).text()) {
				document.getElementById("if"+index).contentWindow.closeInterval();
			}
		} catch (e) {
			console.log(e);
		}
		//增加关闭贷款申请审核页面定时器的功能 结束
		
		hideAllPage();
		$("#tab"+index).hide();
		$("#if"+index).attr({"src":undefined});
		var id = pages[index];
		pages[index] = '';
		menus[id] = undefined;
		for(var i=totalpage;i>=0;i--){
			if(i!=index && pages[i]!=''){
				showPage(i);
				return;
			}
		}
	}
	</script>
</body>
</html>