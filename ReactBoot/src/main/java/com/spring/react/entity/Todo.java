package com.spring.react.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name="T_TODO")
@Data
@DynamicUpdate
@IdClass(TodoId.class)
public class Todo {
	@Id
	private int id;
	
	@Id
	private String username;
	
	private String text;
	
	private boolean checked;
}
