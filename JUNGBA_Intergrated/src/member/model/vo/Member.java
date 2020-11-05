package member.model.vo;

import java.util.Date;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickName;
	private String memberGender;
	private Date memberBirthDay;
	private String memberPhone;
	private String memberEmail;
	private String memberAddress;
	private Date memberRegDate;
	private String memberEnable;
	private String memberGrade;
	private int memberBoard;
	
	
		public Member(String memberId, String memberName, String memberPhone, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
	} //아이디 찾기를 위해 추가
	
	public Member(String memberId, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
	}
	
	
	// selectMemberList
	public Member(int memberNo, String memberId, String memberName, String memberNickName, String memberGender,
			Date memberBirthDay, String memberPhone, String memberEmail, String memberAddress, Date memberRegDate, 
			String memberEnable, String memberGrade, int memberBoard) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberNickName = memberNickName;
		this.memberGender = memberGender;
		this.memberBirthDay = memberBirthDay;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberRegDate = memberRegDate;
		this.memberEnable = memberEnable;
		this.memberGrade = memberGrade;
		this.memberBoard = memberBoard;
	}
	
	public Member(int memberNo, String memberId, String memberName, String memberNickName, String memberGender,
			Date memberBirthDay, String memberPhone, String memberEmail, String memberAddress, Date memberRegDate,
			int memberBoard) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberNickName = memberNickName;
		this.memberGender = memberGender;
		this.memberBirthDay = memberBirthDay;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberRegDate = memberRegDate;
		this.memberBoard = memberBoard;
	}

	public Member(int memberNo, String memberId, String memberPw, String memberName, String memberNickName,
			String memberGender, Date memberBirthDay, String memberPhone, String memberEmail, String memberAddress,
			Date memberRegDate, String memberEnable, String memberGrade) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberNickName = memberNickName;
		this.memberGender = memberGender;
		this.memberBirthDay = memberBirthDay;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberRegDate = memberRegDate;
		this.memberEnable = memberEnable;
		this.memberGrade = memberGrade;
	}	


	public Member(int memberNo, String memberId, String memberPw, String memberName, String memberNickName,
			String memberGender, Date memberBirthDay, String memberPhone, String memberEmail, String memberAddress,
			Date memberRegDate, String memberEnable, String memberGrade, int memberBoard) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberNickName = memberNickName;
		this.memberGender = memberGender;
		this.memberBirthDay = memberBirthDay;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberRegDate = memberRegDate;
		this.memberEnable = memberEnable;
		this.memberGrade = memberGrade;
		this.memberBoard = memberBoard;
	}


	public Member(String memberId, String memberPw, String memberName, String memberNickName, String memberGender,
			Date memberBirthDay, String memberPhone, String memberEmail, String memberAddress) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberNickName = memberNickName;
		this.memberGender = memberGender;
		this.memberBirthDay = memberBirthDay;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
	}

	// 회원수정을 위해 사용
	public Member(String memberId, String memberName, String memberNickName, String memberGender, Date memberBirthDay,
			String memberPhone, String memberEmail, String memberAddress) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberNickName = memberNickName;
		this.memberGender = memberGender;
		this.memberBirthDay = memberBirthDay;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
	}
	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberPw() {
		return memberPw;
	}


	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberNickName() {
		return memberNickName;
	}


	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}


	public String getMemberGender() {
		return memberGender;
	}


	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}


	public Date getMemberBirthDay() {
		return memberBirthDay;
	}


	public void setMemberBirthDay(Date memberBirthDay) {
		this.memberBirthDay = memberBirthDay;
	}


	public String getMemberPhone() {
		return memberPhone;
	}


	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getMemberAddress() {
		return memberAddress;
	}


	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}


	public Date getMemberRegDate() {
		return memberRegDate;
	}


	public void setMemberRegDate(Date memberRegDate) {
		this.memberRegDate = memberRegDate;
	}


	public String getMemberEnable() {
		return memberEnable;
	}


	public void setMemberEnable(String memberEnable) {
		this.memberEnable = memberEnable;
	}


	public String getMemberGrade() {
		return memberGrade;
	}


	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public int getMemberBoard() {
		return memberBoard;
	}

	public void setMemberBoard(int memberBoard) {
		this.memberBoard = memberBoard;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberName="
				+ memberName + ", memberNickName=" + memberNickName + ", memberGender=" + memberGender
				+ ", memberBirthDay=" + memberBirthDay + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail
				+ ", memberAddress=" + memberAddress + ", memberRegDate=" + memberRegDate + ", memberEnable="
				+ memberEnable + ", memberGrade=" + memberGrade + ", memberBoard=" + memberBoard + "]";
	}
	
	
}
