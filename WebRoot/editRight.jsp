<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>保存/更新权限</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
	<s:include value="/header.jsp">
		<table>
			<tr>
				<td class="tdHeader">保存/更新权限:</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<table>
						<tr>
							<td>
							<s:form action="RightAction_saveOrUpdateRight" method="post">
							<s:hidden name="id"/>
							<table>
								<tr>
									<td class="tdFormLabel">权限名称：</td>
									<td class="tdFormControl"><s:textfield name="rightName" cssClass="text"/></td>
								</tr>						
								<tr>
									<td class="tdFormLabel">权限Url：</td>
									<td class="tdFormControl"><s:textfield name="rightUrl" cssClass="text"/></td>
								</tr>
								<tr>
									<td class="tdFormLabel">权限位：</td>
									<td class="tdFormControl"><s:textfield name="rightPos" readonly="readonly" cssClass="text"/></td>
								</tr>
								<tr>
									<td class="tdFormLabel">权限码：</td>
									<td class="tdFormControl"><s:textfield name="rightCode" readonly="readonly" cssClass="text"/></td>
								</tr>
								<tr>
									<td class="tdFormLabel">公共资源：</td>
									<td class="tdFormControl">
										<s:checkbox name="common"/>
									</td>
								</tr>
								<tr>
									<td class="tdFormLabel">权限描述：</td>
									<td class="tdFormControl"><s:textarea name="rightDesc" cssClass="text" rows="10" cols="10"/></td>
								</tr>
								<tr>
									<td class="tdFormLabel"></td>
									<td class="tdFormControl"><s:submit value="确定"/></td>
								</tr>
							</table>
							</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>	
  
		</table>	
	</s:include>
  </body>
</html>
