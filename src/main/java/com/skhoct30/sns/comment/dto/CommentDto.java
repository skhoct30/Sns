package com.skhoct30.sns.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDto {

	private long id;
	private String nickname;
	private String contents;
	private long userId;
	
	
	
}
