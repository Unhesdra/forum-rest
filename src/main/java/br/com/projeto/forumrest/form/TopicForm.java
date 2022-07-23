package br.com.projeto.forumrest.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicForm {
	
	@NotEmpty
	@NotNull
	private String title;
	@NotEmpty
	@NotNull
	private String message;
	@NotEmpty
	@NotNull
	private String subject;
	
	public TopicForm(String title, String message, String subject) {
		this.title = title;
		this.message = message;
		this.subject = subject;
	}
	
	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getSubject() {
		return subject;
	}

}
