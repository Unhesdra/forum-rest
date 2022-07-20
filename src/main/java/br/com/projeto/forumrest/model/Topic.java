package br.com.projeto.forumrest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String message;
	private LocalDateTime postDate = LocalDateTime.now();
	@ManyToOne
	private ForumUser user;
	@ManyToOne
	private ForumSubject subject;
	@OneToMany(mappedBy = "topic")
	private List<Response> responses = new ArrayList<>();;
	@Enumerated(EnumType.STRING)
	private TopicStatus topicStatus = TopicStatus.NEW;
	
	public Topic() {		
	}
	
	public Topic(String title, String message, ForumUser user, ForumSubject subject) {
		this.title = title;
		this.message = message;
		this.user = user;
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ForumSubject getSubject() {
		return subject;
	}

	public void setSubject(ForumSubject subject) {
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public ForumUser getUser() {
		return user;
	}

	public List<Response> getResponses() {
		return responses;
	}
	
	public void addResponse(Response response) {
		this.responses.add(response);
	}

}
