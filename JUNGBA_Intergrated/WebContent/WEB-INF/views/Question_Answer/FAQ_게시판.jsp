<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>FAQ 게시판</title>
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

	
	    .FAQ_title{
	        text-align: left;
	        height:45px;        
	        font-size:20px; 
	        cursor: pointer;
	        padding-left: 15px;
	        padding-bottom: 15px;
	        border-bottom: 1px solid black;
	    }
	    .contents{ 
	        text-align: left;
	        height: 100px; 
	        background: RGB(238,238,238 ); 
	        display: none; 
	        line-height: auto;      
	        margin-left: 15px;
	    } 
	</style>
</head>
<body>
    <%@ include file="../Common/header.jsp" %>
    <section>
        <aside>
	        <h2> 묻고 답하기</h2></a>
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
            <h2 align="center">FAQ</h2>
            <hr>
                <div class='FAQ_title'><h3>
                [사용자등록][기타]정바에서 직접 신청(교육생 모집 등)을 받을 수 있나요?
                </h3></div>
                <p class='contents'>
                	현재 정바를 통한 교육생 신청 및 모집은 불가합니다.
                	향후 지원자가 바로 신청하고 관리할 수 있느 기능이 준비중에 있습니다.
                </p>
                <div class="FAQ_title"><h3>
                [사용자등록][기타]적합한 하위 카테고리가 없을 때는 어떻게 하나요?
                </h3></div>
                <p class='contents'>
             		해당되는 하위 카테고리가 없을 경우, 가장 유사한 카테고리를 선택해주시기 바랍니다.
                </p>
                 <div class="FAQ_title"><h3>
                 [사용자등록][카테고리]카테고리를 잘못 선택했습니다. 어떻게 변경하나요?
                </h3></div>
                <p class='contents'>
				대분류(지원정책, 교육훈련, 대외활동, 창업정보)는 변경할 수 없습니다. 번거롭지만 처음부터 다시 등록해주시기 바랍니다.
                </p>
                <div class="FAQ_title"><h3>
                [사용자 등록][태그]태그란 무엇인가요?
                </h3></div>
                <p class='contents'>
               	 정보를 검색할 때 사용하기 위해 부여하는 단어 혹은 키워드를 의미합니다. 이용자가 검색했을 때,<br>
               	 해당 정보가 도출 될 수 있는 키워드로써 내용을 분류하는데 이용됩니다.
                </p>
                <div class="FAQ_title"><h3>
                [사용자 등록][첨부파일]첨부파일은 어떻게 확인하나요?
                </h3></div>
                <p class='contents'>
              	  첨부한 파일은 상세대용 하단에 보여지며, 누구나 다운로드 받을 수 있습니다.
                </p>
                <div class="FAQ_title"><h3>
                [사용자 등록][첨부파일]등록 가능한 첨부파일에 대해 알고 싶어요.
                </h3></div>
                <p class='contents'>
              	  모든 형식의 첨부파일을 등록할 수 있습니다.
                </p>
                <div class="FAQ_title"><h3>
                [사용자 등록][승인,수정,삭제]삭제한 정보를 복구할 수 있나요?
                </h3></div>
                <p class='contents'>
				삭제한 정보는 복구할 수 없으니 신중하게 선택해주시기 바랍니다.
                </p>
                <div class="FAQ_title"><h3>
                [사용자 등록][등록]등록갯수에 제한이 있나요?
                </h3></div>
                <p class='contents'>
				1인당 등록할 수 있는 정보 갯수의 제한이 없습니다.
                </p>
                <script>
                    $('.FAQ_title').click(function(){
                        $(this).next().slideToggle();
                    });
                </script>
            <br>
        </div> 
    </section>
    <%@ include file="../Common/footer.jsp" %>
</body>
</html>



 