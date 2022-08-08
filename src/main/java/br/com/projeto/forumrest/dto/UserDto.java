package br.com.projeto.forumrest.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.projeto.forumrest.entity.ForumUser;

public class UserDto {
	
	private Long id;
	private String username;
	private String email;
	private Boolean isEnabled;
	private Set<String> userProfileList;
	
	public UserDto(ForumUser user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.isEnabled = user.isEnabled();
		this.userProfileList = user.getAuthorities()
				.stream()
				.map(profile -> profile.getAuthority())
				.collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public Set<String> getUserProfileList() {
		return userProfileList;
	}

}
