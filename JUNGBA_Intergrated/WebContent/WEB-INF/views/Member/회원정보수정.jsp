<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정</title>
    <script  src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script> 
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
    .box{
    	width:80%;
   		text-align: center;
   		background-color: rgb(248, 248, 248); ;
   	}
    #topic{
        border-bottom:1.5px solid black;
    }
    table{
		border-spacing: 0px 0px;
    }
    tr td{
    	 border: 1px solid #444444;
    	 /* border-bottom: 1px solid #444444; */
    }
    
    td:nth-child(3) {
    	border:1px solid white;
    	 width:200px;
    }
    td:nth-child(2) {
   		 background-color: #bbdefb;
   		 width:300px;
  	}
  	
  	td:nth-child(1) {
    	background-color: #e3f2fd;
    	width:170px;
  	}

  
  
</style>
<body>
   <%@ include file="../Common/header.jsp" %>
    <section>
		<aside>
			<h2>마이페이지</h2>
			<hr>
			<div >
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
		
        <div id="main_section" align="center">
            <div class="outer" >
                <div class="box">
                <h2 align="center">회원정보 수정</h2>
                
                <form action="<%= request.getContextPath() %>/update.me" method="post" id="updateForm" name="updateForm">
               		<input type="hidden" id="no" name="no" value="<%= loginUser.getMemberNickName() %>">
                    <table align="center">
                        <tr>
                            <td width="150px"><label class="must">*</label> 아이디</td>
                            <td width="200px">
                            	<input type="text" id="myId" name="myId" value="<%= loginUser.getMemberId() %>" style="background:lightgray;" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="must" >*</label> 이름 </td>
                            <td><input type="text" id="myName"  name="myName" value="<%= loginUser.getMemberName() %>" required></td>
                            <td><label id="nameResult"></label></td>
                        </tr>
                        <tr>
                        	<td><label class="must">*</label>닉네임</td>
                            <td class="middletable"><input type="text" name="myNickName" id="myNickName" value="<%= loginUser.getMemberNickName() %>"  required></td>
                            <td><label id="nickNameResult"></label></td>
                        </tr>
                        <tr>
                            <td><label class="must">*</label>성별</td>
                            <td> <input type="radio"  name="myGender" id="myGenderM" value="M" checked>남자
                                 <input type="radio"  name="myGender" id="myGenderW" value="W" >여자</td>	
                        </tr>
                        <tr>
                            <td><label class="must">*</label>생년월일</td>
                            <td class="middletable"><input type="date" name="myBirthDay" id="myBirthDay" value="<%=loginUser.getMemberBirthDay()%>" required></td>
                        </tr>
                        <tr>
                            <td>연락처</td>
                            <td class="middletable">
                            	<input type="tel" maxlength="11" id="myPhone" name="myPhone" value="<%=loginUser.getMemberPhone()%>" class="phone">
                            </td>
                            <td><label id="phoneResult"></label></td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td><input type="email" id="myEmail" name="myEmail" value="<%= loginUser.getMemberEmail() %>" ></td>
                        </tr>
						<tr>
							<td>주소</td>	
							<td><input type="text" style="widht:100px; height:50px" id="myAddress" name="myAddress" value="<%=loginUser.getMemberAddress()%>" ></td>
						</tr>
                    </table>
                    <script>
					$(function(){
						$("#myName").keyup(function(){
							var regExp = /[가-힣]{2,}/;
							if(!regExp.test($(this).val())){
								$("#nameResult").text("알맞은 이름을 입력하세요").css("color","red");
								$(this).focus().css("background","red");
								isName = false;
							}else{
								$("#nameResult").text("정상입력").css("color","green");
								$(this).focus().css("background","white");
								isName = true;
							}
						});
						
/* 						$("#myName").change(function(){
							var result = overlapCheck("",$(this).val());
							var name = $('#myName');
							
							if(!loginUser.getMemberName().equals(name)){
								if(result==false){
									$("#nickNameResult").text("닉네임 중복입니다.").css("color","red");
									$(this).focus().css("background","red");
									isId = false;	
								}else{
									$("#nickNameResult").text("사용 가능한 닉네임 입니다").css("color","green");
									$(this).css("background","white");
									isId = true;
								}
							}else{
								$("#nickNameResult").text("사용 가능한 닉네임 입니다").css("color","green");
								$(this).css("background","white");
								isId = true;
							}
						}); */
						
						$("#myPhone").change(function(){
							var regExp = /[0-9]/;
							if(!regExp.test($(this).val())){
								$("#phoneResult").text("숫자를 입력하세요").css("color","red");
								$(this).focus().css("background","red");
								isPhone = false;
							}else{
								$("#phoneResult").text("정상 입력").css("color","green");
								$(this).css("background","white");
								isPhone = true;
							}
						});
					})
					</script>
					<script>
            		 	$('#myNickName').change(function(){
            				var nickName = $('#myNickName');
            				var no = $('#no')
            				console.log(nickName);
            				
							if(!nickName || nickName.val().length <3 ){
								$("#nickNameResult").text("3글자 이상 가능합니다.").css("color","red");
								$("#myNickName").css("background","red");
            				}else{  
            					$.ajax({
            						url: "<%= request.getContextPath() %>/checkNickName.me",
            						type: 'post', 
            						data: {nickName:nickName.val() , no:no.val()},
            						success: function(data){
            							console.log(data);
            							
	            						if(data =="success"){
	            							$("#nickNameResult").text("정상 입력").css("color","green");
	            							$("#myNickName").css("background","white");
	            						}else{
	            							$("#nickNameResult").text("사용 불 가능합니다.").css("color","red");
	            							$("#myNickName").css("background","red");
	            						}		
            						}
            					});		
            				  }  
            			})//성강버젼	

                    </script>
					<br>
                    <div class="btns" align="center">
						<input type="submit" style="width: 60pt; height: 30pt" value="수정">
		            	<input type="button" style="width: 60pt; height: 30pt" onclick="location.href='javascript:history.go(-1);'" value="취소">
                    </div>
                    <br>
                </form>
            	</div>
        	</div>
        	<br>
    	</div>

    </section>
   <%@ include file="../Common/footer.jsp" %>
</body>
</html>
