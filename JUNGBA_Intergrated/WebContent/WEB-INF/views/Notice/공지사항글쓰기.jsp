<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>정부지원금 바로지금1</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/side.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/공지사항글쓰기.css"/>

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

            #h21{ text-align: center;} 
        #h22{ margin-top: 290px}
        #text1{width:50%; height:30px; margin-left:20px ;margin-top:20px}
        #textarea{width:80%; height:300px ; margin-top:20px; margin-left:20px;}
        #select1{width:20%; height:30px; margin-top:20px; margin-left:20px;}
        #file{  margin-top:20px; margin-left:20px; }
        #buttons1{ text-align: center; margin-left:20px; margin-top:30px}
        #button1{
            background-color: skyblue; 
            border:none; 
            padding: 15px 30px;   
            border-radius : 3px;
        }
        #button2{
            border:none; 
            padding: 15px 30px;   
            border-radius : 3px;
        }
    </style>
</head>
  
<body>
	<%@ include file="../Common/header.jsp" %>
     <section>
        <aside>
           <h2> 공지사항</h2>
           <hr>
           <h2>제목</h2>
           <h2>카테고리</h2>
           <h2>내용</h2>
           <br><br><br><br><br><br>
           <h2>첨부파일</h2>
        </aside>
        <div id="main_section">
            <form action="<%= request.getContextPath() %>/insert.no" method="post" encType="multipart/form-data">
            	<h2 style="text-align: center;">공지사항 제목</h2>
            	<hr>
           	 	<input type="text" id="text1" placeholder="제목을 입력해주세요." name="title"><br clear="all"> 
            	<select id="select1" name="category">
               	 	<option value="필독">필독</option>
                	<option value="공지">공지</option>
            	</select>
            	<textarea id="textarea" name="content" placeholder="내용을 입력해주세요."></textarea><br>
            	<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
					사진 첨부<input type="file" id="file1" multiple="multiple" name="imageFile"><br>
					첨부 파일<input type="file" id="file2" multiple="multiple" name="uploadFile1"><br>
					첨부 파일<input type="file" id="file3" multiple="multiple" name="uploadFile2"><br>
				</div>
            	<div id="buttons1">
                	<input type="submit" id="button1" value="등록">
               		<input type="button" id="button2" onclick="location.href='javascript:history.go(-1);'" id="cancelBtn" value="취소">
            	</div>
            </form>
        </div>
       </section>
		<br>
	<%@ include file="../Common/footer.jsp" %>
</body>
</html>