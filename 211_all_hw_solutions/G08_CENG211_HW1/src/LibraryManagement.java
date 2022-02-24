import java.io.IOException;

public class LibraryManagement {
	
	private MyArray[] twoDimentionalIssueArray; // Essentially two dimentional array; array of MyArray(which is also an array that will holds issues of each library).
	private final int NUMBER_OF_LIBRARIES = 3; // By this constant variable, the management program is adaptable for sceneraios such as different numbers of libraries.
	
	public LibraryManagement() throws NumberFormatException, IOException {
		
		FileIO io = new FileIO();
		
		MyArray L1_Issues = io.issueReader("L1_Issues.csv");
		MyArray L2_Issues = io.issueReader("L2_Issues.csv");
		MyArray L3_Issues = io.issueReader("L3_Issues.csv");
		
		twoDimentionalIssueArray = new MyArray[NUMBER_OF_LIBRARIES];
		
		// Assignment the rows of two dimentional array;
		twoDimentionalIssueArray[0] = L1_Issues;
		twoDimentionalIssueArray[1] = L2_Issues;
		twoDimentionalIssueArray[2] = L3_Issues;			
	}
	
	public LibraryManagement(MyArray[] twoDimentionalIssueArray) {
		super();
		this.twoDimentionalIssueArray = twoDimentionalIssueArray;
	}

	public MyArray[] getTwoDimentionalIssueArray() {
		return twoDimentionalIssueArray;
	}

	public void setTwoDimentionalIssueArray(MyArray[] twoDimentionalIssueArray) {
		this.twoDimentionalIssueArray = twoDimentionalIssueArray;
	}
	
	public MyArray listMerge() {
		MyArray mergedArray = new MyArray();
		for(int i = 0; i<3; i++) {
			for(int j=0; j<twoDimentionalIssueArray[i].getOccupied(); j++)
				mergedArray.add(twoDimentionalIssueArray[i].getObjectArray()[j]);
		}
		return mergedArray;
	}

	
}
