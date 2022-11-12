package com.spring.react.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.react.dto.MemberDTO;
import com.spring.react.dto.ResponseDTO;
import com.spring.react.entity.Member;
import com.spring.react.jwt.JwtTokenProvider;
import com.spring.react.service.member.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody Member member){
		try {
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			
			//회원가입 후 가입된 회원 정보를 받아오는 객체 생성
			Member joinMember = memberService.join(member);
			
			//리액트로 리턴해 줄 MemberDTO 객체 생성
			MemberDTO memberDTO = new MemberDTO();
			
			//member는 리스트로 리턴되는게 아니여서 responseBody에 memberDTO를 담아서 리턴
			memberDTO.setId(joinMember.getId());
			memberDTO.setUsername(joinMember.getUsername());
			memberDTO.setRole(joinMember.getRole());
			
			return ResponseEntity.ok().body(memberDTO);
		}catch(Exception e){
			ResponseDTO<MemberDTO> response = new ResponseDTO<>();
			
			response.setError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member){
		//로그인 한 Member 객체 생성
		Member loginMember = memberService.login(member.getUsername(), member.getPassword());
		if(loginMember != null) {
			//로그인된 유저에 대한 토큰 발행
			final String token = jwtTokenProvider.create(loginMember);
			
			//리턴해줄 MemberDTO객체 생성
			final MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(loginMember.getId());
			memberDTO.setUsername(loginMember.getUsername());
			memberDTO.setRole(loginMember.getRole());
			//발행된 토큰을 DTO에 담아서 리턴
			memberDTO.setToken(token);
			
			return ResponseEntity.ok().body(memberDTO);
		} else {
			ResponseDTO<MemberDTO> response = new ResponseDTO<>();
			
			response.setError("login failed");
			
			return ResponseEntity.badRequest().body(response);
		}	
	}
}
