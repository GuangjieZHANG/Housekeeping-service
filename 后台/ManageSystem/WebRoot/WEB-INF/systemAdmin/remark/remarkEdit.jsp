<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">修改团队信息</h2>
 
<div class="pageContent">
	
	<form method="post" action="Projectmember_Save" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
		    <dl>
				<dt>编号：</dt>
				<dd>
					<input type="text" name="projectmember.memberid" value="${projectmember.memberid}" <s:if test="editorType=='modify'">readonly</s:if> class="required Memberid" />
					<span class="info">*必填(以“M_”开头加3位数字，例“M_100”)</span>
					<input type="hidden" name="editorType" value="${editorType}" />
				</dd>
			</dl>
			
			<dl>
				<dt>学号：</dt>
				<dd>
					<input type="text" name="projectmember.studentid" value="${projectmember.studentid}" class="required Studentid"/>
					<span class="info">*必填(10位数字，例“2015262601”)</span>
				</dd>
			</dl>
			<dl>
				<dt>姓名：</dt>
				<dd>
					<input type="text" name="projectmember.membername" value="${projectmember.membername}" class="required Membername"/>
					<span class="info">*必填(20个汉字以内，例 “李杰”)</span>
				</dd>
			</dl>
			<dl>
				<dt>学院：</dt>
				<dd>
					<input type="text" name="projectmember.institute" value="${projectmember.institute}" class="required Institute"/>
					<span class="info">*必填(25个汉字以内，例 “软件与微电子学院”)</span>
				</dd>
			</dl>
			
			<!-- =============================================================== -->
					
			<dl>	
				<dt>项目编号：</dt>
				<dd>
					<input type="hidden" name="editorType" value="${editorType}" />
					<s:if test="project!=null">
					   <input type="text" name="projectid" value="${project.projectid}" readonly="readonly" class="required" />
					</s:if>
					<s:elseif test="editorType=='modify'">	
					   <input type="text" name="projectid" lookupName="project.projectid" value="${projectmember.project.projectid}"  class="required" />
					   <a class="btnLook" href="Project_List_LookUp" lookupGroup="project">查找</a>
					</s:elseif>	
					<s:else>
					   <input type="text" name="projectid" lookupName="project.projectid" value="${project.projectid}" readonly="readonly" class="required" />
					    <a class="btnLook" href="Project_List_LookUp" lookupGroup="project">查找</a>
					</s:else>	
				</dd>
			</dl>	
			
			<!-- =============================================================== -->
			
			<dl>
				<dt>团队编号：</dt>
				<dd>
					<input type="hidden" name="editorType" value="${editorType}" />
					<s:if test="team!=null">
					   <input type="text" name="teamid" value="${team.teamid}" readonly="readonly" class="required" />
					</s:if>
					<s:elseif test="editorType=='modify'">	
					   <input type="text" name="teamid" lookupName="team.teamid" value="${projectmember.team.teamid}"  class="required" />
					   <a class="btnLook" href="Team_List_LookUp" lookupGroup="team">查找</a>
					</s:elseif>	
					<s:else>
					   <input type="text" name="teamid" lookupName="team.teamid" value="${team.teamid}" readonly="readonly" class="required" />
					    <a class="btnLook" href="Team_List_LookUp" lookupGroup="team">查找</a>
					</s:else>	
				</dd>
			</dl>
					
			<!-- =============================================================== -->
			
			<dl>
				<dt>角色：</dt>
				<dd>
					<input type="text" name="projectmember.role" value="${projectmember.role}" class="required Roles"/>
					<span class="info">(5个汉字以内，例 “队长”)</span>
				</dd>
			</dl>
			
			<dl>
				<dt>备注：</dt>
				<dd>
					<textarea cols="45" rows="10" name="projectmember.note"  class="required" minlength="0" maxlength="300">${projectmember.note}</textarea>
					<span class="info">(300个字符以内)</span>
				</dd>		
			</dl>
			
		
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

