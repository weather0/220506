package com.jyk.starbucks.service;

import com.jyk.starbucks.vo.MembershipInfo;

public interface MembershipService {
	MembershipInfo memberView(MembershipInfo vo);
	int memberInsert(MembershipInfo vo);
	int memberUpdate(MembershipInfo vo);
	int memberDelete(MembershipInfo vo);
	

}
