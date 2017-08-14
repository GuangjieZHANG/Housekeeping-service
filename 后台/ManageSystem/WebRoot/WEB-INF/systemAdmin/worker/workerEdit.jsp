<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2 class="contentTitle">修改阿姨信息</h2>

<div class="pageContent">
	<form method="post" action="Worker_Save"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<div layoutH="97" style="width:60%;height:350px;border:none; float:right; left: 0px; padding:10px 0px; margin-right: 10px; overflow: hidden;" id="baidu_map"></div>
		<div class="pageFormContent nowrap" style="height: 65px; overflow: auto" layoutH="97">
			<dl>
				<dt>阿姨姓名：</dt>
				<dd style="width: 40%">
					<input type="text" name="worker.workerName"
						value="${worker.workerName}" class="required workerName" placeholder="姓名,例“张三”"/> 
					<input type="hidden" name="editorType" value="${editorType}" />
					<input type="hidden" name="worker.workerId" value="${worker.workerId}" />
				</dd>
			</dl>
			<dl>
				<dt>电话号码：</dt>
				<dd style="width: 40%">
					<input type="text" name="worker.phoneNumber"
						value="${worker.phoneNumber}" class="required phone tel" placeholder="11位手机号码"/> 
				</dd>
			</dl>

			<dl>
				<dt>身份证号：</dt>
				<dd style="width: 40%">
					<input type="text" name="worker.cardId" value="${worker.cardId}"
						class="required ID_card" placeholder="18位身份证号码"/>
				</dd>
			</dl>
			<dl>
				<dt>年龄：</dt>
				<dd style="width: 40%">
					<input type="text" min="18" max="65" step="1" name="worker.age"
						value="${worker.age}" class="required digits age" placeholder="2位数字(18-65),如“30”"/>
				</dd>
			</dl>
			<dl>
				<dt>性别：</dt>
				<dd style="width: 40%">
					<select name="worker.sex" class="required">
						<s:if test='worker.sex=="男"'>
								<option value="男" selected="selected">男</option>
								<option value="女">女</option>
							</s:if>
							<s:else>
								<option value="男">男</option>
								<option value="女" selected="selected">女</option>
							</s:else>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>服务类型：</dt>
				<dd style="width: 40%">
						<s:iterator value="servicePM.list" status="index"  var="cp" >
							<label><input type="checkbox" name="serviceIds" value="<s:property value="serviceTypeId"/>"/><s:property value="serviceTypeName"/></label>
						</s:iterator>
				</dd>
			</dl>
			<dl>
				<dt>简介：</dt>
				<dd style="width: 40%">
					<textarea cols="45" rows="10" name="worker.brief"
						class="required Brief" minlength="0" maxlength="300" placeholder="300字以内">${worker.brief}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>地址：</dt>
				<dd style="width: 60%;">
					<div style="margin-bottom:10px;">
						<input type="hidden" name="worker.longitude" id="longitude" value="${worker.longitude}"/>
						<input type="hidden" name="worker.latitude" id="latitude" value="${worker.latitude}"/>
						<input id="searchplace" name="worker.address" value="${worker.address}" style="width: 85%;" class="textInput required" placeholder="输入搜索关键字" />
					    <a class="btnLook" id="s_p_search_btn" href="#">搜索</a>
					</div>
					<div id="searchlist" style="width:50%; height:250px; margin-right:10%; float:left;"></div>
				</dd>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit"  onclick="checkboxNull();">提交</button>
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
<script type="text/javascript">
function initCheckbox(){
	var checkbox = document.getElementsByName('serviceIds');
	for(var i = 0; i < checkbox.length; i++){
		checkbox[i].checked = false;
	}
}
function checkb(){
	var checkbox = document.getElementsByName('serviceIds');
	for(var i = 0; i < checkbox.length; i++){
		<s:iterator value="worker.servicetypes" status="index"  var="cp">
			if('<s:property value="serviceTypeId"/>' == checkbox[i].value){
				checkbox[i].checked = true;
				continue;
			}
		</s:iterator>
	}
}

//百度地图API功能
//通过经纬度获取address
function getLocation(point){
	var gc = new BMap.Geocoder();
	var addr = "";
	gc.getLocation(point, 
			function(result) {
				console.log(result);
              if (result) {
              	var addComp = result.addressComponents;
              	addr = addComp.province + addComp.city + addComp.district + addComp.street +  addComp.streetNumber;
      			document.getElementById("searchplace").value = addr;
//       			document.getElementById("address").value = '{"province":"' + addComp.province + '", "city":"' + addComp.city + '", "district":"' + addComp.district + '", "street":"' + addComp.street + '", "streetNumber":"' + addComp.streetNumber + '"}';
              }
          });
	return addr;
}
//创建地图函数：
function createMap(){
// 百度地图API功能
	var map = new BMap.Map("baidu_map");
	var point = new BMap.Point(document.getElementById("longitude").value, document.getElementById("latitude").value);
	var marker = new BMap.Marker(point);
	map.centerAndZoom(point,16);
	map.addOverlay(marker);
	window.map = map;//将map变量存储在全局
}

//地图事件设置函数
function setMapEvent(){
  map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
  map.enableScrollWheelZoom();//启用地图滚轮放大缩小
  map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
  map.enableKeyboard();//启用键盘上下左右键移动地图
}
function initMap(){
  createMap();//创建地图
  setMapEvent();//设置地图事件
}

function createSearch() {
  var map = window.map;
  var local = new BMap.LocalSearch(map,
      {
          renderOptions: { map: map, panel: "searchlist" }
      });
  window.local = local;
}
//搜索
function searchPlace(value) {
  window.local.search(value);
}

function createAutocomlete() {
  var map = window.map;
  var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
      {
          "input": "searchplace",
          "location": map
      });
  ac.setInputValue("<s:property value="worker.address"/>");
  ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
      var _value = e.item.value;
      var addr =_value.province + _value.city + _value.district + _value.street + _value.streetNumber ;
      searchPlace(addr);
  });
  map.addEventListener("click",
  	function(e) {
	        if (e.point && e.overlay == null) {
	        	document.getElementById("longitude").value = e.point.lng;
	        	document.getElementById("latitude").value = e.point.lat;
	            searchPlace(getLocation(e.point));
	            map.centerAndZoom(e.point, 16);
	            map.clearOverlays();
	            map.addOverlay(new BMap.Marker(e.point));
	          }
  });
}
$(function(){
	initCheckbox();
	checkb();
	initMap();//创建和初始化地图
  	createSearch();
  	createAutocomlete();
  	$("#s_p_search_btn").click(function () {
  		searchPlace($("#searchplace").val());
  	});
});

</script>

