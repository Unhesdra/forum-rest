package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class SubjectAlreadyExistsException extends RuntimeException {
	
	public SubjectAlreadyExistsException() {
	}
	
	public SubjectAlreadyExistsException(String message) {
		super(message);
	}

}
