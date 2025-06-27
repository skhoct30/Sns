package com.skhoct30.sns.user.service;

import org.springframework.stereotype.Service;

import com.skhoct30.sns.common.MD5HashingEncoder;
import com.skhoct30.sns.user.domain.User;
import com.skhoct30.sns.user.repository.UserRepository;

@Service
public class UserService {
	
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	// 회원가입(사용자추가)
	
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String nickname) {
		

		
		String hashingPassword = MD5HashingEncoder.encode(password);

		int count = userRepository.insertUser(loginId, hashingPassword, name, nickname);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	// 아이디 중복 확인
	public boolean isDuplicateId(String loginId) {
		
		// 중복되는 행이 있는 지 없는지 count 쿼리로 확인
		
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	
	
	// 로그인 기능
	
	public User getUser(String loginId, String password) {
		
		String hashingPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.selectUser(loginId, hashingPassword);
		
	}
	
	
	
	
}
