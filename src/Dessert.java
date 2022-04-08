
public class Dessert extends Menu {
	private String topping;

	public Dessert(String ID, String name, int price, int quantity,String topping) {
		super(ID, name, price, quantity);
		this.topping = topping;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

}
