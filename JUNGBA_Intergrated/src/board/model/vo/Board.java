package board.model.vo;

import java.sql.Date;

public class Board {
	private int 	boardNo;			// 게시판 번호				// B_NO
	private String 	boardName;       	// 게시판 종류				// B_NAME
	private String 	boardTitle;      	// 게시판 제목				// B_TITLE
	private String 	boardContent;    	// 게시판 내용				// B_CONTENT
	private Date 	boardCreateDate;   	// 게시판 생성 날짜			// B_DATE
	private Date 	boardModifyDate;   	// 게시판 수정 날짜			// B_RDATE
	private int 	boardViewCount;     // 게시판 조회수				// B_VIEW_COUNT
	private int 	boardReCommend;     // 게시판 추천수				// B_RECOMMEND
	private char 	boardEnable;       	// 게시판 삭제상태			// B_ENABLE
	private int 	boardWriterNo;     	// 게시판 글쓴이 회원 번호		// B_WRITER
	private String 	boardWriter;        // 게시판 글쓴이 회원			// MEMBER_NICKNAME
	private int 	boardReply;     	// 게시판 댓글				// B_REPLY_COUNT
	private String 	acState;         	// 접수상태				// AC_STATE
	private String 	lcName;          	// 지역이름				// LC_NAME
	private String 	enrollState;     	// 등록요청 상태				// ENROLL_STATE
	private String 	emState;         	// 취업상태				// EM_STATE
	private String 	tcName;          	// 대상명			    	// TC_NAME
	private String 	cgName;          	// 카테고리 이름				// CG_NAME
	private Date 	reStartDate;   		// 모집 시작 날짜				// B_DATE
	private Date 	reEndDate;   		// 모집 끝 날짜				// B_DATE
	private Date 	acStartDate;   		// 활동 시작 날짜				// B_DATE
	private Date 	acEndDate;   		// 활동 끝 날짜				// B_DATE
	public Board() {}

