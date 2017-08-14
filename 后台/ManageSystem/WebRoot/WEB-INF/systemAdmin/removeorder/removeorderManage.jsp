<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="removeordermanage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="<s:property value="pm.numPerPage"/>" />
</form>

<form onsubmit="return navTabSearch(this);" action="SearchRemoveOrder_List" method="post">
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td align="left">
					订单编号:<s:textfield theme="simple" name="searchRemoveOrderId" ></s:textfield>
				</td>
				<td align="left">
					服务类型:
					<select name="searchServiceTypeId" class="required">
						<option value="">请选择服务类型</option>
						<s:iterator value="servicePM.list" status="index"  var="cp" >
							<option value="<s:property value="serviceTypeId"/>"><s:property value="serviceTypeName"/></option>
						</s:iterator>
					</select>
				</td>
				<td align="left">
					用户姓名:<s:textfield theme="simple" name="searchUserName" ></s:textfield>
				</td>
				<td align="left">
					用户电话:<s:textfield theme="simple" name="searchUserPhoneNumber" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td align="left">
					开始时间:<input type="text" id="begin" name="searchBeginTime" class="date required"
						dateFmt="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td align="left">
					结束时间:<input type="text" id="end" name="searchEndTime" class="date required"
						dateFmt="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td align="left">
					阿姨姓名:<s:textfield theme="simple" name="searchWorkerName" ></s:textfield>
				</td>
				<td align="left">
					阿姨电话:<s:textfield theme="simple" name="searchWorkerPhoneNumber" ></s:textfield>
				</td>
				<td align="left">
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="checkInput('begin','end');compareTime('begin','end');">检索</button>
							</div>
						</div>
				</td>
			</tr>
		</table>
	</div>
</div>
</form>
<div class="pageContent">
	<div class="panelBar">         
		<ul class="toolBar">
			<li><a class="add" href="RemoveOrder_Add?projectid=${projectid}" target="navTab" rel="RemoveOrder_Add"><span>新增</span></a></li>
			<li><a class="edit" id="RemoveOrder_Modify" href="RemoveOrder_Modify?removeOrder_id={removeOrder_id}" target="navTab" rel="RemoveOrder_Modify"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="RemoveOrder_BatchDelete" class="delete"><span>删除</span></a></li>
		</ul>
	</div>
		
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center">订单编号</th>
				<th align="center">服务类型</th>
				<th align="center">用户姓名</th>
				<th align="center">用户电话</th>
				<th align="center">服务地址</th>
				<th align="center">预计时间</th>
				<th align="center">删除时间</th>
				<th align="center">阿姨姓名</th>
				<th align="center">阿姨电话</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pm.list" status="index"  var="cp">
				<tr target="removeOrder_id" rel="<s:property value="orderId"/>" modifyTagId="RemoveOrder_Modify"> 
					<td align="center"><input name="ids" value="<s:property value="orderId"/>" type="checkbox"></td>
					<td align="center"><s:property value="orderId"/></td>
					<td align="center"><s:property value="servicetype.serviceTypeName"/></td>
					<td align="center"><s:property value="user.userName"/></td>
					<td align="center"><s:property value="user.phoneNumber"/></td>
					<td align="center"><s:property value="address"/></td>
					<td align="center"><s:date name="predictTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center"><s:date name="removeTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center"><s:property value="worker.workerName"/></td>
					<td align="center"><s:property value="worker.phoneNumber"/></td>				
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