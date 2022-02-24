import java.io.BufferedReader;
import java.io.FileReader;import java.io.IOException;

public class FileIO {
	
	// Method for reading the books from the file; Adding the books to library happens simultanously by creating them in the while loop.
	public Library bookReader(String pathForBooks) throws NumberFormatException, IOException {
		Library someLibrary = new Library();
		BufferedReader csvReader = new BufferedReader(new FileReader(pathForBooks));
	    String row;
		while ((row = csvReader.readLine()) != null) {
	   		String[] listOfAttributes = row.split(",");
	   		// Creation with fields that read from the file:
	   		Book someBook = new Book(listOfAttributes[0], listOfAttributes[1], listOfAttributes[2], listOfAttributes[3], Integer.valueOf(listOfAttributes[4]), listOfAttributes[5], Integer.valueOf(listOfAttributes[6]));
	   		// Custom add method, you can see implementation details in both Library and MyArray classes.
	   		someLibrary.add(someBook);
	   	}
		csvReader.close();
	    return someLibrary;
	}
	
	// Method for reading the issues from the file; Similar implementation as reading books; creation and adding happens in same while loop
	public MyArray issueReader(String pathForIssues) throws NumberFormatException, IOException {
		MyArray someIssueArray = new MyArray();
		BufferedReader csvReader = new BufferedReader(new FileReader(pathForIssues));
	    String row;
		while ((row = csvReader.readLine()) != null) {
			if (row.equals((",,,,"))) {
				break;
			} else {
	   		String[] listOfAttributes = row.split(",");
	   		// Creation with fields that read from the file:
	   		Issue someIssue = new Issue(Integer.valueOf(listOfAttributes[0]), Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], listOfAttributes[3], listOfAttributes[4]);
	   		// Custom add method, you can see implementation details in MyArray class.	
	   		someIssueArray.add(someIssue);
			}
	   	}
		csvReader.close();
	    return someIssueArray;
	}
	
	// Similar implementation as reading books and issues;
	public MyArray memberReader(String pathForMembers) throws NumberFormatException, IOException {
		MyArray someMemberArray = new MyArray();
		BufferedReader csvReader = new BufferedReader(new FileReader(pathForMembers));
	    String row;
	    csvReader.readLine(); // first line: ID,name,email
	    csvReader.readLine(); // second line: admin
	    while ((row = csvReader.readLine()) != null) {
	   		String[] listOfAttributes = row.split(",");
	   		
	   		Member someMember = new Member(Integer.valueOf(listOfAttributes[0]), listOfAttributes[1], listOfAttributes[2]);
	   			   		
	   		someMemberArray.add(someMember);
	   		
	   	}
	    csvReader.close();
	    return someMemberArray;
	}

}
