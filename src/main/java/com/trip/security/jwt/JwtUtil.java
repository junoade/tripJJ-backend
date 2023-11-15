package com.trip.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import com.trip.security.exception.UnAuthroizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token.expirationMillis}")
    private Long accessTokenExpirationMillis;

    @Value("${jwt.refresh-token.expirationMillis}")
    private Long refreshTokenExpirationMillis;

    public String createAccessToken(String userId) {
        return createJwt(userId, "access-token", accessTokenExpirationMillis);
    }

	public String createRefreshToken(String userId) {
		return createJwt(userId, "refresh-token", refreshTokenExpirationMillis);
	}


	/**
	 * Generate JWT
	 * jwt 토큰의 구성 : header(alg, typ) + payload(data) + signature(시크릿키)
	 * @param userId 사용자 ID
	 * @param subject payload에 sub의 value로 들어갈 subject값
	 * @param expiredMillis 토큰 유효기간 설정을 위한 값
	 * @return
	 */
    private String createJwt(String userId, String subject, Long expiredMillis) {
		// Payload 설정
        Claims claims = Jwts.claims();
		// 생성일 (IssuedAt), 유효기간 (Expiration), 토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅
        claims.setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredMillis));

		// 저장할 data의 key, value
        claims.put("userId", userId);

		// JWT의 HEADER, PAYLOAD, SIGNATURE 설정
        return Jwts.builder() // io.jsonwebtoken.impl 필요
				.setHeaderParam("typ","JWT")
                .setClaims(claims)
                // .signWith(SignatureAlgorithm.HS256,secretKey) // deprecated
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact(); // 직렬화 처리
    }

    // 하이쌤의 경우 직접 byte[] 배열로 key 생성
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * 전달 받은 토큰이 제대로 생성된것인지 확인 하고 문제가 있다면 UnauthorizedException을 발생.
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        // JWS(Json Web Signature)를 통해 전달받은 JWT의 서명부 확인; 서버의 private key로 서명했나?
        // Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
        // Jws<Claims> claims = Jwts.parser().setSigningKey(key()).parseClaimsJws(token);
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .requireAudience("string")
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);
            log.debug("checkToken called; claims: {}", claims);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public String getUserId(String authorization) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .requireAudience("string")
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(authorization);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UnAuthroizedException();
        }
        Map<String, Object> value = claims.getBody();
        log.info("value : {}", value);
        return (String) value.get("userId");
    }
}
