package br.com.projeto.forumrest.form;

public class UpdateForm {
	
	private String title;
	private String message;
	private String subject;
	
	public UpdateForm(String title, String message, String subject) {
		this.title = title;
		this.message = message;
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getSubject() {
		return subject;
	}	

}
