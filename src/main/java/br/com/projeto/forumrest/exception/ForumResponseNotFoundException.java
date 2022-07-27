package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class ForumResponseNotFoundException extends RuntimeException {
	
	public ForumResponseNotFoundException() {
	}
	
	public ForumResponseNotFoundException(String message) {
		super(message);
	}

}
