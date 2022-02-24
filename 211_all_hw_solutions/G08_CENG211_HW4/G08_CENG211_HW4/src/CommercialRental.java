
public class CommercialRental<T> extends Rental<T> {
	
	// Specialised Rental class with CustomerType, numberOfMonths, rentalPrice and CommercialCustomer.
	private CustomerType customerType;
	private int numberOfMonths;
	private double rentalPrice;
	private CommercialCustomer customer = new CommercialCustomer();

	public CommercialRental(int rentalCode, T customerID, String carModel, String modelYear, double carBasePrice, CustomerType customerType, int numberOfMonths) {
		super(rentalCode, customerID, carModel, modelYear, carBasePrice);
		this.setNumberOfMonths(numberOfMonths);
		this.customerType = customerType;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public int getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}
	
	// Calculates price of the rental, according to discount rate that got from customerType enum.
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
		double raw_rental_price = numberOfMonths*daily_price*30;
		int discount_ratio_raw = 100 - this.customerType.getDiscount();
		double discount_ratio = ((double) discount_ratio_raw) / 100;
		this.setRentalPrice(raw_rental_price*discount_ratio);
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public CommercialCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CommercialCustomer customer) {
		this.customer = customer;
	}
	

}
