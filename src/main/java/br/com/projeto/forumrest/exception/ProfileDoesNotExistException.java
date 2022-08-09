package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class ProfileDoesNotExistException extends RuntimeException {
	
	public ProfileDoesNotExistException() {
	}
	
	public ProfileDoesNotExistException(String message) {
		super(message);
	}

}
