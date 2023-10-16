package br.com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.todolist.model.UserModel;
import br.com.todolist.repository.UserRespository;


@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	private UserRespository userRepository;
	
	@PostMapping("/")
	public ResponseEntity create(@RequestBody UserModel userModel) {
		var user = this.userRepository.findByUsername(userModel.getUsername());
		
		if (user != null) {
			System.out.println("Usuario ja existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario ja esxiste");
		}
		
		var passwordHashred = BCrypt.withDefaults()
				.hashToString(12, userModel.getPassword().toCharArray());
		
		userModel.setPassword(passwordHashred);
		
		var userCreated = this.userRepository.save(userModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
}