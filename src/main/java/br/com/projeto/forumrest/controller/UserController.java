package br.com.projeto.forumrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.forumrest.dto.UserDto;
import br.com.projeto.forumrest.form.NewUserForm;
import br.com.projeto.forumrest.form.UpdateUserForm;
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
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UpdateUserForm updateUserForm, @PathVariable Long id) {
		UserDto userDto = userService.updateUser(id, updateUserForm);
		return ResponseEntity.ok(userDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
		UserDto userDto = userService.deleteUser(id);
		return ResponseEntity.ok(userDto);
	}


}
