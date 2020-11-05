<%@page import="board.model.vo.FileVO"%>
<%@page import="board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
	FileVO profile = (FileVO)request.getAttribute("profile");
	ArrayList<Board> supportList = (ArrayList<Board>)request.getAttribute("supportList");
	ArrayList<Board> externalList = (ArrayList<Board>)request.getAttribute("externalList");
	ArrayList<Board> commuFreeList = (ArrayList<Board>)request.getAttribute("commuFreeList");
	ArrayList<Board> qaList = (ArrayList<Board>)request.getAttribute("qaList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지(메인)</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script> 
</head>
<style>
html, body {
	margin: 0px;
	height: 100%;
	min-width: 505px;
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

.box {
	width: 200px;
	height: 200px;
	border-radius: 70%;
	overflow: hidden;
}

.profile {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.board_area {
	display: flex;
	justify-content: center;
}

.board_box {
	padding: 0px;
	padding-top: 24px;
	margin: 10px;
	width: 40%;
	line-height: 30px;
}

.board_box a {
	/* text-decoration: none; */
	float: left;
	/* color: white; */
}

.board_box a:hover {
	color: black;
	font-weight: bold;
}
/* .my_util_area li:hover {
	font-weight: bold;
	} */
.box_title {
	display: flex;
	align-items: center;
	padding-left: 20px;
	margin-bottom: 0px;
	height: 40px;
	background-color: rgb(15, 76, 130);
	border-radius: 7px;
}

.box_title h3 {
	color: white;
}

/*파일 업로드 관련 */
.where {
	display: block;
	margin: 25px 15px;
	font-size: 11px;
	color: #000;
	text-decoration: none;
	font-family: verdana;
	font-style: italic;
}

.filebox input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

.filebox label {
	display: inline-block;
	padding: .5em .75em;
	color: #999;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #fdfdfd;
	cursor: pointer;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}

/* named upload */
.filebox .upload-name {
	display: inline-block;
	padding: .5em .75em;
	font-size: inherit;
	font-family: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #f5f5f5;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	-webkit-appearance: none; /* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
}

/* imaged preview */
.filebox .upload-display { /* 이미지가 표시될 지역 */
	margin-bottom: 5px;
}

@media ( min-width : 768px) {
	.filebox .upload-display {
		display: inline-block;
		margin-right: 5px;
		margin-bottom: 0;
	}
}

.filebox .upload-thumb-wrap { /* 추가될 이미지를 감싸는 요소 */
	display: inline-block;
	width: 54px;
	padding: 2px;
	vertical-align: middle;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #fff;
}

.filebox .upload-display img { /* 추가될 이미지 */
	display: block;
	max-width: 100%;
	width: 100% \9;
	height: auto;
}

li{
 	display: flex;
 }
 .area_list{
 white-space: nowrap; 
  width: 70%; 
  overflow: hidden;
  text-overflow: ellipsis; 
	text-align: left;
}
.area_date {
	width: 30%;
	text-align: center;
}

</style>

<body>
	<%@ include file="../Common/header.jsp"%>
	<section>
		<aside>
			<h2>관리자</h2>
			<hr>
			<div>
				<h2></h2>
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
			<div class="box" style="background: #BDBDBD;">
				<%if(profile == null) { %>
					<img class="profile" src="${pageContext.request.contextPath}/UploadFolder/member_profile/profileDefault.png">
				<% } else { %>
					<img class="profile" src="<%= request.getContextPath() %>/UploadFolder/member_profile/<%=profile.getChangeName()%>">
				<% } %>
			</div>

			<div class="filebox bs3-primary preview-image">
	           	<%if(profile == null) { %>
	           	<form action = "<%= request.getContextPath() %>/insertProfile.me" method="post" encType="multipart/form-data">
		           	<input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		            <label for="input_file">프로필 업로드</label> 
		            <input type="file" id="input_file" class="upload-hidden" name="profileFile"><br>
		           	<input type="submit" value="업로드">
		    	</form>
	            <% } else { %>
	           	<form action = "<%= request.getContextPath() %>/updateProfile.me" method="post" encType="multipart/form-data">
	            	<input type="hidden" value="<%=profile.getFileNo()%>" name="originalFileNo">
	            	<input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		            <label for="input_file">프로필 수정</label> 
		            <input type="file" id="input_file" class="upload-hidden" name="profileFile"><br>
		            <input type="submit" value="업로드">
		        </form>
		        <form action = "<%= request.getContextPath() %>/deleteProfile.me" method="post" encType="multipart/form-data">
		        	<input type="hidden" value="<%=profile.getFileNo()%>" name="originalFileNo">
		        	<input type="submit" value="프로필 삭제"><br>
		        </form>
		        <% } %> 
		
	     	</div>			
			

			<div class="my_board">
				<h1 align='left'>최근 게시글</h1>
				<hr>
				<div class="board_area">
					<div class="board_box">
						<div class="box_title">
							<a href="#"><h3>지원정책</h3></a>
						</div>
						<ol id="sp_title">
						<% if(supportList.isEmpty()) { %>
							<li><a href="#" class="area_title">조회된 리스트가 없습니다.</a></li>
						<% } else { %>
							<% for (Board b : supportList) { %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">
									<div class="area_list"><%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>
						<% } %>
						</ol>
						<script>
			        		$(function(){
					            $('#sp_title .area_list').mouseenter(function(){
					               $(this).css({'cursor':'pointer'});
					            }).mouseout(function(){
					               $(this).css('none');
					            }).click(function(){
					            	var bId = $(this).parent().children('input').val();
				 	                  location.href = '<%=request.getContextPath()%>/detail.sp?bId='+ bId;
								});
							});
						</script>
					</div>

					<div class="board_box">
						<div class="box_title">
							<a href="#"><h3>대외활동</h3></a>
						</div>
						<ol id="ea_title">
						<% if(externalList.isEmpty()) { %>
							<li><a href="#" class="area_title">조회된 리스트가 없습니다.</a></li>
						<% } else { %>
							<% for (Board b : externalList) { %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">
									<div class="area_list"><%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>
						<% } %>
						</ol>
						<script>
					        $(function(){
					            $('#ea_title .area_list').mouseenter(function(){
					               $(this).css({'cursor':'pointer'});
					            }).mouseout(function(){
					               $(this).css('none');
					            }).click(function(){
					            	 var bId = $(this).parent().children('input').val();
					                  location.href = '<%=request.getContextPath()%>/detail.ea?bId='+ bId;
								});
							});
						</script>
					</div>
				</div>
				
				<div class="board_area">
					<div class="board_box">
						<div class="box_title">
							<a href="#"><h3>커뮤니티</h3></a>
						</div>
						<ol id="coomu_title">
						<% if(commuFreeList.isEmpty()) { %>
							<li><a href="#" class="area_title">조회된 리스트가 없습니다.</a></li>
						<% } else { %>
							<% for (Board b : commuFreeList) { %>
								<li>
									<input type="hidden" class="area_list_no" value="<%=b.getBoardNo()%>">
									<input type="hidden"  class="area_list_title" value="<%=b.getBoardName()%>">
									<input type="hidden" value="<%=b.getBoardWriterNo()%>">
									<div class="area_list">[<%= b.getBoardName() %>] <%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>
						<% } %>
						</ol>
						<script>
					        $(function(){
					            $('#coomu_title .area_list').mouseenter(function(){
					               $(this).css({'cursor':'pointer'});
					            }).mouseout(function(){
					               $(this).css('none');
					           	}).click(function(){
					           	 	var bId = $(this).parent().children('.area_list_no').val();
						       	 	var bName = $(this).parent().children('.area_list_title').val();
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
					</div>

					<div class="board_box">
						<div class="box_title">
							<a href="#"><h3>Q / A</h3></a>
						</div>
						<ol id="qa_title">
						<% if(qaList.isEmpty()) { %>
							<li><a href="#" class="area_title">조회된 리스트가 없습니다.</a></li>
						<% } else { %>
							<% for (Board b : qaList) { %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">
									<div class="area_list"><%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>
						<% } %>
						</ol>
						<script>
        					$(function(){
					            $('#qa_title .area_list').mouseenter(function(){
					               $(this).css({'cursor':'pointer'});
					            }).mouseout(function(){
					               $(this).css('none');
					            }).click(function(){
					            	var bId = $(this).parent().children('input').val();
					                  location.href = '<%=request.getContextPath()%>/q_detail.qa?bId='+ bId;
								});
							});
						</script>
					</div>
				</div>
			</div>
		</div>

	</section>

	<%@ include file="../Common/footer.jsp"%>
	
	<script>
	$(document).ready(function(){
		   var fileTarget = $('.filebox .upload-hidden');

		    fileTarget.on('change', function(){
		        if(window.FileReader){
		            // 파일명 추출
		            var filename = $(this)[0].files[0].name;
		        } 

		        else {
		            // Old IE 파일명 추출
		            var filename = $(this).val().split('/').pop().split('\\').pop();
		        };

		        $(this).siblings('.upload-name').val(filename);
		    });

		    //preview image 
		    var imgTarget = $('.preview-image .upload-hidden');

		    imgTarget.on('change', function(){
		        var parent = $(this).parent();
		        parent.children('.upload-display').remove();

		        if(window.FileReader){
		            //image 파일만
		            if (!$(this)[0].files[0].type.match(/image\//)) return;
		            
		            var reader = new FileReader();
		            reader.onload = function(e){
		                var src = e.target.result;
		                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>');
		            }
		            reader.readAsDataURL($(this)[0].files[0]);
		        }

		        else {
		            $(this)[0].select();
		            $(this)[0].blur();
		            var imgSrc = document.selection.createRange().text;
		            parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');

		            var img = $(this).siblings('.upload-display').find('img');
		            img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";        
		        }
		    });
		    
		});
 		
	
	</script>	
</body>

</html>