	//자유게시글 글쓰기
	public Board(String boardTitle, String boardContent, int boardWriterNo) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriterNo = boardWriterNo;
	}

	//커뮤니티 게시판
	public Board(int boardNo, String boardName, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount, int boardWriterNo, String boardWriter, int boardReply) {
		super();
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
	}
	//QA 삭제
	public Board(int boardNo) {
		super();
		this.boardNo = boardNo;
	}


	//QA수정
	public Board(int boardNo, String boardTitle, String boardContent, int boardWriterNo, String cgName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriterNo = boardWriterNo;
		this.cgName = cgName;
	}

	//QA게시글 생성
	public Board(String boardTitle, String boardContent, int boardWriterNo, String 	cgName) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriterNo = boardWriterNo;
		this.cgName = cgName;
		
	}

	
	
	//자유게시판
	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDate, Date boardModifyDate,
			int boardViewCount, int boardReCommend, int boardWriterNo,String boardWriter, int boardReply) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardReCommend = boardReCommend;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
	}

	//Q/A 리스트,공지사항
	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount,int boardWriterNo,String boardWriter, int boardReply,String cgName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
		this.cgName = cgName;
	}
	
	//myPage 지원정책 리스트
	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount, int boardReCommend, int boardWriterNo,
			String boardWriter, int boardReply, String acState, String lcName, String enrollState, String emState,
			String tcName, String cgName, Date reStartDate, Date reEndDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardReCommend = boardReCommend;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
		this.acState = acState;
		this.lcName = lcName;
		this.enrollState = enrollState;
		this.emState = emState;
		this.tcName = tcName;
		this.cgName = cgName;
		this.reStartDate = reStartDate; 
		this.reEndDate = reEndDate;  
	}

	//myPage 대외활동 리스트
	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount, int boardReCommend, int boardWriterNo,
			String boardWriter, int boardReply, String acState, String lcName, String enrollState, 
			String tcName, String cgName ,Date reStartDate, Date reEndDate,Date	acStartDate,Date acEndDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardReCommend = boardReCommend;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
		this.acState = acState;
		this.lcName = lcName;
		this.enrollState = enrollState;
		this.tcName = tcName;
		this.cgName = cgName;
		this.reStartDate = reStartDate; 
		this.reEndDate = reEndDate;  	
		this.acStartDate = acStartDate; 
		this.acEndDate = acEndDate;  	
	}
	
	
	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount, int boardReCommend, char boardEnable, int boardWriterNo,
			String boardWriter, int boardReply, String acState, String lcName, String enrollState, String emState,
			String tcName, String cgName, Date reStratDate, Date reEndDate, Date acStartDate, Date acEndDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardReCommend = boardReCommend;
		this.boardEnable = boardEnable;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
		this.acState = acState;
		this.lcName = lcName;
		this.enrollState = enrollState;
		this.emState = emState;
		this.tcName = tcName;
		this.cgName = cgName;
		this.reStartDate = reStratDate;
		this.reEndDate = reEndDate;
		this.acStartDate = acStartDate;
		this.acEndDate = acEndDate;
	}
	
	public Board(int boardNo, String boardName, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount, int boardReCommend, char boardEnable, int boardWriterNo,
			String boardWriter, int boardReply, String acState, String lcName, String enrollState, String emState,
			String tcName, String cgName) {
		super();
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardReCommend = boardReCommend;
		this.boardEnable = boardEnable;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
		this.acState = acState;
		this.lcName = lcName;
		this.enrollState = enrollState;
		this.emState = emState;
		this.tcName = tcName;
		this.cgName = cgName;
	}

	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount, int boardReCommend, char boardEnable, int boardWriterNo,
			String boardWriter, int boardReply, String acState, String lcName, String enrollState, String emState,
			String tcName, String cgName, Date reStratDate, Date reEndDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardReCommend = boardReCommend;
		this.boardEnable = boardEnable;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
		this.acState = acState;
		this.lcName = lcName;
		this.enrollState = enrollState;
		this.emState = emState;
		this.tcName = tcName;
		this.cgName = cgName;
		this.reStartDate = reStratDate;
		this.reEndDate = reEndDate;
	}
	
	//지원정책 커뮤니티
	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDate,
			Date boardModifyDate, int boardViewCount, int boardReCommend, int boardWriterNo, String boardWriter,
			int boardReply, String acState, String lcName, String enrollState, String emState, String tcName,
			String cgName, Date reStartDate, Date reEndDate, Date acStartDate, Date acEndDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardViewCount = boardViewCount;
		this.boardReCommend = boardReCommend;
		this.boardWriterNo = boardWriterNo;
		this.boardWriter = boardWriter;
		this.boardReply = boardReply;
		this.acState = acState;
		this.lcName = lcName;
		this.enrollState = enrollState;
		this.emState = emState;
		this.tcName = tcName;
		this.cgName = cgName;
		this.reStartDate = reStartDate;
		this.reEndDate = reEndDate;
		this.acStartDate = acStartDate;
		this.acEndDate = acEndDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardCreateDate() {
		return boardCreateDate;
	}

	public void setBoardCreateDate(Date boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}

	public Date getBoardModifyDate() {
		return boardModifyDate;
	}

	public void setBoardModifyDate(Date boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}

	public int getBoardViewCount() {
		return boardViewCount;
	}

	public void setBoardViewCount(int boardViewCount) {
		this.boardViewCount = boardViewCount;
	}

	public int getBoardReCommend() {
		return boardReCommend;
	}

	public void setBoardReCommend(int boardReCommend) {
		this.boardReCommend = boardReCommend;
	}

	public char getBoardEnable() {
		return boardEnable;
	}

	public void setBoardEnable(char boardEnable) {
		this.boardEnable = boardEnable;
	}

	public int getBoardWriterNo() {
		return boardWriterNo;
	}

	public void setBoardWriterNo(int boardWriterNo) {
		this.boardWriterNo = boardWriterNo;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getBoardReply() {
		return boardReply;
	}

	public void setBoardReply(int boardReply) {
		this.boardReply = boardReply;
	}

	public String getAcState() {
		return acState;
	}

	public void setAcState(String acState) {
		this.acState = acState;
	}

	public String getLcName() {
		return lcName;
	}

	public void setLcName(String lcName) {
		this.lcName = lcName;
	}

	public String getEnrollState() {
		return enrollState;
	}

	public void setEnrollState(String enrollState) {
		this.enrollState = enrollState;
	}

	public String getEmState() {
		return emState;
	}

	public void setEmState(String emState) {
		this.emState = emState;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public String getCgName() {
		return cgName;
	}

	public void setCgName(String cgName) {
		this.cgName = cgName;
	}

	public Date getReStartDate() {
		return reStartDate;
	}

	public void setReStartDate(Date reStartDate) {
		this.reStartDate = reStartDate;
	}

	public Date getReEndDate() {
		return reEndDate;
	}

	public void setReEndDate(Date reEndDate) {
		this.reEndDate = reEndDate;
	}

	public Date getAcStartDate() {
		return acStartDate;
	}

	public void setAcStartDate(Date acStartDate) {
		this.acStartDate = acStartDate;
	}

	public Date getAcEndDate() {
		return acEndDate;
	}

	public void setAcEndDate(Date acEndDate) {
		this.acEndDate = acEndDate;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardName=" + boardName + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardCreateDate=" + boardCreateDate + ", boardModifyDate="
				+ boardModifyDate + ", boardViewCount=" + boardViewCount + ", boardReCommend=" + boardReCommend
				+ ", boardEnable=" + boardEnable + ", boardWriterNo=" + boardWriterNo + ", boardWriter=" + boardWriter
				+ ", boardReply=" + boardReply + ", acState=" + acState + ", lcName=" + lcName + ", enrollState="
				+ enrollState + ", emState=" + emState + ", tcName=" + tcName + ", cgName=" + cgName + ", reStartDate="
				+ reStartDate + ", reEndDate=" + reEndDate + ", acStartDate=" + acStartDate + ", acEndDate=" + acEndDate
				+ "]";
	}


	
	
	
	
}