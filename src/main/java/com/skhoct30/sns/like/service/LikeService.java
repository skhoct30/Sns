package com.skhoct30.sns.like.service;

import org.springframework.stereotype.Service;

import com.skhoct30.sns.like.domain.Like;
import com.skhoct30.sns.like.respository.LikeRepository;

import jakarta.persistence.PersistenceException;

@Service
public class LikeService {

	private final LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	public boolean addLike(
			long userId
			, long postId) {
		
		Like like = Like.builder()
				.userId(userId)
				.postId(postId)
				.build();

		try {
			likeRepository.save(like);			
		} catch(PersistenceException e) {
			return false;
		}
		return true;
		
	}
	
	public int likeCountByPostId(long postId) {
		return likeRepository.countByPostId(postId);
	}
	
	// 좋아요를 이 사용자가 했는지 안했는지 확인하기 위한 기능
	
	public boolean isLikePostIdAndUserId(long postId, long userId) {
		return likeRepository.existsByPostIdAndUserId(postId, userId);
	}
	
	
	
	
}
