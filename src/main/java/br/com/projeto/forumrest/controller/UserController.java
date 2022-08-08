package br.com.projeto.forumrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.forumrest.dto.UserDto;
import br.com.projeto.forumrest.form.NewUserForm;
import br.com.projeto.forumrest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/listUser")
	public ResponseEntity<List<UserDto>> listUsers() {
		List<UserDto> userDtoList = userService.listUsers();
		return ResponseEntity.ok(userDtoList);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid NewUserForm newUserForm) {
		UserDto userDto = userService.createUser(newUserForm);
		return ResponseEntity.ok(userDto);
	}


}
