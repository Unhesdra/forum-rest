package br.com.projeto.forumrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.forumrest.dto.DetailedTopicDto;
import br.com.projeto.forumrest.dto.TopicDto;
import br.com.projeto.forumrest.form.TopicForm;
import br.com.projeto.forumrest.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	public TopicService topicService;
	
	@GetMapping("/listTopics")
	public ResponseEntity<List<TopicDto>> listTopics() {
		List<TopicDto> topicDtoList = topicService.listTopics();
		return ResponseEntity.ok(topicDtoList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailedTopicDto> getSpecificTopic(@PathVariable Long id) {
		DetailedTopicDto detailedTopicDto = topicService.getSpecificTopic(id);
		return ResponseEntity.ok(detailedTopicDto);
	}
	
	@PostMapping("/createTopic")
	public ResponseEntity<TopicDto> createTopic(@RequestBody @Valid TopicForm topicForm) {
		TopicDto topicDto = topicService.createTopic(topicForm);
		return ResponseEntity.ok(topicDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TopicDto> deleteTopic(@PathVariable Long id) {
		TopicDto topicDto = topicService.deleteTopic(id);
		return ResponseEntity.ok(topicDto);
	}

}
