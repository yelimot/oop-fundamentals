
public class IndividualCustomer implements ICustomer{

	// Daily price for individual customer.
	public double dailyPriceCalculator(double modelBasePrice, double modelYearRatio) {
		double daily_price = modelBasePrice*modelYearRatio;
		return daily_price;
	}

}
