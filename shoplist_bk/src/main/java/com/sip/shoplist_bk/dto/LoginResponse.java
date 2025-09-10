package com.sip.shoplist_bk.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
	
	    private String token;
	    private UserDto user;

	    public LoginResponse(String token, UserDto user) {
	        this.token = token;
	        this.user = user;
	    }
	  
	}

