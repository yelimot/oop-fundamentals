import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileIO {
	private ArrayList<Game> games;
	private ArrayList<Movie> movies;
	private ArrayList<Game> critisizedGames;
	private ArrayList<Movie> critisizedMovies;
	
	public FileIO() {
		this.games = new ArrayList<Game>();
		this.movies = new ArrayList<Movie>();
		this.critisizedGames = new ArrayList<Game>();
		this.critisizedMovies = new ArrayList<Movie>();
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	public void sortMovieList() {
		Collections.sort(this.critisizedMovies, new Comparator<Movie>() {
		    public int compare(Movie m1, Movie m2) {
		        return Double.compare(m2.getRatingAfterCritic(), m1.getRatingAfterCritic());
		    }
		});
	}
	public void sortGameList() {
		Collections.sort(this.critisizedGames, new Comparator<Game>() {
		    public int compare(Game g1, Game g2) {
		        return Double.compare(g2.getRatingAfterCritic(), g1.getRatingAfterCritic());
		    }
		});
	}
	
	public void readSpecificDay(String day, String pathOfCsv) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(pathOfCsv));
	    String row;
	    
	    while ((row = csvReader.readLine()) != null) {
	    	String[] listOfAttributes = row.split(",");
	    	if (listOfAttributes[0].equals(day)) {
	    		switch (listOfAttributes[1]) {
		    		case "0":
		    			Movie someMovie = new Movie(Integer.valueOf(listOfAttributes[0]), Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], Integer.valueOf(listOfAttributes[3]), Integer.valueOf(listOfAttributes[4]), Double.valueOf(listOfAttributes[5]));
		    			movies.add(someMovie);
		    			break;
		    		case "1":
		    			IndefiniteGame someIndefiniteGame = new IndefiniteGame(Integer.valueOf(listOfAttributes[0]), Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], Integer.valueOf(listOfAttributes[3]), Integer.valueOf(listOfAttributes[4]));
			   			games.add(someIndefiniteGame);
			   			break;
		    		case "2":
		    			StoryGame someStoryGame = new StoryGame(Integer.valueOf(listOfAttributes[0]), Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], Integer.valueOf(listOfAttributes[3]), Integer.valueOf(listOfAttributes[4]));
			   			games.add(someStoryGame);
			   			break;
		    		case "3":
		    			CasualGame someCasualGame = new CasualGame(Integer.valueOf(listOfAttributes[0]), Integer.valueOf(listOfAttributes[1]), listOfAttributes[2], Integer.valueOf(listOfAttributes[3]), Integer.valueOf(listOfAttributes[4]));
			   			games.add(someCasualGame);
			   		default:
			   			break;	    		
	    		}
	    	} else {
	    		continue;
	    	}
	    }
    	csvReader.close();
	}

	public ArrayList<Movie> getCritisizedMovies() {
		return critisizedMovies;
	}

	public void setCritisizedMovies(ArrayList<Movie> critisizedMovies) {
		this.critisizedMovies = critisizedMovies;
	}

	public ArrayList<Game> getCritisizedGames() {
		return critisizedGames;
	}

	public void setCritisizedGames(ArrayList<Game> critisizedGames) {
		this.critisizedGames = critisizedGames;
	}
}
