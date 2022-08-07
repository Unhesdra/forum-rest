package br.com.projeto.forumrest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.ResponseDto;
import br.com.projeto.forumrest.entity.Response;
import br.com.projeto.forumrest.entity.Topic;
import br.com.projeto.forumrest.exception.ForumResponseNotFoundException;
import br.com.projeto.forumrest.exception.ForumTopicNotFoundException;
import br.com.projeto.forumrest.form.ResponseForm;
import br.com.projeto.forumrest.repository.ResponseRepository;
import br.com.projeto.forumrest.repository.TopicRepository;

@Service
public class ResponseService {
	
	@Autowired
	private ResponseRepository responseRepository;
	
	@Autowired
	private TopicRepository topicRepository;

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

	public ResponseDto createResponse(ResponseForm responseForm) {
		Optional<Topic> optionalTopic = topicRepository.findById(responseForm.getTopicId());
		if(optionalTopic.isEmpty()) {
			throw new ForumTopicNotFoundException("Topic cannot be found!");
		}

		Topic topic = optionalTopic.get();
		Response response = new Response(responseForm.getMessage(), topic, null);
		responseRepository.save(response);
		return new ResponseDto(response);
	}

	@Transactional
	public ResponseDto updateResponseStatus(Long id, Boolean isSolution) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isPresent()) {
			Response response = optionalResponse.get();
			response.setIsSolution(isSolution);
			return new ResponseDto(response);
		}
		throw new ForumResponseNotFoundException("Response cannot be found!");
	}
	
	@Transactional
	public ResponseDto updateResponseMessage(Long id, ResponseForm responseForm) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isEmpty()) {
			throw new ForumResponseNotFoundException("Response cannot be found!");
		}

		Response response = optionalResponse.get();
		String newMessage = responseForm.getMessage();					
		if(newMessage != null && !newMessage.isBlank()) {
			response.setMessage(newMessage);
		}
		return new ResponseDto(response);
	}

	public ResponseDto deteleResponse(Long id) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isEmpty()) {
			throw new ForumResponseNotFoundException("Response cannot be found!");
		}
		
		Response response = optionalResponse.get();
		ResponseDto responseDto = new ResponseDto(response);
		responseRepository.delete(response);
		return responseDto;
	}

}
