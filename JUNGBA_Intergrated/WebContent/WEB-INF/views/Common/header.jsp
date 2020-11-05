<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	String msg = (String)session.getAttribute("msg");
%>

<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script> 
 
 <style>
header {
  top: 0;
  left: 0;
  right: 0;
  height: 300px;
}
a {
  text-decoration: none;
  color: black;
}
.header_top {
  width: 100%;
  height: 50px;
  background-color: rgb(15, 76, 130);
}
.top {
  display: flex;
  float: right;
  padding-right: 10%;
  list-style: none;
  padding-top: 4px;
}
.top_btn {
  margin: 1px;
  padding: 4px;
  border-radius: 4px;
  color: white;
}
.top_btn:hover {
  border-radius: 4px;
  color: black;
}
.lagefont {
  font-size: 60px;
}
.navbar {
  display: flex;
  width: 80%;
  margin: 0px auto;
  margin-top: 20px;
  justify-content: space-between;
  align-items: center;
}

.navbar_dropdown:hover .navbar_dropdown-content {
	background-color: white;
	display: block;
}
.navbar_dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 100px;
  max-width: 100px;
  box-shadow: 0px 0px 0px 2px rgba(0,0,0,0.2);
  text-align: center;
  font-size: 12px;
}
.navbar_dropdown-content a {
  color: black;
  padding: 5px 6px;
  text-decoration: none;
  display: inline-table;
  
}
.navbar_dropdown-content a:hover{
	 background-color: #f5f5f5;
  	font-weight: bold;
  	border-radius: 4px;
}
.navbar_menu {
  display: flex;
  list-style: none;
  padding: 40px 0px;
  padding-right: 150px;
}

.navbar_menu li {
  border-right: 1px solid silver;
  padding: 8px 12px; /* 마우스 선택영역이 넓어짐 */
}

.navbar_menu li:hover {
  background-color: #f5f5f5;
  font-weight: bold;
  border-radius: 4px;
}

@media screen and (max-width: 768px) {
header {
  height: auto;
}
  .navbar {
    flex-direction: column;
    align-items: center;
    padding: 0px;
  }

  .navbar_title {
    width: 100%;
    font-size: 0px;
    text-align: center;
    padding: 0px;
  }
  .navbar_menu {
    flex-direction: column;
    width: 100%;
    padding: 0px;
  }
  .navbar_menu li {
    border-right: 0px solid silver;
    width: 100%;
    text-align: center;
    font-size: 12px;
    padding: 8px 0px;
  }
  .top {
    position: absolute;
    right: 0px;
    padding: 0px;
  }
  .navbar_logo img {
    display: none;
  }
}
 </style>
 
</head>
<body>
   <header>
      <div class="header_top">
         <div class="top">
         <% if(loginUser == null) { %>
            <li><a href="<%=request.getContextPath()%>/insertForm.me" class="top_btn"><b>회원가입</b></a></li>
            <li><a href="<%=request.getContextPath()%>/loginPage.me" class="top_btn"><b>로그인</b></a></li>
         <% } else { %>
         	<li><label><%= loginUser.getMemberName() %>님의 방문을 환영합니다.</label></li>
         	<% if(loginUser.getMemberName().equals("관리자")) { %>
         		<li><a href="<%=request.getContextPath()%>/adminPage.ad" class="top_btn"><b>관리자 페이지</b></a></li>
         	<% } else { %>
         		<li><a href="<%=request.getContextPath()%>/myPage.me" class="top_btn"><b>마이페이지</b></a></li>
        	 <% } %>
         	<li><a href="<%=request.getContextPath()%>/logout.me" class="top_btn"><b>로그아웃</b></a></li>
         <% } %>
         </div>
      </div>
     <nav class="navbar">
         <div class="navbar_title">
            <a href="<%= request.getContextPath() %>/Main.mi"><span class="lagefont">정</span>부지원금<span class="lagefont">바</span>로 지금</a>
         </div>
         <div class="navbar_menu">
            <li><a href="<%= request.getContextPath() %>/main.no">공지사항</a></li>
            <li><a href="<%= request.getContextPath() %>/main.sp">지원정책</a></li>
             <li class="navbar_dropdown">
               <a href="<%= request.getContextPath() %>/fMain.cm" class="navbar_dropbtn">커뮤니티</a>
             <div class='navbar_dropdown-content'>
                 <a href="<%= request.getContextPath() %>/fMain.cm">자유게시판</a><br>
                 <a href="<%= request.getContextPath() %>/spMain.cm">지원정책</a><br>
                 <a href="<%= request.getContextPath() %>/eaMain.cm">대외활동</a>
             </div>
           </li>
            <li><a href="<%= request.getContextPath() %>/main.ea">대외활동</a></li>
            <li><a href="<%= request.getContextPath() %>/main.fa">묻고 답하기</a></li>
         </div>
         <div class="navbar_logo">
            <img id="logo" src="Media/logo.png" width = "80px" height = "80px">
         </div>
      </nav>
   </header> 
</body>
</html>