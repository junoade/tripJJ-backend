package com.trip.security;

import com.trip.member.service.MemberService;
import com.trip.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // 생성자 하나, Autowired 생략 가능
public class AuthenticationConfig {

	private static final String AUTH_URL_LOGIN = "/v1/auth/login";
	private static final String MEMBER_URL_JOIN = "";

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// httpSecurity 설정 start
		// httpBasic().disable()
		// csrf().disable()
		// cors()
		// .and()

		return httpSecurity
				.httpBasic().disable()
				.csrf().disable()
				.cors().and()
				.authorizeRequests()
				.antMatchers(AUTH_URL_LOGIN).permitAll()
				// .antMatchers("/").permitAll()
				// .antMatchers(HttpMethod.GET, )
				.antMatchers("/qna/**").authenticated()
				.and()
				// session 관련
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(new JwtFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
}
