import java.io.IOException;
import java.text.ParseException;

public class LibraryManagementApp {
	
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		
		LibraryQuery lq = new LibraryQuery();
		
		// Method calls:
		
		lq.mostIssuedBookMethod();
		lq.memberMostIssuesMethod();
		lq.highestPenaltyMethod();
		lq.bookMostCopiesMethod();
		lq.issuedFewestBookMethod();
		lq.memberIssuedLeastCSLibraryMethod();
				
	}
}
