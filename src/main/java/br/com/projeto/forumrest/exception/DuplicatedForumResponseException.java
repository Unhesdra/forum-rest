package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class DuplicatedForumResponseException extends RuntimeException {
	
	public DuplicatedForumResponseException() {
	}
	
	public DuplicatedForumResponseException(String message) {
		super(message);
	}

}
