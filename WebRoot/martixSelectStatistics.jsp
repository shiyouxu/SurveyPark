<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>矩阵型问题统计</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
	 <s:include value="/header.jsp"></s:include>
	 <s:set var="qt" value="qsm.question.questionType"/>
	<table>
		<tr>
			<td colspan="2" class="tdWhiteLine"></td>
		</tr>	
		<tr>
			<td colspan="2" class="tdHeader">矩阵式下拉列表题型调查结果分析:</td>
		</tr>	
		<tr>
			<td colspan="2" class="tdWhiteLine"></td>
		</tr>	
		<tr>
			<td colspan="2" class="tdHeader"><s:property value="qsm.question.title"/></td>
		</tr>	
		<tr>
			<td colspan="2" class="tdWhiteLine"></td>
		</tr> 
		<tr>
			<td colspan="2" style="text-align: left;display: inline;">
				<!-- 创建左上角颜色块，用不同的颜色代表不同的下拉列表选项 -->
				<s:iterator value="qsm.question.matrixSelectOptionArr" status="potst">
					<!-- 对文本框进行修饰，产生颜色块的特效 -->
					<input type="text" readonly="readonly" style="border:1px solid blue;width :12px;height:12px;background-color: <s:property value="colors[#optst.index]"/>"><s:property/>
				</s:iterator>
			</td>
		</tr>
    	<tr>
    		<td>
    			<table>
    				<tr>
    					<td width="30px"></td>
    					<s:iterator value="qsm.question.matrixColTitleArr">
    						<!-- 集合中每个元素都是字符串，直接通过s:property方式输出 -->
    						<td><s:property/></td>
    					</s:iterator>
    				</tr>
    				<!-- 遍历行标题数组 -->
    				<s:iterator var="row" value="qsm.question.matrixRowTitleArr" status="rst">
    					<tr>
    						<td><s:property/></td>
    						<!-- 遍历列表数组 -->
    						<s:iterator var="col" value="qsm.question.matrixColTitleArr" status="cst">
    							<td style="text-align: left;">
    								<!-- 遍历下拉选项数组 -->
    								<s:iterator var="option" value="qsm.question.matrixSelectOptionArr" status="optst">
			    						<input type="text" style="border:1px solid <s:property value='colors[#optst.index]'/>;
			    							background-color: <s:property value='colors[#optst.index]'/>;
			    							width: <s:property value='getPercent(#rst.index,#cst.index,#optst.index)'/>px;
			    							height:12px" readonly="readonly">
    									<!-- 动态调用action中的方法 -->
    									<s:property value="getScale(#rst.index,#cst.index,#optst.index)"/>
    								</s:iterator>
    							</td>
    						</s:iterator>
    					</tr>
    				</s:iterator>
    			</table>
    		</td>
    	</tr>
		<tr>
			<td colspan="2" style="text-align: left;">共有&nbsp;<s:property value="qsm.count"/>&nbsp;人参与问卷 </td>
		</tr>
	</table>   
  </body>
</html>
