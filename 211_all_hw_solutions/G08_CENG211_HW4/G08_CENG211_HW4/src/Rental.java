
public abstract class Rental<T> {
	
	// Abstract class to the prevent code duplication for more specialised classes.
	private int rentalCode;
	private ID<T> customerID;
	private String carModel;
	private String modelYear;
	private double carBasePrice;
	
	public Rental(int rentalCode, T customerID, String carModel, String modelYear, double carBasePrice) {
		super();
		this.rentalCode = rentalCode;
		this.customerID = ID.of(customerID);
		this.carModel = carModel;
		this.modelYear = modelYear;
		this.carBasePrice = carBasePrice;
	}

	public int getRentalCode() {
		return rentalCode;
	}

	public void setRentalCode(int rentalCode) {
		this.rentalCode = rentalCode;
	}

	public T getCustomerID() {
		return this.customerID.getValue();
	}

	public void setCustomerID(T customerID) {
		this.customerID = ID.of(customerID);
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public double getCarBasePrice() {
		return carBasePrice;
	}

	public void setCarBasePrice(double carBasePrice) {
		this.carBasePrice = carBasePrice;
	}
	
	public abstract void calculatePrice();
		
}
