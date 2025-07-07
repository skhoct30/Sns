package com.skhoct30.sns.like.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.sns.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
	
	// SELECT count(*) FROM `like` WHERE `postId` = 4;
	
	public int countByPostId(long postId);

	
	// jpa 기반에서 행이 존재하는지 안하는지 판단해주는 규칙이 있음.
	// exists
	// 특정 행이 존재 하는지 / 안하는지.
	public boolean existsByPostIdAndUserId(long postId, long userId);
	
	
}
