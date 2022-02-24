
public class FinancialStatus implements Validatable {
	
	private int applicantID;
	private int income;
	private int savings;
	
	public FinancialStatus(int applicantID, int income, int savings) {
		super();
		this.applicantID = applicantID;
		this.income = income;
		this.savings = savings;
	}

	public int getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getSavings() {
		return savings;
	}

	public void setSavings(int savings) {
		this.savings = savings;
	}
	
	// Since validity of Document and FinancialStatus classes are dependent I wrote this method in Applicant class.
	public String[] checkValidity() {
		return null;
	}

}
