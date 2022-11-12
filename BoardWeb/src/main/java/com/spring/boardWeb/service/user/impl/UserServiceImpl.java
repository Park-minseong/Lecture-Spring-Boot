package com.spring.boardWeb.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boardWeb.entity.User;
import com.spring.boardWeb.repository.UserRepository;
import com.spring.boardWeb.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public void join(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User idCheck(String userId) {
		if(userRepository.findById(userId).isPresent()) {
			return userRepository.findById(userId).get();
		}else {
			return null;
		}
	}

}
