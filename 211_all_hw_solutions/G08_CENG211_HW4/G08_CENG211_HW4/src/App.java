import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		
		// reading from the file via FileIO class.
		FileIO io = new FileIO();
	    io.ReadContent("HW4_Rentals.csv");
	    
	    // starting the simulation via RentSimulation class.
	    RentSimulation simulation = new RentSimulation();
	    simulation.rentalProcessor(io.getContent());
	    simulation.printRentals();
	}
}
