<%@page import="com.manage.service.IUserService"%>
<%@page import="com.manage.service.impl.UserService"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
IUserService userService = (IUserService) ctx.getBean("UserService");
%>
<form id="pagerForm" method="post" action="remarkmanage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="<s:property value="pm.numPerPage"/>" />
</form>

<form onsubmit="return navTabSearch(this);" action="SearchRemark_List" method="post">
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent"> 
			<tr>
				<td align="left">
					评论编号:<s:textfield theme="simple" name="searchRemarkId" ></s:textfield>
				</td>
				<td align="left">
					订单编号:<s:textfield theme="simple" name="searchStudentid" ></s:textfield>
				</td>
				<td align="left">
					评论者编号:<s:textfield theme="simple" name="searchMembername" ></s:textfield>
				</td>
			</tr>
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
			<li><a class="add" href="Projectmember_Add?projectid=${projectid}&&teamid=${teamid}" target="navTab" rel="Projectmember_Add"><span>新增</span></a></li>
			<li><a class="edit" id="Projectmember_Modify" href="Projectmember_Modify?member_id={member_id}" target="navTab" rel="Projectmember_Modify"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="Projectmember_BatchDelete" class="delete"><span>删除</span></a></li>
		</ul>
	</div>
		
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center">评论编号</th>
				<th align="center">订单编号</th>
				<th align="center">等级</th>
				<th align="center">内容</th>
				<th align="center">评论者编号</th>
				<th align="center">评论时间</th>
			</tr>
		</thead>
		<tbody> 
			<s:iterator value="pm.list" status="index"  var="cp">
				<tr target="member_id" rel="<s:property value="remarkId"/>" modifyTagId="Projectmember_Modify"> 
					<td align="center"><input name="ids" value="<s:property value="remarkId"/>" type="checkbox"></td>
					<td align="center"><s:property value="remarkId"/></td>
					<td align="center"><s:property value="ordered.orderId"/></td>
					<td align="center"><s:property value="level"/></td>
					<td align="center"><s:property value="content"/></td>
					<td align="center"><s:property value="sendId"/></td>
					<td align="center"><s:date name="addTime" format="yyyy-MM-dd HH:mm:ss"/></td>
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
