<%@page import="board.model.vo.FileVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="oracle.net.aso.*"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("board"); 
	System.out.println("공지사항글수정 에서의 Board : " + b);	
	
	String categoryStr = b.getCgName();
	String[] category = new String[2];
	String[] splitStr = categoryStr.split(",");
	for(int i = 0; i < splitStr.length; i++){
		switch(splitStr[i]){
		case "필독" : category[0] = "selected"; break; 
		case "공지" : category[1] = "selected"; break; 
	}
} 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>정부지원금 바로지금1</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/게시글수정.css"/>

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

    .baside{
  	margin-top: 20px;
  	margin-bottom: 40px;
  }
    section{
  	width: 950px;
  }
  textarea{
  	width:100%
  }
 .cgname{
  text-align:center
 }
 #ftextarea{
 	margin-top:0px;
 }
 #bcategory{
 	margin-top: 30px;
  	margin-bottom: 30px;	
 }
 #ftextarea{
 	margin-top: 15px;
 }
    </style>
</head>
  
<body>
	<%@ include file="../Common/header.jsp" %>
     <section>
        <aside>		   
		   <h2>공지사항</h2>
		   <hr>
			<div class=baside><h2>제목</h2></div>
	        <div class=baside><h2>카테고리</h2></div>
	        <div class=baside><h2 id="content">내용</h2></div>
           <br><br><br><br><br><br>
        </aside>
        <div id="main_section">
  		
        
            <form action="<%= request.getContextPath() %>/modify.no" method="post">
			<h2 style="text-align:center;">공지사항 수정</h2>
			<input type="hidden" size="50" name="no" value="<%= b.getBoardNo() %>">
			<hr />
			<h2>
			<input type="text" size="50" name="title" value="<%= b.getBoardTitle() %>">
			</h2>
			<h2>
           	 	<select name="category" id="bcategory">
               	 	<option value="필독" <%= category[0] %>>필독</option>
                	<option value="공지"<%= category[1] %>>공지</option>
            	</select>
            	
            	<h2><textarea id="textarea" name="content" cols="100" rows="15" style="resize:none;"><%= b.getBoardContent() %></textarea></h2>
            	<hr />
            	<h2>
			  <div align="right">
               <input type="submit" class="btn" id="insertNoBtn" value="등록">
               <input type="button" class="btn" onclick="location.href='javascript:history.go(-1);'" id="cancelBtn" value="취소">
            </div>
			</h2>
            </form>
        </div>
       </section>
		<br>
	<%@ include file="../Common/footer.jsp" %>
</body>
</html>