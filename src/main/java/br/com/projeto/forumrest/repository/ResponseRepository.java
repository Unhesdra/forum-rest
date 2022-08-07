package br.com.projeto.forumrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto.forumrest.entity.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {

	@Query("SELECT r FROM Response r "
			+ "LEFT JOIN FETCH r.topic t "
			+ "WHERE t.id = :topicId")
	List<Response> findAllTopicResponses(@Param("topicId") Long topicId);

}
