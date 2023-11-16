package com.trip.practice.member;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.trip.practice.qna.repo.BoardQnaDao2Test;
import com.trip.security.jwt.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthMemberTest {

	private JwtUtil jwtUtil = new JwtUtil();
		
//	@Test
//	void testJwt() {
//		String userId = "test";
//		assertNotEquals(null, jwtUtil.createAccessToken(userId));
//	}
	
	@Test
	void testJwtValidate() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJpYXQiOjE3MDAxMDQxMzgsImV4cCI6MTcwMDEwNzczOCwidXNlcklkIjoidGVzdCJ9.gPH0drbvGT5ES99R2DuU0f2KS-S5CczGrYghxhq5Fnk";
		boolean result = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode("=============doublejj=security=code=============")))                
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
		
		log.debug(String.valueOf(result));
	}
}
