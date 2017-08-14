<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">修改设备信息</h2>
 
<div class="pageContent">
	
	<form method="post" action="Device_Save" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
		    <dl>
				<dt>固定资产号：</dt>
				<dd>
					<input type="text" name="device.deviceid" value="${device.deviceid}" <s:if test="editorType=='modify'">readonly</s:if> class="required Deviceid" />
					<span class="info">*必填</span>
					<input type="hidden" name="editorType" value="${editorType}" />
				</dd>
			</dl>
			<dl>
				<dt>设备类型：</dt>
				<dd>
					<input type="text"  name="device.devicetype" value="${device.devicetype}"  class="required Device.device" />
					<span class="info">*必填(例：电脑、空调....)</span>
				</dd>
			</dl>
			
			<dl>
				<dt>设备型号：</dt>
				<dd>
					<input type="text" name="device.devicemodel" value="${device.devicemodel}" class="required Devicemodel"/>
					<span class="info">*必填</span>
				</dd>
			</dl>
			<dl>
				<dt>参数：</dt>
				<dd>
					<input type="text" name="device.deviceparam" value="${device.deviceparam}" class="required Deviceparam"/>
					<span class="info">*必填</span>
				</dd>
			</dl>
			
			<!-- <dl>
			<dt>购买日期：</dt>
				<dd>
					<input type="text" name="device.buydate" value="${device.buydate}" class="required buydate"/>
					<span class="info">*必填</span>
				</dd>
			</dl> -->
			
			<dl>
				<dt>购买日期：</dt>
				<dd>
					<input type="text" name="device.buydate" value="<s:date name="device.buydate" format="yyyy-MM-dd" />" class="date required" />
					<span class="info">*必填</span>
				</dd>
			</dl>
			<dl>
			<dt>存放地点：</dt>
				<dd>
					<input type="text" name="device.location" value="${device.location}" class="required location"/>
					<span class="info">*必填</span>
				</dd>
			</dl>
			<dl>
			<dt>负责人：</dt>
				<dd>
					<input type="text" name="device.deviceowner" value="${device.deviceowner}" class="required Deviceowner"/>
					<span class="info">*必填</span>
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>					
					<textarea cols="45" rows="10" name="device.note"  class="required" minlength="0" maxlength="300">${device.note}</textarea>
					<span class="info">*必填(300字以内)</span>
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

