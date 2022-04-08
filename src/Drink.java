
public class Drink extends Menu {
	private String size;

	public Drink(String ID, String name, int price, int quantity,String size) {
		super(ID, name, price, quantity);
		this.size=size;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	



}
