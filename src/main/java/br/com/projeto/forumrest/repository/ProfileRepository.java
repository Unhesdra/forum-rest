package br.com.projeto.forumrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.forumrest.entity.ForumUserProfile;

public interface ProfileRepository extends JpaRepository<ForumUserProfile, Long> {

}
