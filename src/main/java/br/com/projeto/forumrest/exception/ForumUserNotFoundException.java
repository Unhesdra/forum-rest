package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class ForumUserNotFoundException extends RuntimeException {
	
	public ForumUserNotFoundException() {
	}
	
	public ForumUserNotFoundException(String message) {
		super(message);
	}

}
