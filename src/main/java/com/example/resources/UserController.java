package com.example.resources;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.dao.UserRepository;
import com.example.model.User;
import com.example.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	
	
	@RequestMapping(value = "/users",method = RequestMethod.GET,produces = {"application/json"})
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if(users.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getUser(@PathVariable("id") long id){
		User user = userService.findUserById(id);
		if(user==null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PostMapping(value = "/user",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> createUser(@RequestBody User user,UriComponentsBuilder builder){
		
		userService.createUser(user);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity(headers,HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/user/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> updateUser(@PathVariable("id") long id,@RequestBody User user){
		User userDetails = userService.findUserById(id);
		if(userDetails==null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		userService.updateUser(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<?> removeUser(@PathVariable("id") long id){
		User user = userService.findUserById(id);
		if(user==null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		userService.removeUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
}
