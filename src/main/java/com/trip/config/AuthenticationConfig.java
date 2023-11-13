package com.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig {

	private static final String AUTH_URL_LOGIN = "/v1/auth/login";
	private static final String MEMBER_URL_JOIN = "";
	
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
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					// .addFilterBefore(new JwtTokenFilter(userService, secretKey),
				.build();
				
	}
}
