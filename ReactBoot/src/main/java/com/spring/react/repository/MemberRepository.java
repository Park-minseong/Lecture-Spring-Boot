package com.spring.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.react.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	//username 중복체크
	boolean existsByUsername(String username);
	
	//로그인 처리
	//Member findByUsernameAndPassword(String username, String password);
	//비밀번호 일치여부는 passwordEncorder로 진행
	Member findByUsername(String username);
}
