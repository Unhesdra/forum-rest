package br.com.projeto.forumrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.forumrest.dto.ResponseDto;
import br.com.projeto.forumrest.form.ResponseForm;
import br.com.projeto.forumrest.service.ResponseService;

@RestController
@RequestMapping("/response")
public class ResponseController {
	
	@Autowired
	private ResponseService responseService;
	
	@GetMapping("/listResponse")
	public ResponseEntity<List<ResponseDto>> getResponseList() {
		List<ResponseDto> responseDtoList = responseService.getResponseList();
		return ResponseEntity.ok(responseDtoList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getSpecificResponse(@PathVariable Long id) {
		ResponseDto responseDto = responseService.getSpecificResponse(id);
		return ResponseEntity.ok(responseDto);
	}
	
	@PostMapping("/createResponse")
	public ResponseEntity<ResponseDto> createResponse(@RequestBody @Valid ResponseForm responseForm) {
		ResponseDto responseDto = responseService.createResponse(responseForm);
		return ResponseEntity.ok(responseDto);
	}
	
	@PutMapping("updateSolution/{id}")
	public ResponseEntity<ResponseDto> updateResponseStatus(@PathVariable Long id, @RequestParam Boolean isSolution) {
		ResponseDto responseDto = responseService.updateResponseStatus(id, isSolution);
		return ResponseEntity.ok(responseDto);
	}

}
