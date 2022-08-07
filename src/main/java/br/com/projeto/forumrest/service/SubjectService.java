package br.com.projeto.forumrest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.SubjectDto;
import br.com.projeto.forumrest.entity.ForumSubject;
import br.com.projeto.forumrest.exception.ForumSubjectNotFoundException;
import br.com.projeto.forumrest.exception.SubjectAlreadyExistsException;
import br.com.projeto.forumrest.form.SubjectForm;
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
		Optional<ForumSubject> optionalSubject = subjectRepository.findBySubject(subjectForm.getSubject());
		if(optionalSubject.isPresent()) {
			throw new SubjectAlreadyExistsException("Could not create new subject, because given subject name already exists in the database!");
		}
		
		ForumSubject forumSubject = new ForumSubject(subjectForm.getSubject());		
		subjectRepository.save(forumSubject);
		return new SubjectDto(forumSubject);
	}

	@Transactional
	public SubjectDto updateSubject(SubjectForm subjectForm, Long id) {
		Optional<ForumSubject> optionalSubjectById = subjectRepository.findById(id);
		if(optionalSubjectById.isEmpty()) {
			throw new ForumSubjectNotFoundException("Subject cannot be found!");
		}
		
		Optional<ForumSubject> optionalSubjectByName = subjectRepository.findBySubject(subjectForm.getSubject());
		if(optionalSubjectByName.isPresent()) {
			throw new SubjectAlreadyExistsException("Could not update subject, because given subject name already exists in the database!");
		}
		
		ForumSubject forumSubject = optionalSubjectById.get();
		forumSubject.setSubject(subjectForm.getSubject());
		return new SubjectDto(forumSubject);
	}

	public SubjectDto deleteSubject(Long id) {
		Optional<ForumSubject> optionalSubject = subjectRepository.findById(id);
		if(optionalSubject.isEmpty()) {
			throw new ForumSubjectNotFoundException("Subject cannot be found!");
		}
		
		ForumSubject subject = optionalSubject.get();
		SubjectDto subjectDto = new SubjectDto(subject);
		subjectRepository.delete(subject);
		return subjectDto;
	}
	
	

}
