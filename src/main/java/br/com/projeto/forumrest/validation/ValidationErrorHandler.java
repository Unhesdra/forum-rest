package br.com.projeto.forumrest.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.projeto.forumrest.exception.ForumResponseNotFoundException;
import br.com.projeto.forumrest.exception.ForumSubjectNotFoundException;
import br.com.projeto.forumrest.exception.ForumTopicNotFoundException;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorDto> errorHandler(MethodArgumentNotValidException exception) {
		List<ErrorDto> errorDtoList = new ArrayList<>();
		
		List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
		
		fieldErrorList.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorDto errorDto = new ErrorDto(e.getField(), message);
			errorDtoList.add(errorDto);
		});
		
		return errorDtoList;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ForumTopicNotFoundException.class)
	public String handle(ForumTopicNotFoundException exception) {
		
		return exception.getMessage();
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ForumSubjectNotFoundException.class)
	public String handle(ForumSubjectNotFoundException exception) {
		
		return exception.getMessage();
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ForumResponseNotFoundException.class)
	public String handle(ForumResponseNotFoundException exception) {
		
		return exception.getMessage();
	}

}
