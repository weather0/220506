package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class SBMenuInfo {
	int menuorder; // 고유필드
	String menu_name;
	int menu_price;
	String menu_type1;
	String menu_type2;
	int menu_avl;
	String menu_size1;
	String menu_size2;
	String menu_size3;
	int vol; // 고유필드

	public String toString() {
		System.out.println(
				menuorder + ". " + menu_name + " : " + menu_price + "원 : 재고=" + menu_avl + " : 기본사이즈=" + menu_size1);

		return null;
	}

}
