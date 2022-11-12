package com.spring.react.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name="T_MEMBER", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Data
@DynamicInsert
public class Member {
	@Id
	//uuid로 랜덤한 키 값 생성
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@ColumnDefault("'ROLE_USER'")
	private String role;
}
