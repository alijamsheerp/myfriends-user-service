package com.myfriends.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfriends.userservice.model.User;
import com.myfriends.userservice.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void save(User n) {
		userRepository.save(n);
		
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

}
