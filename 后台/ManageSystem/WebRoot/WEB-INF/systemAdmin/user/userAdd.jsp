<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">添加用户信息</h2>

<div class="pageContent">

	<form method="post" action="User_Save"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>用户姓名：</dt>
				<dd>
					<input type="text" name="user.userName"
						value="${user.userName}"
						<s:if test="editorType=='modify'">readonly</s:if>
						class="required userName" /> <span class="info">*必填(姓名,例“张三”)</span>
					<input type="hidden" name="editorType" value="${editorType}" />
				</dd>
			</dl>
			<dl>
				<dt>电话号码：</dt>
				<dd>
					<input type="text" name="user.phoneNumber"
						value="${user.phoneNumber}" class="required phone tel" /> <span
						class="info">*必填(11位手机号码)</span>
				</dd>
			</dl>
			<dl>
				<dt>地址：</dt>
				<dd>
					<textarea cols="45" rows="10" name="user.address"
						class="required Address" minlength="0" maxlength="300">${user.address}</textarea>
					<span class="info">*必填</span>
				</dd>
			</dl>
		</div>
<!-- 		<div class="divider"></div> -->

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

