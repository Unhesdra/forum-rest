package br.com.projeto.forumrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.forumrest.model.ForumSubject;

public interface SubjectRepository extends JpaRepository<ForumSubject, Long>{
	
	public Optional<ForumSubject> findBySubject(String subject);

}
