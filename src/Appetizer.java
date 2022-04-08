
public class Appetizer extends Menu {
	private String type;

	public Appetizer(String ID, String name, int price, int quantity,String type) {
		super(ID, name, price, quantity);
		this.type=type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	

}
