<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="leavemanage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="<s:property value="pm.numPerPage"/>" />
</form>

<form onsubmit="return navTabSearch(this);" action="SearchLeave_List" method="post">
	<div class="pageHeader">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td align="left">
						阿姨姓名:<s:textfield theme="simple" name="searchWorkerName" ></s:textfield>
					</td>
					<td align="left">
						阿姨电话:<s:textfield theme="simple" name="searchPhoneNumber" ></s:textfield>
					</td>
					<td align="left">
						开始时间:<input type="text" id="begin" name="searchBeginTime" class="date required"
						dateFmt="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td align="left">
						结束时间:<input type="text" id="end" name="searchEndTime" class="date required"
						dateFmt="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="checkInput('begin','end');compareTime('begin','end');">检索</button>
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
			<li><a class="add" href="Leave_Add" target="navTab" rel="LEAVE_Add"><span>新增</span></a></li>
			<li><a class="edit" id="Leave_Modify" href="Leave_Modify?leave_id={leave_id}" target="navTab" rel="Leave_Modify"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="Leave_BatchDelete" class="delete"><span>删除</span></a></li>
		</ul>
	</div>
		
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center">阿姨姓名</th>
				<th align="center">阿姨电话</th>
				<th align="center">开始时间</th>
				<th align="center">结束时间</th>
				<th align="center">请假缘由</th>
				<th align="center">提交时间</th>
			</tr>
		</thead>
		<tbody>      
			<s:iterator value="pm.list" status="index"  var="cp">
				<tr target="leave_id" rel="<s:property value="leaveId"/>" modifyTagId="Leave_Modify"> 
					<td align="center"><input name="ids" value="<s:property value="leaveId"/>" type="checkbox"></td>
					<td align="center"> <s:property value="worker.workerName"/></td>
					<td align="center"><s:property value="worker.phoneNumber"/></td>
					<td align="center"><s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center"><s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center"><s:property value="description"/></td>
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