package com.skhoct30.sns.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.sns.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
