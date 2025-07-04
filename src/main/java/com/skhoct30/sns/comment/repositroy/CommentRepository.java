package com.skhoct30.sns.comment.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.sns.comment.domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long>{

}
