<%@page import="java.util.ArrayList"%>
<%@page import="board.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("board");
	ArrayList<FileVO> fList = (ArrayList<FileVO>)request.getAttribute("fileList");
	ArrayList<Reply> replyList = (ArrayList<Reply>)request.getAttribute("replyList");
	FileVO titleImg = fList.get(0);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>정부지원금 바로 지금</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script> 
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/body.css">

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

	
    section{
      display: block;
  	}
	.thumbnailArea {
		width: 80%;
		height: auto;
		margin: 0px auto;
		padding: 0px;
	}

	.thumb-list {
		margin: 20px;
		display: flex;
		line-height:15px; 
		border-bottom: 1px solid silver;
	}
	.imageArea{
		margin: auto 0px;
		align-items: "center";
	}
	.textArea{
		line-height: 30px;
		text-align: left;
		margin-left : 30px;
	}

  	.writer_box{
      margin: 0px auto;
      border-bottom: 1px solid silver;
  	}
  	.writer_table{
	    margin: 0px;
	    width: 100%;
  	}
  
  	.writer_text{
      margin-left: 80px;
      line-height: 160px;
      width: 100%;
 	 }
  	textarea{
	    height: 100px;
	    width: 80%;
	    resize: none;
  	}
  	.textline1{
	    width: 80%;
	    height: 30px;
  	}

  	.ea_button{
	    margin: 0 auto; 
	    text-align: center;
  	}	

  	.result_btn{
	    height: 50px;
	    width: 100px;
	    margin: 30px;
  	}
.detailImg{width:250px; height:180px;}
		.udlbtn{background-color:sky; width:70px; height:40px; margin:10px;}   
    </style>
