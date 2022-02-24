
public class CasualGame extends Game {
	
	public CasualGame(int arrivalDay, int code, String name, int durationHour, int averageRating) {
		this.arrivalDay = arrivalDay;
		this.code = code;
		this.name = name;
		this.durationHour = durationHour;
		this.averageRating = averageRating;
	}
	
	public void CritisizedBy(Critic oneCritic) {
		double rac = this.averageRating + ((this.durationHour-3)*3) + (oneCritic.getOpinion());
		this.ratingAfterCritic = rac;
	}

}
