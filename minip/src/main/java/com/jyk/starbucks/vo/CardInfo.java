package com.jyk.starbucks.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


		System.out.printf("│ " + cardorder + ". " + "%-38s│%8d│ %40s│", card_name, card_bal,
				card_no + " │ " + sdf.format(card_regdate) + " │ " + sdf.format(card_expdate));
		System.out.println();

		return null;
	}

}
