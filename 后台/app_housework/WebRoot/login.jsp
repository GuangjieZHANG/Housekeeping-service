<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>请登录</TITLE>
<META content="text/html;charset=gb2312" http-equiv=Content-Type>
<script src="dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<LINK rel=stylesheet type=text/css href="css/login.css">
<SCRIPT language=JavaScript>
	$(document).ready(function(){  
       //点击图片更换验证码
       $("#Verify").click(function(){
         $(this).attr("src","SecurityCodeImage?timestamp="+new Date().getTime());
       });
       $("#submit").click(function(){
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
</SCRIPT>
<META name=GENERATOR content="MSHTML 8.00.6001.19258">
</HEAD>
<BODY marginleft=0 margintop=0 marginheight="0" marginwidth="0">
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" height="100%">
		<TBODY>
			<TR>
				<TD>
					<TABLE border=0 cellSpacing=0 cellPadding=0 width=526 align=center>
							<TBODY>
								<TR>
									<TD colSpan=2><IMG border=0 src="images/al_top.gif">
									</TD>
								</TR>
								<TR>
									<TD colSpan=2>
										<TABLE border=0 cellSpacing=0 cellPadding=0 width=526>
											<TBODY>
												<TR>
													<TD width=155><IMG border=0
														src="images/al_username.gif">
													</TD>
													<TD background=images/al_body_bg.gif width=105>
													   <INPUT
														id=accountId class=input_login maxLength=20
														size=15 name=accountId>
													</TD>
													<TD width=93><IMG border=0
														src="images/al_password.gif">
													</TD>
													<TD background=images/al_body_bg.gif width=105>
													   <INPUT
														id=password class=input_login maxLength=20 size=15
														type=password name=password>
													</TD>
													<TD width=68><IMG border=0
														src="images/al_body_right.gif">
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD colSpan=2>
										<TABLE border=0 cellSpacing=0 cellPadding=0 width=526>
											<TBODY>
												<TR>
													<TD width=77><IMG border=0
														src="images/al_end_left.gif">
													</TD>
													<TD width=339>
														<TABLE border=0 cellSpacing=0 cellPadding=0 width=339>
															<TBODY>
																<TR>
																	<TD height=49 background=images/al_end_bg.gif align=center>
																		<TABLE border=0>
																			<TBODY>
																				<TR class=tr_val>
																					<TD>验证码：</TD>
																					<TD class=a2>
																					<INPUT class=input_val maxLength=4 size=14 name=checkcode id=checkcode>
																					<img src="SecurityCodeImage" id="Verify"  style="cursor:hand;" alt="看不清，换一张"/>
																					</TD>
																				</TR>
																			</TBODY>
																		</TABLE>
																	</TD>
																</TR>
																<TR>
																	<TD><IMG border=0 src="images/al_end_end.gif">
																	</TD>
																</TR>
															</TBODY>
														</TABLE>
													</TD>
													<TD width=110>
														<INPUT border=0 src="images/al_end_right.gif" width=110 height=88 type=image id=submit />
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>						
							</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<DIV></DIV>
</BODY>
</HTML>
