<%@page import="board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Board> noticeList = (ArrayList<Board>)request.getAttribute("noticeList");
	ArrayList<Board> supportList = (ArrayList<Board>)request.getAttribute("supportList");
	ArrayList<Board> externalList = (ArrayList<Board>)request.getAttribute("externalList");
	ArrayList<Board> commuList = (ArrayList<Board>)request.getAttribute("commuList");
	ArrayList<Board> qaList = (ArrayList<Board>)request.getAttribute("qaList");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	display: block;
}
/* 이미지 슬라이더 */
.swiper-container {
	padding-top: 350px;
	height: auto;
	border: 3px solid silver;
	border-radius: 7px;
	box-shadow: 0 0 10px #ccc inset;
}

.swiper-slide {
	text-align: center;
	display: flex; /* 내용을 중앙정렬 하기위해 flex 사용 */
	align-items: center; /* 위아래 기준 중앙정렬 */
	justify-content: center; /* 좌우 기준 중앙정렬 */
}

.swiper-slide img {
	box-shadow: 0 0 5px #555;
	max-width: 95%; /* 이미지 최대너비를 제한, 슬라이드에 이미지가 여러개가 보여질때 필요 */
	margin: 20px;
}

/* 이미지 슬라이더 */
.util_area_a {
	display: flex;
}

.list_type_a {
	padding: 0px;
	padding-top: 24px;
	font-size: 14px;
	width: 50%;
}

.list_type_a ul {
	padding: 0px;
	padding-top: 20px;
	margin: 0px;
	list-style: none;
	font-family: "돋움";
}

.list_type_b {
	padding: 0px;
	padding-top: 24px;
	font-size: 14px;
}

.list_type_b ul {
	padding: 0px;
	padding-top: 20px;
	margin: 0px;
	list-style: none;
	column-count: 2;
	font-family: "돋움";
}

li {
	line-height: 30px;
	margin: 0px 15px;
}

li:hover {
	font-weight: bold;
}
.area_title {
	display: flex;
	align-items: center;
	padding-left: 20px;
	margin-bottom: 0px;
	height: 40px;
	background-color: rgb(15, 76, 130);
	border-radius: 7px;
	color: rgb(255, 213, 0);
}

.area_title a {
	text-decoration: none;
	color: white;
}

.area_title a:hover {
	color: black;
	font-weight: bold;
}

.all_link_area {
	margin-left: 40px;
	font-size: 12px;
}
.area_list{
 	display: flex;
 }
 .area_list_title{
 white-space: nowrap; 
  width: 70%; 
  overflow: hidden;
  text-overflow: ellipsis; 
}
.area_date {
	width: 30%;
	text-align: center;
}

@media screen and (max-width: 768px) {
	.swiper-container {
		top:20px;
	}
	.util_area_a {
		display: block;
		padding-top:20px;
	}
	.list_type_a {
		width: 100%;
		font-size: 12px;
	}
	.list_type_b {
		font-size: 12px;
	}
	.list_type_b ul {
		column-count: 1;
	}
}
</style>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

