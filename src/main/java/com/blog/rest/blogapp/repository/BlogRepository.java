package com.blog.rest.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.rest.blogapp.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	
}
