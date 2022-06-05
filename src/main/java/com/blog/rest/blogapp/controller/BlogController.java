package com.blog.rest.blogapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.rest.blogapp.model.Blog;
import com.blog.rest.blogapp.model.BlogStatus;
import com.blog.rest.blogapp.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping(path = "/blog")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BlogController {
	private Logger log = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	private BlogService service;
	@Autowired
	public ObjectMapper objectMapper;

	@GetMapping(path = "/get-blogs")
	public ResponseEntity<Object> retrieveAllBlogs() throws JsonProcessingException {

		List<Blog> listBlogs = service.getAllBlogs();
		String stringBlogs = objectMapper.writeValueAsString(listBlogs);

		return new ResponseEntity<>(stringBlogs, HttpStatus.OK);

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> approveBlog(@PathVariable Long id) {
		Optional<Blog> op = service.getBlog(id);
		if (op.isPresent()) {
			Blog blog = op.get();
			blog.setPostStatus(BlogStatus.APPROVED);
			service.save(blog);
			log.info("blog saved with id : {}", id);
			return new ResponseEntity<Object>(blog, HttpStatus.OK);
		}

		else
			return ResponseEntity.noContent().build();

	}

	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateBlog(@RequestBody Blog blog) {
		service.save(blog);
		log.info("blog updated");
		return new ResponseEntity<Object>(blog, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> retrieveBlogbyId(@PathVariable Long id) throws JsonProcessingException {

		log.info("fetching blog with id : {}", id);
		Optional<Blog> op = service.getBlog(id);
		if (op.isPresent()) {
			String stringBlog = objectMapper.writeValueAsString(op.get());

			return new ResponseEntity<Object>(stringBlog, HttpStatus.OK);
		} else
			return ResponseEntity.noContent().build();

	}

	@PostMapping(path = "/create")
	public ResponseEntity<Object> createBlog(@RequestBody Blog blog) {
		log.info("inside createBlog");
		if (blog.getDate() == null) {
			blog.setDate(new Date());
		}
		service.save(blog);
		log.info("new blog created");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		log.info("deleting blog with id : {}", id);
		if (service.deleteById(id))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.notFound().build();

	}

}
