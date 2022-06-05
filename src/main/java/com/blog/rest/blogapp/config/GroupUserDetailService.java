package com.blog.rest.blogapp.config;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.rest.blogapp.model.User;
import com.blog.rest.blogapp.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("userService")
public class GroupUserDetailService implements UserDetailsService {

	private Logger log = LoggerFactory.getLogger(GroupUserDetailService.class);
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		//ObjectMapper obj = new ObjectMapper();   //used for debuging purpose 
		try {
			user = userRepo.findByEmail(email).get();

		} catch (NoSuchElementException | UsernameNotFoundException e ) {
			log.error("Error fetching user details : {}", e.getMessage());
		}
		
		GroupUserDetails newUser = new  GroupUserDetails(user);
		
		/*
		 * try { log.info(obj.writeValueAsString(newUser)); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */		
		return newUser;
	}


}
