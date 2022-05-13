package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class SBMenuInfo {
	private int menuorder; // 자바고유필드
	private String menu_name;
	private int menu_price;
	private String menu_type1;
	private String menu_type2;
	private int menu_avl;
	private String menu_size1;
	private String menu_size2;
	private String menu_size3;
	private int vol; // 자바고유필드

	public String toString() {
		System.out.println(menuorder + ". " + menu_name + " : " + menu_price + "원 : 재고=" + menu_avl + " : 기본사이즈=" + menu_size1);

		return null;
	}

}
