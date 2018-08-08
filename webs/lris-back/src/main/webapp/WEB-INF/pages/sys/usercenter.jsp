<!DOCTYPE>
<html lang="zh-cn">
<head>
<title>lris-个人中心</title>
<%@include file="/WEB-INF/include/meta.jsp"%>
<style type="text/css">
	.table th, .table td {text-align: left; height:32px; font-size:12px}
	.btn,btn-sm{
	  font-size:20px;
	  background-color:#B0E0E6;
	}
</style>
</head>
<body >
<div >
	
	<div>
	<table class="table table-striped" >
	<tr>
		<th>平台总数据</th>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td> 分期成功总金额（元）：<fmt:formatNumber value="${plantdata.totalSuccessMoney}" pattern="###,###.##"/>元</td>
		<td>合同金额（元）：<fmt:formatNumber value="${plantdata.totalContractMoney}" pattern="###,###.##"/>元</td>
		<td>分期成功笔数：${plantdata.totalSuccessCount}笔</td>
	</tr>
	<tr>
		<td>分期失败总金额（元）：<fmt:formatNumber value="${plantdata.totalFailMoney}" pattern="###,###.##"/>元</td>
		<td>&nbsp;</td>
		<td>分期失败笔数：${plantdata.totalFailCount}笔</td>
	</tr>
	<tr>
		<td>总分期金额（元）：<fmt:formatNumber value="${plantdata.totalMoney}" pattern="###,###.##"/>元</td>
		<td>&nbsp;</td>
		<td>总分期笔数：${plantdata.totalCount}笔</td>
	</tr>
	<tr>
		<td>申请分期总人数：${plantdata.totalPerson}人</td>
		<td>&nbsp;</td>
		<td>总分期成功率：<fmt:formatNumber value="${plantdata.totalSuccessRate}" pattern="##.##%"/></td>
	</tr>
	<tr>
		<td>审核平均用时：${plantdata.allcosttime}</td>
		<td>&nbsp;</td>
		<td>审核异常单数：${plantdata.allunusual}</td>
	</tr>
	<tr>
		<th>当月分期数据（截止到当前日，实时更新）</th>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>分期成功总金额（元）：<fmt:formatNumber value="${plantdata.monthSuccessMoney}" pattern="###,###.##"/>元</td>
		<td>合同金额（元）：<fmt:formatNumber value="${plantdata.monthContractMoney}" pattern="###,###.##"/>元</td>		
		<td>分期成功笔数：${plantdata.monthSuccessCount}笔</td>
	</tr>
	<tr>
		<td>分期失败总金额（元）：<fmt:formatNumber value="${plantdata.monthFailMoney}" pattern="###,###.##"/>元</td>
		<td>&nbsp;</td>
		<td>分期失败笔数：${plantdata.monthFailCount}笔</td>
	</tr>
	<tr>
		<td>总分期金额（元）：<fmt:formatNumber value="${plantdata.monthMoney}" pattern="###,###.##"/>元</td>
		<td>&nbsp;</td>
		<td>总分期笔数：${plantdata.monthCount}笔</td>
	</tr>
	<tr>
		<td>申请分期总人数：${plantdata.monthPerson}人</td>
		<td>&nbsp;</td>
		<td>总分期成功率：<fmt:formatNumber value="${plantdata.monthSuccessRate}" pattern="##.##%"/></td>
	</tr>
	<tr>
		<td>审核平均用时：${plantdata.monthcosttime}</td>
		<td>&nbsp;</td>
		<td>审核异常单数：${plantdata.monthunusual}</td>
	</tr>
	</table>
	
	</div>
</div>	
<div style="margin:0 auto;border:1px;width:300px;height:50px">
  <span class="btn btn-sm" id="2015" onclick="change(2015)" >2015</span>
  <span class="btn btn-sm" id="2016" onclick="change(2016)" >2016</span>
  <span class="btn btn-sm" id="2017" onclick="change(2017)" >2017</span>
</div>	
<div id="container1" style="min-width:700px;height:400px"></div>
<div id="container2" style="min-width:700px;height:400px"></div>
<%@include file="/WEB-INF/include/script.jsp" %>
<script src="<%=rootpath%>/js/highcharts/exporting.js"></script> 
<script src="<%=rootpath%>/js/highcharts/highcharts.js"></script> 

