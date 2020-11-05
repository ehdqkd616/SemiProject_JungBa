package board.model.vo;

import java.sql.Date;

public class AddFile {
	private int fileNo;
    private String originName;    // 이미지 이름
    private String changeName;
    private String filePath;
    private Date uploadDate;
    private int fileLevel;        // 썸네일에 들어가는지 상세보기에 들어가는지 구별용 
    private int downloadCount;    // 
    private String status;
    private int boardNo;
    
    
    public AddFile() {
    }
    
	public AddFile(int boardNo, String changeName) {
		super();
		this.boardNo = boardNo;
		this.changeName = changeName;
	}

	public AddFile(int fileNo, String originName, String changeName, String filePath, Date uploadDate, int fileLevel,
			int downloadCount, String status, int boardNo) {
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
	

	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "File [fileNo=" + fileNo + ", originName=" + originName + ", changeName=" + changeName + ", filePath="
				+ filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel + ", downloadCount="
				+ downloadCount + ", status=" + status + ", boardNo=" + boardNo + "]";
	}
    
    
}
