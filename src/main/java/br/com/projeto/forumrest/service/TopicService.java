package br.com.projeto.forumrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.DetailedTopicDto;
import br.com.projeto.forumrest.dto.TopicDto;
import br.com.projeto.forumrest.form.TopicForm;
import br.com.projeto.forumrest.model.ForumSubject;
import br.com.projeto.forumrest.model.ForumUser;
import br.com.projeto.forumrest.model.Topic;
import br.com.projeto.forumrest.repository.SubjectRepository;
import br.com.projeto.forumrest.repository.TopicRepository;
import br.com.projeto.forumrest.repository.UserRepository;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<TopicDto> listTopics() {
		List<TopicDto> topicDtoList = topicRepository.findAll()
				.stream()
				.map(topic -> new TopicDto(topic))
				.toList();
		return topicDtoList;
	}
	
	public DetailedTopicDto getSpecificTopic(Long id) {
		Optional<Topic> optionalTopic= topicRepository.findById(id);
		if(optionalTopic.isPresent()) {
			DetailedTopicDto detailedTopicDto = new DetailedTopicDto(optionalTopic.get());
			return detailedTopicDto;
		}
//		Implement custom Exception here
		throw new RuntimeException("Topic cannot be found!");
	}
	
	public TopicDto createTopic(TopicForm topicForm) {
		
		ForumUser user = userRepository.findByUsername("testuser").get();		
		ForumSubject subject = subjectRepository.findBySubject("testsubject").get();
		
		Topic topic =  new Topic(topicForm.getTitle(),
				topicForm.getMessage(),
				user,
				subject);
		
		topicRepository.save(topic);		
		
		return new TopicDto(topic);
	}
	
	public TopicDto deleteTopic(Long id) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);
		if(optionalTopic.isPresent()) {
			TopicDto topicDto = new TopicDto(optionalTopic.get());
			topicRepository.deleteById(id);
			return topicDto;
		}
//		Implement custom Exception here
		throw new RuntimeException("Topic cannot be found!");
	}
	
}
