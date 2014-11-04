<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设计调查</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript" src="jquery-1.7.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$("a[href*=delete]").click(function(){
				return confirm("删除该项");
			});
		});
	</script>
  </head> 
  
  <body>
  	<s:include value="header.jsp"/>
  	<s:set var="sId" value="id"/>
  	<table>
  		<tr>
  			<td colspan="2" class="tdWhitedLine"></td>
  		</tr>
  		<tr>
  			<td colspan="2" class="tdHeader">设计调查</td>
  		</tr>
  		<tr>
  			<td colspan="2" class="tdWhitedLine"></td>
  		</tr>
  		<tr>
  			<td class="tdSHeaderL">
  				<%-- <s:if test="logoPhotoExists()">
  					<img src='%{logoPhotoPath}' width="50px" height="25px">
  				</s:if> --%>
			  	<!-- 调查标题 -->
				<s:property value="title"/>
  			</td>
  			<td class="tdSneaderR">
  				增加logo&nbsp;
  				<s:a href="SurveyAction_editSurvey?sid=%{#sId}">编辑调查</s:a>&nbsp;
  				<s:a href="PageAction_toAddPage?sid=%{#sId}">增加页</s:a>&nbsp;
  			</td>
  		</tr>
		<tr>
			<td colspan="2" style="text-align: left; vertical-align: top;">
				<table>
					<tr>
						<td width="30px"></td>
						<td width="*">
							<table>
								<!-- 输出页面集合 -->
								<s:iterator value="pages" var="p">
								<s:set var="pId" value="#p.id"/>
								<tr>
									<td>
										<table>
											<tr>
												<!-- 页面标题 -->
												<td class="tdPNeaderL"><s:property value="#p.title"/></td>
												<td class="tdPheaderR">
													<s:a href="PageAction_editPage?sid=%{#sId}&pid=%{#pId}">编辑页标题</s:a>&nbsp;
													移动/复制页&nbsp;
													<s:a href="QuestionAction_toSelectQuestionType?sid=%{#sId}&pid=%{#pId}">增加问题</s:a>&nbsp;
													<s:a href="PageAction_deletePage?sid=%{#sId}&pid=%{#pId}">删除页</s:a>&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table>
											<tr>
												<td width="30px"></td>
												<td width="*">
													<table>
														<tr>
															<td>
																<table>
																	<!-- 迭代问题集合 -->
																	<s:iterator value="#p.questions" var="q">
																	<s:set var="qId" value="#q.id"/>
																	<tr>
																		<!-- 问题题干 -->
																		<td class="tdQHeaderL"><s:property value="#q.title"/></td>
																		<td class="tdQHeaderR">
																			<s:a href="QuestionAction_editQuestion?sid=%{#sId}&pid=%{#pId}&qid=%{#qId}">编辑问题</s:a>&nbsp;
																			<s:a href="QuestionAction_deleteQuestion?sid=%{#sId}&qid=%{#qId}">删除问题</s:a>&nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" style="text-align: left;color:black;background-color: white">
																			<!-- 处理选项输出 -->
																			<s:set var="qt" value="#q.questionType"/>
																			<!-- 0,1,2,3 -->
																				<s:if test="#qt lt 4">
																					<s:iterator value="#q.optionArr">
																						<input type="<s:property value="#qt<2?'radio':'checkbox'"/>">
																						<s:property/>
																						<!-- 是否携带br -->
																						<s:if test="#qt == 1 || #qt == 3"><br></s:if>
																					</s:iterator>
																					<s:if test="#q.other">
																						<!-- 是否带其他 -->
																						<input type="<s:property value="#qt<2?'radio':'checkbox'"/>">其他
																						<!-- 其他项样式 -->
																						<s:if test="#q.otherStyle == 1">
																							<input type="text">
																						</s:if>
																						<s:elseif test="#q.otherStyle == 2">
																							<!-- 下拉列表 -->
																							<select>
																								<s:iterator value="#q.otherSelectOptionArr">
																									<option><s:property/></option>
																								</s:iterator>
																							</select>
																						</s:elseif>		
																					</s:if>
																				</s:if>
																				
																				<!-- 4,5 -->
																				
																				<!-- 下拉列表 -->
																				<s:if test="#qt ==4 ">
																					<select>
																						<s:iterator value="#q.optionArr">
																							<option><s:property/></option>
																						</s:iterator>
																					</select>
																				</s:if>
																				<s:elseif test="#qt == 5">
																					<input type="text">
																				</s:elseif>
																				
																				
																				<!-- 6,7,8：矩阵式问题 -->
																				<s:elseif test="#qt > 5">
																					<table>
																						<!-- 列头 -->
																						<tr>
																							<td></td>
																							<s:iterator value="#q.matrixColTitleArr">
																								<td><s:property/></td>
																							</s:iterator>
																						</tr>
																						<!-- 输出n多行 -->
																						<s:iterator value="#q.matrixRowTitleArr">
																							<tr>
																								<td><s:property/></td>
																								<s:iterator value="#q.matrixColTitleArr">
																									<td>
																										<s:if test="#qt == 6"><input type="radio"></s:if>
																										<s:if test="#qt == 7"><input type="checkbox"></s:if>
																										<s:if test="#qt == 8">
																											<select>
																												<s:iterator value="#q.matrixSelectOptionArr">
																													<option><s:property/></option>
																												</s:iterator>
																											</select>	
																										</s:if>	
																									</td>
																								</s:iterator>
																							</tr>
																						</s:iterator>
																					</table>
																				</s:elseif>
																			</td>
																		</tr>
																	</s:iterator>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								</s:iterator>			
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
  	</table>

  </body>
</html>
