package com.spring.react.dto;

import lombok.Data;

@Data
public class TodoDTO {
	private int id;
	
	private String username;
	
	private String text;
	
	private boolean checked;

}
