<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>修改用户授权</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript" src='<s:url value="/jquery-1.7.1.js"></s:url>'></script>
	<script type="text/javascript">
		$(function(){
			$('#one1').click(function(){
				var size = $('#left>option:selected').size();
				if(size != 0){
					$('#left > option:selected').appendTo($('#right'));			
				}
				else{
					$('#left > option:first-child').appendTo($('#right'));
				}
			});
			$('#all1').click(function(){
				$('#left > option').appendTo($('#right'));
			});
			$('#one2').click(function(){
				var size = $('#right > option:selected').size();
				if(size != 0){
					$('#right > option:selected').appendTo($('#left'));
				}else{
					$('#right > option:first-child').appendTo($('#left'));
				}
			});				
			$('#all2').click(function(){
				$('#right > option').appendTo($('#left'));
			});
		});
	</script>
  </head>
  <body>
	<s:include value="/header.jsp"/>
  	<table>
  		<tr>
  			<td class="tdHeader">修改用户权限:</td>
  		</tr>
  		<tr>
  			<td style="vertical-align: top;">
  				<table>
  					<tr>
  						<td>
  							<s:form action="UserAuthorizeAction_updateAuthorize" namespace="/" method="post">
  							<s:hidden name="id"/>
  							<table>
  								<tr>
  									<td class="tdFormLabel" width="200px">email</td>
  									<td class="tdFormControl"><s:textfield name="email" cssClass="text" disabled="true"/></td>
  								</tr>
  								<tr>
  									<td class="tdFormLabel" width="200px">nickName</td>
  									<td class="tdFormControl"><s:textfield name="nickName" cssClass="text" disabled="true"/></td>
  								</tr>
  								<tr>
  									<td class="tdFormLabel" colspan="2">
  										<table>
  											<tr>
  												<td width="45%" align="right">
  													<s:select list="roles"
  														name="ownRoleIds"
  														id="left"
  														listKey="id"
  														listValue="roleName"
  														multiple="true"
  														cssClass="width:100px"
  														size="15"
  													></s:select>
  												</td>
  												<td width="10%" valign="middle" align="center">
  													<input type="button" id="one1" value="&gt;" class="btn"></input>
  													<input type="button" id="one2" value="&lt;" class="btn"></input>
  													<input type="button" id="all1" value="&gt;&gt;" class="btn"></input>
  													<input type="button" id="all2" value="&lt;&lt;" class="btn"></input>
  												</td>
  												<td width="45%" align="left">
  													<s:select list="noOwnRoles"
  														id="right"
  														name="noOwnRoleIds"
  														listKey="id"
  														listValue="roleName"
  														multiple="true"
  														size="15"
  														cssClass="width:100px"
  													></s:select>
  												</td>
  											</tr>
  										</table>
  									</td>
  								</tr>
  								<tr>
  									<td class="tdFormLabel"></td>
  									<td class="tdFormLabel"><s:submit value="确定" cssClass="btn"></s:submit></td>
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