</head>
<body>
    <%@ include file="../Common/header.jsp" %>
    <section>
    <div id="main_section">
    <form action="<%= request.getContextPath() %>/eaModifyForm.cm" method="post" encType="multipart/form-data">
    <input type="hidden" name="no" value="<%= b.getBoardNo() %>">
    <input type="hidden" name="title" value="<%= b.getBoardTitle() %>" />
    <input type="hidden" name="content" value="<%= b.getBoardContent() %>" />
	<input type="hidden" name="category" value="<%= b.getCgName() %>" />
	<input type="hidden" name="acState" value="<%= b.getAcState() %>" />
	<input type="hidden" name="tcName" value="<%= b.getTcName() %>" />
	<input type="hidden" name="lcName" value="<%= b.getLcName() %>" />
	<input type="hidden" name="viewCount" value="<%= b.getBoardViewCount() %>" />
	<input type="hidden" name="reCommend" value="<%= b.getBoardReCommend() %>" />
	<input type="hidden" name="writer" value="<%= b.getBoardWriter() %>" />
	<input type="hidden" name="ea_res_date" value="<%= b.getReStartDate() %>" />
	<input type="hidden" name="ea_ree_date" value="<%= b.getReEndDate() %>" />
	<input type="hidden" name="ea_acs_date" value="<%= b.getAcStartDate() %>" />
	<input type="hidden" name="ea_ace_date" value="<%= b.getAcEndDate() %>" />
    <% System.out.println(b.getReEndDate()); %>
    <h4>대외활동 -<%=b.getCgName()%>
    	<% if(loginUser != null && loginUser.getMemberNickName().equals("운영자")) { %>
			<input type="button" style="float:right" value="등록하기" onclick="enrollBoard();">
		<%} %>
    </h4>
    <hr>
    <h3><%=b.getBoardNo()%>.<%= b.getBoardTitle() %></h3>
     <h4>작성자 : <%=b.getBoardWriter()%></h4>
    <ul class="thumbnailArea">
		<li class="thumb-list">
			<div id="titldImgArea" align="center">
				<a href="<%= request.getContextPath() %>/UploadFolder/external_uploadFiles/<%= titleImg.getChangeName() %>">
					<img id="titleImg" src="<%= request.getContextPath() %>/UploadFolder/external_uploadFiles/<%= titleImg.getChangeName() %>" width="300px" height="80%">
				</a>
			</div>
			<div class="textArea">
				<p>	
					<b>접수상태 </b><br>
					<%=b.getAcState()%></p>
				<p>
					<b>지원대상 </b><br>
					<%=b.getTcName()%></p>
				<p>
					<b>지역 </b><br>
					<%=b.getLcName()%></p>
				<p>
					<b>모집 일정 </b>
					<%=b.getReStartDate()%>~<%=b.getReEndDate()%></p>
				<p>
					<b>활동 일정 </b>
					<%=b.getAcStartDate()%>~<%=b.getAcEndDate()%></p>
			</div>
		</li>
	</ul>
    
    <!-- 대외활동 글쓰기 카테고리,대상,지역별 선택 -->
    <div class="writer_box">
        <table class="writer_table">
          <tr>
            <th>내용</th>
            <td colspan="3"><textarea class="form-control" name="ea_text_contents" id="ea_text_contents" readonly><%=b.getBoardContent()%></textarea></td>
          </tr>
           <tr>
            <th>첨부파일</th>
			 <td><%if(fList.isEmpty()) { %>
                    	첨부파일 없음.
                    <% } else { %>
                    	<%for(int i=1; i < fList.size(); i++){ %>
							<a href="<%= request.getContextPath() %>/UploadFolder/notice_uploadFiles/<%=fList.get(i).getChangeName()%>" download="<%= fList.get(i).getOriginName() %>">
								[ <%=i%><%=fList.get(i).getOriginName()%> ]
							</a>
						<% } %>
					<% } %>
			</td>
          </tr>
        </table>
        <table class="detail">
			<tr>
				
			</tr>
		</table>
    </div>
    <div align="right">
				<input type="button" onclick="location.href='<%= request.getContextPath() %>/eaMain.cm'" id="menuBtn" value="메뉴로">
				<% if(loginUser != null) { %>
					<% if(b.getBoardWriter().equals(loginUser.getMemberNickName()) || loginUser.getMemberNickName().equals("운영자")) { %>
						<input type="submit" id="updateBtn" value="수정">
						<input type="button" id="deleteBtn" value="삭제" onclick="deleteBoard();">
					<% } %>
				<% } %>
				<script>
				function deleteBoard(){
					var num = <%= b.getBoardNo() %>;
					var result = window.confirm(num+'삭제?');
					var wno = <%= b.getBoardWriterNo()%>;
					console.log(wno);
				    if(result){
				    	location.href="<%= request.getContextPath() %>/eaDelete.cm?no="+num;
				    }
				    else{
				        alert('취소하셨습니다.');
				    }
				}
				
				function enrollBoard(){
					var num = <%= b.getBoardNo() %>;
					var result = window.confirm(num+'등록하시겠습니까?');
					var wno = <%= b.getBoardWriterNo()%>;
				    if(result){
				    	location.href="<%= request.getContextPath() %>/enroll.bo?no="+num;
				    }
				    else{
				        alert('취소하셨습니다.');
				    }
				}
		</script>
	</div>
  </form>
  	<!-- 댓글 --> 
    <div> <h2>댓글</h2> </div>
    <hr><br>
    <div id="comment" 	style="line-height:20px; margin: 0px; padding:0px;">
    <% if(replyList.isEmpty()){ %>
    	<text class="text3"><p class="p1">댓글이 없습니다.</p></text>
    <% } else { %>
    	<% for(int i = 0; i < replyList.size(); i++) { %>
     	<table>
        	<% if(replyList.get(i).getProfileImageName() == null) { %>
         		<td><img src="<%= request.getContextPath() %>/UploadFolder/member_profile/profileDefault.png"  width="80" height="80"  id="profile" style="float:top;")></td>
       		<% } else { %>  	
         		<td><img src="<%= request.getContextPath() %>/UploadFolder/member_profile/<%= replyList.get(i).getProfileImageName() %>"  width="80" height="80"  id="profile" style="float:top;")></td>
       		<% } %>   	
      		<text class="text3">
          		<td style="margin:0px; vartical-align:top; width:80%">
	          		<p class="replyIWC"><h3><%= replyList.get(i).getReplyWriter() %></h3></p>
	          		<p class="replyIWC"><%= replyList.get(i).getReplyContent() %></p>
          		</td>
          	</text>
         	<text class="text4">
             		
			<!-- 관리자랑 사용자랑 나눠서 사용해야한다. -->
         	<td class="replyDU" style="width:200px; float:right;">
          		<div><p style="float:right; margin:0px;">게시일 : <%= replyList.get(i).getCreateDate() %></p></div> <!-- 게시 날짜 -->
          		<div><p style="float:right; margin:0px;">수정일 : <%= replyList.get(i).getModifyDate() %></p></div> <!-- 수정 날짜 -->
          		<% if(loginUser != null){ %>
	          		<% if(replyList.get(i).getReplyWriter().equals(loginUser.getMemberNickName()) || loginUser.getMemberNickName().equals("운영자")) { %>
						<p style="float:right; margin:0px;"><input type="button" class="udlbtn" id="deleteBtn" value="댓글 삭제" onclick="deleteReply(<%= replyList.get(i).getReplyNo() %>)">	
					<% } %>
				<% } %>
				<script>
				function deleteReply(replyNo){
					var result = window.confirm(replyNo+' 댓글을 삭제하시겠습니까?');
					var boardNo = <%= b.getBoardNo() %>
		
	    			if(result){
	    				location.href="<%= request.getContextPath() %>/replyDelete.re?replyNo="+replyNo+"&boardNo="+boardNo+"&bName=대외커뮤";
	    			} else{
	        			alert('취소하셨습니다.');
	    			}
				}
				</script>   							
          </td>
          </text>
        </table>
              <% } %>
         	<% } %>
    </div>             
                    
    <br clear="all"><br>
    	<% if(loginUser != null){%>        
        	<form action="replyInsert.re">
           		<div>
	            	<textarea id="replytextarea" name="replyContent" placeholder="댓글을 입력하세요." style="width: 100%; height: 100px; resize: none;"></textarea><br>
	              	<input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>">
	         		<input type="hidden" name="bName" value="대외커뮤">	
	                <input type="submit" class= replySubmit value="등록" style="float:right;" >
           		</div>
          </form>
        <%} %>
    <br clear="all"><br>
  </div>
</section>  
    <%@ include file="../Common/footer.jsp" %>
</body>
</html>

