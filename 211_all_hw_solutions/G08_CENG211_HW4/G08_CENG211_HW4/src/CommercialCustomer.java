
public class CommercialCustomer implements ICustomer {
	
	// Daily price for commercial customer.
	public double dailyPriceCalculator(double modelBasePrice, double modelYearRatio) {
		double daily_price = modelBasePrice*modelYearRatio;
		return daily_price;
	}
}
