<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정부 지원금 바로 지금</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/body.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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


#main_section{
	margin: 0px;
}
.writertext{
      line-height: 70px;
      list-style: none;
  }
    .serach_box{
        background-color: RGB(248,248,248);
        display: flex;
    }
    .select_box{
        margin: 16px;
        line-height: 70px;
    }
    .select_btn{
        height: 40px;
        width: 100px;
        background-color:  RGB(221,228,236);
    }
    .categoty_select{
        height: 30px;
    }
 .result_btn{
	padding: 15px 15px; 
	border-radius : 3px; 
	width:80px; 
	text-align:center;
	margin: 0px 5px;
 }
 .result_btn:hover{background-color:  rgb(194, 194, 194);}


.serachList{
	width: 610px;
	height : 955px;
  	overflow:hidden;
	position:relative;
	margin: 50px auto;
}
.thumbnailArea {
	column-width: 600px;
	height : 955px;
	top:0;
    left:0;
	margin: 0px auto;
	padding: 0px;
	position :absolute;
}
.board_list {
	width: 600px;
	height: 170px;
	margin-bottom: 20px;
	display: flex;
	line-height:20px; 
	border-bottom: 1px solid silver;
	border-radius: 5px;
}

.thumb-list:hover {
	opacity: 0.8;
	cursor: pointer;
}

#insertBtn {
	float: right;
}
 #btnBack{
	cursor: pointer;
	padding: 10px;
	margin: 1200px 20px; 
}
#btnNext{
 	cursor: pointer;
	padding: 10px;
	margin: 1200px 0px; 
}
.imageArea{
	height: 80%;
	margin: auto 0px;
	align-items: "center";
}
.textArea_title{
	width: 100%;
}
.textArea{
	text-align: left;
	margin-left : 30px;
	width: 50%
}
.textArea1{
	float: right;
}
      
