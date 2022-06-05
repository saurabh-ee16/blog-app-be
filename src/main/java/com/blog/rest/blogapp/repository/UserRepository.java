package com.blog.rest.blogapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog.rest.blogapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByEmailAndRoles(String email,String role);

}
