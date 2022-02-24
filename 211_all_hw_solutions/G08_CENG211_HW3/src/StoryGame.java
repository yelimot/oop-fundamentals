
public class StoryGame extends Game {
	
	public StoryGame(int arrivalDay, int code, String name, int durationHour, int averageRating) {
		this.arrivalDay = arrivalDay;
		this.code = code;
		this.name = name;
		this.durationHour = durationHour;
		this.averageRating = averageRating;
	}
	
	public void CritisizedBy(Critic oneCritic) {
		double rac = this.averageRating + (this.durationHour*0.25) + (oneCritic.getOpinion());
		this.ratingAfterCritic = rac;
	}
	
}
