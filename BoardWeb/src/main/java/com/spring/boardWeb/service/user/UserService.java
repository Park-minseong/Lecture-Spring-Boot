package com.spring.boardWeb.service.user;

import com.spring.boardWeb.entity.User;

public interface UserService {

	void join(User user);

	User idCheck(String userId);

}
