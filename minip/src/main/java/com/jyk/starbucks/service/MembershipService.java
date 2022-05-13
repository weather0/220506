package com.jyk.starbucks.service;

import com.jyk.starbucks.vo.MembershipInfo;

public interface MembershipService {
	int signUp(MembershipInfo vo);
	int signIn(MembershipInfo vo);
	int signOut(MembershipInfo vo);
	MembershipInfo memberView(MembershipInfo vo);
	int memberUpdate(MembershipInfo vo);
	int memberDelete(MembershipInfo vo);
	

}
