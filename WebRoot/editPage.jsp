<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加/编辑内容</title>
    
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  
  <body>
	<s:include value="/header.jsp"/>
	<table>
		<tr>
			<td class="tdHeader">增加/编辑页内容:</td>
		</tr>
		<tr>
			<td style="vertical-align: top;">
				<table>
					<tr>
						<td>
							<s:form action="PageAction_saveOrUpdatePage" namespace="/" method="post">
							<s:hidden name="id"/>
							<s:hidden name="sid"/>
							<%-- <s:hidden name="orderno"/> --%>
								<table>
									<tr>
										<td class="tdFormLabel">页面标题:</td>
										<td class="tdFormControl"><s:textfield name="title" cssClass="text"/></td>
									</tr>
									<tr>
										<td class="tdFormLabel">页面描述:</td>
										<td class="tdFormControl"><s:textarea name="description" cssClass="text" cols="20" rows="5"/></td>
									</tr>
									<tr>
										<td class="tdFormLabel"></td>
										<td class="tdFormControl"><s:submit value="确定" cssClass="btn"/></td>
									</tr>
								</table>
							</s:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
  </body>
</html>
