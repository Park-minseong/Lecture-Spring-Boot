package com.spring.boardWeb.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Entity
@Table(name="T_BOARD_REPLY")
@Data
@IdClass(BoardReplyId.class)
@TableGenerator(name="REPLY_SEQ_GENERATOR", table="REPLY_SEQUENCES", pkColumnValue="REPLY_SEQ", allocationSize=1)
public class BoardReply {
	@Id
	@ManyToOne
	@JoinColumn(name="BOARD_SEQ")
	private Board board;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "REPLY_SEQ_GENERATOR")
	private int replySeq;
	
	private String replyWriter;
	
	private String replyContent;
	
	private LocalDateTime replyRegdate = LocalDateTime.now();
}
