
public abstract class Menu {
	private String ID,name;
	private int price,quantity;

	public Menu(String ID,String name,int price,int quantity) {
		this.ID=ID;
		this.name=name;
		this.price=price;
		this.quantity=quantity;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
