package br.com.projeto.forumrest.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	@ManyToOne
	private Topic topic;
	private LocalDateTime postDate = LocalDateTime.now();
	private Boolean solution = false;
	
	public Response() {
	}
	
	public Response(String message, Topic topic) {
		this.message = message;
		this.topic = topic;
	}
	
	public Response (String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSolution() {
		return solution;
	}

	public void setSolution(Boolean solution) {
		this.solution = solution;
	}

	public Long getId() {
		return id;
	}

	public Topic getTopic() {
		return topic;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}
	
}
