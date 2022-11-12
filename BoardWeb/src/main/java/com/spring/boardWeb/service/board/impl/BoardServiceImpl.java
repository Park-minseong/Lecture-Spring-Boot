package com.spring.boardWeb.service.board.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boardWeb.entity.Board;
import com.spring.boardWeb.entity.BoardFile;
import com.spring.boardWeb.entity.BoardReply;
import com.spring.boardWeb.repository.BoardFileRepository;
import com.spring.boardWeb.repository.BoardReplyRepository;
import com.spring.boardWeb.repository.BoardRepository;
import com.spring.boardWeb.service.board.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardFileRepository boardFileRepository;
	
	@Autowired
	BoardReplyRepository boardReplyRepository;
	
	@Override
	public Page<Board> getBoardList(Board board,Pageable pageable) {
		if(board.getSearchKeyword() != null && !board.getSearchKeyword().equals("")) {
			if(board.getSearchCondition().equals("all")) {
				return boardRepository.findByBoardTitleContainingOrBoardWriterContainingOrBoardContentContaining(board.getSearchKeyword(),board.getSearchKeyword(),board.getSearchKeyword(),pageable);
			}else if(board.getSearchCondition().equals("title")) {
				return boardRepository.findByBoardTitleContaining(board.getSearchKeyword(), pageable);
			}else if(board.getSearchCondition().equals("content")) {
				return boardRepository.findByBoardContentContaining(board.getSearchKeyword(), pageable);
			}else if(board.getSearchCondition().equals("writer")) {
				return boardRepository.findByBoardWriterContaining(board.getSearchKeyword(), pageable);
			}else {
				return null;
			}
		}else {
			return boardRepository.findAll(pageable);
		}
	
	}

	@Override
	public int insertBoard(Board board) {
		boardRepository.save(board);
		boardRepository.flush();
		return board.getBoardSeq();
	}
	
	@Override
	public Board getBoard(int boardSeq) {
		return boardRepository.findById(boardSeq).get();
	}

	@Override
	public void deleteBoard(int boardSeq) {
		boardRepository.deleteById(boardSeq);
		
	}

	@Override
	public void updateBoard(Board board) {
		boardRepository.save(board);		
	}

	@Override
	public void insertBoardFileList(List<BoardFile> fileList) {
		//boardFileRepository.saveAll(fileList);
		//int i = 1;
		for(BoardFile boardFile : fileList) {
			boardFile.setFileSeq(boardFileRepository.selectNextFileSeqByBoardBoardSeq(boardFile.getBoard().getBoardSeq()));
			boardFileRepository.save(boardFile);
		}
		
		
	}

	@Override
	public List<BoardFile> getBoardFileList(int boardSeq) {
		Board board = new Board();
		board.setBoardSeq(boardSeq);
		List<BoardFile> fileList = boardFileRepository.findByBoard(board);
		
		if(fileList == null || fileList.isEmpty()) {
			return null;
		}else {
			return fileList;
		}
	}


	@Override
	public void deleteBoardFile(BoardFile boardFile) {
		boardFileRepository.delete(boardFile);
		
	}

	@Override
	public void replyInsert(BoardReply boardReply) {
		boardReplyRepository.save(boardReply);
		
	}

	@Override
	public List<BoardReply> getBoardReplyList(int boardSeq) {
		Board board = new Board();
		board.setBoardSeq(boardSeq);
		List<BoardReply> replyList = boardReplyRepository.findByBoard(board);
		if(replyList == null || replyList.isEmpty()) {
			return null;
		}else {
			return replyList;
		}
	}

	/*
	 * @Override public void addBoardFileList(List<BoardFile> fileList, int fileSeq)
	 * { for(BoardFile boardFile : fileList) { boardFile.setFileSeq(fileSeq++);
	 * boardFileRepository.save(boardFile); }
	 * 
	 * }
	 */
}
