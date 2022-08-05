package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class ForumAuthorNotFoundException extends RuntimeException {
	
	public ForumAuthorNotFoundException() {
	}
	
	public ForumAuthorNotFoundException(String message) {
		super(message);
	}

}
