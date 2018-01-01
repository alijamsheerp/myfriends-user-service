package com.myfriends.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfriends.userservice.model.User;
import com.myfriends.userservice.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
		
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public User getUser(String email){
		
		return userRepository.findOne(email);
	}
}
