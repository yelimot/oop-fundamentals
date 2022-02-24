import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Simulation {
	
	static void simulate() throws IOException {
		FileIO io = new FileIO();
				
		Critic movieCritic_1 = new Critic(0.1, "1. Movie Critic", true);
		Critic movieCritic_2 = new Critic(-0.2, "2. Movie Critic", true);
		Critic movieCritic_3 = new Critic(0.3, "3. Movie Critic", true);
		
		Critic gameCritic_1 = new Critic(5.0, "1. Game Critic", true);
		Critic gameCritic_2 = new Critic(9.0, "2. Game Critic", true);
		Critic gameCritic_3 = new Critic(-3.0, "3. Game Critic", true);
		Critic gameCritic_4 = new Critic(2.0, "4. Game Critic", true);
		Critic gameCritic_5 = new Critic(-7.0, "5. Game Critic", true);	
		
		ArrayList<Critic> availableMovieCriticList = new ArrayList<Critic>() {
			{ 
				add(movieCritic_1); 
				add(movieCritic_2); 
				add(movieCritic_3);
			}
		};
		
		// I named this array 'busy' because, the first day of the simulation all critics will be busy.
		ArrayList<Critic> busyGameCriticList = new ArrayList<Critic>() { 
			{ 
				add(gameCritic_1); 
				add(gameCritic_2); 
				add(gameCritic_3);
				add(gameCritic_4);
				add(gameCritic_5);
			}
		};
		
		ArrayList<Critic> availableGameCriticList = new ArrayList<Critic>();
		
		for (int i = 1; i<6; i++) {
			System.out.println(i+". day:");
			
			io.readSpecificDay(Integer.toString(i), "contents.csv");
			
			// Only the first day games assigned manually, since all critics will be available at first place.
			if (i == 1) {
				gameCritic_1.setAssignedGame(io.getGames().get(4));
				gameCritic_2.setAssignedGame(io.getGames().get(3));
				gameCritic_3.setAssignedGame(io.getGames().get(2));
				gameCritic_4.setAssignedGame(io.getGames().get(1));
				gameCritic_5.setAssignedGame(io.getGames().get(0));
			}
			
			int x = 0;
			while (x<3 && !(io.getMovies().isEmpty())) { // since there are 3 game critics exist
				Movie aMovie = io.getMovies().remove((io.getMovies().size()-1)); // removing last element of movie list (last in first out, lifo)
				Critic aMovieCritic = availableMovieCriticList.remove(0); // first movie critic (first in first out, fifo)
				aMovie.CritisizedBy(aMovieCritic);
				System.out.println(aMovieCritic.getName() + " evaluated: " + aMovie.getName());
				io.getCritisizedMovies().add(0,aMovie);
				availableMovieCriticList.add(aMovieCritic);
				x++;
			}
			
			// To avoid java.util.ConcurrentModificationException I created this tempBusyGameCriticList but this causes duplication of games in result
			ArrayList<Critic> tempBusyGameCriticList = new ArrayList<Critic>(busyGameCriticList);
			for (Critic critic: tempBusyGameCriticList) {
				int criticRemainingTime = critic.getRemainingGameTime() - 8;
				if (criticRemainingTime <= 0) {
					critic.getAssignedGame().CritisizedBy(critic);
					io.getCritisizedGames().add(critic.getAssignedGame());
					System.out.println(critic.getName() + " evaluated: " + critic.getAssignedGame().getName());
					critic.setRemainingGameTime(8);
					availableGameCriticList.add(0, critic);
					busyGameCriticList.remove(critic);
				} else {
					System.out.println(critic.getName() + " works on: " + critic.getAssignedGame().getName());
					critic.setRemainingGameTimeAfterDay(criticRemainingTime);
				}
			}
		
			while (!(availableGameCriticList.isEmpty()) && !(io.getGames().isEmpty())) {
				Game aGame = io.getGames().remove(io.getGames().size()-1); // removing last element of game list (last in first out, lifo)
				Critic aGameCritic = availableGameCriticList.remove(0); // first game critic (first in first out, fifo)
				aGameCritic.setAssignedGame(aGame);
				aGameCritic.setRemainingGameTime();
				busyGameCriticList.add(aGameCritic);
			}			
		}

		io.sortMovieList();
		io.sortGameList();
		
		// I did not print out the contents that are not evaluated:
		System.out.println("Ratings: ");
		for (Movie mov: io.getCritisizedMovies()) {
			System.out.println("#"+mov.getName()+" ("+mov.getYear()+"), "+mov.getRatingAfterCritic());
		}
		
		for (Game game: io.getCritisizedGames()) {
			System.out.println("#"+game.getName()+", "+game.getRatingAfterCritic());
		}
	}
}