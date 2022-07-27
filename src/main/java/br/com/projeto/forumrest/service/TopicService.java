package br.com.projeto.forumrest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.DetailedTopicDto;
import br.com.projeto.forumrest.dto.TopicDto;
import br.com.projeto.forumrest.entity.ForumSubject;
import br.com.projeto.forumrest.entity.ForumUser;
import br.com.projeto.forumrest.entity.Topic;
import br.com.projeto.forumrest.exception.ForumSubjectNotFoundException;
import br.com.projeto.forumrest.exception.ForumTopicNotFoundException;
import br.com.projeto.forumrest.form.TopicForm;
import br.com.projeto.forumrest.form.UpdateTopicForm;
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
		throw new ForumTopicNotFoundException("Topic cannot be found!");
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
	
	@Transactional
	public TopicDto updateTopic(Long id, UpdateTopicForm updateForm) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);
		if(optionalTopic.isPresent()) {
			Optional<ForumSubject> optionalSubject = subjectRepository.findBySubject(updateForm.getSubject());
			if(optionalSubject.isPresent()) {
				Topic topic = optionalTopic.get();
				topic.setTitle(updateForm.getTitle());
				topic.setMessage(updateForm.getMessage());
				topic.setSubject(optionalSubject.get());
				TopicDto topicDto = new TopicDto(topic);
				return topicDto;
			}
//			Implement custom Exception here
			throw new ForumSubjectNotFoundException("Subject cannot be found!");
		}
		throw new ForumTopicNotFoundException("Topic cannot be found!");
	}
	
	public TopicDto deleteTopic(Long id) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);
		if(optionalTopic.isPresent()) {
			TopicDto topicDto = new TopicDto(optionalTopic.get());
			topicRepository.deleteById(id);
			return topicDto;
		}
		throw new ForumTopicNotFoundException("Topic cannot be found!");
	}

	
}