</head>
<body>
	<%@ include file="../Common/header.jsp"%>
	<section>
		<!-- 이미지 슬라이더 -->
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>

		<!-- 클래스명은 변경하면 안 됨 -->
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="Media/mainimage_1.png">
				</div>
				<div class="swiper-slide">
					<img src="Media/mainimage_2.png">
				</div>
				<div class="swiper-slide">
					<img src="Media/mainimage_3.png">
				</div>
				<div class="swiper-slide">
					<img src="Media/mainimage_4.png">
				</div>
				<div class="swiper-slide">
					<img src="Media/mainimage_5.png">
				</div>
			</div>

			<!-- 네비게이션 -->
			<div class="swiper-button-next"></div>
			<!-- 다음 버튼 (오른쪽에 있는 버튼) -->
			<div class="swiper-button-prev"></div>
			<!-- 이전 버튼 -->

			<!-- 페이징 -->
			<div class="swiper-pagination"></div>
			<script>

		new Swiper('.swiper-container', {
		
			slidesPerView : 1, // 동시에 보여줄 슬라이드 갯수
			spaceBetween : 10, // 슬라이드간 간격
			slidesPerGroup : 1, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음
		
			// 그룹수가 맞지 않을 경우 빈칸으로 메우기
			// 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
			loopFillGroupWithBlank : true,
		
			loop : true, // 무한 반복
		
			pagination : { // 페이징
				el : '.swiper-pagination',
				clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
			},
			navigation : { // 네비게이션
				nextEl : '.swiper-button-next', // 다음 버튼 클래스명
				prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
			},
		});

		</script>
		</div>

		<!-- 이미지 슬라이더 -->

		<div class="util_area_a">
			<div class="list_type_a">
				<div class="area_title">
					<a href="<%= request.getContextPath() %>/main.no"><h3>공지사항</h3></a>
				</div>
				<ul id="notice_title">
					<% if(noticeList.isEmpty()) { %>
					<li><a href="#" class="area_list">조회된 리스트가 없습니다.</a></li>
					<% } else { %>
					<% for (Board b : noticeList) { %>
					<li class="area_list">
						<input type="hidden" value="<%=b.getBoardNo()%>">
							<div class="area_list_title"><%=b.getBoardTitle()%></div>
							<div class="area_date"><%=b.getBoardCreateDate()%></div>
						</li>
					<% } %>
					<% } %>
				</ul>
			</div>
			<script>
        $(function(){
	            $('#notice_title .area_list').mouseenter(function(){
	               $(this).css({'cursor':'pointer'});
	            }).mouseout(function(){
	               $(this).css('none');
	            }).click(function(){
	            	 var bId = $(this).parent().children().children('input').val();
	                  location.href = '<%=request.getContextPath()%>/detail.no?bId='+ bId;
			});
			});
		</script>
			<div class="list_type_a">
				<div class="area_title">
					<a href="<%= request.getContextPath() %>/main.sp"><h3>지원정책</h3></a>
				</div>
				<ul id="sp_title">
					<% if(supportList.isEmpty()) { %>
					<li><a href="#" class="area_list">조회된 리스트가 없습니다.</a></li>
					<% } else { %>
					<% for (Board b : supportList) { %>
					<li class="area_list">
						<input type="hidden" value="<%=b.getBoardNo()%>">
							<div class="area_list_title"><%=b.getBoardTitle()%></div>
							<div class="area_date"><%=b.getBoardCreateDate()%></div>
						</li>
					<% } %>
					<% } %>
				</ul>
			</div>
				<script>
        		$(function(){
	            $('#sp_title .area_list').mouseenter(function(){
	               $(this).css({'cursor':'pointer'});
	            }).mouseout(function(){
	               $(this).css('none');
	            }).click(function(){
	            	 var bId = $(this).parent().children().children('input').val();
 	                  location.href = '<%=request.getContextPath()%>/detail.sp?bId='+ bId;
			});
			});
		</script>
		</div>
			<div class="list_type_b">
				<div class="area_title">
					<a href="#"><h3>대외활동</h3></a>
				</div>
				<ul id="ea_title">
					<% if(externalList.isEmpty()) { %>
					<li><a href="#" class="area_list">조회된 리스트가 없습니다.</a></li>
					<% } else { %>
					<% for (Board b : externalList) { %>
					<li class="area_list">
						<input type="hidden" value="<%=b.getBoardNo()%>">
							<div class="area_list_title"><%=b.getBoardTitle()%></div>
							<div class="area_date"><%=b.getBoardCreateDate()%></div>
						</li>
					<% } %>
					<% } %>
				</ul>
			</div>
		<script>
        $(function(){
	            $('#ea_title .area_list').mouseenter(function(){
	               $(this).css({'cursor':'pointer'});
	            }).mouseout(function(){
	               $(this).css('none');
	            }).click(function(){
	            	 var bId = $(this).parent().children().children('input').val();
	                  location.href = '<%=request.getContextPath()%>/detail.ea?bId='+ bId;
			});
			});
		</script>
			<div class="list_type_b">
				<div class="area_title">
					<a href="#"><h3>커뮤니티</h3></a>
					<div class="all_link_area">
						<a href="<%= request.getContextPath() %>/fMain.cm">자유게시판</a> <span
							class="text_bar">|</span> <a
							href="<%= request.getContextPath() %>/spMain.cm">지원정책</a> <span
							class="text_bar">|</span> <a
							href="<%= request.getContextPath() %>/eaMain.cm">대외활동</a>
					</div>
				</div>
				<ul id="coomu_title">
					<% if(commuList.isEmpty()) { %>
					<li><a href="#" class="area_list">조회된 리스트가 없습니다.</a></li>
					<% } else { %>
					<% for (Board b : commuList) { %>
					<li class="area_list">
						<input type="hidden" class="area_list_no" value="<%=b.getBoardNo()%>">
						<input type="hidden"  class="area_list_title" value="<%=b.getBoardName()%>">
						<div class="area_list_title">[<%= b.getBoardName() %>]<%= b.getBoardTitle() %></div>
						<div class="area_date"><%=b.getBoardCreateDate()%></div>
					</li>
					<% } %>
					<% } %>
				</ul>
			</div>
		<script>
        $(function(){
            $('#coomu_title .area_list').mouseenter(function(){
               $(this).css({'cursor':'pointer'});
            }).mouseout(function(){
               $(this).css('none');
           	}).click(function(){
           	 	var bId = $(this).children('.area_list_no').val();
	       	 	var bName = $(this).children('.area_list_title').val();
	       	 	switch(bName){
	       	 	case "자유":
	       	 	location.href = '<%=request.getContextPath()%>/fDetail.cm?bId='+bId;
	       	 	break;
	       	 	case "지원":
	       	 		location.href = '<%=request.getContextPath()%>/spDetail.cm?bId='+bId;
	       	 	break;
	       	 	case "대외":
	       	 		location.href = '<%=request.getContextPath()%>/eaDetail.cm?bId='+bId;
	       	 	break;
	       	 	}
			});
		});
		</script>
	</section>
	<br>
	<%@ include file="../Common/footer.jsp"%>

</body>
</html>