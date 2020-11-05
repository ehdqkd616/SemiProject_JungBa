package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class SearchPwdServlet2
 */
//@WebServlet("/SearchPwdServlet2.me")
@WebServlet(urlPatterns = "/SearchPwd2.me", name="MemberUpdatePwdServlet")
public class SearchPwdUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPwdUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pwd1 = request.getParameter("userPwd1");
		String pwd2 = request.getParameter("userPwd2");
		
		System.out.println("pwd1"+pwd1);
		System.out.println("pwd2"+pwd2);
		
		String page = null;
		Member m = new Member(id,pwd1);
		
		if(pwd1.length() >= 6) {
			if(pwd1.equals(pwd2)) {
				int result = new MemberService().modifyPwdMember(m);
				
				if(result > 0) {
					request.setAttribute("msg", "비밀번호가 재설정 되었습니다.");
					page = "WEB-INF/views/Member/비밀번호찾기_성공_알림창.jsp";
				} else {
					request.setAttribute("msg", "비밀번호가 재설정에 실패하였습니다.");
					page = "WEB-INF/views/Common/errorPage.jsp";
				}
			} else {
				request.setAttribute("msg", "비밀번호가 일치하지않습니다.");
				page = "WEB-INF/views/Common/errorPage.jsp";
			}
		} else {
			request.setAttribute("msg", "비밀번호는 6자 이상이여야합니다.");
			page = "WEB-INF/views/Common/errorPage.jsp";
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
