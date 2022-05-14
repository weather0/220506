package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class CardFace {
	int cardorder;
	String card_name;
	String card_avl;

	public String toString() {
		System.out.println(cardorder + ". " + card_name);

		return null;
	}

}
