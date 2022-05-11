package com.jyk.starbucks.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CardInfo {
	private int cardorder;
	private String card_no;
	private String card_name;
	private Timestamp card_regdate;
	private Timestamp card_expdate;
	private int card_in;
	private int card_out;
	private int card_bal;
	private String id;

	public String toString() {
		System.out.println(cardorder + ". " + card_name + " : " + card_bal + " : " + card_no + " : " + card_regdate + " : " + card_expdate);

		return null;
	}

}
