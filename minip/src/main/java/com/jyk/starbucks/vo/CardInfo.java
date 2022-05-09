package com.jyk.starbucks.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CardInfo {
	private int cardorder;
	private String card_no;
	private String card_name;
	private Date card_regdate;
	private Date card_expdate;
	private int card_in;
	private int card_out;
	private int card_bal;
	private String id;

	public String toString() {
		System.out.println(card_name + " : " + card_bal + " : " + card_no + " : " + card_regdate + " : " + card_expdate);

		return null;
	}

}
