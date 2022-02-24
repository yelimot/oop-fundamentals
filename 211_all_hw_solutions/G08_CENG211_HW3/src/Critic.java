
public class Critic {
	
	private double opinion;
	private String name;
	private Game assignedGame;
	private int remainingGameTime;
	private boolean isAvailable;
	
	
	public Critic(double opinion, String name, boolean isAvailable) {
		this.opinion = opinion;
		this.setName(name);
		this.setAvailable(true);
		this.remainingGameTime = 8;
	}

	public double getOpinion() {
		return opinion;
	}

	public void setOpinion(double opinion) {
		this.opinion = opinion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Game getAssignedGame() {
		return assignedGame;
	}

	public void setAssignedGame(Game assignedGame) {
		this.assignedGame = assignedGame;
	}

	public int getRemainingGameTime() {
		return this.remainingGameTime;
	}
	
	public void setRemainingGameTime() {
		if (this.assignedGame.code == 1) {
			this.remainingGameTime = 4; // Indefinite game should played 4 hours and left.
		} else if (this.assignedGame.code == 2) {
			this.remainingGameTime = this.assignedGame.durationHour; // A game critic should play story games for complete story duration.
		} else if (this.assignedGame.code == 3) { 
			this.remainingGameTime = this.assignedGame.durationHour*3; // A game critic should play casual games for 3 matches.
		}
	}
	
	// method overloading
	public void setRemainingGameTime(int time) {
		this.remainingGameTime = time;
	}
	
	public void setRemainingGameTimeAfterDay(int hours) {
		this.remainingGameTime = hours;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public void criticWorksOn(Game someGame) {
		this.isAvailable = false;
		System.out.println(this.getName() + " works on " + someGame.getName());
	}

}
