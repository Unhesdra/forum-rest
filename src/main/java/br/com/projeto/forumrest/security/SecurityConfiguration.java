package br.com.projeto.forumrest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.projeto.forumrest.jwt.JwtConfig;
import br.com.projeto.forumrest.jwt.JwtTokenVerifierFilter;
import br.com.projeto.forumrest.jwt.JwtUsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig))
		.addFilterAfter(new JwtTokenVerifierFilter(jwtConfig), JwtUsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topic/**").permitAll()
		.antMatchers(HttpMethod.GET, "/subject/**").permitAll()
		.antMatchers(HttpMethod.GET, "/response/**").permitAll()
		.antMatchers("/subject/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("ADMIN")
		.anyRequest().authenticated();
		
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		provider.setUserDetailsService(authenticationService);
		return new ProviderManager(provider);
	}
	

}
