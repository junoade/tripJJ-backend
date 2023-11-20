package com.trip.member.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import com.trip.member.MemberDto;

import javax.swing.text.html.Option;

@Mapper
public interface MemberDao {
	
	int registerMember(MemberDto member);	
	MemberDto login(String userId);
	int modifyMember(MemberDto member);
	int deleteMember(String userId);

	Optional<MemberDto> selectUser(String userId) throws SQLException;
	
	int saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userId) throws SQLException;
	int deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
