package br.com.projeto.forumrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.forumrest.entity.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {

}
