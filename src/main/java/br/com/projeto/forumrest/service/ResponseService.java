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
import br.com.projeto.forumrest.form.UpdateResponseForm;
import br.com.projeto.forumrest.repository.ResponseRepository;
import br.com.projeto.forumrest.repository.TopicRepository;

@Service
public class ResponseService {
	
	@Autowired
	private ResponseRepository responseRepository;
	
	@Autowired
	private TopicRepository topicRepository;

	public List<ResponseDto> getResponseList() {
		List<Response> responseList = responseRepository.findAll();
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
		if(optionalTopic.isPresent()) {
			Topic topic = optionalTopic.get();
			Response response = new Response(responseForm.getMessage(), topic);
			responseRepository.save(response);
			return new ResponseDto(response);
		}
		throw new ForumTopicNotFoundException("Topic cannot be found!");
	}

	@Transactional
	public ResponseDto updateResponseStatus(Long id, UpdateResponseForm updateForm) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isPresent()) {
			Response response = optionalResponse.get();
			response.setIsSolution(updateForm.getIsSolution());
			String newMessage = updateForm.getMessage();
			if(newMessage != null && !newMessage.isBlank()) {
				response.setMessage(updateForm.getMessage());
			}
			return new ResponseDto(response);
		}
		throw new ForumResponseNotFoundException("Response cannot be found!");
	}

	public ResponseDto deteleResponse(Long id) {
		Optional<Response> optionalResponse = responseRepository.findById(id);
		if(optionalResponse.isPresent()) {
			Response response = optionalResponse.get();
			ResponseDto responseDto = new ResponseDto(response);
			responseRepository.delete(response);
			return responseDto;
		}
		throw new ForumResponseNotFoundException("Response cannot be found!");
	}

}
