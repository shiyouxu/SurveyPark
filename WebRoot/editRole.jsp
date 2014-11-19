<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title></title>
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
	<s:include value="/header.jsp"/>    
	<s:form>
		<s:textfield name="roleName"/>	
		<s:textfield name="roleValue"/>	
		<s:textfield name="roleDesc"/>	
		<!-- 左侧列表:Role.rights集合绑定，角色拥有的权限列表 -->
		<s:select list="rights" id="left" listKey="id" listValue="rightName" multiple="true"/>
		
		<!-- 右侧列表：同roleAction.noOwnRights集合绑定，角色没有的权限列表 -->
		<s:select list="noOwnRights" id="right" listKey="id" listValue="rightName" multiple="true"/>
		
	</s:form>
  </body>
</html>
