package com.sip.shoplist_bk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.shoplist_bk.dto.UserDto;
import com.sip.shoplist_bk.entity.User;
import com.sip.shoplist_bk.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public Optional<UserDto> findUserDtoById(int userId) {
		return userRepo.findById(userId).map(UserDto::new);
	}
	
	public Optional<User> findUserById(int userId) {
		return userRepo.findById(userId);
	}
	
	public String findAddressByUserId(int userId) {
		User user = userRepo.findById(userId).orElse(new User());
		return user.getAddress();

	}
	
    public User save(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists: " + user.getEmail());
        }
        return userRepo.save(user);
    }
    
    public User findByEmailAndPw(String email, String password) {
    	
    	  Optional<User> userOpt = userRepo.findByEmail(email);
          if (userOpt.isPresent()) {
              User user = userOpt.get();
              if (user.getPassword().equals(password)) { 
                  return user;
              }
          }
          return null; 
      }
    
    
    public User findByEmail(String email) {
    	
  	  Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
           
                return user;
            
        }
        return null; 
    }
  
    

        public User updateUser(int id, User user) {
        
            Optional<User> existingUserOpt = userRepo.findById(id);
            if (!existingUserOpt.isPresent()) {
                throw new RuntimeException("User not found with id: " + id);
            }

            User existingUser = existingUserOpt.get();

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(user.getPassword());
            }

       
            return userRepo.save(existingUser);
        }
    

        public User findById(int id) {
            return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        }
}
