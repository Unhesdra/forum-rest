package br.com.projeto.forumrest.dto;

import java.time.LocalDateTime;

import br.com.projeto.forumrest.model.Response;

public class ResponseDto {
	
	private String message;
	private LocalDateTime postDate;
	private Boolean solution;
	
	public ResponseDto(Response response) {
		this.message = response.getMessage();
		this.postDate = response.getPostDate();
		this.solution = response.getSolution();
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
