package il.ac.tau.cs.software1.predicate;

public class ByPrice implements Predicate<SmartPhone>{
	private double maxPrice;
	public ByPrice(double maxPrice) { // Q2
		this.maxPrice=maxPrice;
	}

	@Override
	public boolean test(SmartPhone phone) { // Q2
		if (maxPrice >= phone.getPrice())
			return true;
		return false;
	}
	
	

}
