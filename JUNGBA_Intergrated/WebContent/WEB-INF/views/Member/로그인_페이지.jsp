<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">
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
.contentsTable{text-align:center; font-size:12pt;}
.contentsTable thead{background-color:rgb(15, 76, 130); color:white;}
.contentsTable tbody{background-color:#F7F7F7}
#tablename{align-self:center;}
#게시판이름{text-align:center;}


#ptag>a{
   text-decoration: none;
}
section{
   display: block;
}
.loginbox{
   width: 80%;
   padding: 2% 10%;
   text-align: center;
   /* margin-top: 10%; */
   background-color: rgb(248, 248, 248); ;
}
table{
   margin : 0px auto;
}
.logintext{
   height: 30px;
   width: 200px;
}
#loginButton{
   height: 80px;
}


</style>
</head>
<body>
<%@ include file="../Common/header.jsp" %>
   <section id=loginSection>
      <div>
         <form action="<%=request.getContextPath()%>/login.me" method="post">
         <div class="loginbox">
         <input type="hidden" name="email" value="">
            <h1 style="margin:0px;">로그인</h1>
            <table>
               <tr>
                  <td><input type="text" name="userId" id="userId" class="logintext" placeholder="아이디를 입력해주세요" autofocus tabindex="1"></td>
                  <td rowspan="2"><button type="submit"id=loginButton autofocus tabindex="3">로그인</button></td>
               </tr>
               <tr>
                  <td><input type="password" name="userPwd" id="userPwd" class="logintext" placeholder="비밀번호를 입력해주세요" autofocus tabindex="2"></td>
               </tr>
            </table>
            <p align="center" id="ptag">
               <a href="<%=request.getContextPath()%>/searchIdForm.me">아이디 찾기</a> | <a href="<%=request.getContextPath()%>/searchPwdForm.me">비밀번호 찾기</a> | <a href="<%=request.getContextPath()%>/insertForm.me">회원가입</a>
            </p>
            <a id="login-form-btn" href="javascript:loginFormWithKakao()">
  <img
    src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
    width="222"
  />
</a>
         </div>
         </form>
      </div>

<p id="login-form-result"></p>
<script type="text/javascript">
	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
	Kakao.init('d1a381394433d1a528af9cc55303a286');
	
	// SDK 초기화 여부를 판단합니다.
	console.log(Kakao.isInitialized());
  function loginFormWithKakao() {
    Kakao.Auth.loginForm({
      success: function(authObj) {
//         showResult(JSON.stringify(authObj))
        Kakao.API.request({
            url: '/v2/user/me',
            success: function(response) {
                console.log(response);
//                 console.log(response.kakao_account.email);
//                 console.log(response.properties.nickname);
                kakaoLogin(response.kakao_account.email,response.properties.nickname);
            },
            fail: function(error) {
                console.log(error);
            }
        });},
      fail: function(err) {
        showResult(JSON.stringify(err))
      },
    })
  }
  function showResult(result) {
    document.getElementById('login-form-result').innerText = result
  }
  
  function kakaoLogin(email,name){
	  location.href = '<%=request.getContextPath()%>/kakaoLogin.me?email='+ email +'&name=' + name;
  }
</script>
   </section>

   <hr>
   <%@ include file="../Common/footer.jsp" %>
</body>
</html>

