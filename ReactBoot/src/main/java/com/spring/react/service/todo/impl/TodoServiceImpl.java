package com.spring.react.service.todo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.react.entity.Todo;
import com.spring.react.entity.TodoId;
import com.spring.react.repository.TodoRepository;
import com.spring.react.service.todo.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public List<Todo> selectTodoList(String username) {
		return todoRepository.findByUsername(username);
	}

	@Override
	public List<Todo> insertTodo(Todo todo) {
		todoRepository.save(todo);
		return todoRepository.findByUsername(todo.getUsername());
	}

	@Override
	public void deleteTodo(Todo todo) {
		TodoId todoId = new TodoId();
		todoId.setId(todo.getId());
		todoRepository.deleteById(todoId);
		//return todoRepository.findByUsername(todo.getUsername());
	}
	
	@Override
	public void updateTodoId(int id) {
		todoRepository.updateId(id);
		
		//return todoRepository.findByUsername(todo.getUsername());
	}

	@Override
	public List<Todo> updateTodo(Todo todo) {
		todoRepository.save(todo);
		return todoRepository.findByUsername(todo.getUsername());
	}



}
