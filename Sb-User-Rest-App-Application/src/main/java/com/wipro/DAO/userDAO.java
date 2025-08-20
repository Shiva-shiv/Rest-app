package com.wipro.DAO;

import java.util.List;

import com.wipro.model.User;

public interface userDAO {
	public abstract User getUserById(Integer userid) throws Exception;
	List<User> getAllUsers() throws Exception;
	User adduser(User user) throws Exception;
	User updateuser(User user,Integer id) throws Exception;
	String deleteuser(Integer userid) throws Exception;
	
}
