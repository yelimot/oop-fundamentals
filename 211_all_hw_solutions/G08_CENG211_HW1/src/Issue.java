
public class Issue {

	private int ID;
	private int memberID;
	private String bookID;
	private String issueDate;
	private String returningDate;
	
	public Issue(int iD, int memberID, String bookID, String issueDate, String returningDate) {
		super();
		ID = iD;
		this.memberID = memberID;
		this.bookID = bookID;
		this.issueDate = issueDate;
		this.returningDate = returningDate;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturningDate() {
		return returningDate;
	}

	public void setReturningDate(String returningDate) {
		this.returningDate = returningDate;
	}
	
}
