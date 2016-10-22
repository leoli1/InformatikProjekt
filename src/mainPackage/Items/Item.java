package mainPackage.Items;

import java.util.ArrayList;


public class Item {
	public static ArrayList<Item> items = new ArrayList<Item>();
	
	public String name;
	
	public Item(){
		Item.items.add(this);
	}

}
