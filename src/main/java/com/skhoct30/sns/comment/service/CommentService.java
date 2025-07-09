package com.skhoct30.sns.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skhoct30.sns.comment.domain.Comment;
import com.skhoct30.sns.comment.dto.CommentDto;
import com.skhoct30.sns.comment.repositroy.CommentRepository;
import com.skhoct30.sns.user.domain.User;
import com.skhoct30.sns.user.service.UserService;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	
	private final CommentRepository commentRepository;
	private final UserService userService;
	

	
	
	public boolean addComment(
			long userId
			, long postId
			, String contents) {
		
		Comment comment = Comment.builder()
				.userId(userId)
				.postId(postId)
				.contents(contents)
				.build();
		
		try {
			commentRepository.save(comment);			
		} catch(PersistenceException e) {
			return false;
		}
		return true;
	}
	
	
	
	// 특정 게시물의 댓글 목록만 조회
	public List<CommentDto> getCommentListByPostId(long postId) {
		
		List<Comment> commentList = commentRepository.findByPostIdOrderByIdDesc(postId);
		List<CommentDto> commentDtoList = new ArrayList<>();
		for(Comment comment:commentList) {
			
			
			User user = userService.getUserById(comment.getUserId());
			
			
			CommentDto commentDto = CommentDto.builder()
			.id(comment.getId())
			.contents(comment.getContents())
			.userId(comment.getUserId())
			.nickname(user.getNickname())
			.build();
			
			commentDtoList.add(commentDto);
			
		}
		return commentDtoList;
		
	}
	
	
	public void deletetCommentByPostId(long postId) {
		
		commentRepository.deleteByPostId(postId);
		
	}
	
	
}
