import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class App {
		
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		FileIO io = new FileIO();
		
		io.readApplicants("HW2_ApplicantsInfo.csv");
		io.fillApplicants("HW2_ApplicantsInfo.csv");
		
		ArrayList<Applicant> applicantList = new ArrayList<Applicant>();
		
		applicantList = io.getApplicants();
		Collections.sort(applicantList);
		
		for(Applicant applicant: applicantList) {
			if (!(applicant.isAnyMemberNull())) {
				applicant.inspectApplicant();
			}
			applicant.printApplicant();
		}				
	}
}
