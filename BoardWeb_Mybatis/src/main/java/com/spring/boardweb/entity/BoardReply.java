package com.spring.boardweb.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="T_BOARD_REPLY")
@Data
@IdClass(BoardReplyId.class)
public class BoardReply {
	@Id
	@ManyToOne
	@JoinColumn(name="BOARD_SEQ")
	private Board board;
	
	@Id
	private int replySeq;
	
	private String replyWriter;
	
	private String replyContent;
	
	private LocalDateTime replyRegdate = LocalDateTime.now();
}
