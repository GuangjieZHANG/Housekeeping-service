wdawsdawsdawsdawsdwsdawsdawsdawsdawsdawsdawsdaw<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="projectmanage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="<s:property value="pm.numPerPage"/>" />
	<input type="hidden" name="username" value="<s:property value="username"/>" />

</form>
<form onsubmit="return dwzSearch(this, 'dialog');" action="SearchProject_List" method="post">
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			   <td align="left">
					项目编号:<s:textfield theme="simple" name="searchId" ></s:textfield>
				</td>
				<td align="left">
					项目名称:<s:textfield theme="simple" name="searchName" ></s:textfield>
				</td>
				<td align="left">
					开始时间:<s:textfield theme="simple" name="searchStart" ></s:textfield>
				</td>
				<td align="left">
					结束时间:<s:textfield theme="simple" name="searchEnd" ></s:textfield>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>
		</div>
	</div>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center">项目编号</th>
				<th align="center">项目名称</th>
				<th align="center">团队名称</th>
				<th align="center">项目成员</th>
				<th align="center">开始时间</th>
				<th align="center">结束时间</th>
				<th align="center">项目说明</th>
				<th align="center">项目资料</th>
				<th align="center">选择</th>		
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pm.list" var="cp">
			    <tr>
			    	<td align="center"> <s:property value="projectid"/></td>
					<td align="center"><s:property value="projectname"/></td>
					<td align="center"><s:property value="team.teamname"/></td>
					<td align="center"><s:property value="projmember.membername"/></td>
					<td align="center"><s:property value="starttime"/></td>
					<td align="center"><s:property value="endtime"/></td>
					<td align="center"><s:property value="projectdescription"/></td>
					<td align="center"><s:property value=" "/></td> <!-- 项目资料 -->
					<td align="center">&nbsp;<a class="btnSelect" href="javascript:$.bringBack({
					projectid:'<s:property value="projectid"/>', projectname:'<s:property value="projectname"/>'})" title="查找">选择</a></td>
				</tr>
			</s:iterator>	
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${pm.totalCount}条</span>
		</div>
		
		<!--分页组件-->  
		<div class="pagination" targetType="navTab" totalCount="${pm.totalCount}" numPerPage="${pm.numPerPage}" pageNumShown="10" currentPage="${pm.pageNum}"></div>
	</div>
</div>
</form>