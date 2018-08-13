<!DOCTYPE>
<html lang="zh-cn">
<head>
	<title>lris</title>
	<%@include file="/WEB-INF/include/meta.jsp"%>
	
    <style type="text/css">
	.bg{background: url('<%=rootpath%>/images/login-blurry-bg.jpg')}
	.middle-box {
	  width: 420px;
	  position: absolute;
	  top: 50%;
	  left: 50%;
	  margin-top: -150px;
	  margin-left: -210px;
	  z-index: 100;
	}

	.login_header{
		padding: 1px 20px;
	  	background: #11F273;
	 	border-bottom: 1px solid #2082D5;
	 	color:#FFFFFF;
	 	
	}

	.login_content{
		background-color:#F9FCFD; 
		padding-top: 10px;
		border: 1px solid #F9FCFD;
		border-top:none;
	}

	.login_label{
		color:#;
	}	
	#codeimg{height: 30px;width: auto;margin-left: 15px;margin-bottom: 5px;}
	#code{width: 80px;}
    </style>
</head>
<body class="bg">
	<div class="middle-box ">
		<div class="login_header text-center">
			<h3>欢迎使用lris系统运营平台</h3>
		</div>
		<div class="login_content">
		<form action="<%=rootpath%>/login/check" method="post" onsubmit="return onSubmit()" class="form-horizontal" role="form">
			<input type="hidden" name="sp" id="sp">
			<div class="form-group">
			    <label for="username" class="col-sm-3 control-label">用户名</label>
			    <div class="col-sm-8">
			      <input class="form-control" id="username" name="username" placeholder="用户名" value="${param.username}" type="text"  onblur="accountCheck();" />
			    </div>
			</div>
			<div class="form-group">
			    <label for="password" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
			    <div class="col-sm-8">
			      <input class="form-control" id="password" placeholder="密码" type="password"  onblur="passwordCheck();" />
			    </div>
			</div>
			<div class="form-group">
			    <label for="code" class="col-sm-3 control-label">验证码</label>
			    <div class="col-sm-8">
			      <input class="form-control" id="code" name="code" placeholder="验证码" type="text" autocomplete="off" />
			      <img id="codeimg" alt="验证码" src="<%=rootpath%>/login/getcode" onclick="fleshCode()">
			    </div>
			</div>
			<div class="col-sm-offset-3 col-sm-8">
				<p style="height:25px;" class="ml100">
					<font color="red" size="2" id="error">${param.errormsg}</font>
				</p>
			</div>
			<div class="form-group">
			    <div class="col-sm-offset-3 col-sm-8">
			      <button type="submit" class="btn btn-success btn-block" name="login">登 录</button>
			    </div>
			 </div>
			</form>
		</div>
	</div>
	<%@include file="/WEB-INF/include/script.jsp" %>
	<script src="<%=request.getContextPath()%>/js/encrypt.js"></script> 
	<script type="text/javascript">



		var isNull = /^[\s]{0,}$/;
		var loginName = /^[a-z]([\w]*)$/i;
		function accountCheck() {
			var val = $("#username").val();
			var p = $("#error");
			if(isNull.test(val)){
				p.html("用户名不能为空");
				return false;
			}
			return true;
		}
		function passwordCheck() {
			var val = $("#password").val();
			var p = $("#error");
			if (isNull.test(val)) {
				p.html("密码不能为空");
				return false;
			}
			$("#sp").val(base64encode(val));
			return true;
		}
		
		function onSubmit() {
			return accountCheck() && passwordCheck() ;
		}
		function fleshCode(){
			$("#codeimg").attr("src","");
			$("#codeimg").attr("src","<%=rootpath%>/login/getcode");
		}

		if(self!=top){
			top.parent.location.href='<%=rootpath%>/login/tologin';
		}
		
	</script>
	

</body>
</html>
