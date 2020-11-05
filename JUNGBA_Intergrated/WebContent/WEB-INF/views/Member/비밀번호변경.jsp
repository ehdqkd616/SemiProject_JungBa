<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>정부지원금 바로지금</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script> 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">
</head>
<style>
html, body {
	margin: 0px;
	height: 100%;
	min-width: 505px;
}

.lagefont {
	color: rgb(15, 76, 130);
}

section {
	display: flex;
	margin: 0 auto;
	width: 80%;
	font-family: "fantasy";
	position: relative;
	min-height: 80%;
	line-height: 40px;
}

aside {
	min-height: 100%;
	float: left;
	display: block;
	width: 200px;
	line-height: 40px;
	margin-left: 5px;
}

#main_section {
	width: 100%;
	min-height: 100%;
	margin: 0px 50px;
}

a {
	text-decoration: none;
	color: black;
}

hr {
	color: black;
	background-color: black;
	height: 1px;
	margin: 0px;
	border: 1px;
}

.pagination a{
    padding: 8px 16px;
    text-decoration: none;
    border: 1px solid black;
    color: black;
    border-radius : 3px;
}    
.pagination a:hover:not(.action){
    background-color: gray;
}

/* pagination 설정부  */
.com{text-indent : 20px;}

 	.btn{
  		background-color : #E3F2FD;
  		margin : 10px 2px;
  		padding: 10px;
  	}
    #pwsearchbutton{background-color:darkgray; border-radius: 3px; border:none;}
    .box{
   	 text-align: center;
   	 background-color: rgb(248, 248, 248); ;
   	}
    
</style>
<body>
	<%@ include file="../Common/header.jsp" %>
	    <section>
		    <aside>
				<h2>마이페이지</h2>
				<hr>
				<div>
					<h2>나의게시물</h2>
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath}/myPageSupport.me">
								<h3>지원정책 게시판</h3>
							</a>
						</dt>
						<dt>
							<a href="${pageContext.request.contextPath}/myPageExternal.me">
								<h3>대외활동 게시판</h3>
							</a>
						</dt>
						<dt>
							<a href="${pageContext.request.contextPath}/myPageCommuFree.me">
								<h3>자유게시판</h3>
							</a>
						</dt>
						<dt>
							<a href="${pageContext.request.contextPath}/myPageQA.me">
								<h3>Q / A</h3>
							</a>
						</dt>
					</dl>
				</div>		
				<a href="${pageContext.request.contextPath}/memberInfo.me">
					<h2>회원정보</h2>
				</a>
				<a href="${pageContext.request.contextPath}/changePwdForm.me">
					<h2>비밀번호 변경</h2>
				</a>
				<a href="${pageContext.request.contextPath}/deleteForm.me">
					<h2>회원탈퇴</h2>
				</a>
			</aside>
		
        	<div id="main_section">

            	<div class="box">
	            	<form action="<%=request.getContextPath()%>/changePwd.me" method="post">
	            	<input type="hidden" name="id" value="<%= loginUser.getMemberId() %>">
	            	<input type="hidden" name="id" value="<%= loginUser.getMemberId() %>">		       
		            		<br>
							<h1><%= loginUser.getMemberId() %>님</h1>
							<h1> 새로운 비밀번호를 입력해주세요.</h1>
							<table align="center" >	
								<tr>
									<td>비밀번호 : </td>
									<td align="left" ><input style="width:200px" type="password"  id="userPwd1" name="userPwd1" placeholder="비밀번호를 입력해주세요"></td>
									<td style="width:180px"><label name ="pwd1Result" id="pwd1Result"></label></td>
								</tr>
								<tr>
									<td></td>
									<td colspan=2><span class="info">6~12자로 영어와 숫자, !*$만 가능합니다.(영어로 시작)</span></td>
							  	</tr>
								<tr>
									<td>비밀번호 확인:</td>
									<td align="left"><input style="width:200px" type="password" width="200px" id="userPwd2" name="userPwd2" placeholder="비밀번호를 다시 입력해주세요"></td>
									<td><label id="pwd2Result"></label></td>
								</tr>
							</table>
							<p>
		                        <input type="submit" style="width: 60pt; height: 30pt" id="pwsearchbutton"  value="확인" disabled=""	>
		                        <input type="button" style="width: 60pt; height: 30pt" onclick="location.href='javascript:history.go(-1);'" value="취소">
		                    </p>
		                    <br>
						
					</form>
					<script>

						$("#userPwd1").change(function(){
							var regExp = /^[a-zA-Z]([a-zA-Z0-9\!\*\$]){5,11}$/;
							
							if(!regExp.test($(this).val())){
								$("#pwd1Result").text("불가능한 비밀번호 입니다.").css("color","red");
								$(this).focus().css("background","red");
							}else{
								$("#pwd1Result").text("가능한 비밀번호 입니다.").css("color","green");
								$(this).css("background","initial");
							}
						});
						
						
						$("#userPwd2").keyup(function(){
							if($("#userPwd1").val() != $(this).val()){
								$("#pwd2Result").text("비밀번호 불일치").css("color","red");
								$('#pwsearchbutton').attr('disabled', true);
								isPw2 = false;
							}else{
								$("#pwd2Result").html("비밀번호가 일치합니다.").css("color","green");
								$('#pwsearchbutton').attr('disabled', false); 
								isPw2 = true;
							}
						});
					</script>
				</div>
        	</div>
    	</section>
	 <%@ include file="../Common/footer.jsp" %>
</body>
</html>