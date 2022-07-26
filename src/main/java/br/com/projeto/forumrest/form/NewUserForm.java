package br.com.projeto.forumrest.form;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewUserForm {

	@NotNull
	@NotEmpty
	private String username;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;
	@NotNull
	@NotEmpty
	private Set<Long> userProfileIdSet = new HashSet<>();
	
	public NewUserForm(String username, String email, String password, Set<Long> userProfileIdSet) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.userProfileIdSet = userProfileIdSet;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public Set<Long> getUserProfileIdSet() {
		return userProfileIdSet;
	}
	
}
