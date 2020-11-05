<%@page import="java.util.ArrayList"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* 	Member loginUser = (Member)request.getAttribute("loginUser");
	System.out.println("내용확인.jsp에서의 : " + loginUser); */
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원탈퇴</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">
</head>
<style>

    #topic{
        border-bottom:1.5px solid black;
    }
  
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
          	<form action="<%=request.getContextPath()%>/Delete.me" method="post">
            <table align = center>
                <tr>
                    <th><%= loginUser.getMemberName() %>.님</th>
                </tr>
                <tr id="a">
                    <td>
                        <h3>탈퇴를 진행하시려면 비밀번호를 입력해주세요</h3>
                        <input type="hidden" name="userId" value="<%= loginUser.getMemberId() %>">
                        <input type="password" name="userPwd" id="userPwd" placeholder="비밀번호를 입력해주세요." size="30">
                        <input type="submit" name="subPwd" value="탈퇴" >
                	</td>
                </tr>
            </table>
            </form>
        </div>
        
    </section>
    
    <%@ include file="../Common/footer.jsp" %>
</body>
</html>
