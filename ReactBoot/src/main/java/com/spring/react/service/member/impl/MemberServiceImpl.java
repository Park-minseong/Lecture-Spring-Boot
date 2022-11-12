package com.spring.react.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.react.entity.Member;
import com.spring.react.repository.MemberRepository;
import com.spring.react.service.member.MemberService;

import lombok.Data;

@Data
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Member join(Member member) {
		//Member 유효성 검사
		if(member == null || member.getUsername() == null) {
			throw new RuntimeException("invalid Argument");
		}
		
		//username 중복체크
		if(memberRepository.existsByUsername(member.getUsername())) {
			throw new RuntimeException("username aleady exists");
		}
		return memberRepository.save(member);
	}

	@Override
	public Member login(String username, String password) {
		Member loginMember = memberRepository.findByUsername(username);
		
		if(loginMember != null && passwordEncoder.matches(password, loginMember.getPassword()) ) {
			System.out.println(11);
			return loginMember;
		}
		return null;
			
	}
}
