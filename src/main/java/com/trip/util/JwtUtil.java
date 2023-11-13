package com.trip.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	/**
	 * Generate JWT 
	 * @param userId
	 * @param secretKey; JWT에서 서명하는데 사용 
	 * @param expiredMs; millis
	 * @return
	 */
	public static String createJwt(String userId, Long expiredMs) {
		Claims claims = Jwts.claims();
		claims.put("userId", userId);
		return Jwts.builder()
				.setClaims(claims)
				// 발행 일자 
				 // 서버 Locale 정보 주의해야겠는데?
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
				// .signWith(SignatureAlgorithm.HS256,secretKey) // deprecated
				.signWith(key)
				.compact();
	}
}
