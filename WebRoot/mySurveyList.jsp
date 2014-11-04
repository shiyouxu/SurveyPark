<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的调查</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript" src="jquery-1.7.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$("a[href*=delete]").click(function(){
				$(this).attr("disabled","disabled");
			});
		});
	</script>
  </head>
  <body>
  	<s:include value="/header.jsp"></s:include>
  	<s:if test="mySurveys.isEmpty() == true">目前您没有任何调查项!</s:if>
  	<s:else>
  		<table>
  			<tr>
  				<td colspan="10" style="height: 5px;"></td>
  			</tr>
  			<tr>
  				<td colspan="10" class="tdHeader">我的调查</td>
  			</tr>
  			<tr>
  				<td colspan="10" style="height: 5px;"></td>
  			</tr>
  			<tr>
  				<td class="tdListHeader">ID</td>
  				<td class="tdListHeader">调查标题</td>
  				<td class="tdListHeader">创建时间</td>
  				<td class="tdListHeader">状态</td>
  				<td class="tdListHeader">设计</td>
  				<td class="tdListHeader">收集信息</td>
  				<td class="tdListHeader">分析</td>
  				<td class="tdListHeader">打开/关闭</td>
  				<td class="tdListHeader">清楚调查</td>
  				<td class="tdListHeader">删除</td>
  			</tr>
  			<s:iterator id="ms" value="mySurveys">
  				<s:set var="sId" value="id"/>
  				<tr>
  					<td><s:property value="#ms.id"/></td>
  					<td><s:property value="#ms.title"/></td>
  					<td><s:date name="#ms.createTime" format="MM/dd/yy HH:mm"/></td>
  					<td>
  						<s:if test="closed">关闭</s:if>
  						<s:else>开放</s:else>
  					</td>
  					<td><s:a href="SurveyAction_designSurvey?sid=%{#sId}" namespace="/" cssClass="aList">设计</s:a></td>
   					<td><s:a href="CollectionSurveyAction?sid=%{#sId}" namespace="/" cssClass="aList">收集信息</s:a></td>
   					<td><s:a href="SurveyAction_analyzeSurvey?sid=%{#sId}" namespace="/" cssClass="aList">分析</s:a></td>
   					<td><s:a href="SurveyAction_toggleStatus?sid=%{#sId}" namespace="/" cssClass="aList">打开/关闭</s:a></td>
   					<td><s:a href="SurveyAction_clearAnswers?sid=%{#sId}" namespace="/" cssClass="aList">清除调查</s:a></td>
   					<td><s:a href="SurveyAction_deleteSurvey?sid=%{#sId}" namespace="/" cssClass="aList">删除</s:a></td>
  				</tr>
  			</s:iterator>
  		</table>
  	</s:else>
  </body>
</html>
