package br.com.projeto.forumrest.dto;

import br.com.projeto.forumrest.model.ForumSubject;

public class SubjectDto {

	private Long id;
	private String subject;
	
	public SubjectDto(ForumSubject subject) {
		this.id = subject.getId();
		this.subject = subject.getSubject();
	}

	public Long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

}
