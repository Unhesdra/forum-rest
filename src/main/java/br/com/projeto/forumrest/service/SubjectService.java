package br.com.projeto.forumrest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.SubjectDto;
import br.com.projeto.forumrest.form.SubjectForm;
import br.com.projeto.forumrest.model.ForumSubject;
import br.com.projeto.forumrest.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;

	public List<SubjectDto> listSubjects() {
		List<ForumSubject> subjectList = subjectRepository.findAll();
		List<SubjectDto> subjectDtoList = subjectList
				.stream()
				.map(subject -> new SubjectDto(subject))
				.toList();
		return subjectDtoList;
	}

	public SubjectDto createSubject(SubjectForm subjectForm) {
		ForumSubject forumSubject = new ForumSubject(subjectForm.getSubject());
		subjectRepository.save(forumSubject);
		return new SubjectDto(forumSubject);
	}

	@Transactional
	public SubjectDto updateSubject(SubjectForm subjectForm, Long id) {
		Optional<ForumSubject> optionalSubject = subjectRepository.findById(id);
		if(optionalSubject.isPresent()) {
			ForumSubject forumSubject = optionalSubject.get();
			forumSubject.setSubject(subjectForm.getSubject());
			return new SubjectDto(forumSubject);
		}
//		Implement custom Exception here!
		throw new RuntimeException("Subject cannot be found!");
	}

	public SubjectDto deleteSubject(Long id) {
		Optional<ForumSubject> optionalSubject = subjectRepository.findById(id);
		if(optionalSubject.isPresent()) {
			ForumSubject subject = optionalSubject.get();
			SubjectDto subjectDto = new SubjectDto(subject);
			subjectRepository.delete(subject);
			return subjectDto;
		}
//		Implement custom Exception here!
		throw new RuntimeException("Subject cannot be found!");
	}
	
	

}
