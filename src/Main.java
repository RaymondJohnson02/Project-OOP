import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	Scanner scan = new Scanner(System.in);
	int count = 0;
	
	void cls() {
		for (int i = 0; i < 26; i++) {
			System.out.println();
		}
	}
	
	void menu() {
		System.out.println("SunibResto\r\n"
				+ "====================\r\n"
				+ "1. Insert New Menu\r\n"
				+ "2. View Menu\r\n"
				+ "3. Update Menu\r\n"
				+ "4. Delete Menu\r\n"
				+ "5. Get Order\r\n"
				+ "6. View Order\r\n"
				+ "7. Exit");
	}
	
	void createDelay() {
		System.out.println("Press any key to continue...");
		scan.nextLine();
	}
	
	public String idGenerator() {
		String id="";
		int num1 = count/100;
		int num2 = count/10 - num1*10;
		int num3 = count%10;
		id = "MN"+num1+num2+num3;
		count++;
		return id;
	}
	
	void insertDummyData(Vector<Menu> menus) throws IOException {
		File file = new File("Menu.txt");
		Scanner scanfile = new Scanner(file);
		while(scanfile.hasNextLine()) {
			String line = scanfile.nextLine();
			String attributes[] = line.split(",");
			switch(attributes[0]) {
				case "Appetizer":
					menus.add(new Appetizer(idGenerator(),attributes[1],Integer.valueOf(attributes[2]),Integer.valueOf(attributes[3]),attributes[4]));
					break;
				case "MainCourse":
					menus.add(new MainCourse(idGenerator(),attributes[1],Integer.valueOf(attributes[2]),Integer.valueOf(attributes[3]),attributes[4]));
					break;
				case "Dessert" :
					menus.add(new Dessert(idGenerator(),attributes[1],Integer.valueOf(attributes[2]),Integer.valueOf(attributes[3]),attributes[4]));
					break;
				case "Drink":
					menus.add(new Drink(idGenerator(),attributes[1],Integer.valueOf(attributes[2]),Integer.valueOf(attributes[3]),attributes[4]));
					break;
			}
		}
	}
	
	public void insertMenu(Vector<Menu> menus) {
		String name="",cat="",type="",id="";
		int pri=0,quan=0;
		cls();
		do {
			System.out.print("Choose Category [Appetizer | MainCourse | Dessert | Drink]: ");
			cat=scan.nextLine();
		} while (!cat.equals("Appetizer") && !cat.equals("MainCourse") &&!cat.equals("Dessert") &&!cat.equals("Drink"));
		
		do {
			System.out.print("Input name [length must be more than 4]: ");
			name=scan.nextLine();
		} while (name.length()<=4);
		
		do {
			System.out.print("Input price [must be more than 1000]: ");
			pri=scan.nextInt();
			scan.nextLine();
		} while (pri<1000);
		
		do {
			System.out.print("Input quantity [must be more than 1]: ");
			quan=scan.nextInt();
			scan.nextLine();
		} while (quan<1);
		
		id = idGenerator();
		
		
		if(cat.equals("Appetizer") || cat.equals("MainCourse")) {
			do {
				System.out.print("Input Type [Normal | Vegan | Halal]: ");
				type=scan.nextLine();
			} while (!type.equals("Normal") && !type.equals("Vegan") &&!type.equals("Halal"));
			
			if(cat.equals("Appetizer")) {
				menus.add(new Appetizer(id, name, pri, quan, type));
			}
			
			if(cat.equals("MainCourse")) {
				menus.add(new MainCourse(id, name, pri, quan, type));
			}
		}
		
		if(cat.equals("Dessert") ) {
			do {
				System.out.print("Input Topping [Strawberry | Chocolate | Vanilla]: ");
				type=scan.nextLine();
			} while (!type.equals("Strawberry") && !type.equals("Chocolate") &&!type.equals("Vanilla"));
			menus.add(new Dessert(id, name, pri, quan, type));
		}
		
		if(cat.equals("Drink") ) {
			do {
				System.out.print("Input Size [Medium | Large]: ");
				type=scan.nextLine();
			} while (!type.equals("Medium") && !type.equals("Large"));
			menus.add(new Drink(id, name, pri, quan, type));
		}
		System.out.println("successfully add menu");
		createDelay();
	}

	public void viewMenu(Vector<Menu> menus) {
		cls();
		if(menus.isEmpty()) {
			System.out.println("there's no menu");
		}
		else {
			System.out.println("================================================================================================");
			System.out.println("| ID    | Name                                     | Price  | Qty | Category     | Description |");
			System.out.println("================================================================================================");
			for (int i = 0; i < menus.size(); i++) {
				
				System.out.print("| "+menus.get(i).getID()+" | ");
				System.out.printf("%-40s |",menus.get(i).getName());
				System.out.printf(" %-6d | ",menus.get(i).getPrice());
				System.out.printf("%-3d | ",menus.get(i).getQuantity());
				String category = menus.get(i).toString();
				category = category.substring(0,category.indexOf("@"));
				System.out.printf("%-12s | ",category);
				switch(category) {
				case "Appetizer":
					Appetizer temp1 = (Appetizer)menus.get(i);
					System.out.printf("%-11s",temp1.getType());
					break;
				case "MainCourse":
					MainCourse temp2 = (MainCourse)menus.get(i);
					System.out.printf("%-11s",temp2.getType());
					break;
				case "Dessert":
					Dessert temp3 = (Dessert)menus.get(i);
					System.out.printf("%-11s",temp3.getTopping());
					break;
				case "Drink":
					Drink temp4 = (Drink)menus.get(i);
					System.out.printf("%-11s",temp4.getSize());
					break;
				}
				System.out.println(" |");
			}
			System.out.println("================================================================================================");
			System.out.println("================================================================================================");
		}
	}
	
	int findMenuWithID(Vector<Menu> menus, String ID) {
		int idx = -1;
		for(int i=0; i<menus.size(); i++) {
			if(menus.get(i).getID().equals(ID)) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	void updateMenu(Vector<Menu> menus) {
		cls();
		viewMenu(menus);
		String ID = "";
		int idx=-1;
		do {
			System.out.print("Input menu ID to update: ");
			ID=scan.nextLine();
			idx = findMenuWithID(menus,ID);
			if(idx==-1) {
				System.out.println("Please input the right ID");
			}
		} while (idx==-1);
		
		String name="";
		do {
			System.out.print("Input name [length must be more than 4]: ");
			name=scan.nextLine();
		} while (name.length()<=4);
				
		int pri=0;
		do {
			System.out.print("Input price [must be more than 1000]: ");
			pri=scan.nextInt();
			scan.nextLine();
		} while (pri<1000);
				
		int quan=0;
		do {
			System.out.print("Input quantity [must be more than 1]: ");
			quan=scan.nextInt();
			scan.nextLine();
		} while (quan<1);
				
		String category = menus.get(idx).toString();
		category = category.substring(0,category.indexOf("@"));
		
		String type = "";
		
		if(category.equals("Appetizer")) {
			do {
				System.out.print("Input Type [Normal | Vegan | Halal]: ");
				type=scan.nextLine();
			} while (!type.equals("Normal") && !type.equals("Vegan") && !type.equals("Halal"));
			
			Appetizer temp = (Appetizer)menus.get(idx);
			temp.setName(name);
			temp.setPrice(pri);
			temp.setQuantity(quan);
			temp.setType(type);
			menus.setElementAt(temp, idx);
		}
	
		if(category.equals("MainCourse")) {
			do {
				System.out.print("Input Type [Normal | Vegan | Halal]: ");
				type=scan.nextLine();
			} while (!type.equals("Normal") && !type.equals("Vegan") && !type.equals("Halal"));
			
			MainCourse temp = (MainCourse)menus.get(idx);
			temp.setName(name);
			temp.setPrice(pri);
			temp.setQuantity(quan);
			temp.setType(type);
			menus.setElementAt(temp, idx);	
		}
				
		if(category.equals("Dessert") ) {
			String topping = "";
			do {
				System.out.print("Input Topping [Strawberry | Chocolate | Vanilla]: ");
				topping=scan.nextLine();
			} while (!topping.equals("Strawberry") && !topping.equals("Chocolate") &&!topping.equals("Vanilla"));
			
			Dessert temp = (Dessert)menus.get(idx);
			temp.setName(name);
			temp.setPrice(pri);
			temp.setQuantity(quan);
			temp.setTopping(topping);
			menus.setElementAt(temp, idx);
		}
			
		if(category.equals("Drink") ) {
			String size = "";
			do {
				System.out.print("Input Size [Medium | Large]: ");
				size=scan.nextLine();
			} while(!size.equals("Medium") && !size.equals("Large"));

			Drink temp = (Drink)menus.get(idx);
			temp.setName(name);
			temp.setPrice(pri);
			temp.setQuantity(quan);
			temp.setSize(size);
			menus.setElementAt(temp,idx);
		}
		System.out.println("successfully update menu");
		createDelay();
	}
	
	void deleteMenu(Vector<Menu> menus) {
		viewMenu(menus);
		String ID="";
		int idx = -1;
		do {
			System.out.print("Input menu ID to delete : ");
			ID = scan.nextLine();
			idx = findMenuWithID(menus,ID);
			if(idx==-1) {
				System.out.println("Please input the right ID");
			}
		} while (idx==-1);
		
		if(idx!=-1) {
			String cek = "";
			do {
				System.out.print("Are you sure want to delete [Y/N]: ");
				cek=scan.nextLine();
			} while (!cek.equals("Y") && !cek.equals("N"));
			
			if(cek.equals("Y")) {
				menus.remove(idx);
				System.out.println("Successfully delete menu");
			}else {
				System.out.println("Cancel delete");
			}
			createDelay();
		}
	}
	
	public void getOrderMenu(Vector<Menu> menus, Vector<Order> orders) {
		String name="";
		do {
			System.out.print("Input your name [length must more than 4] : ");
			name=scan.nextLine();
		} while (name.length()<4);
		
		String gender="";
		do {
			System.out.print("Input your gender [Male | Female] : ");
			gender=scan.nextLine();
		} while (!gender.equals("Male") && !gender.equals("Female"));
		
		Customer customer = new Customer(name, gender);
		viewMenu(menus);
		
		Vector<Menu> menuOrder = new Vector<>();
		int total=0;
		
		String ID = "";
		do {
			if(ID.equals("")) {
				System.out.print("Input menu ID to order [type 'cancel' to go back to main menu] : ");
			}
			else {
				System.out.print("Input menu ID to order [type 'order' to finish order] : ");
			}
			if(ID.equals("cancel")) {
				createDelay();
				return;
			}
			ID=scan.nextLine();
			for (int i = 0; i < menus.size(); i++) {
				if(ID.equals(menus.get(i).getID())){
					menuOrder.add(new Menu(menus.get(i).getID(),menus.get(i).getName(),menus.get(i).getPrice(),menus.get(i).getQuantity()){});
				}
			}
		} while (!ID.equals("cancel") && !ID.equals("order"));
		
		Order newOrder = new Order(customer, new Vector<Menu>(), total);
		if(ID.equals("order")) {
			System.out.println("heres you order(s):");
			for (int i = 0; i < menuOrder.size(); i++) {
				System.out.println("Pesanan ke - "+(i+1)+" : "+menuOrder.get(i).getName()+" "+menuOrder.get(i).getPrice());
				total+=menuOrder.get(i).getPrice();
			}
			System.out.println("total : "+total);
			for (int i = 0; i < menuOrder.size(); i++) {
				newOrder.getMenuOrdered().add(new Menu(menuOrder.get(i).getID(),menuOrder.get(i).getName(),menuOrder.get(i).getPrice(),menuOrder.get(i).getQuantity()) {});
			}
			newOrder.setTotalPrice(total);
		}
		orders.add(newOrder);
		createDelay();
	}
	
	public void viewOrder(Vector<Order> orders) {
		cls();
		if(orders.isEmpty()) {
			System.out.println("you havent order");
		}
		else {
			System.out.println("List Order");
			System.out.println("==========================");
			for (int i = 0; i < orders.size(); i++) {
				System.out.println("Customer Name : "+orders.get(i).getCustomer().getName());
				for (int j = 0; j < orders.get(i).getMenuOrdered().size(); j++) {
					System.out.println("order "+(j+1)+" : "+orders.get(i).getMenuOrdered().get(j).getName()+" "+orders.get(i).getMenuOrdered().get(j).getPrice());
				}
				System.out.println("Total : "+orders.get(i).getTotalPrice());
				System.out.println("==========================");
			}
			System.out.println("==========================");
		}
		createDelay();
	}
	
	public Main() throws IOException {
		Vector<Menu> menus = new Vector<>();
		Vector<Order> orders = new Vector<>();
		int pil=-1;
		count=1;
		insertDummyData(menus);
		
		do {
			cls();
			menu();
			
			do {
				try {
					System.out.print(">> ");
					pil=scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} while (pil<1||pil>7);
			
			if(pil==1) {
				insertMenu(menus);
			}else if(pil==2) {
				viewMenu(menus);
				createDelay();
			}else if(pil==3) {
				updateMenu(menus);
			}else if(pil==4) {
				deleteMenu(menus);
			}else if(pil==5) {
				getOrderMenu(menus,orders);
			}else if(pil==6) {
				viewOrder(orders);
			}else if(pil==7) {
				System.out.println("Thankyou for using our program...");
			}else {
				System.out.println("Invalid input");
			}
		} while (pil!=7);
		
	}

	public static void main(String[] args) throws IOException {
		new Main();
	}

}
