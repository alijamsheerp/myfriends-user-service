package com.myfriends.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myfriends.userservice.model.User;
import com.myfriends.userservice.services.UserService;

@RestController
@RequestMapping(path="/myfriends")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/ping")
	@ResponseBody
	public String ping(){
		return "Authentication Service is UP and RUNNING";
	}
	
	//Copied from Demo
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User n = new User();
		n.setFirstName(name);
		n.setEmail(email);
		userService.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userService.findAll();
	}
}
