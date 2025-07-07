package com.skhoct30.sns.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skhoct30.sns.comment.dto.CommentDto;
import com.skhoct30.sns.comment.service.CommentService;
import com.skhoct30.sns.common.FileManager;
import com.skhoct30.sns.like.service.LikeService;
import com.skhoct30.sns.post.domain.Post;
import com.skhoct30.sns.post.dto.PostDto;
import com.skhoct30.sns.post.repository.PostRepository;
import com.skhoct30.sns.user.domain.User;
import com.skhoct30.sns.user.service.UserService;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;


// Lombok 에 있는 어노테이션  >> 얘를 쓰면 아래 PostService 에 객체 주입을 생성자를 통해 간결하게 할 수 있다.
@RequiredArgsConstructor

@Service
public class PostService {

	private final PostRepository postRepository;
	private final UserService userService;
	
	private final LikeService likeService;
	private final CommentService commentService;
	
//	public PostService(PostRepository postRepository
//			, UserService userService
//			//, LikeService likeService
//			, CommentService commentService) {
//		this.postRepository = postRepository;
//		this.userService = userService;
//		
//		//this.likeService = likeService;
//		this.commentService = commentService;
//	}
	
	
	
	
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
			
			// 특정 게시물 좋아요 갯수
			int likeCount = likeService.likeCountByPostId(post.getId());
			
			
			
			List<CommentDto> commentList = commentService.getCommentListByPostId(post.getId());
			
			PostDto postDto = PostDto.builder()
			.id(post.getId())
			.nickname(user.getNickname())
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.userId(post.getUserId())
			.likeCount(likeCount)
			.commentList(commentList)
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
