<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">新增请假信息</h2>

<div class="pageContent">

	<form method="post" action="Leave_Save"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>阿姨姓名：</dt>
				<dd>
					<input type="hidden" name="editorType" value="${editorType}" />
					<s:if test="worker!=null">
						<input type="text" name="workerName" value="${worker.workerName}"
							readonly="readonly" class="required" />
						<input type="hidden" name="workerId" value="${worker.workerId}" />
					</s:if>
					<s:elseif test="editorType=='modify'">
						<input type="text" name="workerName"
							lookupName="worker.workerName" value="${worker.workerName}"
							class="required" />
						<input type="hidden" name="workerId" lookupName="worker.workerId"
							value="${worker.workerId}" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:elseif>
					<s:else>
						<input type="text" name="workerName"
							lookupName="worker.workerName" value="${worker.workerName}"
							readonly="readonly" class="required" />
						<input type="hidden" name="workerId" lookupName="worker.workerId"
							value="${worker.workerId}" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:else>
				</dd>
			</dl>

			<dl>
				<dt>阿姨电话：</dt>
				<dd>
					<s:if test="worker!=null">
						<input type="text" name="phoneNumber"
							value="${worker.phoneNumber}" readonly="readonly"
							class="required" />
					</s:if>
					<s:elseif test="editorType=='modify'">
						<input type="text" name="phoneNumber"
							lookupName="worker.phoneNumber" value="${worker.phoneNumber}"
							class="required" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:elseif>
					<s:else>
						<input type="text" name="phoneNumber"
							lookupName="worker.phoneNumber" value="${worker.phoneNumber}"
							readonly="readonly" class="required" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:else>
				</dd>
			</dl>

			<dl>
				<dt>开始时间：</dt>
				<dd>
					<!-- 					<input type="text" name="leave.beginTime" value="<s:date name="leave.beginTime" format="yyyy-MM-dd HH:mm:ss" />" class="date required" /> -->
					<input type="text" name="leave.beginTime" class="date required"
						dateFmt="yyyy-MM-dd HH:mm:ss" />
				</dd>
			</dl>
			<dl>
				<dt>结束时间：</dt>
				<dd>
					<input type="text" name="leave.endTime" class="date required"
						dateFmt="yyyy-MM-dd HH:mm:ss" />
				</dd>
			</dl>


			<dl>
				<dt>请假缘由：</dt>
				<dd>
					<textarea cols="45" rows="10" name="leave.description"
						class="required" minlength="0" maxlength="300">${leave.description}</textarea>
					<span class="info">*必填(300字以内)</span>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>

