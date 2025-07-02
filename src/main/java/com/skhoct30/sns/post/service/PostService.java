package com.skhoct30.sns.post.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skhoct30.sns.common.FileManager;
import com.skhoct30.sns.post.domain.Post;
import com.skhoct30.sns.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {

	private final PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	
	
	
	// 게시글 작성
	public boolean addPost(
			long userId
			, String contents
			, MultipartFile File) {
		
		// 파일 추가하는 기능
		String imagePath = FileManager.saveFile(userId, File);
		
		
		Post post = Post.builder()
				.userId(userId)
				.contents(contents)
				.imagePath(imagePath)
				.build();
		
		
		try {
			postRepository.save(post);			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
		
		
	}
	
	
}
