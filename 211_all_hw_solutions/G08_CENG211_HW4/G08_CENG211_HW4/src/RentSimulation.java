import java.util.ArrayList;
import java.util.Random;

public class RentSimulation {
	
	// Simulation class
	// Intended consequences as instance variables:
	private int cars_rented = 0;
	private int commercial_rentals = 0;
	private int commercial_rental_month = 0;
	private int individual_rentals = 0;
	private int individual_rental_day = 0;
	private int rentals_individual_nonmember = 0;
	private int rentals_individual_member = 0;
	private int rentals_silver_commercial = 0;
	private int rentals_gold_commercial = 0;
	private int rentals_platinum_commercial = 0;
	private ArrayList<Integer> random7DigitInt;
	private ArrayList<Rental<String>> individualRentals = new ArrayList<Rental<String>>();
	private ArrayList<Rental<String>> commercialRentals = new ArrayList<Rental<String>>();
	
	public RentSimulation() {
			
	}
	
	// Checks validity of the ID, if ID is invalid throws custom exception which is InvalidIdException.
	private void idChecker(String id) throws InvalidIdException {
		boolean result = false;
		if (id.startsWith("M")) {
			result = id.length() == 12 ? true : false;
		} else if (id.startsWith("P") || id.startsWith("G") || id.startsWith("S")) {
			result = id.length() == 8 ? true : false;
		} else {
			result = id.length() == 11 ? true : false;
		}
		if (!result) {
			throw new InvalidIdException("Customer Id format is not appropriate");
		}
	}
	
	public ArrayList<Integer> getRandom7DigitInt() {
		return random7DigitInt;
	}
	
	// Method for producing 7 digit random rental code:
	public void setRandom7DigitInt(){
	    ArrayList<Integer> list = new ArrayList<Integer>(50);
	    Random random = new Random();
	    
	    for (int i = 0; i < 50; i++)
	    {
	    	Integer rand7DigitInt = random.nextInt(9999999 - 1000000) + 1000000;
	    	if (!list.contains(rand7DigitInt)) {
	    		list.add(rand7DigitInt);
	    	} else {
	    		continue;
	    	}
	    }
	    
	    this.random7DigitInt = list;
	} 
	
	// Process the content array that already read by FileIO class. 
	// Creates related rental objects by making the necessary checks and store them related arrays.
	public void rentalProcessor(ArrayList<String[]> content) {
		this.setRandom7DigitInt();
		for(String[] aRecord: content) {
			if(aRecord.length < 2) continue;
	   		if (aRecord[0].equals("Individual")) {
	   			// try-catch block for ID validation:
	   			try {
	   				this.idChecker(aRecord[1]);
	   				this.cars_rented++;
	   				this.individual_rentals++;
	   				boolean isMember = (aRecord[1].startsWith("M")) ? (isMember = true) : (isMember = false);
   					IndividualRental<String> someIndividualRental = new IndividualRental<String>(random7DigitInt.remove(random7DigitInt.size()-1), 
   							aRecord[1], aRecord[3], aRecord[4], Double.valueOf(aRecord[5]), Boolean.valueOf(isMember), Integer.valueOf(aRecord[2]));
   					someIndividualRental.calculatePrice();
   					individualRentals.add(someIndividualRental);
   					if(isMember) { this.rentals_individual_member++; } else { this.rentals_individual_nonmember++; }
   					this.individual_rental_day += someIndividualRental.getNumberOfDays();
	   			} catch (InvalidIdException iie) {
	   				System.out.println("An exception has thrown");
	   			} 
	   		} else {
	   			try {
	   				this.idChecker(aRecord[1]);
	   				this.cars_rented++;
	   				this.commercial_rentals++;
	   				String membershipType = String.valueOf(aRecord[1].charAt(0));
	   				switch (membershipType) {
	   					case "S":
	   						CommercialRental<String> someCommercialRental1 = new CommercialRental<String>(random7DigitInt.remove(random7DigitInt.size()-1), 
	   		   						aRecord[1], aRecord[3], aRecord[4], Double.valueOf(aRecord[5]), CustomerType.SILVER, Integer.valueOf(aRecord[2]));
	   						someCommercialRental1.calculatePrice();
	   						commercialRentals.add(someCommercialRental1);
	   						this.rentals_silver_commercial++;
	   						this.commercial_rental_month += someCommercialRental1.getNumberOfMonths();
	   						break;
	   					case "P":
	   						CommercialRental<String> someCommercialRental2 = new CommercialRental<String>(random7DigitInt.remove(random7DigitInt.size()-1), 
	   								aRecord[1], aRecord[3], aRecord[4], Double.valueOf(aRecord[5]), CustomerType.PLATINUM, Integer.valueOf(aRecord[2]));
	   						someCommercialRental2.calculatePrice();
	   						commercialRentals.add(someCommercialRental2);
	   						this.rentals_platinum_commercial++;
	   						this.commercial_rental_month += someCommercialRental2.getNumberOfMonths();
	   						break;
	   					case "G":
	   						CommercialRental<String> someCommercialRental3 = new CommercialRental<String>(random7DigitInt.remove(random7DigitInt.size()-1), 
	   								aRecord[1], aRecord[3], aRecord[4], Double.valueOf(aRecord[5]), CustomerType.GOLD, Integer.valueOf(aRecord[2]));
	   						someCommercialRental3.calculatePrice();
	   						commercialRentals.add(someCommercialRental3);
	   						this.rentals_gold_commercial++;
	   						this.commercial_rental_month += someCommercialRental3.getNumberOfMonths();
	   						break;
	   					default:
	   						break;
	   				}
   						   				
	   			} catch (InvalidIdException iie) {
	   				System.out.println("An exception has thrown");
	   			}
	   		}
	   	}
	}
	
