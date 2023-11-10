package com.trip.member.dao;

import org.apache.ibatis.annotations.Mapper;
import com.trip.member.MemberDto;

@Mapper
public interface MemberDao {
	
	int registerMember(MemberDto member);	
	MemberDto login(String userId);
	int modifyMember(MemberDto member);
	int deleteMember(String userId);
	
}
