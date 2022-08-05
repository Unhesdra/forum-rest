package br.com.projeto.forumrest.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.projeto.forumrest.entity.Topic;

public class DetailedTopicDto {
	
	private String title;
	private String message;
	private LocalDateTime postDate;
	private String username;
	private String subject;
	private List<ResponseDto> responses; 
	
	public DetailedTopicDto(Topic topic) {
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.postDate = topic.getPostDate();
		this.username = topic.getAuthor().getUsername();
		this.subject = topic.getSubject().getSubject();
		this.responses = topic.getResponses()
				.stream()
				.map(response -> new ResponseDto(response))
				.toList();
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public String getUsername() {
		return username;
	}

	public String getSubject() {
		return subject;
	}

	public List<ResponseDto> getResponses() {
		return responses;
	}

}
