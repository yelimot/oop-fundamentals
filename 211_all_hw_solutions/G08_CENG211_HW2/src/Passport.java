import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Passport implements Validatable {
	
	private int applicantID;
	private String passportNo;
	private String expirationDate;
	
	public Passport(int applicantID, String passportNo, String expirationDate) {
		super();
		this.applicantID = applicantID;
		this.passportNo = passportNo;
		this.expirationDate = expirationDate;
	}
	
	public int getApplicantID() {
		return applicantID;
	}
	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	// This method checks passport validations, if rejected returns appropriate rejection message.
	// If not rejected, "passed" message returns that means passport is valid.
	public String[] checkValidity() {
		
		char c0 = passportNo.charAt(0);
		char c7 = passportNo.charAt(7);
		char c8 = passportNo.charAt(8);
		char c9 = passportNo.charAt(9);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		Date dateExpiration = null;
		try {
			dateExpiration = sdf.parse(expirationDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date currentDate = new Date();
		
		long elapsedms = dateExpiration.getTime() - currentDate.getTime();
        long diffDays = TimeUnit.DAYS.convert(elapsedms, TimeUnit.MILLISECONDS);
				
		if ((this.passportNo.length() != 10) ||
				(c0 != 'P') ||
				!(Character.isDigit(c7) && Character.isDigit(c8) && Character.isDigit(c9))) {
				 return new String[] {"Rejected", "Passport is not valid"};
		} else if (diffDays < 180) {
			return new String[] {"Rejected", "Passport expiration date is not valid"};
		} else {
			return new String[] {"Passed", "Duration will be calculated"};
		}
	}
}
