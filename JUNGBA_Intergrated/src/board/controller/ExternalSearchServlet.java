package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import board.model.service.ExternalService;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.PageInfo;

/**
 * Servlet implementation class ExternalSearch
 */
@WebServlet("/SearchList.ea")
public class ExternalSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExternalSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExternalService service = new ExternalService();

		String[] agearr = request.getParameterValues("age[]");
        String[] localarr = request.getParameterValues("local[]");
        ArrayList<Board> bList = new ArrayList<Board>(); // 게시판 리스트 가져오기
        String category = request.getParameter("cate");
        
        System.out.println("category : " + category);
		 System.out.println("age : " + agearr);
		 System.out.println("local : " +localarr );
        
        if(agearr == null && localarr==null && (category==null || category.equals("선택"))) {
        	bList = service.selectExList(1); // 게시판 리스트 가져오기
        }else {
		 bList = service.selectExSearchList(1,category,agearr,localarr); // 게시판 리스트 가져오기
        }
        response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(bList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
