<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form id="pagerForm" method="post" action="usermanage">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="<s:property value="pm.numPerPage"/>" /> <input
		type="hidden" name="userName" value="<s:property value="userName"/>" />
</form>

<form onsubmit="return dwzSearch(this, 'dialog');" action="User_List_LookUp"
	method="post">
	<div class="pageHeader">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td align="left">用户姓名:<s:textfield theme="simple"
							name="searchUserName"></s:textfield>
					</td>
					<td align="left">用户电话:<s:textfield theme="simple"
							name="searchPhoneNumber"></s:textfield>
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

	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center">用户姓名</th>
				<th align="center">电话号码</th>
				<th align="center">地址</th>
				<th align="center">选择</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pm.list" status="index" var="cp">
				<tr>
					<td align="center"><s:property value="userName" /></td>
					<td align="center"><s:property value="phoneNumber" /></td>
					<td align="center"><s:property value="address" /></td>
					<td align="center">&nbsp;<a class="btnSelect"
						href="javascript:$.bringBack({userId:'<s:property value="userId"/>', 
								userName:'<s:property value="userName"/>', 
								phoneNumber:'<s:property value="phoneNumber"/>'})"
						title="查找">选择</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${pm.totalCount}条</span>
		</div>

		<!--分页组件-->
		<div class="pagination" targetType="navTab"
			totalCount="${pm.totalCount}" numPerPage="${pm.numPerPage}"
			pageNumShown="10" currentPage="${pm.pageNum}"></div>
	</div>
</div>