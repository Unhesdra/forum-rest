package br.com.projeto.forumrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.forumrest.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
