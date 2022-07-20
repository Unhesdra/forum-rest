package br.com.projeto.forumrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
