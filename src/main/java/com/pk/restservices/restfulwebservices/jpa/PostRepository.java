package com.pk.restservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.restservices.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{

}
