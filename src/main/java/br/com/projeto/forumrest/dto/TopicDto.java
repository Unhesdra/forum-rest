package br.com.projeto.forumrest.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.projeto.forumrest.model.Topic;

public class TopicDto {
	
	private String title;
	private String message;
	private LocalDateTime postDate;
	private String username;
	private String subject;
	private List<String> messages;
	
	public TopicDto(Topic topic) {
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.postDate = topic.getPostDate();
		this.username = topic.getUser().getUsername();
		this.subject = topic.getSubject().getSubject();
		this.messages = topic
				.getResponses()
				.stream()
				.map(response -> response.getMessage())
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

	public List<String> getMessages() {
		return messages;
	}

}
