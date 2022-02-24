
public class InvalidIdException extends Exception {
	
	// Custom exception class in the case of InvalidId.
	private static final long serialVersionUID = 8640023736440470869L;

	public InvalidIdException(String errorMessage) {
		super(errorMessage);
	}
}
