package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class SBMenuInfo {
	private int menuorder;
	private String menu_name;
	private int menu_price;
	private String menu_type1;
	private String menu_type2;
	private int menu_avl;
	private int cart;
	
	public String toString() {
		System.out.println(menuorder+". "+menu_name+" : "+menu_price+" : "+menu_avl);

		return null;
	}

}
