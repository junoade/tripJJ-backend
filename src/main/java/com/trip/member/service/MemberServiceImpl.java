package com.trip.member.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.trip.member.MemberDto;
import com.trip.member.dao.MemberDao;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public int registerMember(MemberDto member) {
		member.setUserPass(BCrypt.hashpw(member.getUserPass(), BCrypt.gensalt()));
		return memberDao.registerMember(member);
	}

	@Override
	public MemberDto login(String userId, String userPass) throws Exception {
		MemberDto member = memberDao.login(userId);
		return member!=null && authUser(userPass, member.getUserPass()) ? member : null;
	}

//	동일한 ID에 대하여, DB내 암호화된 비밀번호와 로그인시 입력한 플레인 비밀번호 비교
	@Override
	public boolean authUser(String userPass, String encrypted) throws Exception {
		return BCrypt.checkpw(userPass, encrypted);
	}

	@Override
	public int modifyMember(MemberDto member) {
		return memberDao.modifyMember(member);
	}

	@Override
	public int deleteMember(String userId) {
		return memberDao.deleteMember(userId);
	}

}
