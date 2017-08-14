<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">修改项目信息</h2>
 
<div class="pageContent">
	
	<form method="post" action="Project_Save" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
		    <dl>
				<dt>项目编号：</dt>
				<dd>
					<input type="text" name="project.projectid" value="${project.projectid}" <s:if test="editorType=='modify'">readonly</s:if> class="required projectid" />
	 			    <span class="info">*必填(P_开头，后加8位数字)</span>
					
				</dd>
			</dl>
			<dl>
				<dt>项目名称：</dt>
				<dd>
					<input type="text" name="project.projectname" value="${project.projectname}"  class="required projectname" />
					<span class="info">*必填(3-15个汉字)</span>
				</dd>   
			</dl>
			
			
			<dl>
				<dt>团队编号：</dt>
			   <dd>
			   		<input type="hidden" name="editorType" value="${editorType}" />
					<s:if test="team!=null">
					 	<input type="text" name="teamid" value="${team.teamid}" readonly="readonly" class="required" />
					</s:if>
					<s:elseif test="editorType=='modify'">
						<input type="text" name="teamid" lookupName="team.teamid" value="${project.team.teamid}"  class="required" />
						<a class="btnLook" href="Team_List_LookUp" lookupGroup="team">查找</a>
					</s:elseif>	
					<s:else>
					<input type="text" name="teamid" lookupName="team.teamid" value="${team.teamid}"  readonly="readonly" class="required" />
						<a class="btnLook" href="Team_List_LookUp" lookupGroup="team">查找</a>
					</s:else>
			   </dd>
			</dl>
			
			<dl>
				<dt>开始时间：</dt>
				<dd>
					<input type="text" name="project.starttime" value="<s:date name="project.starttime" format="yyyy-MM-dd" />" class="date required" />
				</dd>
			</dl>
			<dl>
				<dt>结束时间：</dt>
				<dd>
					<input type="text" name="project.endtime" value="<s:date name="project.endtime" format="yyyy-MM-dd" />" class="date required" />
				</dd>
			</dl>
			
			<dl>
				<dt>项目说明：</dt>
				<dd>
					<textarea cols="45" rows="10" name="project.projectdescription"  class="required" minlength="0" maxlength="300">${project.projectdescription}</textarea>
					<span class="info">*必填(300字以内)</span>
				</dd>
			</dl>
			


<div class="divider"></div>
  <div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
			<!-- 需要修改附件的添加操作  -->
			<li><a class="add" href="Projectmember_Add?projectid=${projectid}&&teamid=${teamid}" target="navTab" rel="Projectmember_Add"><span>新增</span></a></li>
			<li><a class="edit" id="Projectmember_Modify" href="Projectmember_Modify?member_id={member_id}" target="navTab" rel="Projectmember_Modify"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="Projectmember_BatchDelete" class="delete"><span>删除</span></a></li>
		
		</ul>
	</div>	
	<table class="table" width="100%" layoutH="138">
		
		<thead>
			<tr>
				<th align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center">编号</th>
				<th align="center">名称</th>
				<th align="center">类型</th>
				<th align="center">种类</th>
				<th align="center">提交时间</th>
				<th align="center">附件</th>
				<th align="center">备注</th>			
			</tr>
		</thead>
		
		<!-- 需要修改附件的添加操作  -->
		<tbody>
			<s:iterator value="pm.list" status="index"  var="cp">
				<tr target="member_id" rel="<s:property value="memberid"/>" modifyTagId="Projectmember_Modify"> 
					<td align="center"><input name="ids" value="<s:property value="memberid"/>" type="checkbox"></td>
					<td align="center"> <s:property value="memberid"/></td>
					<td align="center"><s:property value="studentid"/></td>
					<td align="center"><s:property value="membername"/></td>
					<td align="center"><s:property value="institute"/></td>
					<td align="center"><s:property value="project.projectname"/></td>
					<td align="center"><s:property value="team.teamname"/></td>
					<td align="center"><s:property value="note"/></td>	
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option <s:if test='%{numPerPage=="20"}'>selected="selected"</s:if> value="20">1</option>
				<option <s:if test='%{numPerPage=="50"}'>selected="selected"</s:if> value="50">2</option>
				<option <s:if test='%{numPerPage=="100"}'>selected="selected"</s:if> value="100">100</option>
				<option <s:if test='%{numPerPage=="200"}'>selected="selected"</s:if> value="200">200</option>
			</select>
			<span>条，共${pm.totalCount}条</span>
		</div>
		
		<!--分页组件-->  
		<div class="pagination" targetType="navTab" totalCount="${pm.totalCount}" numPerPage="${pm.numPerPage}" pageNumShown="10" currentPage="${pm.pageNum}"></div>
	</div>
  </div>
</div>
		

		
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

