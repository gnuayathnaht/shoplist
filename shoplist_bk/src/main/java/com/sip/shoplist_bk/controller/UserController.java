package com.sip.shoplist_bk.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@PostMapping("/register")
	public ResponseEntity<User> registerWithAuth(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User registerUser = userRepo.save(user);
		return ResponseEntity.ok(registerUser);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginWithAuth(@RequestBody User user) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
		);

		if (authentication.isAuthenticated()) {
			String token = jwtUtil.generateToken(user.getEmail(), user.getId());
			User userExist = userService.findByEmail(user.getEmail());
			UserDto userDto = new UserDto(userExist);
			return ResponseEntity.ok(new LoginResponse(token, userDto));
		} else {
			return ResponseEntity.status(401).body("Wrong email or password");
		}
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
	public ResponseEntity<UserDto> getProfile(@RequestHeader("Authorization") String authHeader) {
	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        String token = authHeader.substring(7);
	        if (jwtUtil.validateToken(token)) {
	            Integer userId = jwtUtil.extractUserId(token);
	            User user = userService.findById(userId);
	            return ResponseEntity.ok(new UserDto(user));
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
