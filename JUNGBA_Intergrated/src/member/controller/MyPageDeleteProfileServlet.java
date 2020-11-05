package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/deleteProfile.me")
public class MyPageDeleteProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyPageDeleteProfileServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int loginMemberNo = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
		
		MemberService memberService = new MemberService();
		int result = memberService.deleteProfile(loginMemberNo);
		
		if(result > 0) {
			response.sendRedirect("myPage.me");
		} else {
			request.setAttribute("msg", "프로필 삭제에 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
