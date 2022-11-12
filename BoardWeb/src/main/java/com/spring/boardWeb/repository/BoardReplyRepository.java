package com.spring.boardWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boardWeb.entity.Board;
import com.spring.boardWeb.entity.BoardReply;
import com.spring.boardWeb.entity.BoardReplyId;

public interface BoardReplyRepository  extends JpaRepository<BoardReply, BoardReplyId>{

	List<BoardReply> findByBoard(Board board);

}
