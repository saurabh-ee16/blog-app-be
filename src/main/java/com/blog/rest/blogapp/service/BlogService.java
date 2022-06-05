package com.blog.rest.blogapp.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.rest.blogapp.model.Blog;
import com.blog.rest.blogapp.model.BlogStatus;
import com.blog.rest.blogapp.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	BlogRepository blogRepo;

	@Autowired
	EntityManager entityManager;

	@PostConstruct
	private void init() {
		Blog blog = new Blog();
		blog.setPostStatus(BlogStatus.PENDING);
		blog.setDate(new Date());
		blog.setSubject("lorem ipsum");
		blog.setDescription(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sed ipsum vitae ante viverra bibendum non quis diam. Sed eu sapien ac tortor elementum consectetur id ut nulla. Phasellus efficitur dolor quis eros porttitor, ac tempus mauris ornare. Nullam nec est sit amet lectus eleifend hendrerit. Maecenas dictum enim ultrices, tempus lacus ut, pellentesque nibh. Nunc ac finibus tellus, sit amet viverra mi. Nulla ut tristique quam, et scelerisque nibh. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc quis velit luctus, mattis augue id, consectetur orci.\r\n"
						+ "\r\n"
						+ "Suspendisse efficitur odio id scelerisque rhoncus. Morbi malesuada lacinia eros non consectetur. Duis in enim eget metus malesuada suscipit. Pellentesque venenatis elit nec quam dapibus, quis feugiat neque pulvinar. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc eu pellentesque magna, et dignissim magna. Fusce molestie, tellus at sagittis venenatis, massa velit lacinia nibh, dignissim vulputate est sem ac est. Donec quis diam bibendum eros commodo rutrum.");
		blogRepo.save(blog);
	}

	public List<Blog> getAllBlogs() {

		List<Blog> blogsList = blogRepo.findAll();

		Collections.sort(blogsList, new Comparator<Blog>() {

			@Override
			public int compare(Blog o1, Blog o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		return blogsList;

	}

	public Optional<Blog> getBlog(Long id) {

		Optional<Blog> optional = blogRepo.findById(id);
		return optional;

	}

	public void save(Blog blog) {
		blogRepo.save(blog);
	}

	public boolean deleteById(Long id) {
		
		blogRepo.deleteById(id);
		return true;
	}

}
