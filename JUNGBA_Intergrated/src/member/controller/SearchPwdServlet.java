package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class SearchId
 */
@WebServlet("/searchPwd.me")
//@WebServlet(urlPatterns = "/searchPwd.me", name="MemberUpdatePwdServlet")
public class SearchPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("userName");
		String id = request.getParameter("userId");
		String phoneA = request.getParameter("userPhone");
		String email = request.getParameter("userEmail");
		String radio = request.getParameter("pwsearchradio");
		
		System.out.println("name:" +name);
		System.out.println("id:" + id);
		System.out.println("phone:" +phoneA);
		System.out.println("email:" +email);
		System.out.println("radio:" +radio);
		
		ArrayList<Member> list = new MemberService().searchPwd(name);
		
        PrintWriter out = response.getWriter();
     	System.out.println("list"+list); 	
     	
     	String page = null;
      	
     	Member mb = new MemberService().selectMember(id);
     	
     	
     	if(radio.equals("email"))  {
     		if(name !=null || id !=null || email != null) {
	     		System.out.println("1");
		      	for(Member m : list) {
		      		System.out.println("2");
			      	if(email.equals(m.getMemberEmail()) && id.equals(m.getMemberId()) ){
			      		System.out.println("3");
			      	      if(list != null ) {
			      	    	page = "WEB-INF/views/Member/비밀번호찾기후_변경.jsp"; //비밀번호 jsp 하나더 생성
			      	    	request.setAttribute("m", m);  	
			      	      } else {
				    			page = "WEB-INF/views/Common/errorPage.jsp";
				    			request.setAttribute("msg", "비밀번호 찾기에 실패하였습니다.");
				    	    }
			      	} else {
						page = "WEB-INF/views/Common/errorPage.jsp";
						request.setAttribute("msg", "비밀번호 찾기에 실패하였습니다.");
				    }
		      	}
     		} else {
				page = "WEB-INF/views/Common/errorPage.jsp";
				request.setAttribute("msg", "세개 전부 값을 넣어주세요.");
	      	}
     	} else if(radio.equals("phone")) {
     		if(name !=null || id !=null || phoneA != null) {
     		if(phoneA.length()==11) {
     		System.out.println("5");
	    		String phone1=phoneA.substring(0,3);
	    		String phone2=phoneA.substring(3,7);
	    		String phone3=phoneA.substring(7,11);
	    		phone1 += ("-"); phone1 += phone2; phone1 += ("-"); phone1 += phone3;
		      	for(Member m : list) {
		      		if(phone1.equals(m.getMemberPhone()) && id.equals(m.getMemberId()) ){
		      	      	if(list != null ) {
			      	    	page = "WEB-INF/views/Member/비밀번호찾기후_변경.jsp"; //비밀번호 jsp 하나더 생성
			      	    	request.setAttribute("m", m);  	
		      	      		} else {
			    			page = "WEB-INF/views/Common/errorPage.jsp";
			    			request.setAttribute("msg", "비밀번호 찾기에 실패하였습니다.");
			    	    }
		      		} else {
		    			page = "WEB-INF/views/Common/errorPage.jsp";
		    			request.setAttribute("msg", "비밀번호 찾기에 실패하였습니다.");
		    	    }
		      	}
     		} else {
    			page = "WEB-INF/views/Common/errorPage.jsp";
    			request.setAttribute("msg", "비밀번호 11자리를 입력해주세요.");
     		}
     	}else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "세개 전부 값을 넣어주세요.");
	      }
      	} else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "비밀번호 찾기에 실패하였습니다.");
	    }
     	
     	request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
