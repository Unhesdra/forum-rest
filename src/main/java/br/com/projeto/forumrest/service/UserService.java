package br.com.projeto.forumrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.UserDto;
import br.com.projeto.forumrest.entity.ForumUser;
import br.com.projeto.forumrest.exception.UsernameAlreadyExistsException;
import br.com.projeto.forumrest.form.NewUserForm;
import br.com.projeto.forumrest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDto> listUsers() {
		List<ForumUser> userList = userRepository.findAll();
		List<UserDto> userDtoList = userList
				.stream()
				.map(u -> new UserDto(u))
				.toList();

		return userDtoList;
	}
	
	public UserDto createUser(NewUserForm newUserForm) {
		Optional<ForumUser> userOptional = userRepository.findByUsername(newUserForm.getUsername());
		if(userOptional.isPresent()) {
			throw new UsernameAlreadyExistsException("Cannot create new user, because the given username is already being used!");
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(newUserForm.getPassword());
		
		ForumUser newUser = new ForumUser(newUserForm.getUsername(), newUserForm.getEmail(), encryptedPassword);
		userRepository.save(newUser);
		
		return new UserDto(newUser);
	}

}
