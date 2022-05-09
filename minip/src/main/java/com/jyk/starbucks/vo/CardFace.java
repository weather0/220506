package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class CardFace {
	private int card_order;
	private String card_name;
	private String card_avl;
	
	public String toString() {
		System.out.println(card_order+". "+card_name);

		return null;
	}

}
