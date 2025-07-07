package com.skhoct30.sns.comment.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.sns.comment.domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	
	// 특정 게시물의 댓글 리스트
	// WHERE `postId` = #{} ORDER BY ID DESC
	
	public List<Comment> findByPostIdOrderByIdDesc(long postId);
	

}
