package com.jyk.starbucks.vo;

import com.jyk.starbucks.app.MainMenu;

import lombok.Data;

@Data
public class CardFace {
	int cardorder;
	String card_name;
	String card_avl;

	public String toString() {
		
		System.out.print("│ " + cardorder + ". " + card_name);
		MainMenu.korPrint(40, card_name);
		System.out.println(" │");

		return null;
	}

}
