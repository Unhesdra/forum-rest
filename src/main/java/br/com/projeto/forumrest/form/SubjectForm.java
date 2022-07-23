package br.com.projeto.forumrest.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubjectForm {
	
	@NotEmpty
	@NotNull
	private String subject;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
