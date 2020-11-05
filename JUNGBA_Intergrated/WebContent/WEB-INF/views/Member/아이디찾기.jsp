<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 기본틀</title>
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

  
	section {
      display: block;
      line-height: 40px;
    }
    #main_section {
      width: 100%;
    } 
    .loginbox{
  	 width: 80%;
  	 padding: 2% 10%;
   	 text-align: center;
   	 background-color: rgb(248, 248, 248); ;
   	 }
   	  #idSearchButton{background-color:darkgray; border-radius: 3px; border:none;}
}
    .checknumber{width:100px;}
    #idsearchbutton{background-color:darkgray; border-radius: 3px; border:none;}
</style>
<body>
    <%@ include file="../Common/header.jsp" %>
    <section>
    
        <div id="main_section">
            <section id=id_serch_Section >
               <%--  <form action="<%=request.getContextPath()%>/searchId.me" method="post"> --%>
                    <div class="loginbox">
                        <h1 align="center">아이디 찾기</h1>                   
	                        <table align="center" ">
	                            <tr>
	                                <td style = text-align:right>이름 :</td>
	                                <td>
	                                	<input type="text" name ="userName" id="userName" placeholder="이름을 입력해주세요">
	                                </td>
	                            <tr>
	                                <td style = text-align:right><input type=radio name=idsearchradio value="phone" checked>휴대폰번호 :</td>
	                                <td>
	                                	<input type="phone" name="userPhone" id="userPhone" placeholder="'-' 없이 입력해주세요">
	                                </td>
	                            </tr>   
	                            </tr>
	                            <tr>
	                                <td style = text-align:right><input type=radio name=idsearchradio value="email">이메일 :</td>
	                                <td>
	                                	<input type="email" name="userEmail" id="userEmail" placeholder="이메일을 입력해주세요">
	                                </td>
	                            </tr>
	                        </table>
                        <p align="center">
                        	<input type="button" align="center" name="idSearchButton" id="idSearchButton" style="width: 60pt; height: 30pt" value="확인">
                        	<input type="button" style="width: 60pt; height: 30pt" onclick="location.href='javascript:history.go(-1);'" value="취소">
                        </p>
                   </div>
                        <p style="height:100px; width:100%; text-align:center; font-size: 150%;" id="idResult2"></p>
                        <script>
							$('#idSearchButton').click(function(){
								var name = $('#userName');
								var phone = $('#userPhone');
								var email = $('#userEmail');
						        var radio = $('input[name="idsearchradio"]:checked');
						        
						       	console.log(radio);
								console.log(name);
								console.log(phone);
								console.log(email);
								
								$.ajax({
									url: "<%= request.getContextPath() %>/searchId.me",
									type: 'post',
									data: { name:name.val(), phone:phone.val() , email:email.val() , radio: radio.val() },
									success: function(data){
										console.log(data);
											
										$('#idResult2').text('');
										
 										if(data.length > 2 ){
 											$('#idResult2').append('아이디는');
											$('#idResult2').append(data);
											$('#idResult2').append(' 입니다.');
											$('#idResult2').css({'color':'black'});
										} else{
											$('#idResult2').text('찾을 수 없습니다.');
											$('#idResult2').css({'color':'red'});
										}
/* 										for(var i =0; i<data.length; i++){
											$('#idResult2').append(data[i]);
											if(data[i]=='.'){
												$('#idResult2').append('<br>');
											}
										} */
									}
								});
							}) 
                      </script> 
                    
                <!-- </form> -->
            </section>
        </div>
    </section>
    
    <%@ include file="../Common/footer.jsp" %>
</body>
</html>



 