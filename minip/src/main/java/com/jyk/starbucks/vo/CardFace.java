package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class CardFace {
	private int cardorder;
	private String card_name;
	private String card_avl;
	
	public String toString() {
		System.out.println(cardorder+". "+card_name);

		return null;
	}

}
