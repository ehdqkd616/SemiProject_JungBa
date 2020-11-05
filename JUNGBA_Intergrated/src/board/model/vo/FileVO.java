package board.model.vo;

import java.sql.Date;

public class FileVO {
	private int fileNo;
	private String originName;	// 이미지 이름
	private String changeName;	
	private String filePath;
	private Date uploadDate;
	private int fileLevel;		// 썸네일에 들어가는지 상세보기에 들어가는지 구별용 
	private int downloadCount;	// 
	private String status;
	private int boardNo;
	private int memberNo;
	
	public FileVO() {}
	
	
	public FileVO(int boardNo, String changeName) {
		super();
		this.boardNo = boardNo;
		this.changeName = changeName;
	}
	
	// member를 위한 FileVO 생성자
	public FileVO(String changeName, int memberNo) {
		super();
		this.changeName = changeName;
		this.memberNo = memberNo;
	}
	
	public FileVO(int fileNo, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, int downloadCount, String status, int boardNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.status = status;
		this.boardNo = boardNo;
	}
	
	public FileVO(int fileNo, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, int downloadCount, String status, int boardNo, int memberNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.status = status;
		this.boardNo = boardNo;
		this.memberNo = memberNo;
	}
	

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getBoardNo() {
		return boardNo;
	}
	
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public void setbBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	@Override
	public String toString() {
		return "FileVO [fileNo=" + fileNo + ", originName=" + originName + ", changeName=" + changeName + ", filePath="
				+ filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel + ", downloadCount="
				+ downloadCount + ", status=" + status + ", boardNo=" + boardNo + ", memberNo=" + memberNo + "]";
	}

	
}
