package br.com.projeto.forumrest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.dto.UserDto;
import br.com.projeto.forumrest.entity.ForumUser;
import br.com.projeto.forumrest.entity.ForumUserProfile;
import br.com.projeto.forumrest.exception.ForumUserNotFoundException;
import br.com.projeto.forumrest.exception.ProfileDoesNotExistException;
import br.com.projeto.forumrest.exception.UsernameAlreadyExistsException;
import br.com.projeto.forumrest.form.NewUserForm;
import br.com.projeto.forumrest.form.UpdateUserForm;
import br.com.projeto.forumrest.repository.ProfileRepository;
import br.com.projeto.forumrest.repository.UserRepository;
import br.com.projeto.forumrest.security.PasswordConfig;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;

	public List<UserDto> listUsers() {
		List<ForumUser> userList = userRepository.findAll();
		List<UserDto> userDtoList = userList
				.stream()
				.map(u -> new UserDto(u))
				.toList();

		return userDtoList;
	}
	
	public UserDto createUser(NewUserForm newUserForm) {
		Optional<ForumUser> optionalUser = userRepository.findByUsername(newUserForm.getUsername());
		if(optionalUser.isPresent()) {
			throw new UsernameAlreadyExistsException("Cannot create new user, because the given username is already being used!");
		}
		
		Set<Optional<ForumUserProfile>> optionalProfileSet = newUserForm.getUserProfileIdSet()
			.stream()
			.map(id -> profileRepository.findById(id))
			.collect(Collectors.toSet());
		
		Set<ForumUserProfile> userProfileSet = optionalProfileSet.stream().map(optionalProfile -> {
			if(optionalProfile.isEmpty()) {
				throw new ProfileDoesNotExistException("Entered authority does not exist in the database!");
			}
			else {
				return optionalProfile.get();
			}
		}).collect(Collectors.toSet());
		
		String encryptedPassword = new PasswordConfig()
				.passwordEncoder()
				.encode(newUserForm.getPassword());
		
		ForumUser newUser = new ForumUser(
				newUserForm.getUsername(),
				newUserForm.getEmail(),
				encryptedPassword,
				userProfileSet);
		
		userRepository.save(newUser);
		
		return new UserDto(newUser);
	}
	
	@Transactional
	public UserDto updateUser(Long id, UpdateUserForm updateUserForm) {
		Optional<ForumUser> optionalUser = userRepository.findById(id);
		if(optionalUser.isEmpty()) {
			throw new ForumUserNotFoundException("Forum user cannot be found!");
		}
		
		String encryptedPassword = new PasswordConfig()
				.passwordEncoder()
				.encode(updateUserForm.getPassword());
		
		ForumUser forumUser = optionalUser.get();
		
		forumUser.setEmail(updateUserForm.getEmail());
		forumUser.setPassword(encryptedPassword);
		forumUser.clearUserProfileSet();
		updateUserForm.getUserProfileIdSet().stream().forEach(profileId -> {
			Optional<ForumUserProfile> optionalProfile = profileRepository.findById(profileId);
			if(optionalProfile.isEmpty()) {
				System.out.println(optionalProfile);
				throw new ProfileDoesNotExistException("Entered authority does not exist in the database!");
			}
			else {
				forumUser.addUserProfile(optionalProfile.get());
			}
		});
		
		return new UserDto(forumUser);		
	}

	public UserDto deleteUser(Long id) {
		Optional<ForumUser> optionalUser = userRepository.findById(id);
		if(optionalUser.isEmpty()) {
			throw new ForumUserNotFoundException("Forum user cannot be found!");
		}
		
		ForumUser forumUser = optionalUser.get();
		UserDto userDto = new UserDto(forumUser);
		
		userRepository.delete(forumUser);
		
		return userDto;
	}

}
