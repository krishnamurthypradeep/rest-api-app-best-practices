package com.myapp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	
	 @Bean
	   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .csrf(AbstractHttpConfigurer::disable)
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authorizeHttpRequests(authz -> authz
	                        .requestMatchers("/login").permitAll()
	                        .anyRequest().authenticated()
	                )
	                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
	        return http.build();
	    }
	
//	@Bean
//	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//		return http.authorizeExchange(exchange -> exchange.anyExchange().authenticated())
//				.oauth2Login(Customizer.withDefaults()).build();
//	}
}

// Spring Security 
// Refactor Our application to delegate user authentication to Keycloak via OIDC Protocol
// Support For OAuth2 Spring Security oAuth2 OIDC/OAuth2
// Using Spring security 
//1. Use OpenIDConnect to authenticate users
// 2. Extract The information about the authenticates User
