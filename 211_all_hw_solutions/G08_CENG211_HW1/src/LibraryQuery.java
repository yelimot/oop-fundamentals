import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LibraryQuery {
	
	private Library allLibraries = new Library();
	private MyArray allIssues = new MyArray();
	private MyArray Members = new MyArray();
	private MyArray CSLibraryIssues = new MyArray();
	
	public LibraryQuery() throws NumberFormatException, IOException {
		
		// Reading from the file and creating fields of the LibraryQuery:
		
		FileIO io = new FileIO(); 
		
		Library Library1 = io.bookReader("L1_Books.csv");
		Library Library2 = io.bookReader("L2_Books.csv");
		Library Library3 = io.bookReader("L3_Books.csv");
		
		Library[] libraryArray = {Library1, Library2, Library3};
		
		for(int i = 0; i<3; i++) {
			for(int j=0; j<libraryArray[i].getOccupied(); j++)
				allLibraries.add(libraryArray[i].getBookArray()[j]);
		}
		
		// Merging the lists for required operations.
		LibraryManagement lm = new LibraryManagement();
		allIssues = lm.listMerge();

		MyArray L3_Issues = io.issueReader("L3_Issues.csv");
		CSLibraryIssues = L3_Issues;
			
		Members = io.memberReader("Members.csv");
	}
	
	// Method for question 1. The most issued book (among the three libraries).
	// The outer loop picks elements one by one. The inner loop finds the frequency of the picked element and compares it with the maximum so far.
	public void mostIssuedBookMethod() {
		int maxOccur = 0;
		Book mostIssuedBook = new Book();
		mostIssuedBook.setID(((Issue)(allIssues.getObjectArray()[0])).getBookID());
		for(int i=0; i<allIssues.getOccupied(); i++) {
			int tempMax = 0;
			for(int j=0; j<allIssues.getOccupied(); j++) {
				if( ((Issue)(allIssues.getObjectArray()[i])).getBookID().equals(((Issue)(allIssues.getObjectArray()[j])).getBookID()) ) {
					tempMax += 1;
				}
			}
			if(tempMax > maxOccur) {
				maxOccur = tempMax;
				mostIssuedBook.setID(((Issue)(allIssues.getObjectArray()[i])).getBookID());
			}
		}
		for (int i=0; i<allLibraries.getOccupied(); i++) {
			if (mostIssuedBook.getID().equals(allLibraries.getBookArray()[i].getID())) {
				mostIssuedBook.setTitle(allLibraries.getBookArray()[i].getTitle());
			}
		}
		System.out.println("1- "+mostIssuedBook.getTitle());		
	}
	
	// Method for question 2. The member who issues the most books (for all three libraries and all years).
	// Similar to previous implementation.
	public void memberMostIssuesMethod() {
		int maxOccur = 0;
		Member memberMostIssues = new Member();
		memberMostIssues.setID(((Issue)(allIssues.getObjectArray()[0])).getMemberID());
		for(int i=0; i<allIssues.getOccupied(); i++) {
			int tempMax = 0;
			for(int j=0; j<allIssues.getOccupied(); j++) {
				if( ((Issue)(allIssues.getObjectArray()[i])).getMemberID() == (((Issue)(allIssues.getObjectArray()[j])).getMemberID()) )  {
					tempMax += 1;
				}
			}
			if(tempMax > maxOccur) {
				maxOccur = tempMax;
				memberMostIssues.setID(((Issue)(allIssues.getObjectArray()[i])).getMemberID());
			}
		}
		
		for (int i=0; i<Members.getOccupied(); i++) {
			if (memberMostIssues.getID() == ((Member)(Members.getObjectArray()[i])).getID()) {
				memberMostIssues.setName(((Member)(Members.getObjectArray()[i])).getName());
			}
		}
		System.out.println("2- "+memberMostIssues.getName());		
	}
	
	// Two methods for question 3. Upper one is helper, finds difference between two dates.
	// Second method iterates over the issue array and finds the penalty for late returning.
	public long penaltyHelper(String string1, String string2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
		
		Date firstDate = sdf.parse(string1);
		Date secondDate = sdf.parse(string2);
	    
        long daysDifference = ((secondDate.getTime() - firstDate.getTime()) / (1000*60*60*24)) % 365;

        return daysDifference;
	}
	
	public void highestPenaltyMethod() throws ParseException {
		long dateDifference = 0;
		long tempDifference = 0;
		for (int i=0; i<allIssues.getOccupied(); i++) {
			tempDifference = penaltyHelper(((Issue)(allIssues.getObjectArray()[i])).getIssueDate(), ((Issue)(allIssues.getObjectArray()[i])).getReturningDate());
			if (tempDifference >= dateDifference) {
				dateDifference = tempDifference;
			}
		}
		System.out.println("3- "+((dateDifference-15)*0.5)); // -15 for the; the day that the book returned + borrowing period of a book which is 14 days
	}
	
	// Method for fourth question; The book with the most copies (among the three libraries).
	// Iterates over the libraries and finds the book with the most copies.
	public void bookMostCopiesMethod() {
		int quantity = allLibraries.getBookArray()[0].getQuantity();
		Book bookMostCopies = new Book();
		for (int i=0; i<allLibraries.getOccupied(); i++) {
			if (quantity < allLibraries.getBookArray()[i].getQuantity()) {
				bookMostCopies.setTitle(allLibraries.getBookArray()[i].getTitle());
			}
		}
		System.out.println("4- "+bookMostCopies.getTitle());
	}
	
	// Two methods for question 5. Above one finds the book from the library by the bookID since its quantity required, then returns it.
	// Actual methods calls the second method each iteration and compares its quantity, finds the book with the fewest number of copies and prints it.
	public Book bookFinderHelper(String bookID) {
		for(int i=0; i<allLibraries.getOccupied(); i++) {
			if(allLibraries.getBookArray()[i].getID().equals(bookID)) {
				return (allLibraries.getBookArray()[i]);
			}
		}
		return null;
	}
	// Actual methods calls the second method each iteration and compares its quantity, finds the book with the fewest number of copies and prints it.
	public void issuedFewestBookMethod() {
		int quantity = Integer.MAX_VALUE;
		Book tempBook = new Book();
		Book issuedFewestBook = new Book();
		for(int i=0; i<allIssues.getOccupied(); i++) {
			tempBook = bookFinderHelper(((Issue)(allIssues.getObjectArray()[i])).getBookID());
			if (quantity > tempBook.getQuantity()) {
				issuedFewestBook = tempBook;
				quantity = tempBook.getQuantity();
			}
		}
		System.out.println("5- "+issuedFewestBook.getTitle());
	}
	
	// Method for question 6. countListOfMembers holds the number of issues each member from the Computer Science Library has.
	public void memberIssuedLeastCSLibraryMethod() {
		MyArray countListOfMembers = new MyArray();
		for (int i = 0; i<Members.getOccupied(); i++) {
			int count = 0;
			for (int j=0; j<CSLibraryIssues.getOccupied(); j++) {
				if (((Member)(Members.getObjectArray()[i])).getID() == ((Issue)(CSLibraryIssues.getObjectArray()[j])).getMemberID()) {
					count += 1;
				}				
			}
			countListOfMembers.add(count);
		}
		
		// Finding index of the member that has least number of issues:
		int indexOfMin = 0;
		for (int i = 1; i<countListOfMembers.getOccupied(); i++ ) {
	      if ( (int)(countListOfMembers.getObjectArray()[i]) < (int)(countListOfMembers.getObjectArray()[indexOfMin]) ) {
	    	  indexOfMin = i;
	      }
		}
		
		System.out.println("6- "+((Member)(Members.getObjectArray()[indexOfMin])).getName());
	}

}
