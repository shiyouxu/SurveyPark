<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册页面</title>
	<!-- <link rel="stylesheet" type="text/css" href="styles.css"> -->

  </head>
  <body>
	<s:include value="header.jsp"/>
	<s:form action="RegAction_doReg" namespace="/" method="post">
		<table>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td colspan="2" class="tdHeader">注册新用户</td>
			</tr>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td class="tdFormLable">E-mail:</td>
				<td class="tdFormControl">
					<s:textfield name="email" cssClass="text"/>
					<font class="fonterror"><s:fielderror><s:param>email</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLable">密&nbsp;&nbsp;码:</td>
				<td class="tdFormControl">
					<s:password name="password" cssClass="text"/>
					<font class="fonterror"><s:fielderror><s:param>password</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLable">确认密码：</td>
				<td class="tdFormControl"><s:password name="confirmPassword" cssClass="text"/></td>
			</tr>
			<tr>
				<td class="tdFormLable">昵称:</td>
				<td class="tdFormControl">
					<s:textfield name="nickname" cssClass="text"/>
					<font class="fonterror"><s:fielderror><s:param>nickname</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLable"></td>
				<td class="tdFormControl"><s:submit cssClass="btn" value="确定"/></td>
			</tr>
		</table>
	</s:form>
  </body>
</html>
