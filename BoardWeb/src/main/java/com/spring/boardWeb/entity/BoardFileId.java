package com.spring.boardWeb.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BoardFileId implements Serializable{
	private int board;
	private int fileSeq;
}