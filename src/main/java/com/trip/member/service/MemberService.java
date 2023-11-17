package com.trip.member.service;

import java.sql.SQLException;
import java.util.Map;

import com.trip.member.MemberDto;

public interface MemberService {
	
	int registerMember(MemberDto member);	
	
	MemberDto login(String userId, String userPass) throws Exception;
	boolean authUser(String userPass, String encrypted) throws Exception;
	
	int modifyMember(MemberDto member);
	int deleteMember(String userId);

	int saveRefreshToken(String userId, String refreshToken) throws SQLException;
	Object getRefreshToken(String userId) throws SQLException;
	int deleteRefreshToken(String userId) throws SQLException;
}
