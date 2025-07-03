package com.skhoct30.sns.post.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter //값을 얻어와서 사용 해야해서 추가
public class PostDto {
	
	// 하나 의 게시물의 정보
	// 게시물의 정보를 묶을 데이터들을 모아서 사용한다.
	
	private long id;
	
	private String nickname;
	private String contents;
	private String imagePath;
	
	// 작성자의 로그인 아이디
	// primarykey 도 같이 묶어서 관리하자
	private long userId;
	
	
	
	
	
	

}
