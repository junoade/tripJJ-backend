package com.trip.member.service;

import com.trip.member.MemberDto;

public interface MemberService {
	
	int registerMember(MemberDto member);	
	
	MemberDto login(String userId, String userPass) throws Exception;
	boolean authUser(String userPass, String encrypted) throws Exception;
	
	int modifyMember(MemberDto member);
	int deleteMember(String userId);

}
