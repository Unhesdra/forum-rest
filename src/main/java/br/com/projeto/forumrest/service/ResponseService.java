package br.com.projeto.forumrest.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.ResponseDto;
import br.com.projeto.forumrest.entity.ForumUser;
import br.com.projeto.forumrest.entity.Response;
import br.com.projeto.forumrest.entity.Topic;
import br.com.projeto.forumrest.exception.DuplicatedForumResponseException;
import br.com.projeto.forumrest.exception.ForumAuthorNotFoundException;
import br.com.projeto.forumrest.exception.ForumResponseNotFoundException;
import br.com.projeto.forumrest.exception.ForumTopicNotFoundException;
import br.com.projeto.forumrest.exception.LoggedUserIsNotAuthorException;
import br.com.projeto.forumrest.form.ResponseForm;
import br.com.projeto.forumrest.repository.ResponseRepository;
import br.com.projeto.forumrest.repository.TopicRepository;
import br.com.projeto.forumrest.repository.UserRepository;

@Service
public class ResponseService {
	
	@Autowired
	private ResponseRepository responseRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;

	public List<ResponseDto> getResponseList(Long topicId) {
		List<Response> responseList = responseRepository.findAllTopicResponses(topicId);
		List<ResponseDto> responseDtoList = responseList
				.stream()
				.map(response -> new ResponseDto(response))
				.toList();
		
		return responseDtoList;
	}

	public ResponseDto getSpecificResponse(Long id) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isPresent()) {
			ResponseDto responseDto = new ResponseDto(optionalResponse.get());
			return responseDto;
		}
		throw new ForumResponseNotFoundException("Response cannot be found!");
	}

	public ResponseDto createResponse(ResponseForm responseForm, Principal principal) {
		Optional<Topic> optionalTopic = topicRepository.findById(responseForm.getTopicId());
		if(optionalTopic.isEmpty()) {
			throw new ForumTopicNotFoundException("Topic cannot be found!");
		}
		
		Optional<ForumUser> optionalUser = userRepository.findByUsername(principal.getName());
		if(optionalUser.isEmpty()) {
			throw new ForumAuthorNotFoundException("Author cannot be found!");
		}
		
		Topic topic = optionalTopic.get();
		
		topic
		.getResponses()
		.stream()
		.forEach(r -> {
			if(r.getMessage().equalsIgnoreCase(responseForm.getMessage())) {
				throw new DuplicatedForumResponseException("This response already exists for this topic!");
			}			
		});
		
		ForumUser forumUser = optionalUser.get();
		
		Response response = new Response(responseForm.getMessage(), topic, forumUser);
		responseRepository.save(response);
		return new ResponseDto(response);
	}

	@Transactional
	public ResponseDto updateResponseStatus(Long id, Boolean isSolution, Principal principal) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isEmpty()) {
			throw new ForumResponseNotFoundException("Response cannot be found!");
		}
		
		Response response = optionalResponse.get();
		checkIfLoggedUserIsAuthor(principal, response);
		
		response.setIsSolution(isSolution);
		return new ResponseDto(response);
	}
	
	@Transactional
	public ResponseDto updateResponseMessage(Long id, ResponseForm responseForm, Principal principal) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isEmpty()) {
			throw new ForumResponseNotFoundException("Response cannot be found!");
		}

		Response response = optionalResponse.get();
		checkIfLoggedUserIsAuthor(principal, response);
		
		String newMessage = responseForm.getMessage();					
		if(newMessage != null && !newMessage.isBlank()) {
			response.setMessage(newMessage);
		}
		return new ResponseDto(response);
	}

	public ResponseDto deteleResponse(Long id, Principal principal) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isEmpty()) {
			throw new ForumResponseNotFoundException("Response cannot be found!");
		}
		
		Response response = optionalResponse.get();
		checkIfLoggedUserIsAuthor(principal, response);
		
		ResponseDto responseDto = new ResponseDto(response);
		responseRepository.delete(response);
		return responseDto;
	}	

	private void checkIfLoggedUserIsAuthor(Principal principal, Response response) {
		String loggedUsername = response.getTopic().getAuthor().getUsername(); 
		if(!loggedUsername.equals(principal.getName())) {
			throw new LoggedUserIsNotAuthorException("Cannot proceed, because logged user is not the topic's author!");
		}
	}
	
}
