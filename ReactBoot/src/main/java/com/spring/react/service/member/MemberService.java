package com.spring.react.service.member;

import com.spring.react.entity.Member;

public interface MemberService {

	Member join(Member member);

	Member login(String username, String password);
}
