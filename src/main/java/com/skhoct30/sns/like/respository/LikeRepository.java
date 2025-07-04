package com.skhoct30.sns.like.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skhoct30.sns.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
