<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css"> 
.anchorBL{ 
display:none; 
} 
</style> 
<h2 class="contentTitle">新增订单信息</h2>
 
<div class="pageContent">
	
	<form method="post" action="Unorder_Save" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div layoutH="97" style="width:60%;height:350px;border:none; float:right; left: 0px; padding:10px 0px; margin-right: 10px; overflow: hidden;" id="baidu_map"></div>
		<div class="pageFormContent nowrap" layoutH="97" >
			<dl>
				<dt>服务类型：</dt>
				<dd style="width: 40%">
					<select name="servicetype.serviceTypeId" class="required">
						<option value="">请选择服务类型</option>
						<s:iterator value="servicePM.list" status="index"  var="cp" >
							<option value="<s:property value="serviceTypeId"/>"><s:property value="serviceTypeName"/></option>
						</s:iterator>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>顾客姓名：</dt>
				<dd style="width: 40%">
					<input type="hidden" name="editorType" value="${editorType}" />
					<s:if test="user!=null">
						<input type="text" name="user.userName" value="${user.userName}"
							readonly="readonly" class="required" />
						<input type="hidden" name="user.userId" value="${user.userId}" />
					</s:if>
					<s:elseif test="editorType=='modify'">
						<input type="text" name="user.userName"
							lookupName="user.userName" value="${user.userName}"
							class="required" />
						<input type="hidden" name="user.userId" lookupName="user.userId"
							value="${user.userId}" />
						<a class="btnLook" href="User_List_LookUp" lookupGroup="user">查找</a>
					</s:elseif>
					<s:else>
						<input type="text" name="user.userName"
							lookupName="user.userName" value="${user.userName}"
							readonly="readonly" class="required" />
						<input type="hidden" name="user.userId" lookupName="user.userId"
							value="${user.userId}" />
						<a class="btnLook" href="User_List_LookUp" lookupGroup="user">查找</a>
					</s:else>
				</dd>
			</dl>

			<dl>
				<dt>顾客电话：</dt>
				<dd style="width: 40%">
					<s:if test="user!=null">
						<input type="text" name="user.phoneNumber"
							value="${user.phoneNumber}" readonly="readonly"
							class="required" />
					</s:if>
					<s:elseif test="editorType=='modify'">
						<input type="text" name="user.phoneNumber"
							lookupName="user.phoneNumber" value="${user.phoneNumber}"
							class="required" />
						<a class="btnLook" href="User_List_LookUp" lookupGroup="user">查找</a>
					</s:elseif>
					<s:else>
						<input type="text" name="user.phoneNumber"
							lookupName="user.phoneNumber" value="${user.phoneNumber}"
							readonly="readonly" class="required" />
						<a class="btnLook" href="User_List_LookUp" lookupGroup="user">查找</a>
					</s:else>
				</dd>
			</dl>
			<dl>
				<dt>预计时间：</dt>
				<dd style="width: 40%">
					<input type="text" id="end" name="unorder.predictTime" class="date required"
						dateFmt="yyyy-MM-dd HH:mm:ss" />
				</dd>
			</dl>

			<dl>
				<dt>阿姨姓名：</dt>
				<dd style="width: 40%">
					<s:if test="worker!=null">
						<input type="text" name="worker.workerName" value="${worker.workerName}"
							readonly="readonly" class="textInput" />
						<input type="hidden" name="worker.workerId" value="${worker.workerId}" />
					</s:if>
					<s:elseif test="editorType=='modify'">
						<input type="text" name="worker.workerName"
							lookupName="worker.workerName" value="${worker.workerName}"
							class="textInput" />
						<input type="hidden" name="worker.workerId" lookupName="worker.workerId"
							value="${worker.workerId}" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:elseif>
					<s:else>
						<input type="text" name="worker.workerName"
							lookupName="worker.workerName" value="${worker.workerName}"
							readonly="readonly" class="textInput" />
						<input type="hidden" name="worker.workerId" lookupName="worker.workerId"
							value="${worker.workerId}" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:else>
				</dd>
			</dl>

			<dl>
				<dt>阿姨电话：</dt>
				<dd style="width: 40%">
					<s:if test="worker!=null">
						<input type="text" name="worker.phoneNumber"
							value="${worker.phoneNumber}" readonly="readonly"
							class="textInput" />
					</s:if>
					<s:elseif test="editorType=='modify'">
						<input type="text" name="worker.phoneNumber"
							lookupName="worker.phoneNumber" value="${worker.phoneNumber}"
							class="textInput" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:elseif>
					<s:else>
						<input type="text" name="worker.phoneNumber"
							lookupName="worker.phoneNumber" value="${worker.phoneNumber}"
							readonly="readonly" class="textInput" />
						<a class="btnLook" href="Worker_List_LookUp" lookupGroup="worker">查找</a>
					</s:else>
				</dd>
			</dl>
			<dl>
				<dt>地址：</dt>
				<dd style="width: 60%;">
<!-- 					<textarea cols="45" rows="10" name="address" -->
<!-- 						class="required Address" minlength="0" maxlength="300">${address}</textarea> -->
<!-- 					<span class="info">*必填</span> -->
					<div style="margin-bottom:10px;">
						<input type="hidden" name="unorder.longitude" id="longitude"/>
						<input type="hidden" name="unorder.latitude" id="latitude"/>
<!-- 						<input id="searchplace" name="searchplace" value="${address}" autocomplete="on" style="width: 85%;" class="textInput required" placeholder="输入搜索关键字" /> -->
<!-- 					    <input type="hidden" id="address" name="unorder.address"/> -->
							<input id="searchplace" name="unorder.address" value="${address}" autocomplete="on" style="width: 85%;" class="textInput required" placeholder="输入搜索关键字" />
					    	<a class="btnLook" id="s_p_search_btn" href="#" title="搜索">搜索</a>
					</div>
					<div id="searchlist" style="width:85%; height:250px; margin-right:10%; float:left;"></div>
					<div style="clear:both;"></div>
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
<script type="text/javascript">
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
//         			document.getElementById("address").value = '{"province":"' + addComp.province + '", "city":"' + addComp.city + '", "district":"' + addComp.district + '", "street":"' + addComp.street + '", "streetNumber":"' + addComp.streetNumber + '"}';
                }
            });
	return addr;
}
//创建地图函数：
function createMap(){
 // 百度地图API功能
	var map = new BMap.Map("baidu_map");
	var point = new BMap.Point(116.331398,39.897445);
	var marker = new BMap.Marker(point);
	map.centerAndZoom(point,16);
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			marker = new BMap.Marker(r.point);
			map.addOverlay(marker);
			map.panTo(r.point);
			document.getElementById("longitude").value = r.point.lng;
			document.getElementById("latitude").value = r.point.lat;
			getLocation(r.point);
// 			${"#longitude"}.text(r.point.lng);
// 			${"#latitude"}.text(r.point.lat);
// 			${"#searchplace"}.text('{"province":"' + addComp.province + '", "city":"' + addComp.city + '", "district":"' + addComp.district + '", "street":"' + addComp.street + '", "streetNumber":"' + addComp.streetNumber + '"}');
		}
		else {
			map.addOverlay(marker);
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true});
	window.map = map;//将map变量存储在全局
}

// 地图事件设置函数
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
    initMap();//创建和初始化地图
    createSearch();
    createAutocomlete();
    $("#s_p_search_btn").click(function () {
        searchPlace($("#searchplace").val());
    });
});
</script>
