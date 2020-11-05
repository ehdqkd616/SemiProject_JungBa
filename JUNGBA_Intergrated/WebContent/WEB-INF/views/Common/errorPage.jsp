<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정부 지원금 바로 지금</title>
</head>
<body>
	<h1 align="center"><%= msg %></h1>
	<div align="center">
		<button onclick="history.back();">이전 페이지</button>
		<button onclick="Location.href='<%= request.getContextPath() %>/Main.mi'">홈으로 돌아가기</button>
	</div>
</body>
</html>