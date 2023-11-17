package com.trip.member.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.trip.member.MemberDto;

@Mapper
public interface MemberDao {
	
	int registerMember(MemberDto member);	
	MemberDto login(String userId);
	int modifyMember(MemberDto member);
	int deleteMember(String userId);
	
	int saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userId) throws SQLException;
	int deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
