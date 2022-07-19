package br.com.projeto.forumrest.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String message;
	private LocalDateTime postDate;
	private ForumUser user;
	private ForumSubject subject;
	private List<Response> responses;
	
	public Topic() {		
	}
	

}
