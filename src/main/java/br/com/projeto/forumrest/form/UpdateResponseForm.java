package br.com.projeto.forumrest.form;

public class UpdateResponseForm {
	
	private String message;
	private Boolean isSolution;
	
	public UpdateResponseForm(String message, Boolean isSolution) {
		this.message = message;
		this.isSolution = isSolution;
	}

	public String getMessage() {
		return message;
	}

	public Boolean getIsSolution() {
		return isSolution;
	}

}
