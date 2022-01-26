package il.ac.tau.cs.software1.predicate;

import java.util.List;

public class Store<T extends Product> {
	private List<T> inventory;
	
	public Store(List <T> inventory) {
		this.inventory = inventory;
	}

	public List<T> getInventory() {
		return inventory;
	}

	public String getInventoryDescription() { // Q4
		String s="";
		for (T product:inventory) {
			s+=product.getDescription();
		}
		return s; 
	}

	public void transform(Predicate<T> pred, Action<T> action) { // Q5
		for (T product:inventory) {
			if (pred.test(product))
				action.performAction(product);
		}
	}
}
