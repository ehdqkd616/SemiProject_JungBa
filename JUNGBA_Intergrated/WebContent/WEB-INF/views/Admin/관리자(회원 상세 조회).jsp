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
    <title>관리자(회원 목록 조회)</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
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


    .box {
        margin-top: 40px;
        margin-right: 50px;
        width: 300px;
        height: 300px;
        border-radius: 70%;
        overflow: hidden;
        float: left;
    }

    .profile {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    #topContents {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }
	
	#memInfo {
		float: left;
	}
	
    #infoTable {
        border-spacing: 10px;
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
   <%@ include file="../Common/header.jsp" %>
    <section>
        <aside>
            <h2><a href="#">관리자</h2></a>
            <hr>
            <div>
                <dl>
                    <dt><a href="${pageContext.request.contextPath}/memList.ad">
                            <h3>회원 목록 조회</h3>
                        </a></dt>
                    <dt><a href="${pageContext.request.contextPath}/spList.ad">
                            <h3>정보 공유 신청</h3>
                        </a></dt>
                    <dt><a href="${pageContext.request.contextPath}/eaList.ad">
                            <h3>대외 활동 신청</h3>
                        </a></dt>
                </dl>
            </div>
        </aside>

        <div id="main_section" align="center">
            <h2>
            <div id="subTitle"><b><%=member.getMemberNickName() %>님의 회원 상세 조회</b></div>
            </h2>
            <hr>
            <div id="topContents">
				<div class="box" style="background: #BDBDBD;">
					<%if(profile == null) { %>
						<img class="profile" src="${pageContext.request.contextPath}/UploadFolder/member_profile/profileDefault.png">
					<% } else { %>
						<img class="profile" src="<%= request.getContextPath() %>/UploadFolder/member_profile/<%=profile.getChangeName()%>">
					<% } %>
				</div>
                <div id="memInfo">
                    <table id="infoTable">
                        <tr>
                            <td>이름(Name) : </td>
                            <td><%= member.getMemberName() %></td>
                        </tr>
                        <tr>
                            <td>아이디(ID) : </td>
                            <td><%= member.getMemberId() %></td>
                        </tr>
                        <tr>
                            <td>휴대전화(Phone) : </td>
                            <td><%= member.getMemberPhone() %></td>
                        </tr>
                        <tr>
                            <td>이메일(E-mail) : </td>
                            <td><%= member.getMemberEmail() %></td>
                        </tr>
                        <tr>
                            <td>성별(Gender) : </td>
                            <td>여성</td>
                        </tr>
                        <tr>
                            <td>주소(Address) : </td>
                            <td><%= member.getMemberAddress() %></td>
                        </tr>
                        <tr>
                            <td>생년월일(BirthDay) : </td>
                            <td><%= member.getMemberBirthDay() %></td>
                        </tr>
                    </table>
                </div>
            </div>

            <br clear="all">

            <div id="boardContents">
                <div class="board_area">
                    <div class="board_box">
						<div class="box_title">
							<a href="#"><h3>지원정책</h3></a>
						</div>
						<ol id="sp_title">
						<% if(supportList.isEmpty()) { %>
							<li><a href="#" class="area_title">조회된 리스트가 없습니다.</a></li>
						<% } else if(supportList.size() >= 4) { %>
							<% for (int i = 0; i < 4; i++) { %>
								<% Board b = supportList.get(i); %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">
									<input id="enroll" type="hidden" value="<%=b.getEnrollState() %>">
									<div class="area_list"><%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>
						<% } else { %>
							<% for (Board b : supportList) { %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">
									<input id="enroll" type="hidden" value="<%=b.getEnrollState() %>">
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
					            	var enroll = $(this).parent().children('#enroll').val();
				            		if(enroll == 'N'){
					            		location.href = '<%= request.getContextPath() %>/spDetail.cm?bId=' + bId;
				            		} else {
				            			location.href = '<%= request.getContextPath() %>/detail.sp?bId=' + bId;
				            		}
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
						<% } else if(externalList.size() >= 4) { %>
							<% for (int i = 0; i < 4; i++) { %>
								<% Board b = externalList.get(i); %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">
									<input id="enroll" type="hidden" value="<%=b.getEnrollState() %>">
									<div class="area_list"><%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>
						<% } else { %>
							<% for (Board b : externalList) { %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">
									<input id="enroll" type="hidden" value="<%=b.getEnrollState() %>">
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
					                var enroll = $(this).parent().children('#enroll').val();
									if(enroll == 'N'){
					            		location.href = '<%=request.getContextPath()%>/eaDetail.cm?bId=' + bId;
					            	} else {
					            		location.href = '<%=request.getContextPath()%>/detail.ea?bId='+ bId;
									}
								});
							});
						</script>
		            </div>
                </div>
                    
				<div class="board_area">
					<div class="board_box">
						<div class="box_title">
							<a href="#"><h3>자유게시판</h3></a>
						</div>
					<ol id="coomu_title">
						<% if(commuFreeList.isEmpty()) { %>
							<li><a href="#" class="area_title">조회된 리스트가 없습니다.</a></li>
						<% } else if(commuFreeList.size() >= 4) { %>
							<% for (int i = 0; i < 4; i++) { %>
								<% Board b = commuFreeList.get(i); %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">									
									<div class="area_list"><%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>							
						<% } else { %>
							<% for (Board b : commuFreeList) { %>
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
					            $('#coomu_title .area_list').mouseenter(function(){
					               $(this).css({'cursor':'pointer'});
					            }).mouseout(function(){
					               $(this).css('none');
					           	}).click(function(){
					           	 	var bId = $(this).parent().children('input').val();
						       	 	location.href = '<%=request.getContextPath()%>/fDetail.cm?bId='+bId;
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
						<% } else if(qaList.size() >= 4) { %>
							<% for (int i = 0; i < 4; i++) { %>
								<% Board b = qaList.get(i); %>
								<li>
									<input type="hidden" value="<%=b.getBoardNo()%>">								
								<div class="area_list"><%=b.getBoardTitle()%></div>
									<div class="area_date"><%=b.getBoardCreateDate()%></div>
								</li>
							<% } %>						
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
    <%@ include file="../Common/footer.jsp" %>
</body>

</html>