<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	session.removeAttribute("selectedId");
	request.getSession().invalidate();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<META name=GENERATOR content="MSHTML 8.00.6001.19258">
<title>阿姨来了CMS</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"  />
<link rel="stylesheet" type="text/css" href="css/styles.css">


<script type="text/javascript" src="js/jquery.js" ></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){  
    //点击图片更换验证码
    $("#Verify").click(function(){
      $(this).attr("src","SecurityCodeImage?timestamp="+new Date().getTime());
    });
    $("#login-button").click(function(){
    		if ($("#accountId").val() == "") {
			   
				alert("请输入您的用户名!");
				return false;
			}
			if ($("#password").val() == "") {
				alert("请输入您的密码!");
				return false;
			}
			if ($("#checkcode").val() == "") {
			
				alert("请输入您的验证码!");
				return false;
			}
			
			$.ajax({
				url: "Login_Login",
				type: "post",
				data: {"accountId":$("#accountId").val(),"password":$("#password").val(),"checkcode":$("#checkcode").val()},
				cache: false,
				success: function(result){
					var res = eval("(" +result+ ")");
					if(res.success=="true")
						window.location.href = "Main";
					else
						alert(res.msg);
				},
				error: function(result){
					alert("服务端异常！");
				}
			});
	 	});
	});
</script>
</head>

<body>
	<div class="wrapper">
		<div class="container">
			<h1 style="text-align:center;">Welcome</h1>
			<form class="form" name="form" id="form"  method="post">
				<div id="text">
					<INPUT id=accountId class=input_login maxLength=20 size=15 name=accountId placeholder="账号">
					<INPUT id=password class=input_login maxLength=20 size=15 type=password name=password placeholder="密码">
				</div>
				<div id="verify" class="row" >
					<INPUT class="col-xs-6" maxLength=4 size=14 name=checkcode id=checkcode 
						style="width: 150px;  display:inline-block; float:none; 
						text-align:left; margin-right:-4px;"
						placeholder="验证码">
					<img class="col-xs-6" src="SecurityCodeImage" id="Verify" 
						style="cursor:hand; width: 100px;  display:inline-block;
                		float:none; text-align:left; margin-right:-4px;" 
                		alt="看不清，换一张" />
				</div>
				<div id="submit">
					<button type="submit" id="login-button">登录</button>
				</div>
			</form>
		</div>
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>


	</div>

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		$('#login-button').click(function(event) {
			event.preventDefault();
			$('form').fadeOut(500);
			$('.wrapper').addClass('form-success');
		});
	</script>

</body>
</html>
