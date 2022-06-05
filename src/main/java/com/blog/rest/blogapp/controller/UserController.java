package com.blog.rest.blogapp.controller;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blog.rest.blogapp.model.User;
import com.blog.rest.blogapp.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@PostMapping(path = "/signup")
	public ResponseEntity<Object> signUp(@RequestBody User user) {

		String email = user.getEmail();
		if (userRepo.findByEmail(email).isPresent()) { // need to change findByEmail
			log.debug("User already exist with email id : {}", email);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {

			String encryptPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptPassword);
			userRepo.save(user);
			log.info("User saved with userName : {}", user.getUserName());
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
					.buildAndExpand(user.getUserName()).toUri();
			return ResponseEntity.created(uri).build();
		}

	}

	@GetMapping(path = "/signin")
	public ResponseEntity<Object> signIn() {
		return new ResponseEntity<>("Authenticated", HttpStatus.OK);
	}

	@PostMapping(path = "/role-auth")
	public ResponseEntity<Object> authenticateUserByRole(@RequestBody Object body)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		String request = objMapper.writeValueAsString(body);
		JsonNode jsonNode = objMapper.readTree(request);
		String email = jsonNode.get("email").asText();
		String role = jsonNode.get("role").asText();

		Optional<User> optional = userRepo.findByEmailAndRoles(email, role);
		if (optional.isPresent()) {
			return new ResponseEntity<>("User and Role Authenticated", HttpStatus.OK);

		} else
			return ResponseEntity.notFound().build();

	}

}
