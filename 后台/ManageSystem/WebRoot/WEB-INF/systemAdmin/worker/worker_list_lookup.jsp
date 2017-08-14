<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="pagerForm" method="post" action="workermanage">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="<s:property value="pm.numPerPage"/>" /> <input
		type="hidden" name="wokerName" value="<s:property value="wokerName"/>" />
</form>

<form onsubmit="return dwzSearch(this, 'dialog');"
	action="Worker_List_LookUp" method="post">
	<div class="pageHeader">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td align="left">阿姨姓名:<s:textfield theme="simple"
							name="searchWorkerName"></s:textfield>
					</td>
					<td align="left">电话号码:<s:textfield theme="simple"
							name="searchPhoneNumber"></s:textfield>
					</td>
					<td align="left">身份证号:<s:textfield theme="simple"
							name="searchCardId"></s:textfield>
					</td>
					<td align="left">性别:
						<select name="searchWokerSex" class="required">
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4">
						服务类型:
						<select name="searchServiceTypeId" class="required">
							<option value="">请选择服务类型</option>
							<s:iterator value="servicePM.list" status="index"  var="cp" >
								<option value="<s:property value="serviceTypeId"/>"><s:property value="serviceTypeName"/></option>
							</s:iterator>
						</select>
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center">阿姨姓名</th>
				<th align="center">电话号码</th>
				<th align="center">身份证号</th>
				<th align="center">年龄</th>
				<th align="center">性别</th>
				<th align="center">服务类型</th>
				<th align="center">地址</th>
				<th align="center">简介</th>
				<th align="center">选择</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pm.list" var="cp">
				<tr>
					<td align="center"><s:property value="workerName" /></td>
					<td align="center"><s:property value="phoneNumber" /></td>
					<td align="center"><s:property value="cardId" /></td>
					<td align="center"><s:property value="age" /></td>
					<td align="center"><s:property value="sex" /></td>
					<td align="center"><s:iterator value="servicetypes"><s:property value="serviceTypeName"/>&nbsp;</s:iterator></td>
					<td align="center"><s:property value="address" /></td>
					<td align="center"><s:property value="brief" /></td>
					<td align="center">&nbsp;<a class="btnSelect"
						href="javascript:$.bringBack({workerId:'<s:property value="workerId"/>', 
								workerName:'<s:property value="workerName"/>', 
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