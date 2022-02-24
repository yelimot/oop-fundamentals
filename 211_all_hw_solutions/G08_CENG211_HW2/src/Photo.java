
public class Photo implements Validatable {
	
	private int applicantID;
	private String resolution;
	private String position;

	public Photo(int applicantID, String resolution, String position) {
		super();
		this.applicantID = applicantID;
		this.resolution = resolution;
		this.position = position;
	}

	public int getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	// This method checks photo validations, if rejected, returns appropriate rejection message.
	// If not rejected, "passed" message returns that means photo is valid.
	public String[] checkValidity() {
		String[] listOfAttributes = this.resolution.split("x");
		if (!(listOfAttributes[0].equals(listOfAttributes[1]))) {
			return new String[] {"Rejected", "The photo should be square"};
		} else if ((Integer.valueOf(listOfAttributes[0]) < 600) ||
					(Integer.valueOf(listOfAttributes[0]) > 1200)) {
			return new String[] {"Rejected", "Resolution of photo is not valid"};
		} else if (  !( ((this.position).equals("Neutral Face")) || ((this.position).equals("Natural Smile")) )  ) {
			return new String[] {"Rejected", "Position in the photo is not valid"};
		} else {
			return new String[] {"Passed", "Duration will be calculated"};
		}
	}

}
