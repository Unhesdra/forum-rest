package br.com.projeto.forumrest.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {
	
	private final JwtConfig jwtConfig;
	
	public JwtTokenVerifierFilter(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
		
		if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authorizationHeader.replaceAll(jwtConfig.getTokenPrefix(), "");
		
		try {
			String key = jwtConfig.getSecretKey();
			
			Jws<Claims> claimsJws = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token);
			
			Claims body = claimsJws.getBody();
			
			String username = body.getSubject();
			@SuppressWarnings("unchecked")
			var authorities = (List<Map<String, String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
					.map(m -> new SimpleGrantedAuthority(m.get("authority")))
					.collect(Collectors.toSet());
			
			Authentication athentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(athentication );
		}
		catch(JwtException e) {
			throw new IllegalStateException(String.format("Token %s cannot be trusted!", token));
		}
		
		filterChain.doFilter(request, response);
	}

}
