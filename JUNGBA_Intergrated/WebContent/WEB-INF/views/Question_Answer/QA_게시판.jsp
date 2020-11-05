<%@page import="board.model.vo.PageInfo"%>
<%@page import="board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Q&A 게시판</title>
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

    
</style>
<body>
    <%@ include file="../Common/header.jsp" %>
    <section>
        <aside>
           <h2>묻고 답하기</h2>
            <hr>
            <dl>
               <dt>
                    <a href="<%=request.getContextPath()%>/main.fa"><h3>FAQ</h3></a>
                </dt>
                <dt>
                    <a href="<%=request.getContextPath()%>/main.qa"><h3>Q/A</h3></a>
                </dt>
            </dl>
        </aside>
        <div id="main_section">
            <h2 align="center">Q/A</h2>
            <table class="contentsTable" width="800px" align="center">
                <thead>
                    <tr>
                        <th width= "50px">번호</th>
                        <th width= "100px">카테고리</th>
                        <th width= "400px">제목</th>
                        <th width= "100px">작성자</th>
                        <th width= "100px">날짜</th>
                        <th width= "60px">조회</th>
                    </tr>
                </thead>
                <tbody>
                  <% if(list.isEmpty()){ %>
	            <tr>
	               <td colspan="6">글이 존재 하지 않습니다.</td>
	            </tr>
            <% } else { %>
            <% 		for(Board b : list){ %>
            <% System.out.println(b); %>
          	<tr>
          		<td>
          			<input type="hidden" value="<%= b.getBoardNo() %>">
          			<%= b.getBoardNo() %>
          		</td>
          		<td><%= b.getCgName() %></td>
          		<td><%= b.getBoardTitle() %></td>
          		<td><%= b.getBoardWriter() %></td>
          		<td><%= b.getBoardCreateDate()  %></td>
          		<td><%= b.getBoardViewCount()  %></td>
          	</tr>
            <% 		} %>
            <% } %>
                </tbody>
                <tfoot>
                	<tr>
	                <td colspan="2"></td>
	                <td>
	                <form action = "<%= request.getContextPath() %>/search.qa">
	                	<select name="opt"> <!-- 검색 컬럼 -->
	                		<option value="all">전체 목록</option>
	                		<option value="writer">작성자</option>
	                		<option value="title">제목</option>
	                		<option value="content">내용</option>
	                		<option value="title_content">제목+내용</option>
	                		<option value="category">카테고리</option>
	                	</select>
	                	<input type="text" name="word" value="" placeholder="검색어를 입력하세요">
	           			<button type="submit">검색</button>
	           		</form>
	           		</td>
	           		<td colspan="2"></td>
	                <td>
	                    <% if(loginUser != null){ %>
						<button onclick="location.href='<%= request.getContextPath()  %>/insertForm.qa'">글쓰기</button>
						<% } %>
	                </td>
	                </tr>
                </tfoot>
            </table>
 		<%@ include file="../Common/page.jsp" %>
		</div>
        <script>
        $(function(){
        	<%if(!list.isEmpty()){%> 
	            $('tbody td').mouseenter(function(){
	               $(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
	            }).mouseout(function(){
	               $(this).parent().css('background', 'none');
	            }).click(function(){
	               var bId = $(this).parent().children().children('input').val();
	                  location.href = '<%= request.getContextPath() %>/q_detail.qa?bId=' + bId;
	            })
	        <% } %>
         })
      </script>
    </section>
   <%@ include file="../Common/footer.jsp" %>
</body>
</html>



 