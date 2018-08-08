<%@page import="com.lris.ain.core.query.Page"%>
<div id="pagepanel">
<%
Page pages = (Page)request.getAttribute("page");
int currentPage = pages.getPageIndex();
int totalPage = pages.getTotalPage();
int totalCount = pages.getTotalCount();
%>
<div class='row col-sm-12'>
<div class="col-sm-3 col-sm-offset-1" style="margin-top: 25px;text-align:right;">第<%=currentPage%>页/共<%=totalPage%>页（共<%=totalCount%>条记录）</div>
<div class='col-sm-8'>
<nav>
<ul class='pagination'>
<%if(currentPage>1){%>
<li><a href='#' onclick='gotoPage(1)'>&laquo;&laquo;</a></li>
<li><a href='#' onclick='gotoPage(<%=currentPage-1%>)'>&laquo;</a></li>
<%}else{%>
<li class='disabled'><a>&laquo;&laquo;</a></li>
<li class='disabled'><a>&laquo;</a></li>
<%}%>

<%
int pagingBarCount = 7;
for(int i=currentPage-3;pagingBarCount > 0;i++, pagingBarCount--){
	if (i > totalPage) {
		break;
	}
	if (i < 1) {
		i = 1;
	}
	if (currentPage == i) {
	%><li class='active'><a><%=i%></a></li><%
	} else {
	%><li><a href='#' onclick='gotoPage(<%=i%>)'><%=i%></a></li><%
	}
}
%>

<%if(currentPage<totalPage){%>
<li><a href='#' onclick='gotoPage(<%=currentPage+1%>)'>&raquo;</a></li>
<li><a href='#' onclick='gotoPage(<%=totalPage%>)'>&raquo;&raquo;</a></li>
<%}else{%>
<li class='disabled'><a>&raquo;</a></li>
<li class='disabled'><a>&raquo;&raquo;</a></li>
<%}%>
</ul>
</nav>
</div>
</div>
</div>
<script type="text/javascript">
function gotoPage(i){
	$("#totalCount").val(<%=pages.getTotalCount()%>);
	$("#pageIndex").val(i);
	$("form")[0].submit();
}
</script>