package com.spring.react.controller.todo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.react.dto.ResponseDTO;
import com.spring.react.dto.TodoDTO;
import com.spring.react.entity.Todo;
import com.spring.react.service.todo.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {
	@Autowired
	private TodoService todoService;

	@GetMapping("/todo/selectTodoList")
	public ResponseEntity<?> selecteTodoList(@AuthenticationPrincipal String username) {
		try {

			List<Todo> todoList = todoService.selectTodoList(username);

			List<TodoDTO> todoDTOList = new ArrayList<TodoDTO>();

			for (Todo t : todoList) {
				TodoDTO todoDTO = new TodoDTO();
				todoDTO.setId(t.getId());
				todoDTO.setUsername(t.getUsername());
				todoDTO.setText(t.getText());
				todoDTO.setChecked(t.isChecked());

				todoDTOList.add(todoDTO);
			}

			ResponseDTO<TodoDTO> response = new ResponseDTO<>();

			response.setData(todoDTOList);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<TodoDTO> response = new ResponseDTO<TodoDTO>();
			response.setError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/todo/insertTodoList")
	public ResponseEntity<?> insertTodoList(@RequestBody Todo todo, @AuthenticationPrincipal String username) {
		// ResponseDTO를 ResponseEntity에 담아서 리턴
		try {

			List<Todo> todoList = todoService.insertTodo(todo);

			List<TodoDTO> todoDTOList = new ArrayList<TodoDTO>();

			for (Todo t : todoList) {
				TodoDTO todoDTO = new TodoDTO();
				todoDTO.setId(t.getId());
				todoDTO.setUsername(t.getUsername());
				todoDTO.setText(t.getText());
				todoDTO.setChecked(t.isChecked());

				todoDTOList.add(todoDTO);
			}

			// TestDTO를 담은 응답객체 생성
			ResponseDTO<TodoDTO> response = new ResponseDTO<>();

			response.setData(todoDTOList);

			// 통신이 ok(status가 200)일 때 응답 바디에 ResponseDTO를 담아서 리턴
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			ResponseDTO<TodoDTO> response = new ResponseDTO<TodoDTO>();
			response.setError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping("/todo/deleteTodoList")
	public ResponseEntity<?> deleteTodoList(@RequestBody Todo todo, @AuthenticationPrincipal String username) {
		// ResponseDTO를 ResponseEntity에 담아서 리턴
		try {
			todo.setUsername("test");
			
			todoService.deleteTodo(todo);
			
			todoService.updateTodoId(todo.getId());
			
			List<Todo> todoList = todoService.selectTodoList(todo.getUsername());
			
			List<TodoDTO> todoDTOList = new ArrayList<TodoDTO>();

			for (Todo t : todoList) {
				TodoDTO todoDTO = new TodoDTO();
				todoDTO.setId(t.getId());
				todoDTO.setUsername(t.getUsername());
				todoDTO.setText(t.getText());
				todoDTO.setChecked(t.isChecked());

				todoDTOList.add(todoDTO);
			}

			// TestDTO를 담은 응답객체 생성
			ResponseDTO<TodoDTO> response = new ResponseDTO<>();

			response.setData(todoDTOList);

			// 통신이 ok(status가 200)일 때 응답 바디에 ResponseDTO를 담아서 리턴
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			ResponseDTO<TodoDTO> response = new ResponseDTO<TodoDTO>();
			response.setError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	/*
	 * @PostMapping("/todo/updateCheckTodo") public ResponseEntity<?>
	 * updateCheckTodo(@RequestBody Todo todo) { // ResponseDTO를 ResponseEntity에 담아서
	 * 리턴 try { todo.setChecked(!todo.isChecked()); List<Todo> todoList =
	 * todoService.updateCheckTodo(todo);
	 * 
	 * List<TodoDTO> todoDTOList = new ArrayList<TodoDTO>();
	 * 
	 * for (Todo t : todoList) { TodoDTO todoDTO = new TodoDTO();
	 * todoDTO.setId(t.getId()); todoDTO.setUsername(t.getUsername());
	 * todoDTO.setText(t.getText()); todoDTO.setChecked(t.isChecked());
	 * 
	 * todoDTOList.add(todoDTO); }
	 * 
	 * // TestDTO를 담은 응답객체 생성 ResponseDTO<TodoDTO> response = new ResponseDTO<>();
	 * 
	 * response.setData(todoDTOList);
	 * 
	 * // 통신이 ok(status가 200)일 때 응답 바디에 ResponseDTO를 담아서 리턴 return
	 * ResponseEntity.ok().body(response);
	 * 
	 * } catch (Exception e) { ResponseDTO<TodoDTO> response = new
	 * ResponseDTO<TodoDTO>(); response.setError(e.getMessage()); return
	 * ResponseEntity.badRequest().body(response); } }
	 */
	@PutMapping("/todo/updateCheckTodo")
	public ResponseEntity<?> updateCheckTodo(@RequestBody Todo todo, @AuthenticationPrincipal String username) {
		// ResponseDTO를 ResponseEntity에 담아서 리턴
		try {
			todo.setChecked(!todo.isChecked());
			List<Todo> todoList = todoService.updateTodo(todo);

			List<TodoDTO> todoDTOList = new ArrayList<TodoDTO>();

			for (Todo t : todoList) {
				TodoDTO todoDTO = new TodoDTO();
				todoDTO.setId(t.getId());
				todoDTO.setUsername(t.getUsername());
				todoDTO.setText(t.getText());
				todoDTO.setChecked(t.isChecked());

				todoDTOList.add(todoDTO);
			}

			// TestDTO를 담은 응답객체 생성
			ResponseDTO<TodoDTO> response = new ResponseDTO<>();

			response.setData(todoDTOList);

			// 통신이 ok(status가 200)일 때 응답 바디에 ResponseDTO를 담아서 리턴
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			ResponseDTO<TodoDTO> response = new ResponseDTO<TodoDTO>();
			response.setError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
