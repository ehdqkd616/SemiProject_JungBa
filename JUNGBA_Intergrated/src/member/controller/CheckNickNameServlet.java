package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class CheckNickNameServlet
 */
@WebServlet("/checkNickName.me")
public class CheckNickNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckNickNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName= request.getParameter("nickName");
		String no = request.getParameter("no");
		int result = new MemberService().nickName(nickName);
		
      	PrintWriter out = response.getWriter();
      	System.out.println("여긴 서브릿");
      	System.out.println("nickName:" +nickName);
      	System.out.println("no:" +no);
      	if(!nickName.equals(no)){
	      	if(result >0) {
	      		out.append("fail");
	      	}else {
	      		out.append("success");
	      	}
      	}else {
      		out.append("success");
      	}
      	out.flush();
      	out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
