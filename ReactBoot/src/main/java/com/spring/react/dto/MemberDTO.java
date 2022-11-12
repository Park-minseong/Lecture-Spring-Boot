package com.spring.react.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String username;
	private String password;
	private String role;
	private String token;
}
