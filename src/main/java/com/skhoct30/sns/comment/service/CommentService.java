package com.skhoct30.sns.comment.service;

import org.springframework.stereotype.Service;

import com.skhoct30.sns.comment.domain.Comment;
import com.skhoct30.sns.comment.repositroy.CommentRepository;

import jakarta.persistence.PersistenceException;

@Service
public class CommentService {
	
	
	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	
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
	
}
