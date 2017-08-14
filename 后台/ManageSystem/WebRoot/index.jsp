<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>自动提示</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<style type="text/css">
body{font-size:13px;margin:0px}
#container{width:600px;height:400px}
.label{margin-left:20px;font-weight:bold;font-size:14px}
</style>
</head>
<body>
<div style="margin:50px">请输入:<input type="text" id="suggestId" size="30" value="百度" style="width:300px;" /></div>
<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:300px;height:600px;position:absolute;left: 650px;top:20px;"></div>
<div id="container"></div>
<script type="text/javascript">
function G(id) {
    return document.getElementById(id);
}

var map = new BMap.Map("container");
var point = new BMap.Point(116.3964,39.9093);
map.centerAndZoom(point,13);
map.enableScrollWheelZoom();

var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
    {"input" : "suggestId"
    ,"location" : map
});

ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
var str = "";
    var _value = e.fromitem.value;
    var value = "";
    if (e.fromitem.index > -1) {
        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }    
    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
    
    value = "";
    if (e.toitem.index > -1) {
        _value = e.toitem.value;
        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }    
    str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
    G("searchResultPanel").innerHTML = str;
});

var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
var _value = e.item.value;
    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
    
    setPlace();
});

function setPlace(){// 创建地址解析器实例
var myGeo = new BMap.Geocoder();// 将地址解析结果显示在地图上,并调整地图视野
myGeo.getPoint(myValue, function(point){
  if (point) {
    map.centerAndZoom(point, 16);
    map.addOverlay(new BMap.Marker(point));
  }
}, "北京");
}
</script>
</body>
</html>