<%@page import="board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>정부 지원금 바로 지금</title>
    
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


    #bottomContent {
        margin-top: 30px;
    }

    #infoTable {
        width: 80%;
        border: 1px solid black;
        border-collapse: collapse;

    }

    #infoTable th {
        background-color: whitesmoke;
        border: 1px solid black;
    }

    #infoTable td {
        border: 1px solid black;
        text-align: center;
    }
</style>

<body>
    <%@ include file="../Common/header.jsp" %>
    <section>
        <aside>
            <h2><a href="${pageContext.request.contextPath}/adminPage.ad">
            	관리자
            </h2></a>
            <hr>
            <div>
                <dl>
                    <dt><a href="${pageContext.request.contextPath}/memList.ad">
                            <h3>회원 목록 조회</h3>
                        </a></dt>
                    <dt><a href="${pageContext.request.contextPath}/spList.ad">
                            <h3>지원 정책 신청</h3>
                        </a></dt>
                    <dt><a href="${pageContext.request.contextPath}/eaList.ad">
                            <h3>대외 활동 신청</h3>
                        </a></dt>
                </dl>
            </div>
        </aside>

        <div id="main_section" align="center">
            <h2>
                <div id="subTitle"><b>지원 정책 신청</b></div>
            </h2>
            <hr>
            <div id="bottomContent">
            	<form action = "<%= request.getContextPath() %>/searchSupport.ad">
                <div id="searchDiv">
                    <select name="opt">
                    	<option value="전체">전체 목록</option>
                        <option value="승인 대기">승인 대기</option>
                        <option value="승인 완료">승인 완료</option>
                    </select>
                    <button type="submit">검색</button>
                </div>
                </form>
                <div id="tableDiv">
                	<table class="contentsTable" width="800px" align="center">
	                	<thead>
	                        <tr>
	                            <th>번호</th>
	                            <th>제목</th>
	                            <th>카테고리</th>
	                            <th>작성자</th>
	                            <th>게시 날짜</th>
	                            <th>조회수</th>
	                            <th>승인상태</th>
	                        </tr>
	            		</thead>
	            		<tbody>
							<% if(list.isEmpty()){ %>
							<tr>
								<td colspan="7">글이 존재 하지 않습니다.</td>
							</tr>
						<% } else { %>
						<%		for (Board b : list) { %>
							<tr>
								<td><input type="hidden" value="<%=b.getBoardNo()%>">
									<%=b.getBoardNo()%></td>
								<td><%=b.getBoardTitle()%></td>
								<td><%=b.getCgName() %></td>
								<td><input type="hidden" value="<%=b.getBoardWriterNo()%>">
									<%=b.getBoardWriter()%></td>
								<td><%=b.getBoardCreateDate()%></td>
								<td><%=b.getBoardViewCount()%></td>
								<td><input id="enroll" type="hidden" value="<%=b.getEnrollState()%>">
									<%=b.getEnrollState()%></td>
							</tr>
						<%		} %>
						<%	} %>
						</tbody>
                    </table>
                    <%@ include file="../Common/page.jsp" %>
                </div>
            </div>
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
	                var enroll = $(this).parent().children().children('#enroll').val();
	                <% if(loginUser != null){%>
	            		if(enroll == 'N'){
		            		location.href = '<%= request.getContextPath() %>/spDetail.cm?bId=' + bId;
	            		} else {
	            			location.href = '<%= request.getContextPath() %>/detail.sp?bId=' + bId;
	            		}
	            <% }else{ %>
	            		alert('회원만 이용할 수 있는 서비스입니다.')
	            <% } %>
	         });
	         <% } %>
		});
    </script>
    </section>
    <%@ include file="../Common/footer.jsp" %>
</body>

</html>