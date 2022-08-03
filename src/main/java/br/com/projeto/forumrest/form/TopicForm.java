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
	@NotNull
	private Long subjectId;
	
	public TopicForm(String title, String message, Long subjectId) {
		this.title = title;
		this.message = message;
		this.subjectId = subjectId;
	}
	
	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public Long getSubject() {
		return subjectId;
	}

}
