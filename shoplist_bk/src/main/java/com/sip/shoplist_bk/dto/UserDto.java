package com.sip.shoplist_bk.dto;

import com.sip.shoplist_bk.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDto {
	private Integer id;
	private String email;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
	}
}
