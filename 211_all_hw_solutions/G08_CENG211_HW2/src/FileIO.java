import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	
	// Member field as a ArrayList of applicants.
	private ArrayList<Applicant> applicants;
	
	public FileIO() {
		applicants = new ArrayList<Applicant>();
	}
	
	public ArrayList<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(ArrayList<Applicant> applicants) {
		this.applicants = applicants;
	}
	
	// This is a helper method that get the associated applicant by its applicantID to field its variables (like passport, photo etc.)
	private Applicant helperMethodGetById(int someApplicantID) {
		for (Applicant applicant : applicants) {
			if(applicant.getApplicantID() == someApplicantID) {
				return applicant;
			}
		}
		return null;
	}
	
	// This method creates applicants while reading them from the csv and store them in applicants member field.
	public ArrayList<Applicant> readApplicants(String pathOfCsv) throws IOException {
		
		BufferedReader csvReader = new BufferedReader(new FileReader(pathOfCsv));
	    String row;
		
	    while ((row = csvReader.readLine()) != null) {
	   		String[] listOfAttributes = row.split(",");
	   		
	   		if (listOfAttributes[0].equals("A")) {
	   			Applicant someApplicant = new Applicant(Integer.valueOf(listOfAttributes[1]), listOfAttributes[2]);
	   			applicants.add(someApplicant);
	   		}
	   	}
		csvReader.close();
		
	    return applicants;
	}
	
	// This method reads other classes from the csv.file and bounds them to related applicant.
	public void fillApplicants (String pathOfCsv) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(pathOfCsv));
	    String row;
		
	    while ((row = csvReader.readLine()) != null) {
	   		String[] listOfAttributes = row.split(",");
	   		
		   	switch (listOfAttributes[0]) {
		   		case "S":
		   			Passport somePassport = new Passport(Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], listOfAttributes[3]);
		   			Applicant refApplicant1 = helperMethodGetById(Integer.valueOf(listOfAttributes[1]));
		   			refApplicant1.setApplicantPassport(somePassport);
		   			break;
		   		case "P":
		   			Photo somePhoto = new Photo(Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], listOfAttributes[3]);
		   			Applicant refApplicant2 = helperMethodGetById(Integer.valueOf(listOfAttributes[1]));
		   			refApplicant2.setApplicantPhoto(somePhoto);
		   			break;
		   		case "F":
		   			FinancialStatus someFinancialStatus = new FinancialStatus(Integer.valueOf(listOfAttributes[1]), Integer.valueOf(listOfAttributes[2]), Integer.valueOf(listOfAttributes[3]));
		   			Applicant refApplicant3 = helperMethodGetById(Integer.valueOf(listOfAttributes[1]));
		   			refApplicant3.setApplicantFinancialStatus(someFinancialStatus);
		   			break;
		   		case "D":
		   			Applicant refApplicant4 = helperMethodGetById(Integer.valueOf(listOfAttributes[1]));
		   			if (listOfAttributes.length == 4) {
		   				Document someDocument = new Document(Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], Integer.valueOf(listOfAttributes[3]));
		   				refApplicant4.getApplicantDocuments().add(someDocument);
		   				break;
		   			} else {
		   				Document someDocument = new Document(Integer.valueOf(listOfAttributes[1]), listOfAttributes[2]);
		   				refApplicant4.getApplicantDocuments().add(someDocument);
		   				break;
		   			}
		   		default:
		   			continue;
		   	}
	    }

		csvReader.close();
	}
}
