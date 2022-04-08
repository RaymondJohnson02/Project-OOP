import java.util.Vector;

public class Order {
	   private Customer customer;
	   private Vector<Menu> menuOrdered;
	   private int TotalPrice;

	

	public Order(Customer customer, Vector<Menu> menuOrdered, int totalPrice) {
		this.customer = customer;
		this.menuOrdered = menuOrdered;
		this.TotalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vector<Menu> getMenuOrdered() {
		return menuOrdered;
	}

	public void setMenuOrdered(Vector<Menu> menuOrdered) {
		this.menuOrdered = menuOrdered;
	}

	public int getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		TotalPrice = totalPrice;
	}
	
	

}
