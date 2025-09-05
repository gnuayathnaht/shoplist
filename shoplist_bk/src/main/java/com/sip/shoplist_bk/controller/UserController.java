package com.sip.shoplist_bk.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.shoplist_bk.dto.LoginResponse;
import com.sip.shoplist_bk.dto.UserDto;
import com.sip.shoplist_bk.entity.User;
import com.sip.shoplist_bk.jwt.JwtUtil;
import com.sip.shoplist_bk.repo.UserRepo;
import com.sip.shoplist_bk.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200/")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserByID(@PathVariable int userId){
		Optional<UserDto> user = userService.findUserDtoById(userId);
		System.out.println(user);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@PostMapping
	public ResponseEntity<User> saveRegisterUser(@RequestBody User user) {
		User registerUser = userRepo.save(user);
		return ResponseEntity.ok(registerUser);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginCheck(@RequestBody User user) {
		User userExist = userService.findByEmail(user.getEmail());

		if (userExist == null) {
			return ResponseEntity.status(401).body("Email not found");
		}

		if (!userExist.getPassword().equals(user.getPassword())) {
			return ResponseEntity.status(401).body("Password incorrect");
		}

		String token = jwtUtil.generateToken(userExist.getEmail(), userExist.getId());
		return ResponseEntity.ok(new LoginResponse(token, userExist));
	}
	
	@GetMapping("/validate")
	public ResponseEntity<Boolean> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        String token = authHeader.substring(7);
	        boolean isValid = jwtUtil.validateToken(token);

	        if (isValid) {
	            return ResponseEntity.ok(true);
	        }
	    }
	    return ResponseEntity.status(401).body(false);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> getProfile(@RequestHeader("Authorization") String authHeader) {
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			System.out.println("Incoming JWT Token: " + token);
			if (jwtUtil.validateToken(token)) {
				Integer userId = jwtUtil.extractUserId(token);
				User user = userService.findById(userId);
				// User userDto = new User();
				return ResponseEntity.ok(user);
			}
		}
		return ResponseEntity.status(401).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
		User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}

}
