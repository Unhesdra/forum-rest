package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class UsernameAlreadyExistsException extends RuntimeException {
	
	public UsernameAlreadyExistsException() {
	}
	
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

}
