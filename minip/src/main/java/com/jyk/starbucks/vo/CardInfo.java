package com.jyk.starbucks.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class CardInfo {
	int cardorder; // 고유필드
	String card_no;
	String card_name;
	Timestamp card_regdate;
	Timestamp card_expdate;
	int card_in;
	int card_out;
	int card_bal;
	String id;

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.print("│ " + cardorder + ". " + card_name);
		int k = 40 - card_name.replaceAll("[ㄱ-ㅎㅏ-ㅣ가-힣]", "  ").length();
		for (int i = 0; i < k; i++) {
			System.out.print(" ");
		}
		System.out.printf("│ %6d │ %s │", card_bal,
				card_no + " │ " + sdf.format(card_regdate) + " │ " + sdf.format(card_expdate));
		System.out.println();

		return null;
	}

}
