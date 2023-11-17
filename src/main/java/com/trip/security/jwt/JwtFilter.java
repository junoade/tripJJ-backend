package com.trip.security.jwt;

import com.trip.member.service.MemberService;
import com.trip.security.exception.UnAuthroizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;


// 필터 방식
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    	
        if(!validateAuthroization(authorization, request, response, filterChain)) {
        	return; 
        }
        
        String token = authorization.split(" ")[1];
        
        validateTokenFormat(token, request, response, filterChain);
        validateTokenExpiration(token, request, response, filterChain);
        
        // Token body에서 username 꺼내기
        String userName = "";

        // 유효한 권한 유저 관련 정보 설정
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));

        // Detail
        // request 안에 인증되었다는 것 확인해서 전달
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
    
    private boolean validateAuthroization(String authorization, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	log.debug("authorization : {}", authorization);

        if(authorization == null || !authorization.startsWith("Bearer")) {
            log.error("authorization is not valid");
            filterChain.doFilter(request, response);
            return false;
        }
        
        return true;
    }
    
    private void validateTokenFormat(String token, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	if(!jwtUtil.checkTokenFormat(token)) {
    		throw new UnAuthroizedException();
    	}
    }
    
    private void validateTokenExpiration(String token, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug(token);
        
        // Token Expired 여부 확인
        if(jwtUtil.isExpired(token)) {
        	log.error("authorization has been expired");
        	throw new UnAuthroizedException();
        }
    }
  
}
