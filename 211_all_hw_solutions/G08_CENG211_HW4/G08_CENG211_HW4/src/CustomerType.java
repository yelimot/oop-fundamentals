
public enum CustomerType {
	SILVER(20),
	GOLD(25),
	PLATINUM(30);
	
	// There is a interface implementation hint, in the homework description but, I used interface at other field.
	// Discount rates for different type of customers, I used enumeration.
	// There can be added additional type of customers easily.
	
	private final int discount;
	 
	CustomerType(int discount) {
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}
}
