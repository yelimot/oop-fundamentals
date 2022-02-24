import java.util.ArrayList;

public class Applicant implements Comparable {
	
	private int applicantID;
	private String applicantName;
	private String applicationType;
	private Passport applicantPassport = null;
	private Photo applicantPhoto = null;
	private FinancialStatus applicantFinancialStatus = null;
	private ArrayList<Document> applicantDocuments = new ArrayList<Document>();
	private String status;
	private String durationOrReason;
	
	public Applicant(int ApplicantID, String ApplicantName) {
		applicantID = ApplicantID;
		applicantName = ApplicantName;
	}
	
	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType() {
		int firstTwoDigit = this.applicantID / 10000;
		switch (firstTwoDigit) {
			case 11:
				this.applicationType = "Tourist"; 
				break;
			case 23:
				this.applicationType = "Worker"; 
				break;
			case 25:
				this.applicationType = "Educational"; 
				break;
			case 30:
				this.applicationType = "Immigrant"; 
				break;
			default:
				break;
		}
	}
	
	public int getApplicantID() {
		return applicantID;
	}
	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public Passport getApplicantPassport() {
		return applicantPassport;
	}
	public void setApplicantPassport(Passport applicantPassport) {
		this.applicantPassport = applicantPassport;
	}
	public Photo getApplicantPhoto() {
		return applicantPhoto;
	}
	public void setApplicantPhoto(Photo applicantPhoto) {
		this.applicantPhoto = applicantPhoto;
	}
	public FinancialStatus getApplicantFinancialStatus() {
		return applicantFinancialStatus;
	}
	public void setApplicantFinancialStatus(FinancialStatus applicantFinancialStatus) {
		this.applicantFinancialStatus = applicantFinancialStatus;
	}
	public ArrayList<Document> getApplicantDocuments() {
		return applicantDocuments;
	}
	public void setApplicantDocuments(ArrayList<Document> applicantDocuments) {
		this.applicantDocuments = applicantDocuments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDurationOrReason() {
		return durationOrReason;
	}

	public void setDurationOrReason(String durationOrReason) {
		this.durationOrReason = durationOrReason;
	}
	
	// Since validity of FinancialStatus and Document are dependent I wrote this method in this class.
	public String[] checkValidityFinancialStatusAndDocument() {
		
		String applicantType = this.getApplicationType();
		
		if (applicantType.equals("Tourist")) {
			
			int _2k = 2000;
			int _3k = 3000;
			int _12k = 12000;
			int _4k = 4000;
			int _6k = 6000;
			
			// if tourist application has an invitation letter, necessary income and savings are halved
			if (!(this.applicantDocuments.isEmpty())) {
				_2k /= 2;
				_3k /= 2;
				_12k /= 2;
				_4k /= 2;
				_6k /= 2;
			}
			
			if (this.applicantFinancialStatus.getIncome() < _2k) {
				return new String[] {"Rejected", "Applicant does not have a stable financial status"};
			} else if ((this.applicantFinancialStatus.getIncome() < _3k) && (this.applicantFinancialStatus.getIncome() >= _2k)) {
				if (this.applicantFinancialStatus.getSavings() < _12k) {
					return new String[] {"Rejected", "Applicant does not have a stable financial status"};
				}
			} else if ((this.applicantFinancialStatus.getIncome() < _4k) && (this.applicantFinancialStatus.getIncome() >= _3k)) {
				if (this.applicantFinancialStatus.getSavings() < _6k) {
					return new String[] {"Rejected", "Applicant does not have a stable financial status"};
				}
			} else {
				return new String[] {"Passed", "Duration will be calculated"};
			}
		}
		
		else if (applicantType.equals("Educational")) {
			
			boolean checkInvitation = false;
			for(Document doc : this.applicantDocuments) {
				if (doc.getDocumentType().equals("IL")) {
					checkInvitation = true;
				}
			}
			
			int _1k = 1000;
			int _2k = 2000;
			int _6k = 6000;
			int _3k = 3000;
			
			//  If educational application has an invitation letter, necessary income and savings are halved. 
			if (checkInvitation) {
				_1k /= 2;
				_2k /= 2;
				_6k /= 2;
				_3k /= 2;
			}
			
			boolean checkAcceptence = false;
			for(Document doc : this.applicantDocuments) {
				if (doc.getDocumentType().equals("LA")) {
					checkAcceptence = true;
				}
			}
			
			if (this.applicantFinancialStatus.getIncome() < _1k) {
				return new String[] {"Rejected", "Applicant does not have a stable financial status"};
			} else if ((this.applicantFinancialStatus.getIncome() < _2k) && (this.applicantFinancialStatus.getIncome() >= _1k)) {
				if (this.applicantFinancialStatus.getSavings() < _6k) {
					return new String[] {"Rejected", "Applicant does not have a stable financial status"};
				}
			} else if ((this.applicantFinancialStatus.getIncome() < _3k) && (this.applicantFinancialStatus.getIncome() >= _2k)) {
				if (this.applicantFinancialStatus.getSavings() < _3k) {
					return new String[] {"Rejected", "Applicant does not have a stable financial status"};
				}
			} else if (!checkAcceptence) {
				return new String[] {"Rejected", "Applicant does not have a letter of acceptance"};
			} else {
				return new String[] {"Passed", "Duration will be calculated"};
			}
		}
		
		else if (applicantType.equals("Worker")) {
			
			boolean checkAcceptence = false;
			for(Document doc : this.applicantDocuments) {
				if (doc.getDocumentType().equals("LA")) {
					checkAcceptence = true;
				}
			}
			
			if (this.applicantFinancialStatus.getSavings() < 2000) {
				return new String[] {"Rejected", "Applicant does not have a stable financial status"};
			} else if (!checkAcceptence) {
				return new String[] {"Rejected", "Applicant does not have a letter of acceptance"};
			} else {
				return new String[] {"Passed", "Duration will be calculated"};
			}
		}
		
		else if (applicantType.equals("Immigrant")) {
			
			boolean checkInvitation = false;
			for(Document doc : this.applicantDocuments) {
				if (doc.getDocumentType().equals("IL")) {
					checkInvitation = true;
				}
			}
			
			int _4k = 4000;
			int _50k = 50000;
			
			//  If educational application has an invitation letter, necessary income and savings are halved. 
			if (checkInvitation) {
				_4k /= 2;
				_50k /= 2;
			}
			
			boolean checkGreen = false;
			for(Document doc : this.applicantDocuments) {
				if (doc.getDocumentType().equals("GC")) {
					checkGreen = true;
				}
			}
			
			if (checkGreen) {
				if (this.applicantFinancialStatus.getSavings() < _4k) {
					return new String[] {"Rejected", "Applicant does not have a stable financial status"};
				}
			} else if(this.applicantFinancialStatus.getSavings() < _50k) {
				return new String[] {"Rejected", "Applicant does not have a stable financial status"};
			} else {
				return new String[] {"Passed", "Duration will be calculated"};
			}
		}
		
		return new String[] {"Passed", "Duration will be calculated"};
	}
	
	// Checks if any of the member is null since any of it null, there is no need to check validity for others.
	public boolean isAnyMemberNull() {
		this.setApplicationType();
		if (this.applicantPassport == null) {
			this.status = "Rejected";
			this.durationOrReason = "Applicant does not have a passport";
			return true;
		} else if (this.applicantPhoto == null) {
			this.status = "Rejected";
			this.durationOrReason = "Applicant does not have a photo";
			return true;
		} else if (this.applicantFinancialStatus == null) {
			this.status = "Rejected";
			this.durationOrReason = "Applicant does not have a financial status report";
			return true;
		} else {
			return false;
		}
	}
	
	// Inspects application data members in order if rejection exist, sets status and durationOrReason. If rejection is not exist, calls calculateVisaDuration.
	public void inspectApplicant() {
				
		String[] checkValidity = this.applicantPassport.checkValidity();
		if (checkValidity[0].equals("Rejected")) {
			this.status = checkValidity[0];
			this.durationOrReason = checkValidity[1];
			return;
		} else {
			checkValidity = this.applicantPhoto.checkValidity();
			if (checkValidity[0].equals("Rejected")) {
				this.status = checkValidity[0];
				this.durationOrReason = checkValidity[1];
				return;
			} else {
				checkValidity = this.checkValidityFinancialStatusAndDocument();
				if (checkValidity[0].equals("Rejected")) {
					this.status = checkValidity[0];
					this.durationOrReason = checkValidity[1];
					return;
				} else {
					this.calculateVisaDuration();
					return;
				}
			}
		}
	}
	
	public void printApplicant() {
		if (this.status.equals("Accepted")) {
			System.out.println("Applicant ID:" +this.applicantID+ " Name: " +this.applicantName+ " Visa Type: " +this.applicationType+ " Status: " +this.status+ " Visa Duration: " +this.durationOrReason);
		} else {
			System.out.println("Applicant ID:" +this.applicantID+ " Name: " +this.applicantName+ " Visa Type: " +this.applicationType+ " Status: " +this.status+ " Reason: " +this.durationOrReason);
		}
	}
	
	// Method for sorting the ArrayList by applicantID.
	public int compareTo(Object otherApplicant) {
		int compareId = ((Applicant)otherApplicant).getApplicantID();
		return this.applicantID-compareId;
	}
	
	// Method for calculating visa duration according to applicantType.
	public void calculateVisaDuration() {
		String applicantType = this.getApplicationType();
		if (applicantType.equals("Tourist")) {
			boolean checkInvitation = true;
			for(Document doc : this.applicantDocuments) {
				if (doc.getDocumentType().equals("IL")) {
					checkInvitation = true;
				}
			}
			int DC;
			if (checkInvitation) {
				DC = ((this.applicantFinancialStatus.getIncome()-2000)*6 + this.applicantFinancialStatus.getSavings()) / 12000;
			} else {
				DC = ((this.applicantFinancialStatus.getIncome()-2000)*6 + this.applicantFinancialStatus.getSavings()) / 6000;
			}
			if ((DC<2) && (DC>=1)) {
				this.status = "Accepted";
				this.durationOrReason = "6 Months";
			} else if ((DC<4) && (DC>=2)) {
				this.status = "Accepted";
				this.durationOrReason = "1 Year";
			} else {
				this.status = "Accepted";
				this.durationOrReason = "5 Years";
			}
		} else if (applicantType.equals("Worker")) {
			if (!(this.applicantDocuments.isEmpty()) && this.applicantDocuments.get(0).getDocumentType().equals("LA")) {
				if ((this.applicantDocuments.get(0).getDuration() < 25) && 
						((this.applicantDocuments.get(0).getDuration() > 11))) {
					this.status = "Accepted";
					this.durationOrReason = "1 Year";
				} else if ((this.applicantDocuments.get(0).getDuration() < 49) && 
						((this.applicantDocuments.get(0).getDuration() > 23))) {
					this.status = "Accepted";
					this.durationOrReason = "2 Years";
				} else if (this.applicantDocuments.get(0).getDuration() > 59) {
					this.status = "Accepted";
					this.durationOrReason = "5 Years";
				} else {
					this.status = "Rejected";
					this.durationOrReason = "Document duration is invalid";
				}
			} else {
				this.status = "Rejected";
				this.durationOrReason = "Document duration is invalid";
			}
		} else if (applicantType.equals("Educational")) {
			if (!(this.applicantDocuments.isEmpty()) && this.applicantDocuments.get(0).getDocumentType().equals("LA")) {
				if ((this.applicantDocuments.get(0).getDuration() < 25) && 
						((this.applicantDocuments.get(0).getDuration() > 11))) {
					this.status = "Accepted";
					this.durationOrReason = "1 Year";
				} else if ((this.applicantDocuments.get(0).getDuration() < 49) && 
						((this.applicantDocuments.get(0).getDuration() > 23))) {
					this.status = "Accepted";
					this.durationOrReason = "2 Years";
				} else if (this.applicantDocuments.get(0).getDuration() > 59) {
					this.status = "Accepted";
					this.durationOrReason = "4 Years";
				} else {
					this.status = "Rejected";
					this.durationOrReason = "Document duration and passport expiration date are inconsistent";
				}
			} else {
				this.status = "Rejected";
				this.durationOrReason = "Document duration is invalid";
			}
		} else {
			this.status = "Accepted";
			this.durationOrReason = "Permanent";
		}
	}
		
}

