<%@page import="java.util.ArrayList"%>
<%@page import="board.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("board"); 
// 	System.out.println("대외활동커뮤_글수정 에서의 Board : " + b);	
	
	ArrayList<FileVO> fList = (ArrayList<FileVO>)request.getAttribute("fileList");
	FileVO titleImg = fList.get(0);
	String categoryStr = b.getCgName();
	String[] categoryarr = new String[7];
	String[] splitCate = categoryStr.split(",");
	for(int i = 0; i < splitCate.length; i++){
		switch(splitCate[i]){
		case "선택" : categoryarr[0] = "selected"; break; 
		case "공모전" : categoryarr[1] = "selected"; break; 
		case "채용박람회/취업박람회" : categoryarr[2] = "selected"; break; 
		case "경진대회" : categoryarr[3] = "selected"; break; 
		case "봉사활동" : categoryarr[4] = "selected"; break; 
		case "서포터즈" : categoryarr[5] = "selected"; break; 
		case "관람/전시/체험" : categoryarr[6] = "selected"; break; 
		}
	}
	
	String tcName = b.getTcName();
	String[] tcarr = new String[4];
	String[] splitTc = tcName.split(",");
	for(int i = 0; i < splitTc.length; i++){
		switch(splitTc[i]){
		case "청년": tcarr[0] = "checked"; break;
		case "중년": tcarr[1] = "checked"; break;
		case "장년": tcarr[2] = "checked"; break;
		case "어르신": tcarr[3] = "checked"; break;
		}
	}
	
	String lcName = b.getLcName();
	String[] lcarr = new String[21];
	String[] splitLc = lcName.split(",");
	for(int i = 0; i < splitLc.length; i++){
		switch(splitLc[i]){
		case "가평군": lcarr[0] = "checked"; break;
		case "고양시": lcarr[1] = "checked"; break;
		case "과천시": lcarr[2] = "checked"; break;
		case "광명시": lcarr[3] = "checked"; break;
		case "광주시": lcarr[4] = "checked"; break;
		case "구리시": lcarr[5] = "checked"; break;
		case "군포시": lcarr[6] = "checked"; break;
		case "김포시": lcarr[7] = "checked"; break;
		case "남양주시": lcarr[8] = "checked";break;
		case "동두천시": lcarr[9] = "checked";break;
		case "부천시": lcarr[10] = "checked"; break;
		case "성남시": lcarr[11] = "checked"; break;
		case "수원시": lcarr[12] = "checked"; break;
		case "시흥시": lcarr[13] = "checked"; break;
		case "안산시": lcarr[14] = "checked"; break;
		case "안양시": lcarr[15] = "checked"; break;
		case "파주시": lcarr[16] = "checked"; break;
		case "평택시": lcarr[17] = "checked"; break;
		case "포천시": lcarr[18] = "checked"; break;
		case "하남시": lcarr[19] = "checked"; break;
		case "화성시": lcarr[20] = "checked"; break;
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>정부지원금 바로지금</title>
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


section{
      display: block;
  }
  .writerinformanion_box{
      background-color: RGB(248,248,248);
      display: flex;
  }
  .writertext{
      list-style: none;
      line-height: 70px;
  }
  .select_box{
      margin: 16px;
      line-height: 70px;
  }
  .select_btn{
      height: 50px;
      width: 100px;
      background-color:  RGB(221,228,236);
  }
  .categoty_select{
      height: 40px;
  }
  .writer_box{
      margin: 0px auto;
  }
  .writer_table{
    margin: 0px;
    margin-top: 100px;
    width: 100%;
    line-height: 80px;
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
</style>
<body>
    <%@ include file="../Common/header.jsp" %>
 <!----------------------------------------header------------------------------------------->
 <section>
 <div id="main_section">
  <form action="<%= request.getContextPath() %>/eaModify.cm" method="post" encType="multipart/form-data">
    <h1 align="center">대외활동 수정</h1>
    <div class="writerinformanion_box">
      <ul class="writertext">
          <th>
              <li><h3>카테고리</h3></li>
              <li><h3>대상</h3></li>
              <li><h3>지역</h3></li>
          </th>
      </ul>
      <!-- 대외활동 글쓰기 카테고리,대상,지역별 선택 -->
      <div class="select_box">
          <h3>
              <select class="categoty_select" name="ea_category">
                  <option value="선택" <%= categoryarr[0] %> selected disabled hidden  >선택해주세요.</option>
                  <option value="공모전" <%= categoryarr[1] %>>공모전</option>
                  <option value="채용박람회/취업박람회" <%= categoryarr[2] %>>채용박람회/취업박람회</option>
                  <option value="경진대회" <%= categoryarr[3] %>>경진대회</option>
                  <option value="봉사활동" <%= categoryarr[4] %>>봉사활동</option>
                  <option value="서포터즈" <%= categoryarr[5] %>>서포터즈</option>
                  <option value="관람/전시/체험" <%= categoryarr[6] %>>관람/전시/체험</option>
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
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_18" value="청년" <%= tcarr[0] %>>
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_35" value="중년" <%= tcarr[1] %>>
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_50" value="장년" <%= tcarr[2] %>>
				<input type="checkbox" name="ck_ea_age" id="ck_ea_age_65" value="어르신" <%= tcarr[3] %>>
            </div>
            <script>
            $(document).ready(function(){
            	if($("#ck_ea_age_18").is(":checked") == true){
           		 $("#ea_age_18").css({'background':'darkgray'});
           		}
           		if($("#ck_ea_age_35").is(":checked") == true){
           		  	 $("#ea_age_35").css({'background':'darkgray'});
           	    }
           		if($("#ck_ea_age_50").is(":checked") == true){
           		  	 $("#ea_age_50").css({'background':'darkgray'});
           	    }
           		if($("#ck_ea_age_65").is(":checked") == true){
           		  	 $("#ea_age_65").css({'background':'darkgray'});
           	    }
           		if($("#ck_GP").is(":checked") == true){
          		  	 $("#GP").css({'background':'darkgray'});
          	    }
           		if($("#ck_GY").is(":checked") == true){
          		  	 $("#GY").css({'background':'darkgray'});
          	    }
           		if($("#ck_GC").is(":checked") == true){
          		  	 $("#GC").css({'background':'darkgray'});
          	    }
           		if($("#ck_GM").is(":checked") == true){
          		  	 $("#GM").css({'background':'darkgray'});
          	    }
           		if($("#ck_GJ").is(":checked") == true){
          		  	 $("#GJ").css({'background':'darkgray'});
          	    }
           		if($("#ck_GL").is(":checked") == true){
          		  	 $("#GL").css({'background':'darkgray'});
          	    }
           		if($("#ck_GP2").is(":checked") == true){
          		  	 $("#GP2").css({'background':'darkgray'});
          	    }
           		if($("#ck_GP3").is(":checked") == true){
          		  	 $("#GP3").css({'background':'darkgray'});
          	    }
           		if($("#ck_NYJ").is(":checked") == true){
          		  	 $("#NYJ").css({'background':'darkgray'});
          	    }
           		if($("#ck_DDC").is(":checked") == true){
          		  	 $("#DDC").css({'background':'darkgray'});
          	    }
           		if($("#ck_BC").is(":checked") == true){
          		  	 $("#BC").css({'background':'darkgray'});
          	    }
           		if($("#ck_SN").is(":checked") == true){
          		  	 $("#SN").css({'background':'darkgray'});
          	    }
           		if($("#ck_SW").is(":checked") == true){
          		  	 $("#SW").css({'background':'darkgray'});
          	    }
           		if($("#ck_SH").is(":checked") == true){
          		  	 $("#SH").css({'background':'darkgray'});
          	    }
           		if($("#ck_AS").is(":checked") == true){
          		  	 $("#AS").css({'background':'darkgray'});
          	    }
           		if($("#ck_AY").is(":checked") == true){
          		  	 $("#AY").css({'background':'darkgray'});
          	    }
           		if($("#ck_PJ").is(":checked") == true){
          		  	 $("#PJ").css({'background':'darkgray'});
          	    }
           		if($("#ck_PT").is(":checked") == true){
          		  	 $("#PT").css({'background':'darkgray'});
          	    }
           		if($("#ck_PC").is(":checked") == true){
          		  	 $("#PC").css({'background':'darkgray'});
          	    }
           		if($("#ck_HN").is(":checked") == true){
          		  	 $("#HN").css({'background':'darkgray'});
          	    }
           		if($("#ck_HS").is(":checked") == true){
          		  	 $("#HS").css({'background':'darkgray'});
          	    }
            	});
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
	              <button type="button" class = "select_btn" id="GP">가평군</button>
	              <button type="button" class = "select_btn" id="GY">고양시</button>
	              <button type="button" class = "select_btn" id="GC">과천시</button>
	              <button type="button" class = "select_btn" id="GM">광명시</button>
	              <button type="button" class = "select_btn" id="GJ">광주시</button>
	              <button type="button" class = "select_btn" id="GL">구리시</button>
	              <button type="button" class = "select_btn" id="GP2">군포시</button>
	              <br>
	              <button type="button" class = "select_btn" id="GP3">김포시</button>
	              <button type="button" class = "select_btn" id="NYJ">남양주시</button>
	              <button type="button" class = "select_btn" id="DDC">동두천시</button>
	              <button type="button" class = "select_btn" id="BC">부천시</button>
	              <button type="button" class = "select_btn" id="SN">성남시</button>
	              <button type="button" class = "select_btn" id="SW">수원시</button>
	              <button type="button" class = "select_btn" id="SH">시흥시</button>
	              <br>
	              <button type="button" class = "select_btn" id="AS">안산시</button>
	              <button type="button" class = "select_btn" id="AY">안양시</button>
	              <button type="button" class = "select_btn" id="PJ">파주시</button>
	              <button type="button" class = "select_btn" id="PT">평택시</button>
	              <button type="button" class = "select_btn" id="PC">포천시</button>
	              <button type="button" class = "select_btn" id="HN">하남시</button>
	              <button type="button" class = "select_btn" id="HS">화성시</button>
              </div>
              <div id="ck_ea_areatable">
             	<input type="checkbox" name="ck_lc" id="ck_GP" value="가평군" <%= lcarr[0] %>>
				<input type="checkbox" name="ck_lc" id="ck_GY" value="고양시" <%= lcarr[1] %>>
				<input type="checkbox" name="ck_lc" id="ck_GC" value="과천시" <%= lcarr[2] %>>
				<input type="checkbox" name="ck_lc" id="ck_GM" value="광명시" <%= lcarr[3] %>>
				<input type="checkbox" name="ck_lc" id="ck_GJ" value="광주시" <%= lcarr[4] %>>
				<input type="checkbox" name="ck_lc" id="ck_GL" value="구리시" <%= lcarr[5] %>>
				<input type="checkbox" name="ck_lc" id="ck_GP2" value="군포시" <%= lcarr[6] %>>
              	<input type="checkbox" name="ck_lc" id="ck_GP3" value="김포시" <%= lcarr[7] %>>
				<input type="checkbox" name="ck_lc" id="ck_NYJ" value="남양주시" <%= lcarr[8] %>>
				<input type="checkbox" name="ck_lc" id="ck_DDC" value="동두천시" <%= lcarr[9] %>>
				<input type="checkbox" name="ck_lc" id="ck_BC" value="부천시" <%= lcarr[10] %>>
				<input type="checkbox" name="ck_lc" id="ck_SN" value="성남시" <%= lcarr[11] %>>
				<input type="checkbox" name="ck_lc" id="ck_SW" value="수원시" <%= lcarr[12] %>>
				<input type="checkbox" name="ck_lc" id="ck_SH" value="시흥시" <%= lcarr[13] %>>
				<input type="checkbox" name="ck_lc" id="ck_AS" value="안산시" <%= lcarr[14] %>>
				<input type="checkbox" name="ck_lc" id="ck_AY" value="안양시" <%= lcarr[15] %>>
				<input type="checkbox" name="ck_lc" id="ck_PJ" value="파주시" <%= lcarr[16] %>>
				<input type="checkbox" name="ck_lc" id="ck_PT" value="평택시" <%= lcarr[17] %>>
				<input type="checkbox" name="ck_lc" id="ck_PC" value="포천시" <%= lcarr[18] %>>
				<input type="checkbox" name="ck_lc" id="ck_HN" value="하남시" <%= lcarr[19] %>>
				<input type="checkbox" name="ck_lc" id="ck_HS" value="화성시" <%= lcarr[20] %>>
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
      <!-- </div> -->
    </div>
    <!-- 대외활동 글쓰기 카테고리,대상,지역별 선택 -->
    <div class="writer_box">
    <input type="hidden" size="50" name="no" value="<%= b.getBoardNo() %>">
        <table class="writer_table">
          <tr>
            <th width="15%">제목</th>
            <td width="50%" colspan="3"><input type ="text" name = "ea_title" placeholder="제목을 입력하세요." class=textline1 value="<%=b.getBoardTitle() %>"></td>
          </tr>
          <tr>
            <th>현재 메인 이미지</th>
            <td colspan="3" name="ea_orgin_title_image" value="<%=titleImg.getOriginName() %>"><%= titleImg.getOriginName() %></td>
          </tr>
          <tr>
            <th>메인 이미지 수정</th>
            <td colspan="3"><input type ="file" multiple="multiple" id="ea_title_image" name = "ea_title_image" class=textline2></td>
          </tr>
          <tr>
            <th>모집 일정</th>
            <td colspan="3"><input type ="date" id="ea_res_date"name = "ea_res_date"  class=dateline1 value="<%=b.getReStartDate() %>">
              ~ <input type ="date" id="ea_ree_date"name = "ea_ree_date"  class=dateline1 value="<%=b.getReEndDate() %>"> 까지</td>
          </tr>
          <tr>
            <th>활동 기간</th>
            <td colspan="3"><input type ="date" id="ea_acs_date"name = "ea_acs_date"  class=dateline1 value="<%=b.getAcStartDate() %>">
              ~ <input type ="date" id="ea_ace_date"name = "ea_ace_date"  class=dateline1 value="<%=b.getAcEndDate() %>"> 까지</td>
          </tr>
          <tr>
            <th>내용</th>
            <td colspan="3"><textarea class="form-control" name="ea_text_contents" id="ea_text_contents" placeholder="내용을 입력해 주세요" ><%=b.getBoardContent() %></textarea></td>
          </tr>
           <%if(fList.size()>1){ %>
	          	<tr>
	            <th>현재 참고자료</th>
	            <td><%= fList.get(1).getOriginName() %></td>
	            </tr>
	            <tr>
	            <th>참고자료 수정</th>
	            <td colspan="3"><input type ="file"  multiple="multiple" id="ea_main_image" name = "ea_main_image" class=textline2></td>
	            </tr>
            <%}else{ %>
	            <tr>
	            <th>참고자료</th>
	            <td colspan="3"><input type ="file"  multiple="multiple" id="ea_main_image" name = "titleImage" class=textline2></td>
	            </tr>
            <%} %>
        </table>
    </div>
    <div class = "ea_button">
      <input type="submit" id="insertBtn" value="작성완료">
      <input type="button" id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/eaMain.cm'" value="취소하기">
    </div>
	</script>
  </form>
  </div>
</section>  
    <%@ include file="../Common/footer.jsp" %>
</body>
</html>



 