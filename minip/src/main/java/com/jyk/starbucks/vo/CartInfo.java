package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class CartInfo {
	private int cartorder; // 고유필드
	private String menu_name;
	private int menu_price;
	private int volume;
	private String id;

}
