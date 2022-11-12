package com.spring.react.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class TodoId implements Serializable{
	private int id;
	private String username;
}
