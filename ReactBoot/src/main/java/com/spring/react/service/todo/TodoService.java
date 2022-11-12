package com.spring.react.service.todo;

import java.util.List;

import com.spring.react.entity.Todo;

public interface TodoService {

	List<Todo> selectTodoList(String username);

	List<Todo> insertTodo(Todo todo);

	void deleteTodo(Todo todo);

	List<Todo> updateTodo(Todo todo);
	
	void updateTodoId(int id);
}
