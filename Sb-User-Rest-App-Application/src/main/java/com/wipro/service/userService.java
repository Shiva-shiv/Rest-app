package com.wipro.service;

import java.util.List;

import com.wipro.exception.UserException;
import com.wipro.model.User;

public interface userService {
	public abstract User getUserById(Integer userid) throws UserException;
	List<User> getAllUsers() throws UserException;
	User adduser(User user) throws UserException;
	User updateuser(User user,Integer id) throws UserException;
	String deleteuser(Integer userid) throws Exception;
}
