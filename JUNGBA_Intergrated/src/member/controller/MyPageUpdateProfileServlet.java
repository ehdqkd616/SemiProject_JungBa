package member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.vo.FileVO;
import common.MyFileRenamePolicy;
import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/updateProfile.me")
public class MyPageUpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageUpdateProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10; // 10MByte로 전송파일 용량을 제한
			String root = request.getSession().getServletContext().getRealPath("/"); // 웹 서버 컨테이너 경로 추출
			String savePath = root + "UploadFolder/member_profile/";
			
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
			}
		 	
			MultipartRequest multiRequest 
				= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			
			String saveFile = null;	// 바뀐 파일의 이름 저장
			String originFile = null;	// 원본 파일의 이름 저장
			
			Enumeration<String> files = multiRequest.getFileNames(); // 폼에서 전송된 파일 리스트들의 name 반환
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				System.out.println("files.nextElement : "+name);
				System.out.println("multiRequest,getFilesystemName(name) : "+multiRequest.getFilesystemName(name));
				if(multiRequest.getFilesystemName(name) != null) {
					saveFile = (multiRequest.getFilesystemName(name));
					originFile = (multiRequest.getOriginalFileName(name)); // getOriginalFileName() : 실제 사용자가 업로드 할때의 파일명
				}
			}
			
			int loginMemberNo = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
			
			FileVO profile = new FileVO();
			profile.setFilePath(savePath);
			profile.setOriginName(originFile);
			profile.setChangeName(saveFile);
			
			////////////////////////// 추후 수정 예정 ////////////////////////////
//			int originalFileNo = Integer.parseInt(multiRequest.getParameter("originalFileNo"));
//			System.out.println("originalFileNo : " + originalFileNo);
			//////////////////////////////////////////////////////////////////
			
			System.out.println("savePath : "+savePath);
			System.out.println("originFile : "+originFile);
			System.out.println("changeName : "+saveFile);
			
			MemberService memberService = new MemberService();
			
			int result = memberService.updateProfile(profile, loginMemberNo);
			
			//////////////////////////추후 수정 예정 ////////////////////////////
//			int result = memberService.updateProfile(profile, originalFileNo, loginMemberNo);
			//////////////////////////////////////////////////////////////////
			
			
			if(result > 0) {
				response.sendRedirect("myPage.me");
			} else {
				File failedFile = new File(savePath + saveFile);
				failedFile.delete();
				
				request.setAttribute("msg", "프로필 수정에 실패했습니다.");
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp");
				view.forward(request, response);
			}
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}