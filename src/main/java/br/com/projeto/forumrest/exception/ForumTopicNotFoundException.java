package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class ForumTopicNotFoundException extends RuntimeException {
	
	public ForumTopicNotFoundException() {
	}
	
	public ForumTopicNotFoundException(String message) {
		super(message);
	}

}
