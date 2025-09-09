package com.sip.shoplist_bk.dto;

import com.sip.shoplist_bk.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
	
	    private String token;
	    private User user;

	    public LoginResponse(String token, User user) {
	        this.token = token;
	        this.user = user;
	    }
	  
	}

