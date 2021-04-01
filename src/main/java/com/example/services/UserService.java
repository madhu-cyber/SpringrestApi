package com.example.services;

import java.util.List;

import com.example.model.User;

public interface UserService {

	void createUser(User user);
	List<User> getAllUsers();
	void updateUser(User user);
	User findUserById(long id);
	void removeUser(User user);
}