	public int getCars_rented() {
		return cars_rented;
	}

	public void setCars_rented(int cars_rented) {
		this.cars_rented = cars_rented;
	}

	public int getCommercial_rentals() {
		return commercial_rentals;
	}

	public void setCommercial_rentals(int commercial_rentals) {
		this.commercial_rentals = commercial_rentals;
	}

	public int getCommercial_rental_month() {
		return commercial_rental_month;
	}

	public void setCommercial_rental_month(int commercial_rental_month) {
		this.commercial_rental_month = commercial_rental_month;
	}

	public int getIndividual_rentals() {
		return individual_rentals;
	}

	public void setIndividual_rentals(int individual_rentals) {
		this.individual_rentals = individual_rentals;
	}

	public int getIndividual_rental_day() {
		return individual_rental_day;
	}

	public void setIndividual_rental_day(int individual_rental_day) {
		this.individual_rental_day = individual_rental_day;
	}

	public int getRentals_individual_nonmember() {
		return rentals_individual_nonmember;
	}

	public void setRentals_individual_nonmember(int rentals_individual_nonmember) {
		this.rentals_individual_nonmember = rentals_individual_nonmember;
	}

	public int getRentals_individual_member() {
		return rentals_individual_member;
	}

	public void setRentals_individual_member(int rentals_individual_member) {
		this.rentals_individual_member = rentals_individual_member;
	}

	public int getRentals_silver_commercial() {
		return rentals_silver_commercial;
	}

	public void setRentals_silver_commercial(int rentals_silver_commercial) {
		this.rentals_silver_commercial = rentals_silver_commercial;
	}

	public int getRentals_gold_commercial() {
		return rentals_gold_commercial;
	}

	public void setRentals_gold_commercial(int rentals_gold_commercial) {
		this.rentals_gold_commercial = rentals_gold_commercial;
	}

	public int getRentals_platinum_commercial() {
		return rentals_platinum_commercial;
	}

	public void setRentals_platinum_commercial(int rentals_platinum_commercial) {
		this.rentals_platinum_commercial = rentals_platinum_commercial;
	}
	
	public void printRentals() {
		System.out.println("Welcome");
		System.out.println("Total number of cars rented: "+ this.cars_rented);
		System.out.println("Total number of commercial rentals: "+ this.commercial_rentals);
		System.out.println("Total number of commercial rental-month: "+ this.commercial_rental_month);
		System.out.println("Total number of individual rentals: "+ this.individual_rentals);
		System.out.println("Total number of individual rental-day: "+ this.individual_rental_day);
		System.out.println("Total number of rentals of individual non-member customers: "+ this.rentals_individual_nonmember);
		System.out.println("Total number of rentals of individual member customers: "+ this.rentals_individual_nonmember);
		System.out.println("Total number of rentals of silver commercial customers: "+ this.rentals_silver_commercial);
		System.out.println("Total number of rentals of gold commercial customers: "+ this.rentals_gold_commercial);
		System.out.println("Total number of rentals of platinum commercial customers: "+ this.rentals_platinum_commercial);
		System.out.println("No Rental Code Customer ID isMember Number of Days Car Model Model Year Rental Price");
		for (int i = 0; i<individualRentals.size(); i++) {
			System.out.println(i+1 +" "+ individualRentals.get(i).getRentalCode() +" "+ individualRentals.get(i).getCustomerID() +" "
					+((IndividualRental<?>)individualRentals.get(i)).getIsMember() +" "+ ((IndividualRental<?>)individualRentals.get(i)).getNumberOfDays() +" "
					+individualRentals.get(i).getCarModel() +" "+ individualRentals.get(i).getModelYear() +" "+ ((IndividualRental<?>)individualRentals.get(i)).getRentalPrice());
		}
		for (int j = 0; j<commercialRentals.size(); j++) {
			System.out.println(j+1 +" "+ commercialRentals.get(j).getRentalCode() +" "+ commercialRentals.get(j).getCustomerID() +" "
					+((CommercialRental<?>)commercialRentals.get(j)).getCustomerType().name()+" "+ ((CommercialRental<?>)commercialRentals.get(j)).getNumberOfMonths() +" "
					+commercialRentals.get(j).getCarModel() +" "+ commercialRentals.get(j).getModelYear() +" "+ ((CommercialRental<?>)commercialRentals.get(j)).getRentalPrice());
		}
	}

	
}
