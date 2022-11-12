package com.spring.boardWeb.service.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boardWeb.entity.Board;
import com.spring.boardWeb.entity.BoardFile;
import com.spring.boardWeb.entity.BoardReply;

public interface BoardService {

	Page<Board> getBoardList(Board board, Pageable pageable);

	int insertBoard(Board board);
	
	Board getBoard(int boardSeq);

	void deleteBoard(int boardSeq);

	void updateBoard(Board board);

	void insertBoardFileList(List<BoardFile> fileList);

	List<BoardFile> getBoardFileList(int boardSeq);

	void deleteBoardFile(BoardFile boardFile);

	void replyInsert(BoardReply boardReply);

	List<BoardReply> getBoardReplyList(int boardSeq);

}
