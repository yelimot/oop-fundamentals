
public class Movie {
	private int arrivalDay;
	private int code;
	private String name;
	private int year;
	private int durationMinutes;
	private double averageRating;
	private double ratingAfterCritic;
	
	public Movie() {
		
	}
	
	public Movie(int arrivalDay, int code, String name, int year, int durationMinutes, double averageRating) {
		this.arrivalDay = arrivalDay;
		this.code = code;
		this.name = name;
		this.year = year;
		this.durationMinutes = durationMinutes;
		this.averageRating = averageRating;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public double getRatingAfterCritic() {
		return ratingAfterCritic;
	}

	public void setRatingAfterCritic(double ratingAfterCritic) {
		this.ratingAfterCritic = ratingAfterCritic;
	}
	
	public void CritisizedBy(Critic oneCritic) {
		double rac = this.averageRating + ((this.durationMinutes-150)*0.01) - ((2021 - this.year) * 0.01) + (oneCritic.getOpinion());
		this.ratingAfterCritic = rac;
	}
}
