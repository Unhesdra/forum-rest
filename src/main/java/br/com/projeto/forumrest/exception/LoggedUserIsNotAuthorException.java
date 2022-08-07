package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class LoggedUserIsNotAuthorException extends RuntimeException {
	
	public LoggedUserIsNotAuthorException() {
	}
	
	public LoggedUserIsNotAuthorException(String message) {
		super(message);
	}

}
