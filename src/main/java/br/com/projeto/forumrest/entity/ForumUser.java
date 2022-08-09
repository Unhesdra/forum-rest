package br.com.projeto.forumrest.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
public class ForumUser implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	private Boolean isAccountNonExpired;
	private Boolean isAccountNonLocked;
	private Boolean isCredentialNonExpired;
	private Boolean isEnabled;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ForumUserProfile> userProfileSet = new HashSet<>();
	
	public ForumUser() {		
	}
	
	public ForumUser (String username, String email, String password, Set<ForumUserProfile> userProfileSet) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.userProfileSet = userProfileSet;
		this.isAccountNonExpired = true;
		this.isAccountNonLocked = true;
		this.isCredentialNonExpired = true;
		this.isEnabled = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	
	public void clearUserProfileSet() {
		userProfileSet.clear();
	}
	
	public void addUserProfile(ForumUserProfile forumUserProfile) {
		userProfileSet.add(forumUserProfile);
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userProfileSet;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
