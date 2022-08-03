package br.com.projeto.forumrest.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.forumrest.entity.ForumUser;
import br.com.projeto.forumrest.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<ForumUser> optionalUser = userRepository.findByUsername(username);
		if(optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User cannot be found!");
		}
		
		return optionalUser.get();
	}

}
