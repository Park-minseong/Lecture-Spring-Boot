package com.spring.react.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.react.dto.ResponseDTO;
import com.spring.react.dto.TestDTO;

@RestController
@RequestMapping("/api")
public class TestController {
	@GetMapping("/test")
	public String test() {
		return "Hello World";
	}
	
	@PostMapping("/test2")
	public ResponseEntity<?> test2(@RequestBody TestDTO test) {
		System.out.println(test.toString());
		
		//ResponseDTO를 ResponseEntity에 담아서 리턴
		List<TestDTO> testList = new ArrayList<TestDTO>();
		
		for(int i =0; i <3; i++){
			TestDTO testDTO = new TestDTO();
			testDTO.setId(i);
			testDTO.setName("name" + i);
			testList.add(testDTO);
		}
		
		//TestDTO를 담은 응답객체 생성
		ResponseDTO<TestDTO> response = new ResponseDTO<>();
		
		response.setData(testList);
		
		//통신이 ok(status가 200)일 때 응답 바디에 ResponseDTO를 담아서 리턴
		return ResponseEntity.ok().body(response);
	}
}
