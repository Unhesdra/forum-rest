package br.com.projeto.forumrest.exception;

@SuppressWarnings("serial")
public class ForumSubjectNotFoundException extends RuntimeException {
	
	public ForumSubjectNotFoundException() {
	}
	
	public ForumSubjectNotFoundException(String message) {
		super(message);
	}

}