</style>
<body>
	<%@ include file="../Common/header.jsp"%>
	<section>
		<div>
			<input type="button" id="btnBack" value="<<">
		</div>
		<div id="main_section">
		<h2 align="center">대외 활동</h2>
		<div class="serach_box">
	      <ul class="writertext">
	          <th>
	              <li><h6>카테고리</h6></li>
	              <li><h6>대상</h6></li>
	              <li><h6>지역</h6></li>
	          </th>
	      </ul>
      <!-- 대외활동 글쓰기 카테고리,대상,지역별 선택 -->
      <div class="select_box">
          <h3>
              <select class="categoty_select" name="ea_category" id="ea_category">
                  <option value="선택" selected>선택</option>
                  <option value="공모전">공모전</option>
                  <option value="채용박람회/취업박람회">채용박람회/취업박람회</option>
                  <option value="경진대회">경진대회</option>
                  <option value="봉사활동">봉사활동</option>
                  <option value="서포터즈">서포터즈</option>
                  <option value="관람/전시/체험">관람/전시/체험</option>
              </select>
          </h3>
          <h3>
              <div>
                  <button type="button"class="select_btn" id="ea_age_18">청년<br>(18~34세) </button>
                  <button type="button"class="select_btn" id="ea_age_35">중년<br>(35~49세) </button>
                  <button type="button"class="select_btn" id="ea_age_50">장년<br>(50~64세) </button>
                  <button type="button"class="select_btn" id="ea_age_65">어르신<br>(65세 이상) </button>
              </div>
              <div id="ck_ea_age">
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_18" value="청년"">
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_35" value="중년">
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_50" value="장년">
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_65" value="어르신">
            </div>
            <script>
               $(function(){
                  $("#ck_ea_age").hide();
                  $("#ea_age_18").click(function(){
                     $("#ck_ea_age_18").click();
                     if($("#ck_ea_age_18").is(":checked") == true){
                    	 $(this).css({'background':'darkgray'});
                     }else{
                    	 $(this).css('background', 'RGB(221,228,236)');
                     }
                  });
                  $("#ea_age_35").click(function(){
                     $("#ck_ea_age_35").click();
                     if($("#ck_ea_age_35").is(":checked") == true){
                    	 $(this).css({'background':'darkgray'});
                     }else{
                    	 $(this).css('background', 'RGB(221,228,236)');
                     }
                  });
                  $("#ea_age_50").click(function(){
                     $("#ck_ea_age_50").click();
                     if($("#ck_ea_age_50").is(":checked") == true){
                    	 $(this).css({'background':'darkgray'});
                     }else{
                    	 $(this).css('background', 'RGB(221,228,236)');
                     }
                  });
                  $("#ea_age_65").click(function(){
                     $("#ck_ea_age_65").click();
                     if($("#ck_ea_age_65").is(":checked") == true){
                    	 $(this).css({'background':'darkgray'});
                     }else{
                    	 $(this).css('background', 'RGB(221,228,236)');
                     }
                  });
               });
           </script>
          </h3>
          <h3 id = "ea_areatable_div">
              <div id= "ea_areatable">
	              <button type="button" class = "select_btn" id= "GP">가평군</button>
	              <button type="button" class = "select_btn" id= "GY">고양시</button>
	              <button type="button" class = "select_btn" id= "GC">과천시</button>
	              <button type="button" class = "select_btn" id= "GM">광명시</button>
	              <button type="button" class = "select_btn" id= "GJ">광주시</button>
	              <button type="button" class = "select_btn" id= "GL">구리시</button>
	              <button type="button" class = "select_btn" id= "GP2">군포시</button>
	              <br>
	              <button type="button" class = "select_btn" id= "GP3">김포시</button>
	              <button type="button" class = "select_btn" id= "NYJ">남양주시</button>
	              <button type="button" class = "select_btn" id= "DDC">동두천시</button>
	              <button type="button" class = "select_btn" id= "BC">부천시</button>
	              <button type="button" class = "select_btn" id= "SN">성남시</button>
	              <button type="button" class = "select_btn" id= "SW">수원시</button>
	              <button type="button" class = "select_btn" id= "SH">시흥시</button>
	              <br>
	              <button type="button" class = "select_btn" id= "AS">안산시</button>
	              <button type="button" class = "select_btn" id= "AY">안양시</button>
	              <button type="button" class = "select_btn" id= "PJ">파주시</button>
	              <button type="button" class = "select_btn" id= "PT">평택시</button>
	              <button type="button" class = "select_btn" id= "PC">포천시</button>
	              <button type="button" class = "select_btn" id= "HN">하남시</button>
	              <button type="button" class = "select_btn" id= "HS">화성시</button>
              </div>
              <div id="ck_ea_areatable">
             	<input type="checkbox" name="ck_lc" id="ck_GP" value="가평군">
				<input type="checkbox" name="ck_lc" id="ck_GY" value="고양시">
				<input type="checkbox" name="ck_lc" id="ck_GC" value="과천시">
				<input type="checkbox" name="ck_lc" id="ck_GM" value="광명시">
				<input type="checkbox" name="ck_lc" id="ck_GJ" value="광주시">
				<input type="checkbox" name="ck_lc" id="ck_GL" value="구리시">
				<input type="checkbox" name="ck_lc" id="ck_GP2" value="군포시">
              	<input type="checkbox" name="ck_lc" id="ck_GP3" value="김포시">
				<input type="checkbox" name="ck_lc" id="ck_NYJ" value="남양주시">
				<input type="checkbox" name="ck_lc" id="ck_DDC" value="동두천시">
				<input type="checkbox" name="ck_lc" id="ck_BC" value="부천시">
				<input type="checkbox" name="ck_lc" id="ck_SN" value="성남시">
				<input type="checkbox" name="ck_lc" id="ck_SW" value="수원시">
				<input type="checkbox" name="ck_lc" id="ck_SH" value="시흥시">
				<input type="checkbox" name="ck_lc" id="ck_AS" value="안산시"">
				<input type="checkbox" name="ck_lc" id="ck_AY" value="안양시">
				<input type="checkbox" name="ck_lc" id="ck_PJ" value="파주시">
				<input type="checkbox" name="ck_lc" id="ck_PT" value="평택시">
				<input type="checkbox" name="ck_lc" id="ck_PC" value="포천시">
				<input type="checkbox" name="ck_lc" id="ck_HN" value="하남시">
				<input type="checkbox" name="ck_lc" id="ck_HS" value="화성시">
            </div>
             <script>
               $(function(){
                  $("#ck_ea_areatable").hide();
                  $("#GP").click(function(){
                     $("#ck_GP").click();
                     if($("#ck_GP").is(":checked") == true){
                    	 $(this).css({'background':'darkgray'});
                     }else{
                    	 $(this).css('background', 'RGB(221,228,236)');
                     }
                  });
                  $("#GY").click(function(){
                      $("#ck_GY").click();
                      if($("#ck_GY").is(":checked") == true){
                     	 $(this).css({'background':'darkgray'});
                      }else{
                     	 $(this).css('background', 'RGB(221,228,236)');
                      }
                   });
                  $("#GC").click(function(){
                      $("#ck_GC").click();
                      if($("#ck_GC").is(":checked") == true){
                     	 $(this).css({'background':'darkgray'});
                      }else{
                     	 $(this).css('background', 'RGB(221,228,236)');
                      }
                   });
                  $("#GM").click(function(){
                      $("#ck_GM").click();
                      if($("#ck_GM").is(":checked") == true){
                     	 $(this).css({'background':'darkgray'});
                      }else{
                     	 $(this).css('background', 'RGB(221,228,236)');
                      }
                   });
                  $("#GJ").click(function(){
                      $("#ck_GJ").click();
                      if($("#ck_GJ").is(":checked") == true){
                     	 $(this).css({'background':'darkgray'});
                      }else{
                     	 $(this).css('background', 'RGB(221,228,236)');
                      }
                   });
                  $("#GL").click(function(){
                      $("#ck_GL").click();
                      if($("#ck_GL").is(":checked") == true){
                     	 $(this).css({'background':'darkgray'});
                      }else{
                     	 $(this).css('background', 'RGB(221,228,236)');
                      }
                   });
                  $("#GP2").click(function(){
                      $("#ck_GP2").click();
                      if($("#ck_GP2").is(":checked") == true){
                     	 $(this).css({'background':'darkgray'});
                      }else{
                     	 $(this).css('background', 'RGB(221,228,236)');
                      }
                   });
                  $("#GP3").click(function(){
                      $("#ck_GP3").click();
                      if($("#ck_GP3").is(":checked") == true){
                     	 $(this).css({'background':'darkgray'});
                      }else{
                     	 $(this).css('background', 'RGB(221,228,236)');
                      }
                   });
                   $("#NYJ").click(function(){
                       $("#ck_NYJ").click();
                       if($("#ck_NYJ").is(":checked") == true){
                      	 $(this).css({'background':'darkgray'});
                       }else{
                      	 $(this).css('background', 'RGB(221,228,236)');
                       }
                    });
                   $("#DDC").click(function(){
                       $("#ck_DDC").click();
                       if($("#ck_DDC").is(":checked") == true){
                      	 $(this).css({'background':'darkgray'});
                       }else{
                      	 $(this).css('background', 'RGB(221,228,236)');
                       }
                    });
                   $("#BC").click(function(){
                       $("#ck_BC").click();
                       if($("#ck_BC").is(":checked") == true){
                      	 $(this).css({'background':'darkgray'});
                       }else{
                      	 $(this).css('background', 'RGB(221,228,236)');
                       }
                    });
                   $("#SN").click(function(){
                       $("#ck_SN").click();
                       if($("#ck_SN").is(":checked") == true){
                      	 $(this).css({'background':'darkgray'});
                       }else{
                      	 $(this).css('background', 'RGB(221,228,236)');
                       }
                    });
                   $("#SW").click(function(){
                       $("#ck_SW").click();
                       if($("#ck_SW").is(":checked") == true){
                      	 $(this).css({'background':'darkgray'});
                       }else{
                      	 $(this).css('background', 'RGB(221,228,236)');
                       }
                    });
                   $("#SH").click(function(){
                       $("#ck_SH").click();
                       if($("#ck_SH").is(":checked") == true){
                      	 $(this).css({'background':'darkgray'});
                       }else{
                      	 $(this).css('background', 'RGB(221,228,236)');
                       }
                    });
                   $("#AS").click(function(){
                       $("#ck_AS").click();
                       if($("#ck_AS").is(":checked") == true){
                      	 $(this).css({'background':'darkgray'});
                       }else{
                      	 $(this).css('background', 'RGB(221,228,236)');
                       }
                    });
                    $("#AY").click(function(){
                        $("#ck_AY").click();
                        if($("#ck_AY").is(":checked") == true){
                       	 $(this).css({'background':'darkgray'});
                        }else{
                       	 $(this).css('background', 'RGB(221,228,236)');
                        }
                     });
                    $("#PJ").click(function(){
                        $("#ck_PJ").click();
                        if($("#ck_PJ").is(":checked") == true){
                       	 $(this).css({'background':'darkgray'});
                        }else{
                       	 $(this).css('background', 'RGB(221,228,236)');
                        }
                     });
                    $("#PT").click(function(){
                        $("#ck_PT").click();
                        if($("#ck_PT").is(":checked") == true){
                       	 $(this).css({'background':'darkgray'});
                        }else{
                       	 $(this).css('background', 'RGB(221,228,236)');
                        }
                     });
                    $("#PC").click(function(){
                        $("#ck_PC").click();
                        if($("#ck_PC").is(":checked") == true){
                       	 $(this).css({'background':'darkgray'});
                        }else{
                       	 $(this).css('background', 'RGB(221,228,236)');
                        }
                     });
                    $("#HN").click(function(){
                        $("#ck_HN").click();
                        if($("#ck_HN").is(":checked") == true){
                       	 $(this).css({'background':'darkgray'});
                        }else{
                       	 $(this).css('background', 'RGB(221,228,236)');
                        }
                     });
                    $("#HS").click(function(){
                        $("#ck_HS").click();
                        if($("#ck_HS").is(":checked") == true){
                       	 $(this).css({'background':'darkgray'});
                        }else{
                       	 $(this).css('background', 'RGB(221,228,236)');
                        }
                     });
               });
           </script>
          </h3>
      </div>
	</div>
	  <div class = "sp_button">
		<%if(loginUser!=null && loginUser.getMemberNickName().equals("운영자")) {%>
		<input type="button" class="result_btn" onclick="location.href='insertForm.ea'"id="insertBtn" value="작성하기">
		<%}%>
		<input type='button' class='result_btn' name='btn' value='초기화' style="float: right;" id="btnReset">
		<input type='button' class='result_btn' id='btnSearch' name='btn' value='검색' style="float: right;" id="p_sp_search_submit">
		</div>
		<br clear="all">
		<div class="serachList" align="center">
			<ul class="thumbnailArea" id=boardArea>
				
			</ul>
		</div>
		<script>
		$(function(){//페이지 생성될때 리스트 불러오기
			$.ajax({
	            url:'SearchList.ea',
	            data: {
				},
	            success: function(data){
	               var boardArea = $("#boardArea");
	               if(data.length>0){
	                  var boardSize = 0;
	  	              console.log(data);
	                  for(var i=0;i<data.length; i++){
						var input="<li class='board_list' id='board_list"+i+"'><div class='imageArea' id='imageArea"+i+"'>"
						+"</div><div class='textArea' id='textArea"+i+"'><h3>"
						+data[i].boardNo+"."
						+data[i].boardTitle+"</h3><p><b>접수상태 </b>"
						+data[i].acState+"</p><p><b>지원대상 </b>"
						+data[i].tcName+"</p><p><b>지역 </b>"
						+data[i].lcName+"</p></div><div class='textArea1' id='textArea1"+i+"'><b>조회수 </b>"
						+data[i].boardViewCount+"</p><p><b>추전수 </b>"
						+data[i].boardReCommend+"</p></div></li>";
						boardArea.append(input);
	                  	getAdoptImage(data[i].boardNo,data.length,i);
	                  	$('#board_list'+i).attr("onclick","location.href='detail.ea?bId="+data[i].boardNo+"'");
	                  }
						getlistCount();
	               }else{
	            	   var input="등록된 게시판이 없습니다.";
	               }
	            },
	            error: function(data){
	               alert("ajax 콘텐츠 에러 발생")
	            }
			});
		});
		$('#btnSearch').click(function(){// 검색 눌렀을때 리스트 불러오기
   			var age = document.getElementsByName("ck_ea_age");
          	var checkAge = [];
          	for(var i = 0; i < age.length; i++){
             	if(age[i].checked == true){
              		checkAge.push(age[i].value);
              	}
          	}
          	var local = document.getElementsByName("ck_lc");
          	var checkLocal = [];
          	for(var i = 0; i < local.length; i++){
       	 		if(local[i].checked == true){
              		checkLocal.push(local[i].value);
              	}
          	}
   			$.ajax({
   				url: 'SearchList.ea',
    			data: {cate:$('#ea_category').val(),
						age: checkAge,
    					local:checkLocal
   						},
    			success: function(data){
    				 var boardArea = $("#boardArea");
    				$('#boardArea').empty();
    				if(data==null){
    					  var input="등록된 게시판이 없습니다.";
	  						boardArea.append(input);
    				}else{
	  	               if(data.length>0){
	  	                  var boardSize = 0;
	  	                  for(var i=0;i<data.length; i++){
	  	                	console.log("검색결과 : " + data.lenght);
	  						var input="<li class='board_list' id='board_list"+i+"'><div class='imageArea' id='imageArea"+i+"'>"
	  						+"</div><div class='textArea' id='textArea"+i+"'><h3>"
	  						+data[i].boardNo+"."
	  						+data[i].boardTitle+"</h3><p><b>접수상태 </b>"
	  						+data[i].acState+"</p><p><b>지원대상 </b>"
	  						+data[i].tcName+"</p><p><b>지역 </b>"
	  						+data[i].lcName+"</p></div><div class='textArea1' id='textArea1"+i+"'><b>조회수 </b>"
	  						+data[i].boardViewCount+"</p><p><b>추전수 </b>"
	  						+data[i].boardReCommend+"</p></div></li>"
	  						boardArea.append(input);
	  	                  	getAdoptImage(data[i].boardNo,data.length,i);
	  	                  	$('#board_list'+i).attr("onclick","location.href='detail.ea?bId="+data[i].boardNo+"'");
	  	                  }
	  	                getlistCount();
	  	               }else{
	  	            	   var input="등록된 게시판이 없습니다.";
	  						boardArea.append(input);
	  	               }
    				}
  	            },
  	            error: function(data){
  	               alert("ajax 콘텐츠 에러 발생")
  	            }
	      		});
      	});
		function getAdoptImage(boardNo,length,i){//리스트에 맞게 이미지 가져오기
	         $.ajax({
	            url: 'SearchIeage.ea',
	            success: function(data){
	            	  var input=""; 
	                  for (var j = 0; j < data.length; j++) {
	                  var imageArea = $("#imageArea"+i);
		                  if(boardNo == data[j].boardNo){
		                  	input="<img src='<%= request.getContextPath() %>/UploadFolder/external_uploadFiles/"+data[j].changeName+"' width='150px' height='100%'>";
		                  	imageArea.prepend(input);
	                  }
	                  }
	            },
	            error: function(data){
	               alert("ajax 이미지 에러 발생");
	            }
	         });
	      }
		$('#btnReset').click(function(){//초기화 버튼
			$("input[type=checkbox]").prop("checked",false);
			$(".select_btn").css('background', 'RGB(221,228,236)');
			$('#ea_category').val('선택');
		});
		
		function getlistCount(){
			var imgs;
			var img_count;
			var img_position = 1;
			
			imgs = $(".thumbnailArea");
			img_count = imgs.children().length; //ul의 자식, 즉 li의 갯수
			
			maxPage = Math.ceil(img_count)/5;
			 $(".thumbnailArea").css({'left':'0px'});
			console.log("최대 페이지" + maxPage);
			$('#btnBack').click(function(){
				back();
			});
			
			$('#btnNext').click(function(){
				next();
			});
		 function back() {
		        if(1<img_position){
		          imgs.animate({
		            left:'+=610px'
		          });
		          img_position--;
					console.log("이전 :"+img_position);
		        }
		      }
		      function next() {
		        if(maxPage>img_position){
		          imgs.animate({
		            left:'-=610px'
		          });
		          img_position++;
					console.log("다음 :"+img_position);
		        }
		      }
		}
		
      </script>
	</div>		
	<div>
			<input type="button" id="btnNext" value=">>">
	</div>		
	</section>
	<%@ include file="../Common/footer.jsp"%>
</body>
</html>