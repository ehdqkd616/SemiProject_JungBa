package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dao.MemberDAO;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateFormServlet
 */
@WebServlet("/update.me")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("업데이트 서블릿 입니다.");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginUser");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("myId");
		String name = request.getParameter("myName");
		String nickName = request.getParameter("myNickName");
		String gender = request.getParameter("myGender");
		String date = request.getParameter("myBirthDay");
		Date birthday = null;
		System.out.println("gender"+gender);
		System.out.println("생일"+date);
		
		
		if(date != "") {
			String[] dateArr = date.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1]) - 1;
			int day = Integer.parseInt(dateArr[2]);
			
			birthday = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		}else {
			birthday  = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		System.out.println("생일"+birthday);
		String phone = request.getParameter("myPhone");
		String email = request.getParameter("myEmail");
		String address = request.getParameter("myAddress");
		
		Member m = new Member(id,name,nickName,gender,birthday,phone,email,address);
		int result = new MemberService().updateInfo(m);	

		
		String page = null;
		System.out.println(result);
		if(result > 0) {
			System.out.println("result > 0 다음 내용");
			
			Member member = new Member(loginMember.getMemberId(), loginMember.getMemberPw()); 
			Member loginUser = new MemberService().loginMember(member);
			System.out.println("login" + loginUser);
			
			session.setMaxInactiveInterval(600);
			session.setAttribute("loginUser", loginUser);
			
			request.getRequestDispatcher("WEB-INF/views/Member/회원정보.jsp").forward(request, response);
			/* request.setAttribute("loginUser", loginUser); */
		} else {
			request.getSession().setAttribute("msg", " 정보수정에 실패");
			page = "WEB-INF/views/Common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
