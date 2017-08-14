<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="usermanage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="<s:property value="pm.numPerPage"/>" />
	<input type="hidden" name="userName" value="<s:property value="userName"/>" />
</form>

<form onsubmit="return navTabSearch(this);" action="SearchUser_List" method="post">
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td align="left">
					用户姓名:<s:textfield theme="simple" name="searchUserName" ></s:textfield>
				</td>
				<td align="left">
					用户电话:<s:textfield theme="simple" name="searchPhoneNumber" ></s:textfield>
				</td>
		</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="User_Add" target="navTab" rel="USER_Add"><span>新增</span></a></li>
			<li><a class="edit" id="User_Modify" href="User_Modify?user_id={user_id}" target="navTab" rel="User_Modify"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="User_BatchDelete" class="delete"><span>删除</span></a></li>
		</ul>
	</div>
		
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center">用户姓名</th>
				<th align="center">电话号码</th>
				<th align="center">地址</th>
			</tr>
		</thead>
		<tbody>      
			<s:iterator value="pm.list" status="index"  var="cp">
				<tr target="user_id" rel="<s:property value="userId"/>" modifyTagId="User_Modify"> 
					<td align="center"><input name="ids" value="<s:property value="userId"/>" type="checkbox"></td>
					<td align="center"> <s:property value="userName"/></td>
					<td align="center"><s:property value="phoneNumber"/></td>
					<td align="center"><s:property value="address"/></td>
				</tr>
			</s:iterator>	
		</tbody>
	</table>
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option <s:if test='%{numPerPage=="20"}'>selected="selected"</s:if> value="20">20</option>
				<option <s:if test='%{numPerPage=="50"}'>selected="selected"</s:if> value="50">50</option>
				<option <s:if test='%{numPerPage=="100"}'>selected="selected"</s:if> value="100">100</option>
				<option <s:if test='%{numPerPage=="200"}'>selected="selected"</s:if> value="200">200</option>
			</select>
			<span>条，共${pm.totalCount}条</span>
		</div>
		
		<!--分页组件-->  
		<div class="pagination" targetType="navTab" totalCount="${pm.totalCount}" numPerPage="${pm.numPerPage}" pageNumShown="10" currentPage="${pm.pageNum}"></div>
	</div>
</div>