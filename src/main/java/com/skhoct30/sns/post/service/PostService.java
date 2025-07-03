package com.skhoct30.sns.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skhoct30.sns.common.FileManager;
import com.skhoct30.sns.post.domain.Post;
import com.skhoct30.sns.post.dto.PostDto;
import com.skhoct30.sns.post.repository.PostRepository;
import com.skhoct30.sns.user.domain.User;
import com.skhoct30.sns.user.service.UserService;

import jakarta.persistence.PersistenceException;



@Service
public class PostService {

	private final PostRepository postRepository;
	private final UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
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
	
	
	
	public List<PostDto> getPostList() {
		
		// List<Post> postList = postRepository.findByUserIdOrderByIdDesc(userId);

		//return postList;
		
		List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
				
	
		List<PostDto> postDtoList = new ArrayList<>();
		
		for(Post post:postList) {
			
			User user = userService.getUserById(post.getUserId());
			
			PostDto postDto = PostDto.builder()
			.id(post.getId())
			.nickname(user.getNickname())
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.userId(post.getUserId())
			.build();
			
			postDtoList.add(postDto);
		}
		return postDtoList;
		
		
	}
	
	
	
	// 한명의 게시물을 얻어오는 내용
	// 프라이머리 키로 메모 하나를 얻어와 한행의 정보가 일치하는지 확인
	
	public Post getPost(long id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			return optionalPost.get();
			//true
		} else {
			return null;
		}
		
	}
	
	
}
