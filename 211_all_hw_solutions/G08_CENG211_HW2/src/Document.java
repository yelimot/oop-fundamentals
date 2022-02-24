
public class Document implements Validatable {
	
	private int applicantID;
	private String documentType;
	@SuppressWarnings("null")
	private Integer duration = null;
	
	public Document() {
		
	}
	
	public Document(int applicantID, String documentType, int duration) {
		super();
		this.applicantID = applicantID;
		this.documentType = documentType;
		this.duration = duration;
	}
	
	public Document(int applicantID, String documentType) {
		this.applicantID = applicantID;
		this.documentType = documentType;
	}


	public int getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	// Since validity of Document and FinancialStatus classes are dependent I wrote this method in Applicant class.
	public String[] checkValidity() {
		return null;
	}

}
