<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-权限管理编辑</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<style type="text/css">
ul{width:100%;padding:0;margin: 0;list-style:none;}
ul li{float:left;margin-right: 20px;}
ul>li:first-child{width:100px;}

</style>
</head>
<body>
	<div style="width:100%;z-index: 1;position:fixed;margin-top: 0px;border: 1px;background-color: white;">
		<div>
			<button type="button" onclick="check();" class="btn btn-primary btn-sm mr10">
				<i class="fa fa-save"></i><span class="ml10">保 存</span>
			</button>
			<button type="button" onclick="back();" class="btn btn-primary btn-sm mr10">
				<i class="fa fa-reply"></i><span class="ml10">返 回</span>
			</button>
			<font style="font-size: 20px;vertical-align: middle;font-weight: 700;">${obj.f02}</font>
		</div>
	</div>
	
	<div class="table-responsive">
		<form class="form-horizontal" style="margin-top: 30px;"  role="form"
			action="<%=rootpath%>/Role/rightupdate " method="post"
			id="form1">
			<input type="hidden" name="id" value="${param.id}" />
			
			<c:forEach items="${rightF02}" var="rights" varStatus="sta">
				<div class="table-responsive">
					<div class="panel panel-default">
						<div class="panel-heading">
							<leader style="font-weight:700;">${sta.count}.${rights.key} </leader>
							<label class="radio-inline"> <input type="radio"
								name="_radio=${sta.index+1}"
								onclick="selectAll('_module=${sta.index+1}')" /> 全选
							</label> <label class="radio-inline"> <input type="radio"
								name="_radio=${sta.index+1}"
								onclick="unselectAll('_module=${sta.index+1}')" /> 全不选
							</label> <label class="radio-inline"> <input type="radio"
								name="_radio=${sta.index+1}"
								onclick="revertAll('_module=${sta.index+1}')" /> 反选
							</label>
						</div>
						<div class="panel-body">
							<div id="_module=${sta.index+1}">
							<c:forEach items="${rights.value}" var="m1" varStatus="vare">
								<div style="display:block;">
								<ul class="clearfix">
								<li><label>${m1.key}:</label></li>
								<c:forEach items="${m1.value}" var="objs" varStatus="varc">
									<li><label ${objs.roleId==obj.f01?'style="color:#FF0000;"':''}><input type="checkbox" name="rightIds" id="inlineCheckbox1" value="${objs.privilegeId}" ${objs.roleId==obj.f01?'checked':''} />${objs.f04}</label> </li>
								</c:forEach>
								</ul>
								</div>
							</c:forEach>
							</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</form>
	</div>
	<%@include file="/WEB-INF/include/script.jsp"%>
	<script type="text/javascript">
		function selectAll(id) {
			var inputs = document.getElementById(id).getElementsByTagName(
					"input");
			for (var i = 0; i < inputs.length; i++) {
				var input = inputs[i];
				if (input.type != 'checkbox') {
					continue;
				}
				input.checked = true;
			}
		}
		function unselectAll(id) {
			var inputs = document.getElementById(id).getElementsByTagName(
					"input");
			for (var i = 0; i < inputs.length; i++) {
				var input = inputs[i];
				if (input.type != 'checkbox') {
					continue;
				}
				input.checked = false;
			}
		}
		function revertAll(id) {
			var inputs = document.getElementById(id).getElementsByTagName(
					"input");
			for (var i = 0; i < inputs.length; i++) {
				var input = inputs[i];
				if (input.type != 'checkbox') {
					continue;
				}
				input.checked = !input.checked;
			}
		}
		function check() {
			$("#form1").submit();
		}

		function back() {
			window.history.go(-1);
		}
	</script>
</body>
</html>