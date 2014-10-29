<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆页面</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  
  <body>
	<s:include value="header.jsp"/>
		<s:if test="#session{'user'}!=null">
			<div class="divNavigatorOuterFrame">
				<div class="divNavigatorInnerFrame" style="text-align: right;">
					欢迎<s:property value="#session{'user'}.nickName"/>&nbsp;&nbsp;
				</div>
			</div>
			<div class="divWhtieLine"></div>
		</s:if>
		<s:form action="LoginAction_doLogin" namespace="/" method="post">
			<table>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td colspan="2" class="tdHeader">用户登陆</td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td class="tdFormLabel" width="40%">E-mail:</td>
					<td class="tdFormControl">
						<input type="text" name="email" class="text" value="">
						<font class="fonterror"><br><s:actionerror></s:actionerror></font>
					</td>
				</tr>
				<tr>
					<td class="tdFormLabel" width="40%">密码:</td>
					<td class="tdFormControl">
						<input type="password" name="password" class="text" value="">
						<font class="fonterror"></font>
					</td>
				</tr>
				<tr>
					<td class="tdFormLabel"></td>
					<td class="tdFormControl"><s:submit type="submit" cssClass="btn" value="登陆"></s:submit></td>
				</tr>
			</table>
		</s:form>
  </body>
</html>