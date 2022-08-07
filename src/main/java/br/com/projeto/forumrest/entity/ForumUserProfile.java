package br.com.projeto.forumrest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
public class ForumUserProfile implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authority;
	
	public ForumUserProfile() {
	}
	
	public ForumUserProfile(String authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getAuthority() {
		return "ROLE_" + authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	

}
