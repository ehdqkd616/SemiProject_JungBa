package board.model.vo;

import java.sql.Date;

public class Reply {
	
	private int replyNo;
	private String replyContent;
	private Date createDate;
	private Date modifyDate;
	private int replyWriterNo;
	private String replyWriter;
	private String replyEnable;
	private int refBid;
	private String profileImageName;
	
	

	public Reply() {
		
	}
	
	
	public Reply(int replyNo) {
		super();
		this.replyNo = replyNo;
	}
	
	// replyInsert를 위한 생성자
	public Reply(int replyWriterNo, int refBid, String replyContent) {
		super();
		this.replyWriterNo = replyWriterNo;
		this.refBid = refBid;
		this.replyContent = replyContent;
	}
	

	// replyDetail을 위한 생성자
	public Reply(int replyNo, String replyContent, Date createDate, Date modifyDate, int replyWriterNo,
			String replyWriter, String replyEnable, int refBid, String profileImageName) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.replyWriterNo = replyWriterNo;
		this.replyWriter = replyWriter;
		this.replyEnable = replyEnable;
		this.refBid = refBid;
		this.profileImageName = profileImageName;
	}


	public int getReplyNo() {
		return replyNo;
	}



	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}



	public String getReplyContent() {
		return replyContent;
	}



	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Date getModifyDate() {
		return modifyDate;
	}



	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}



	public int getReplyWriterNo() {
		return replyWriterNo;
	}



	public void setReplyWriterNo(int replyWriterNo) {
		this.replyWriterNo = replyWriterNo;
	}



	public String getReplyWriter() {
		return replyWriter;
	}



	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}



	public String getReplyEnable() {
		return replyEnable;
	}



	public void setReplyEnable(String replyEnable) {
		this.replyEnable = replyEnable;
	}



	public int getRefBid() {
		return refBid;
	}



	public void setRefBid(int refBid) {
		this.refBid = refBid;
	}



	public String getProfileImageName() {
		return profileImageName;
	}



	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}
	
}
