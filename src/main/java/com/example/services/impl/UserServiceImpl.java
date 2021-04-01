package com.example.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserRepository;
import com.example.model.User;
import com.example.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void createUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User findUserById(long id) {
		
		return userRepository.findUserById(id);
	}

	@Override
	public void removeUser(User user) {
		userRepository.delete(user);
		
	}
	
}
