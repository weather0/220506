package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class CartInfo {
	int cartorder; // 고유필드
	String menu_name;
	int menu_price;
	int volume;
	String id;

}
