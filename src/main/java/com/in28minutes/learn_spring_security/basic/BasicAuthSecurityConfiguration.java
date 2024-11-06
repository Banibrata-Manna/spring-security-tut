package com.in28minutes.learn_spring_security.basic;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> {
					requests.anyRequest().authenticated();
					});
		http.sessionManagement(
					session -> 
						session.sessionCreationPolicy(
								SessionCreationPolicy.STATELESS)
				);
//		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		http.csrf().disable();
		return http.build();
	}
	@Bean
	public UserDetailsService userDeatilService () {
		var user = User.withUsername("Banibrata")
			.password("{noop}dummy")
			.roles("USER")
			.build();
		
		var admin = User.withUsername("Santanu")
				.password("{noop}sai")
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
}
