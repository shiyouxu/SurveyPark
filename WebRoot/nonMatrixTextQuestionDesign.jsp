<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>非矩阵式文本框</title>
     <!--TODO 5  -->
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
	<s:include value="/header.jsp"/>
	<s:form action="QuestionAction_saveOrUpdateQuestion.action" method="post">
		<s:hidden name="id"/>	
		<s:hidden name="questionType"/>
  		<s:hidden name="pid"/>
  		<s:hidden name="sid"/>  
		<table>
			<tr>
				<td colspan="2" class="tdQHeaderL">非矩阵型问题设计：</td>
			</tr>
			<tr>
				<td width="35%" style="text-align: right;">问题标题：</td>
				<td width="*" style="text-align: left;"><s:textfield name="title" cssClass="text"/></td>
			</tr>
			
			<tr>
				<td style="text-align: right;"></td>
				<td width="*" style="text-align: left;"><input type="submit" name="ok" value="确定" class="btn"></td>
			</tr>
		</table>
	</s:form>
  

  </body>
</html>
