<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">修改房间信息</h2>
 
<div class="pageContent">
	
	<form method="post" action="Room_Save" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
		    <dl>
				<dt>房间编号：</dt>
				<dd>
					<input type="text" name="room.roomID" value="${room.roomID}"  class="required RoomID" />
					<input type="hidden" name="editorType" value="${editorType}" />
				</dd>
			</dl>
			<dl>
				<dt>房间名称：</dt>
				<dd>
					<input type="text"  name="room.roomname" value="${room.roomname}"  class="required Roomname" />
				</dd>
			</dl>
			
			<dl>
				<dt>房间面积：</dt>
				<dd>
					<input type="text" name="room.roomarea" value="${room.roomarea}" class="required Areaname"/>

				</dd>
			</dl>
			
			<dl>
				<dt>备注：</dt>
				<dd>					
					<textarea cols="45" rows="10" name="room.note"  class="required" >${room.note}</textarea>
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

