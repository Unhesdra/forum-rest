package br.com.projeto.forumrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.forumrest.entity.ForumUser;

public interface UserRepository extends JpaRepository<ForumUser, Long> {
	
	public Optional<ForumUser> findByUsername(String username);

}
