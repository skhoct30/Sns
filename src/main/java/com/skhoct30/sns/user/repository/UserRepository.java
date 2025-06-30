package com.skhoct30.sns.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.skhoct30.sns.user.domain.User;

@Mapper
public interface UserRepository {
	
	// 마이바티스 에서 가져올 정수 int 값
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("nickname") String nickname);
	
	
	
	// 조회를 통해 행의 개수 확인 있는지 없는지
	public int selectCountByLoginId(@Param("loginId") String loginId);
	
	// 로그인을 위한 API
	public User selectUser(@Param("loginId") String loginId, @Param("password") String password);
	
	
	//
	
	
}
