package com.jyk.starbucks.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class MembershipInfo {
	private String id;
	private String password;
	private String contact;
	private Timestamp signupdate;
	private String tier;
	private int star;
	private int coupon;
	private Timestamp signinthis;
	private Timestamp signinlast;
	private int signinwho;

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (E) HH:mm:ss");
		
		System.out.println(id + " : " + password + " : " + contact + " : " + tier + " : " + sdf.format(signupdate) + " : " + star + " : " + coupon + " : " + sdf.format(signinthis) + " : " + sdf.format(signinlast));

		return null;
	}

}
