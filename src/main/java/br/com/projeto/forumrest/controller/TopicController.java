package br.com.projeto.forumrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.forumrest.dto.TopicDto;
import br.com.projeto.forumrest.form.TopicForm;
import br.com.projeto.forumrest.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	public TopicService topicService;
	
	@PostMapping("/createTopic")
	public ResponseEntity<TopicDto> createTopic(TopicForm topicForm) {
		TopicDto topicDto = topicService.createTopic(topicForm);
		return ResponseEntity.ok(topicDto);
	}

}
