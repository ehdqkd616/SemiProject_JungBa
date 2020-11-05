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

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet(urlPatterns = "/Delete.me", name="DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Id = request.getParameter("userId");
		String Pwd = request.getParameter("userPwd");
		System.out.println("Pwd"+ Pwd);
		System.out.println("Id"+ Id);
		
		Member m = new MemberService().selectMember(Id);
		System.out.println("get"+m.getMemberPw());
		String page = null;
		
		if(!Pwd.equals(null)) {
			if(Pwd.equals(m.getMemberPw())) {
				int result = new MemberService().deleteMember(Id);
				System.out.println("memberId: "+Id);
				System.out.println("result:"+result);
				
				if(result > 0) { 
					HttpSession session = request.getSession();
					session.invalidate(); 
					page = "WEB-INF/views/Member/비밀번호찾기_성공_알림창.jsp";
					request.setAttribute("msg", "탈퇴에 성공하셨습니다.");
				} else {
					page = "WEB-INF/views/Common/errorPage.jsp";
					request.setAttribute("msg", "삭제에 실패하였습니다.");
				}
			} else {
				page = "WEB-INF/views/Common/errorPage.jsp";
				request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			}
		} else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "비밀번호가 존재하지않습니다.");
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
