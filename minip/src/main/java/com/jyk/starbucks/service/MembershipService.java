package com.jyk.starbucks.service;

import com.jyk.starbucks.vo.MembershipInfo;

public interface MembershipService {
	int signUp(MembershipInfo vo);
	int signIn(MembershipInfo vo);
	int signOut(MembershipInfo vo);
	String[] findID(String vo1, String vo2);
	String[] findPW(String pw);
	MembershipInfo memberView(MembershipInfo vo);
	int memberUpdate(MembershipInfo vo, String mod);
	int memberDelete(MembershipInfo vo);
	

}
