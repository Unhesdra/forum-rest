package br.com.projeto.forumrest.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ResponseForm {
	
	@NotNull
	@NotEmpty
	private String message;
	@NotNull
	private Long topicId;
	
	public ResponseForm(String message, Long topicId) {
		this.message = message;
		this.topicId = topicId;
	}

	public String getMessage() {
		return message;
	}

	public Long getTopicId() {
		return topicId;
	}

}
