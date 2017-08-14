<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">密码修改</h2>
 
<div class="pageContent">
	
	<form method="post" action="Login_UpdatePwd" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>原密码：</dt>
				<dd>
					<input  type="password" name="oldpassword" class="required alphanumeric textInput" minlength="6" maxlength="20" />
					<span class="info">*必填(字母、数字、下划线 6-20位)</span>
				</dd>
			</dl>
			<dl>
				<dt>新密码：</dt>
				<dd>
					<input  type="password" name="newpassword" id="newpassword" class="required alphanumeric textInput valid" minlength="6" maxlength="20"/>
					<span class="info">*必填(字母、数字、下划线 6-20位)</span>
				</dd>
			</dl>
			<dl>
				<dt>确认密码：</dt>
				<dd>
					<input type="password" name="confirmpassword" class="required alphanumeric textInput" equalTo="#newpassword" minlength="6" maxlength="20"/>
					<span class="info">*必填(字母、数字、下划线 6-20位)</span>
				</dd>
			</dl>
			<div class="divider"></div>
			 
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>