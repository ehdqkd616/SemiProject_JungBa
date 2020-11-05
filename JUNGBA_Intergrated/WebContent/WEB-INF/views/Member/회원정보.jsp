<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정</title>
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
.contentsTable{text-align:center; font-size:12pt;}
.contentsTable thead{background-color:rgb(15, 76, 130); color:white;}
.contentsTable tbody{background-color:#F7F7F7}
#tablename{align-self:center;}
#게시판이름{text-align:center;}


	section {
      display: flex;
      line-height: 40px;
    }
    #main_section {
      width: 100%;
    }
    .box{
    	width:65%;
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
					<dt >
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
			<a href="${pageContext.request.contextPath}/changePwdCf.me">
				<h2>비밀번호 변경</h2>
			</a>
			<a href="${pageContext.request.contextPath}/deleteForm.me">
				<h2>회원탈퇴</h2>
			</a>
		</aside>
		
        <div id="main_section" align="center">
                <div class="box">
                <h2 align="center">회원정보</h2>
                
                <form action="<%= request.getContextPath() %>/updateForm.me" method="post" id="updateForm" name="updateForm">
                    <table id='table' align="center">
                        <tr>
                            <td style="width:150px;">아이디</td>
                            <td style="width:200px"><%= loginUser.getMemberId() %></td>
                        </tr>
                        <tr>
                            <td> 이름 </td>
                            <td><%= loginUser.getMemberName() %></td>
                        </tr>
                        <tr>
                        	<td> 닉네임 </td>
                            <td><%= loginUser.getMemberNickName() %></td>
                        </tr>
                        <tr>
                        	<td> 성별 </td>
                        	<td> <%= loginUser.getMemberGender() %>
                        	</td>
                        </tr>
                        <tr>
                            <td> 생년월일 </td>
                            <td><%= loginUser.getMemberBirthDay() %> </td>
                        </tr>
                        <tr>
                        	<td> 연락처 </td>
                        	<td><%= loginUser.getMemberPhone() %> </td>
                        </tr>
                        <tr>
                            <td> 이메일 </td>
                            <td><%= loginUser.getMemberEmail() %> </td>
                        </tr>
                        <tr>
                            <td> 주소 </td>
                            <td><%= loginUser.getMemberAddress() %> </td>
                        </tr>
                    </table>
					<br>
                    <div class="btns" align="center">
						<input type="submit" style="width: 60pt; height: 30pt" value="수정">
		            	<input type="button" style="width: 60pt; height: 30pt" onclick="location.href='javascript:history.go(-1);'" value="취소">
                    </div>
					<br>
                </form>
            	</div>
            	<br>
        	</div>
    </section>
   <%@ include file="../Common/footer.jsp" %>
</body>
</html>
