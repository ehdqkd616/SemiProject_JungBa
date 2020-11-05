<%@page import="board.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();	
	String pageName = pi.getPageName();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정부 지원금 바로 지금</title>
<style>
    .pagingArea button{background: white; border: 0; outline: 0; margin: 1px;}
	button:hover{cursor: pointer;}
	#numBtn{background: rgb(15, 76, 130); color: white; border-radius: 7px; width: 40px; heigth: 40px; padding: 5px 0px;}
	#choosen{background: skyblue; color: white; width: 40px; border-radius: 7px; padding: 5px 0px;}
</style>
</head>
<body>
	<!--  페이징 -->
	<div class="pagingArea" align="center">
		<!-- 맨처음으로 -->
		<button class="paginbtn" onclick="location.href='<%= request.getContextPath()+pageName %>?currentPage=1'" id="firstBtn">처음</button>
		<script>
			if(<%= currentPage %> <=1){
				var before = $('#firstBtn');
				before.attr('disabled', 'true');
			}
		</script>
		<!-- 이전 페이지로 -->
		<button class="paginbtn" onclick="location.href='<%= request.getContextPath()+pageName %>?currentPage=<%= currentPage - 1 %>'" id="beforeBtn">이전</button>
		<script>
			if(<%= currentPage %> <=1){
				var before = $('#beforeBtn');
				before.attr('disabled', 'true');
			}
		</script>
		<!-- 10개 페이지 목록 -->
		<% for(int p = startPage; p<= endPage; p++){ %>
			<% if(p == currentPage){ %>
			<button id="choosen" disabled><%= p %></button>
			<% } else{ %>
				<button id="numBtn" onclick="location.href='<%= request.getContextPath()+pageName %>?currentPage=<%= p %>'"><%= p %></button>
			<% } %>
		<% } %>
		<!-- 다음 페이지로 -->
		<button class="paginbtn" onclick="location.href='<%= request.getContextPath()+pageName %>?currentPage=<%= currentPage + 1 %>'" id="afterBtn">다음</buuton>
		<script>
			if(<%= currentPage %> >= <%= maxPage %>){
				var after = $('#afterBtn');
				after.attr('disabled','true');
			}
		</script>
		
		<!-- 맨 끝으로 -->
		<button class="paginbtn" onclick="location.href='<%= request.getContextPath()+pageName %>?currentPage=<%= maxPage %>'" id="lastBtn">맨끝</button>
		<script>
			if(<%= currentPage %> >= <%= maxPage %>){
				var after = $('#lastBtn');
				after.attr('disabled','true');
			}
		</script>
	</div>
</body>
</html>