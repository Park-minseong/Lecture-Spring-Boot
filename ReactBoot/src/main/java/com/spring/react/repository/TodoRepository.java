package com.spring.react.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.react.entity.Todo;
import com.spring.react.entity.TodoId;

@Transactional//ddl실행 루 항상 commit돼어 트랙잭션이 종료되도록 설정
public interface TodoRepository extends JpaRepository<Todo, TodoId> {
	List<Todo> findByUsername(String username);
	
	@Modifying(clearAutomatically = true)//없을시 ExecuteStatement가 실행되는데 => 항상 select구문으로 인식됨
	@Query(value = "update t_todo t set t.id = t.id - 1 where t.id > :id", nativeQuery = true)
	void updateId(@Param("id") int id);
}
