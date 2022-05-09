package com.jyk.starbucks.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MembershipInfo {
	private String id;
	private String password;
	private String contact;
	private Date signupdate;
	private String tier;
	private int star;
	private int coupon;
	private Date signinthis;
	private Date signinlast;
	private int signinwho;

	public String toString() {
		System.out.println(id + " : " + password + " : " + contact + " : " + tier + " : " + signupdate + " : " + star + " : " + coupon + " : " + signinthis + " : " + signinlast);

		return null;
	}

}
