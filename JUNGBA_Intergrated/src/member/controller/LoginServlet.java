package member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

//@WebServlet("/login.me") //연결할수 있는 언어테이션
@WebServlet(urlPatterns = "/login.me", name="LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("userId");
		String memberPw = request.getParameter("userPwd");
		
		Member member = null;
		Member loginUser = null;
		
		System.out.println("일반 로그인");
		member = new Member(memberId, memberPw);
		loginUser = new MemberService().loginMember(member);

		
		if(loginUser != null) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(600);
		session.setAttribute("loginUser", loginUser);
		response.sendRedirect(request.getContextPath());
			
		} else {
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
