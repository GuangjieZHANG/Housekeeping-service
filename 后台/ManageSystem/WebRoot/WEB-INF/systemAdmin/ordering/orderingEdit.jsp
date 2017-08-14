<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">修改获奖信息</h2>
 
<div class="pageContent">
	
	<form method="post" action="Awardrecord_Save" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
		    <dl>
				<dt>获奖编号：</dt>
				<dd>
					<input type="text" name="awardrecord.awardid" value="${awardrecord.awardid}" <s:if test="editorType=='modify'">readonly</s:if> class="required Awardrecordid" />
					<span class="info">*必填(A_开头+3位0~9数字)</span>
					<input type="hidden" name="editorType" value="${editorType}" />
				</dd>
			</dl>
			<dl>	
				<dt>项目编号：</dt>
				<dd>
					<input type="hidden" name="editorType" value="${editorType}" />
					<s:if test="project!=null">
					   <input type="text" name="projectid" value="${project.projectid}" readonly="readonly" class="required" />
					</s:if>
					<s:elseif test="editorType=='modify'">	
					   <input type="text" name="projectid" lookupName="project.projectid" value="${awardrecord.project.projectid}"  class="required" />
					   <a class="btnLook" href="Project_List_LookUp" lookupGroup="project">查找</a>
					</s:elseif>	
					<s:else>
					   <input type="text" name="projectid" lookupName="project.projectid" value="${project.projectid}" readonly="readonly" class="required" />
					   <a class="btnLook" href="Project_List_LookUp" lookupGroup="project">查找</a>
					</s:else>	
				</dd>
			</dl>	
				
			
			<dl>
				<dt>赛事名称：</dt>
				<dd>
					<input type="text" name="awardrecord.competitionname" value="${awardrecord.competitionname}" class="required"  minlength="3" maxlength="50"/>
					<span class="info">*必填(3-50字以内)</span>
				</dd>
			</dl>
			<dl>
				<dt>赛事等级：</dt>
				<dd>
					<select name="awardrecord.competitionlevel">
						<option value="国际级">国际级</option>
						<option value="国家级">国家级</option>
						<option value="省级">省级</option>
						<option value="市级">市级</option>
						<option value="校级">校级</option>		
					</select>
				</dd>
			</dl>
			<dl>
				<dt>获奖等级：</dt>
				<dd>					
					<select name="awardrecord.awardlevel">
						<option value="特等奖">特等奖</option>
						<option value="一等奖">一等奖</option>
						<option value="二等奖">二等奖</option>
						<option value="三等奖">三等奖</option>
						<option value="优秀奖">优秀奖</option>
						<option value="优胜奖">优胜奖</option>		
					</select>
				</dd>
			</dl>
			
			<dl>
				<dt>备注：</dt>
				<dd>
					<textarea cols="45" rows="10" name="awardrecord.note" class="required" minlength="0" maxlength="300">${awardrecord.note}</textarea>
					<span class="info">300字以内</span>
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

