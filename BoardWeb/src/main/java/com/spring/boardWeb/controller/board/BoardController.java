package com.spring.boardWeb.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boardWeb.commons.FileUtils;
import com.spring.boardWeb.entity.Board;
import com.spring.boardWeb.entity.BoardFile;
import com.spring.boardWeb.entity.BoardReply;
import com.spring.boardWeb.service.board.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/getBoardList")
	public ModelAndView getBoardListView(@PageableDefault(page = 0, size = 10, sort="boardSeq" ,direction=Direction.DESC) Pageable pageable, Board board) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/getBoardList.html");
		
		if(board.getSearchCondition() != null && !board.getSearchCondition().equals("")) {
			mv.addObject("searchCondition", board.getSearchCondition());
		}

		if(board.getSearchKeyword()!= null && !board.getSearchKeyword().equals("")) {
			mv.addObject("searchKeyword", board.getSearchKeyword());
		}
		
		Page<Board> boardList = boardService.getBoardList(board, pageable);
		mv.addObject("boardList", boardList);
		
		return mv;
	}
	
	@GetMapping("/getBoard/{boardSeq}")
	public ModelAndView getBoardView(@PathVariable int boardSeq, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("loginUser") == null) {
			mv.setViewName("user/login.html");
			return mv;
		}
		
		mv.setViewName("board/getBoard.html");
		
		Board board = boardService.getBoard(boardSeq);
		List<BoardFile> fileList = boardService.getBoardFileList(boardSeq);
		List<BoardReply> replyList = boardService.getBoardReplyList(boardSeq);

		
		mv.addObject("board", board);
		mv.addObject("fileList", fileList);
		mv.addObject("replyList", replyList);
		
		return mv;
	}
	
	@GetMapping("/insertBoard")
	public ModelAndView insertBoardView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/insertBoard.html");
		
		return mv;
	}
	
	@PostMapping("/insertBoard")
	public void insertBoard(HttpSession session, HttpServletResponse response, Board board, HttpServletRequest request, MultipartHttpServletRequest multipartServletRequest) throws IOException {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("loginUser") == null) {
			mv.setViewName("user/login.html");
			response.sendRedirect("/board/login.html");
		}
		
		int boardSeq = boardService.insertBoard(board);
	    
	    System.out.println(boardSeq + "//////sdfsdf//////////");
	    FileUtils fileUtils = new FileUtils();
	    List<BoardFile> fileList = fileUtils.parseFileInfo(boardSeq, request, multipartServletRequest);
	    
	    boardService.insertBoardFileList(fileList);
	    
		response.sendRedirect("/board/getBoardList");
	}
	
	@GetMapping("/deleteBoard/{boardSeq}")
	public ModelAndView deleteBoard(@PathVariable int boardSeq) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/board/getBoardList");
		boardService.deleteBoard(boardSeq);
		return mv;
	}
	
	@PostMapping("/updateBoard")
	public void updateBoard(Board board, HttpServletResponse response, HttpServletRequest request, MultipartHttpServletRequest multopartServletRequset) throws IOException {
		boardService.updateBoard(board);
		
		FileUtils fileUtils = new FileUtils();
		
		List<BoardFile> fileList = fileUtils.parseFileInfo(board.getBoardSeq(), request, multopartServletRequset);
		
		boardService.insertBoardFileList(fileList);
		
		response.sendRedirect("/board/getBoardList");
	}
	
	@RequestMapping("/fileDown")
	public ResponseEntity<Resource> fileDown(@RequestParam String fileName, HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/") + "/upload/";
		
		Resource resource = new FileSystemResource(path + fileName);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders header = new HttpHeaders();
		
		try {
			header.add("Content-Disposition","attachment; fileName=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	@PostMapping("/deleteBoardFile")
	public void deleteBoardFile(@RequestParam int boardSeq, @RequestParam int fileSeq) {
		Board board = new Board();
		board.setBoardSeq(boardSeq);
		
		BoardFile boardFile = new BoardFile();
		boardFile.setBoard(board);
		boardFile.setFileSeq(fileSeq);
		boardService.deleteBoardFile(boardFile);
	}
	
	@PostMapping("/replyInsert")
	public void replyInsert(BoardReply boardReply, int boardSeq, HttpServletResponse response) throws IOException {
		
		Board board = new Board();
		board.setBoardSeq(boardSeq);
		boardReply.setBoard(board);
		boardService.replyInsert(boardReply);
		response.sendRedirect("/board/getBoard/"+boardSeq);
	}
	
}
