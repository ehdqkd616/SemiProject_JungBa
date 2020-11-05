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
@WebServlet("/searchId.me")
public class SearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String phoneA = request.getParameter("phone");
		String email = request.getParameter("email");
		String radio = request.getParameter("radio");
		
		System.out.println("name: " +name);
		System.out.println("phone:" +phoneA);
		System.out.println("email:" +email);
		System.out.println("radio:" + radio);
		
		
		ArrayList<Member> list = new MemberService().searchId(name);
		
        PrintWriter out = response.getWriter();
     	System.out.println("list"+list); 	
      	
     	if(radio.equals("email"))  {  
	      	for(Member m : list) {
		      	if(email.equals(m.getMemberEmail())){
		      	      if(list != null ) {
		      	    	  out.append("  '" +m.getMemberId()+"'  ");  
		      	      }
		      	}
	      	}
     	} else if(radio.equals("phone")) {
    		String phone1=phoneA.substring(0,3);
    		String phone2=phoneA.substring(3,7);
    		String phone3=phoneA.substring(7,11);
    		phone1 += ("-"); phone1 += phone2; phone1 += ("-"); phone1 += phone3;
	      	for(Member m : list) {
	      		if(phone1.equals(m.getMemberPhone())){
	      	      	if(list != null ) {
	      	      		out.append("  '"+m.getMemberId()+"'  ");  
	      	      	}
	      		}
	      	}
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
