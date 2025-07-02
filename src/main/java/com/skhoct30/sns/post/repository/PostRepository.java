package com.skhoct30.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.sns.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	
	// 한명의 리스트/ 한명의 사용자 리스트 만 조회
	
	// WHERE `userId` = #{} ORDER BY `id` desc
	
	public List<Post> findByUserIdOrderByIdDesc(long userId);

}
