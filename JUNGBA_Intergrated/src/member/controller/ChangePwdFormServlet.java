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
 * Servlet implementation class ChangePwdFormServlet
 */
//@WebServlet("/changePwdForm.me")
@WebServlet(urlPatterns = "/changePwdForm.me", name="MemberUpdatePwdServlet2")
public class ChangePwdFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdFormServlet() {
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
		
		System.out.println("pwd1"+pwd1);
		
		String page = null;
		Member m = new MemberService().selectMember(id);
		
		System.out.println("m.getMemberPw()"+m.getMemberPw());
		if(pwd1 !=null) {
			if(pwd1.equals(m.getMemberPw())) {
				page = "WEB-INF/views/Member/비밀번호변경.jsp";
			} else {
				request.setAttribute("msg", "비밀번호가 일치하지않습니다.");
				page = "WEB-INF/views/Common/errorPage.jsp";
			}
		} else {
			request.setAttribute("msg", "비밀번호값이 존재하지않습니다.");
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
