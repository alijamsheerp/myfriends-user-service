package com.myfriends.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myfriends.userservice.model.User;
import com.myfriends.userservice.services.UserService;

@RestController
@RequestMapping(path="/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/ping")
	@ResponseBody
	public String ping(){
		return "Authentication Service is UP and RUNNING";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, consumes = "application/json") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestBody User user) {
		
		userService.save(user);
		return "Saved";
	}
	
/*	@GetMapping(path="/all") // <-- This works but don't expose externally as this return all the user data
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON
		return userService.findAll();
	}*/
	
	@RequestMapping(value="/getUser", method = RequestMethod.POST, consumes = "application/json") // Map ONLY GET Requests
	public @ResponseBody String getUser (@RequestBody User user) {
		
		String returnMessage = "Invalid Email or Passord";
		
		if(user.getEmail() !=null && user.getPassword() != null){
			User userData = null;
			try {
				userData = userService.getUser(user.getEmail());
			} catch (Exception e){
				returnMessage = "Invalid Email";
			}
			
			if(userData != null && userData.getPassword().equals(user.getPassword())){
				returnMessage = userData.getFirstName()+"#"+userData.getLastName();
			} else {
				returnMessage = "Invalid Password";
			}
		}
		
		return returnMessage;
	}
	
}
