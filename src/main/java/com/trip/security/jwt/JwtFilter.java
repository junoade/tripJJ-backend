package com.trip.security.jwt;

import com.trip.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// 필터 방식
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private MemberService memberService;

    private JwtUtil util;

    private String jwtSecret;

    @Autowired
    public JwtFilter(MemberService memberService, JwtUtil util) {
        this.memberService = memberService;
        this.util = util;
    }

    public JwtFilter(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        if(authorization == null || !authorization.startsWith("Bearer")) {
            log.error("authorization is not valid");
            filterChain.doFilter(request, response);
            return;
        }

        // Token에서 username 꺼내기
        String token = authorization.split(" ")[1];

        // Token Expired 여부 확인
        /*if(!util.checkToken(token)) {
            log.error("token is not valid");
            filterChain.doFilter(request, response);
            return;
        }*/

        String userName = "";

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));

        // Detail
        // request 안에 인증되었다는 것 확인해서 전달
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
