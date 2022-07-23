package br.com.projeto.forumrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.forumrest.dto.SubjectDto;
import br.com.projeto.forumrest.form.SubjectForm;
import br.com.projeto.forumrest.service.SubjectService;

@RestController
@RequestMapping(("/subject"))
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("/listSubjects")
	public ResponseEntity<List<SubjectDto>> listSubjects() {
		List<SubjectDto> subjectDtoList = subjectService.listSubjects();
		return ResponseEntity.ok(subjectDtoList);
	}
	
	@PostMapping("/createSubject")
	public ResponseEntity<SubjectDto> createSubject(@RequestBody @Valid SubjectForm subjectForm) {
		SubjectDto subjectDto = subjectService.createSubject(subjectForm);
		return ResponseEntity.ok(subjectDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SubjectDto> updateSubject(@RequestBody @Valid SubjectForm subjectForm, @PathVariable Long id) {
		SubjectDto subjectDto = subjectService.updateSubject(subjectForm, id);
		return ResponseEntity.ok(subjectDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SubjectDto> deleteSubject(@PathVariable Long id) {
		SubjectDto subjectDto = subjectService.deleteSubject(id);
		return ResponseEntity.ok(subjectDto);
	}

}
