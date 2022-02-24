import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	
	// This class reads the contents from the file and stores them in ArrayList of Strings.
	private ArrayList<String[]> content = new ArrayList<String[]>();
	
	public FileIO() {
		
	}
	
	public void ReadContent(String path) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(path));
	    String row;
		while ((row = csvReader.readLine()) != null) {
	   		String[] listOfAttributes = row.split(",");
	   		this.content.add(listOfAttributes);
		}
		csvReader.close();
	}

	public ArrayList<String[]> getContent() {
		return content;
	}

	public void setContent(ArrayList<String[]> content) {
		this.content = content;
	}
}
