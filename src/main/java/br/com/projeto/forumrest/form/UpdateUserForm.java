package br.com.projeto.forumrest.form;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateUserForm {
	
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;
	@NotNull
	@NotEmpty
	private Set<Long> userProfileIdSet = new HashSet<>();
	
	public UpdateUserForm(String email, String password, Set<Long> userProfileSet) {
		this.email = email;
		this.password = password;
		this.userProfileIdSet = userProfileSet;
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
