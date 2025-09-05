package com.sip.shoplist_bk.dto;

import com.sip.shoplist_bk.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDto {
	private Integer id;
	private String name;    
	private String email;       
	private String password;    
	private String phone;      
	private String address;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.address = user.getAddress();
	}
}
