
public abstract class Game {
	
	// Game is abstract class since we should avoid duplicated code.
	// Indefinite, story etc.. games are extends this class but they have their own CritisizedBy method
	
	protected int arrivalDay;
	protected int code;
	protected String name;
	protected int durationHour;
	protected int averageRating;
	protected double ratingAfterCritic;
	
	public Game() {
		
	}
		
	public int getArrivalDay() {
		return arrivalDay;
	}
	public void setArrivalDay(int arrivalDay) {
		this.arrivalDay = arrivalDay;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDurationHour() {
		return durationHour;
	}
	public void setDurationHour(int durationHour) {
		this.durationHour = durationHour;
	}
	public int getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}

	public double getRatingAfterCritic() {
		return ratingAfterCritic;
	}

	public void setRatingAfterCritic(double ratingAfterCritic) {
		this.ratingAfterCritic = ratingAfterCritic;
	}
	
	// Abstract method with no body:
	abstract void CritisizedBy(Critic oneCritic);

}
