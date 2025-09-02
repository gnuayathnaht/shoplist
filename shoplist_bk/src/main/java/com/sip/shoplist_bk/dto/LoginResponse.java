package com.sip.shoplist_bk.dto;

import com.sip.shoplist_bk.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	
	    private String token;
	    private User user;

	    public LoginResponse(String token, User user) {
	        this.token = token;
	        this.user = user;
	    }
	  
	}

