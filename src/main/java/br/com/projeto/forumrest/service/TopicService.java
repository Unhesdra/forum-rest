package br.com.projeto.forumrest.service;

import java.security.Principal;
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
import br.com.projeto.forumrest.exception.ForumAuthorNotFoundException;
import br.com.projeto.forumrest.exception.ForumSubjectNotFoundException;
import br.com.projeto.forumrest.exception.ForumTopicNotFoundException;
import br.com.projeto.forumrest.exception.LoggedUserIsNotAuthorException;
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
		
		if(optionalTopic.isEmpty()) {
			throw new ForumTopicNotFoundException("Topic cannot be found!");
		}
		
		DetailedTopicDto detailedTopicDto = new DetailedTopicDto(optionalTopic.get());
		return detailedTopicDto;
	}
	
	public TopicDto createTopic(TopicForm topicForm, Principal principal) {
		
		Optional<ForumUser> optionalUser = userRepository.findByUsername(principal.getName());
		
		if(optionalUser.isEmpty()) {
			throw new ForumAuthorNotFoundException("Topic author cannot be found!");
		}
		
		Optional<ForumSubject> optionalSubject = subjectRepository.findById(topicForm.getSubject());
		
		if(optionalSubject.isEmpty()) {
			throw new ForumSubjectNotFoundException("Subject cannot be found!");
		}
		
		ForumUser user = optionalUser.get();
				
		ForumSubject subject = optionalSubject.get();
		
		Topic topic =  new Topic(topicForm.getTitle(),
				topicForm.getMessage(),
				user,
				subject);
		
		topicRepository.save(topic);		
		
		return new TopicDto(topic);
	}
	
	@Transactional
	public TopicDto updateTopic(Long id, UpdateTopicForm updateForm, Principal principal) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);
		
		if(optionalTopic.isEmpty()) {
			throw new ForumTopicNotFoundException("Topic cannot be found!");
		}

		Optional<ForumSubject> optionalSubject = subjectRepository.findBySubject(updateForm.getSubject());
		
		if(optionalSubject.isEmpty()) {
			throw new ForumSubjectNotFoundException("Subject cannot be found!");
		}
		
		Optional<ForumUser> optionalUser = userRepository.findByUsername(principal.getName());
		
		if(optionalUser.isEmpty()) {
			throw new ForumAuthorNotFoundException("Topic author cannot be found!");
		}
		
		Topic topic = optionalTopic.get();
		ForumUser forumUser = optionalUser.get();
		
		if(forumUser.getId() != topic.getAuthor().getId()) {
			throw new LoggedUserIsNotAuthorException("Cannot update topic, because current user is not the author!");
		}
		
		topic.setTitle(updateForm.getTitle());
		topic.setMessage(updateForm.getMessage());
		topic.setSubject(optionalSubject.get());
		TopicDto topicDto = new TopicDto(topic);
		
		return topicDto;
	}
	
	public TopicDto deleteTopic(Long id, Principal principal) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);
		
		if(optionalTopic.isEmpty()) {
			throw new ForumTopicNotFoundException("Topic cannot be found!");
		}
		
		Optional<ForumUser> optionalUser = userRepository.findByUsername(principal.getName());
		
		if(optionalUser.isEmpty()) {
			throw new ForumAuthorNotFoundException("Topic author cannot be found!");
		}
		
		Topic topic = optionalTopic.get();
		ForumUser forumUser = optionalUser.get();
		
		if(forumUser.getId() != topic.getAuthor().getId()) {
			throw new LoggedUserIsNotAuthorException("Cannot delete topic, because current user is not the author!");
		}
		
		TopicDto topicDto = new TopicDto(topic);
		topicRepository.deleteById(id);
		
		return topicDto;
	}

	
}
