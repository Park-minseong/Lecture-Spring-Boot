package com.spring.boardweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.boardweb.entity.Board;
import com.spring.boardweb.entity.BoardReply;
import com.spring.boardweb.entity.BoardReplyId;

public interface BoardReplyRepository  extends JpaRepository<BoardReply, BoardReplyId>{

	List<BoardReply> findByBoard(Board board);
	
	@Query(value="select ifnull(max(f.reply_Seq), 0)+1 from t_board_reply f where f.board_seq = :boardSeq", nativeQuery=true)
	int selectNextReplySeqByBoardBoardSeq(@Param("boardSeq") int BoardSeq);

}
