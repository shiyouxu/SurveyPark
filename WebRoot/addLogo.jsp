<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加logo的页面</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
	<s:include value="/header.jsp"></s:include>    
	<table>
		<tr>
			<td>增加Logo：</td>
		</tr>
		<tr>
			<td style="vertical-align: top;"> 
				<table>
					<tr>
						<td>
							<s:form action="SurveyAction_doAddLogo" method="post" enctype="multipart/form-data">
							<s:hidden name="sid"/>
							<table>
								<tr>
									<td class="tdFormLabel">选择Logo：</td>
									<td class="tdFormControl">
										<s:file name="logoPhoto"/>
										<%-- <s:fielderror fieldName="logoPhoto"/> --%>
									</td>
								</tr>							
								<tr>
									<td class="tdFormLabel"></td>
									<td class="tdFormControl"><s:submit cssClass="btn" value="确定"/></td>
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