<script type="text/javascript">
	function toUrl(url,title,id){
		parent.gotoPage(url,title,id);
	}
	
	$(function () {
		var months = "${plantdata.months}".substring(1,"${plantdata.months}".length-1).split(",");
		
	    $('#container1').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '最近12个月分期一览表'
	        },
	        xAxis: {
	            categories:months
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '金额 (元)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.2f} 元</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true,
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '分期总金额',
	            data: ${eachmonthtotal},
	            color: '#1C86EE'

	        }, {
	            name: '贷款成功金额',
	            data: ${eachmonthsuccess},
				color: '#71C671'

	        }, {
	            name: '贷款失败金额',
	            data: ${eachmonthfail},
	            color: '#CD0000'
	        }]
	    });
	    
	    $('#container2').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '最近12月回款率'
	        },
	        xAxis: {
	            categories:months
	        },
	        yAxis: [{
	            min: 0,
	            title: {
	                text: '金额（元）'
	            }
	        }],
	        legend: {
	            shadow: false
	        },
	        tooltip: {
	            shared: true
	        },
	        plotOptions: {
	            column: {
	                grouping: false,
	                shadow: false,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '应还金额',
	            color: 'rgba(165,170,217,1)',
	            data: ${shouldrepay},
	            pointPadding: 0.3,
	            tooltip: {
	                valueSuffix: ' 元',
	                valueDecimals: 2
	            }
	        }, {
	            name: '已还金额',
	            color: 'rgba(126,86,134,.9)',
	            data:  ${repayed},
	            pointPadding: 0.4,
	            tooltip: {
	                valueSuffix: ' 元',
	                valueDecimals: 2
	            }
	        },{
	            name: '还款率',
	            data:  ${repayrate},
	            pointPadding: 100,
	            showInLegend: false,
	            tooltip: {
	                valueSuffix: '%',
	                valueDecimals: 2
	                
	            }
	        }]
	    });
	});	
	
	function change(year) {
		$("#2015").css({"background-color":"#B0E0E6"});
		$("#2016").css({"background-color":"#B0E0E6"});
		$("#2017").css({"background-color":"#B0E0E6"});
		var val=document.getElementById(year);
		$(val).css({"background-color":"red"});
		$.ajax({
			url:'<%=rootpath%>/usercenterexpand',
			type:'post',
			dataType:'json',
			data:{"year":year},
			success:function(data) {
				 var months = data.obj.months.substring(1,data.obj.months.length-1).split(",");
				
			    $('#container1').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: year+'分期一览表'
			        },
			        xAxis: {
			        	categories:months
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: '金额 (元)'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.2f} 元</b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true,
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            name: '分期总金额',
			            data: data.obj.eachmonthtotal,
			            color: '#1C86EE'

			        }, {
			            name: '贷款成功金额',
			            data: data.obj.eachmonthsuccess,
						color: '#71C671'

			        }, {
			            name: '贷款失败金额',
			            data: data.obj.eachmonthfail,
			            color: '#CD0000'
			        }]
			    });
			    
			    $('#container2').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: year+'回款率'
			        },
			        xAxis: {
			            categories:months
			        },
			        yAxis: [{
			            min: 0,
			            title: {
			                text: '金额（元）'
			            }
			        }],
			        legend: {
			            shadow: false
			        },
			        tooltip: {
			            shared: true
			        },
			        plotOptions: {
			            column: {
			                grouping: false,
			                shadow: false,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            name: '应还金额',
			            color: 'rgba(165,170,217,1)',
			            data: data.obj.shouldrepays,
			            pointPadding: 0.3,
			            tooltip: {
			                valueSuffix: ' 元',
			                valueDecimals: 2
			            }
			        }, {
			            name: '已还金额',
			            color: 'rgba(126,86,134,.9)',
			            data:  data.obj.repayeds,
			            pointPadding: 0.4,
			            tooltip: {
			                valueSuffix: ' 元',
			                valueDecimals: 2
			            }
			        },{
			            name: '还款率',
			            data:  data.obj.repayrates,
			            pointPadding: 100,
			            showInLegend: false,
			            tooltip: {
			                valueSuffix: '%',
			                valueDecimals: 2
			                
			            }
			        }]
			    });
			}
		});	
	}
</script>
</body>
</html>