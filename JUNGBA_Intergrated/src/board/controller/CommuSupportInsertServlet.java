package board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.CommunityService;
import board.model.vo.Board;
import board.model.vo.FileVO;
import common.MyFileRenamePolicy;
import member.model.vo.Member;


/**
 * Servlet implementation class CommuSupportInsertServlet
 */
@WebServlet("/spInsert.cm")
public class CommuSupportInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommuSupportInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	      
	      // encType 이 multipart/form-data 로 전송되었는지 확인
	      if(ServletFileUpload.isMultipartContent(request)) {
	         int maxSize = 1024 * 1024 * 10;
	         String root = request.getSession().getServletContext().getRealPath("/");
	         String savePath = root + "support_uploadFiles/";
	         
	         System.out.println(savePath);
	         
	         File f = new File(savePath);
	         if(!f.exists()) {
	            f.mkdirs();
	         }
	         
			
	         MultipartRequest multiRequest 
	         = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
	         
	         ArrayList<String> saveFiles = new ArrayList<String>(); // 바뀐 파일의 이름을 저장 할 ArrayList
	         ArrayList<String> originFiles = new ArrayList<String>(); //원본 파일의 이름을 저장할 ArrayList
	         
	         Enumeration<String> files = multiRequest.getFileNames(); // getFileNames() : 폼에서 전송된 File의 name 반환 //여러개 가능
	    
	         while(files.hasMoreElements()) {
	            String name = files.nextElement();
	            
	            if(multiRequest.getFilesystemName(name)!= null) { // getFilesystemName() : RenamePolicy의 rename()에서 작성한대로 rename된 파일 명
	               saveFiles.add(multiRequest.getFilesystemName(name));
	               originFiles.add(multiRequest.getOriginalFileName(name));
	            }
	         }
		 String category = multiRequest.getParameter("sp_category");
		 String[] emStatearr = multiRequest.getParameterValues("ck_sp_em");
		 String[] agearr = multiRequest.getParameterValues("ck_sp_age");
         String[] localarr = multiRequest.getParameterValues("ck_lc");
         String title = multiRequest.getParameter("sp_title");
         String content = multiRequest.getParameter("sp_text_contents");
         String bWriter = ((Member)request.getSession().getAttribute("loginUser")).getMemberNickName();
         int userId = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
		 String emState = "";
 		 if(emStatearr != null) {
 			for (int i = 0; i< emStatearr.length; i++) {
 				if(i == emStatearr.length -1) {
 					emState += emStatearr[i];
 				}else
 					emState += emStatearr[i] + ",";
 			}
 		 }
         
         String age = "";
 		 if(agearr != null) {
 			for (int i = 0; i< agearr.length; i++) {
 				if(i == agearr.length -1) {
 					age += agearr[i];
 				}else
 					age += agearr[i] + ",";
 			}
 		 }
 		 
 		String local = "";
		 if(localarr != null) {
			for (int i = 0; i< localarr.length; i++) {
				if(i == localarr.length -1) {
					local += localarr[i];
				}else
					local += localarr[i] + ",";
			}
		 }
		 

	 		String strsp_res_date = multiRequest.getParameter("sp_res_date"); 
	 		Date sp_res_date = null;
	 		if(strsp_res_date != "") {
	 			String[] dateArr = strsp_res_date.split("-");
	 			
	 			int year = Integer.parseInt(dateArr[0]);
	 			int month = Integer.parseInt(dateArr[1]) - 1;
	 			int day = Integer.parseInt(dateArr[2]);
	 			
	 			sp_res_date = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
	 		}else {
	 			sp_res_date =new Date(new GregorianCalendar().getTimeInMillis());
	 		}  
	 		
	 		String strsp_ree_date = multiRequest.getParameter("sp_ree_date"); 
	 		Date sp_ree_date = null;
	 		if(strsp_ree_date != "") {
	 			String[] dateArr = strsp_ree_date.split("-");
	 			
	 			int year = Integer.parseInt(dateArr[0]);
	 			int month = Integer.parseInt(dateArr[1]) - 1;
	 			int day = Integer.parseInt(dateArr[2]);
	 			
	 			sp_ree_date = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
	 		}else {
	 			sp_ree_date =new Date(new GregorianCalendar().getTimeInMillis());
	 		} 
	 		 
 		
         Board b = new Board();
         
         b.setBoardTitle(title);
         b.setBoardContent(content);
         b.setBoardWriter(bWriter);
         b.setBoardWriterNo(userId);
         b.setCgName(category);
         b.setEmState(emState);
         b.setTcName(age);
         b.setLcName(local);
         b.setReStartDate(sp_res_date);
         b.setReEndDate(sp_ree_date);
         
         ArrayList<FileVO> fileList = new ArrayList<FileVO>();
         for(int i  = originFiles.size() - 1; i>=0; i--) {
        	 FileVO af = new FileVO();
        	 af.setFilePath(savePath);
        	 af.setOriginName(originFiles.get(i));
        	 af.setChangeName(saveFiles.get(i));
            
            if(i == originFiles.size()-1) {
            	af.setFileLevel(0);
            }else {
            	af.setFileLevel(1);
            }
            fileList.add(af);
         }
         System.out.println("b : " + b);
         int result = new CommunityService().insertSpAddFile(b, fileList);
         
         if(result>0) {
            response.sendRedirect("spMain.cm");
         }else {
            for(int i = 0; i < saveFiles.size(); i++) {
               File failedFile = new File(savePath + saveFiles.get(i));
               failedFile.delete();
            }
            request.setAttribute("msg", "게시판 등록에 실패하였습니다.");
            request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp").forward(request, response);
         }
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
