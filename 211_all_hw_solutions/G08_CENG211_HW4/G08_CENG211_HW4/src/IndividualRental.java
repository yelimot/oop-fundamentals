
public class IndividualRental<T> extends Rental<T> {
	
	// Specialised Rental class with isMember, numberOfDays, rentalPrice and IndividualCustomer.
	private boolean isMember;
	private int numberOfDays;
	private double rentalPrice;
	private IndividualCustomer customer = new IndividualCustomer();

	public IndividualRental(int rentalCode, T customerID, String carModel, String modelYear, double carBasePrice, boolean isMember, int numberOfDays) {
		super(rentalCode, customerID, carModel, modelYear, carBasePrice);
		this.setMember(isMember);
		this.setNumberOfDays(numberOfDays);
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	
	public String getIsMember() {
		if (this.isMember) {
			return "Yes";
		} else {
			return "No";
		}
	}
	
	// Calculates the rental price by checking membership.
	public void calculatePrice() {
		double model_year_ratio;
		if (this.getModelYear().equals("2022")){
			model_year_ratio = 1;
		} else if (this.getModelYear().equals("2021") || (this.getModelYear().equals("2020"))) {
			model_year_ratio = 0.95;
		} else {
			model_year_ratio = 0.9;
		}
		double daily_price = this.customer.dailyPriceCalculator(this.getCarBasePrice(), model_year_ratio);
		double raw_rental_price = this.numberOfDays*daily_price;
		if (this.isMember) {
			this.setRentalPrice(raw_rental_price*0.9);
		} else {
			this.setRentalPrice(raw_rental_price);
		}
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public IndividualCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(IndividualCustomer customer) {
		this.customer = customer;
	}

		
}
