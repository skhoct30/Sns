package com.skhoct30.sns.comment.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.sns.comment.domain.Comment;

import jakarta.transaction.Transactional;


public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	
	// 특정 게시물의 댓글 리스트
	// WHERE `postId` = #{} ORDER BY ID DESC
	
	public List<Comment> findByPostIdOrderByIdDesc(long postId);
	
	// SELECT * FROM `comment` WHERE `postId` = 4;
	// DELETET FROM `comment` WHERE `postId` = 4;
	// transcation : 한꺼번에 수행해야할 쿼리를 묶어서 수행
	// roll back : 수행과정에 문제가 생기면 이전 과정으로 되돌린다.
	
	@Transactional
	public void deleteByPostId(long postId);
	

}
