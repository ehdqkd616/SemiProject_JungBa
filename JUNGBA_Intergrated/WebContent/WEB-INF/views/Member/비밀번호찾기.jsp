<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 기본틀</title>
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
  	 width: 80%;
  	 padding: 2% 10%;
   	 text-align: center;
   	 background-color: rgb(248, 248, 248); ;
   	}
    
</style>
<body>
   <%@ include file="../Common/header.jsp" %>
    <section>
    
        <div id="main_section">
            <div class="box">
                <form action="<%=request.getContextPath()%>/searchPwd.me" method="post">
                        <h1 align="center">비밀번호 찾기</h1>
                        
                        <table align="center">	
                            <tr>
                                <td style = text-align:right>이름 :</td>
                                <td><input type="text" id="userName" name="userName" placeholder="이름을 입력해주세요"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td style = text-align:right>ID :</td>
                                <td><input type="text" id="userId" name="userId" placeholder="ID를 입력해주세요"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td style = text-align:right><input type=radio value="phone" name=pwsearchradio checked>휴대폰 번호 :</td>
                                <td><input type="phone" id="userPhone" name="userPhone" placeholder="'-' 없이 입력해주세요"></td>
                            </tr>
                            <tr>
                                <td style = text-align:right><input type=radio value="email" name=pwsearchradio>이메일 :</td>
                                <td><input type="email" id="userEmail" name="userEmail"  placeholder="이메일을 입력해주세요"></td>
                            </tr>
                        </table>
                        <p align="center">
                        	<input type="submit" id="pwsearchbutton" style="width: 60pt; height: 30pt" value="확인"	>
                        	<input type="button" style="width: 60pt; height: 30pt" onclick="location.href='javascript:history.go(-1);'" value="취소">
                        </p>                    
                </form>
			</div>
        </div>
    </section>
    <%@ include file="../Common/footer.jsp" %>
</body>
</html>