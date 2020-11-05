<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
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
.contentsTable{text-align:center; font-size:12pt;}
.contentsTable thead{background-color:rgb(15, 76, 130); color:white;}
.contentsTable tbody{background-color:#F7F7F7}
#tablename{align-self:center;}
#게시판이름{text-align:center;}

  
  #filetext{
  		margin-top : 220px;
  	}
  </style>
  <body>
    <%@ include file="../Common/header.jsp" %>
    <section>
      <aside>
        <h2>묻고답하기</h2>
        <hr />
        <h2>제목</h2>
        <h2>카테고리</h2>
        <h2>내용</h2>
      </aside>
      <div id="main_section">
	      <form action="<%= request.getContextPath() %>/insert.qa" method="post" encType="multipart/form-data">
			 <h2 style="text-align: center;">질문하기</h2>
			<hr>
			<h2>
			<input type="text" size="50" name="title" placeholder="제목을 입력해주세요.">
			</h2>
			<h2>
			  <select name="category" class="combo_category">
			    <option value="이용관련">이용관련</option>
			    <option value="회원관련">회원관련</option>
			    <option value="게시글등록">게시글 등록</option>
			    <option value="이벤트">이벤트</option>
			    <option value="신고">신고</option>
			    <option value="기타">기타</option>
			  </select>
			</h2>
			<h2><textarea id="textarea" name="content" placeholder="내용을 입력해주세요." cols="100" rows="15" style="resize:none;"></textarea></h2>
			<hr>
			<!-- 파일 업로드 하는 부분 -->
			<div id="fileArea">
				사진 첨부<input type="file" id="file1" multiple="multiple" name="imageFile"><br>
				첨부 파일<input type="file" id="file2" multiple="multiple" name="uploadfile1"><br>
				첨부 파일<input type="file" id="file3" multiple="multiple" name="uploadfile2"><br>
			</div>			
			<hr>
			<h2>
			  <div align="right">
               <input type="submit" id="insertNoBtn" value="등록">
               <input type="button" onclick="location.href='javascript:history.go(-1);'" id="cancelBtn" value="취소">
            </div>
			</h2>
	       </form>
      </div>
    </section>
    <%@ include file="../Common/footer.jsp" %>
  </body>
</html>
