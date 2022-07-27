package br.com.projeto.forumrest.dto;

import java.time.LocalDateTime;

import br.com.projeto.forumrest.model.Response;

public class ResponseDto {
	
	private Long id;
	private String message;
	private LocalDateTime postDate;
	private Boolean solution;
	
	public ResponseDto(Response response) {
		this.id = response.getId();
		this.message = response.getMessage();
		this.postDate = response.getPostDate();
		this.solution = response.getIsSolution();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public Boolean getSolution() {
		return solution;
	}

}
