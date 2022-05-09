package com.jyk.starbucks.vo;

import java.util.Date;

import lombok.Data;
@Data
public class CardInfo {
	private String card_no;
	private String card_name;
	private Date card_regdate;
	private Date card_expdate;
	private int card_in;
	private int card_out;
	private int card_bal;
	private String id;
	
	
	public String toString() {
		System.out.println(id + " : " + card_no + " : " + card_name + " : " + card_regdate + " : " + card_expdate + " : " + card_bal);

		return null;
	}
	
	
	
//	public static void myAccount() {
//		
//	}

}
