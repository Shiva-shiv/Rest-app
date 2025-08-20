package com.wipro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.DAO.UserJpaDAO;
import com.wipro.exception.UserException;
import com.wipro.model.User;
@Service
@Transactional
public class userServiceImpl implements userService {
	@Autowired
	private UserJpaDAO dao;
	@Override
	public User getUserById(Integer userid) throws UserException  {
		try {
			//System.out.println("from service : "+userid);
			User user = dao.getUserById(userid);
			return user;
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		try {
			List<User> list = new ArrayList<>();
			list = dao.getAllUsers();
			return list;
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public User adduser(User user) throws UserException {
		try {
			return dao.adduser(user);
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public User updateuser(User user,Integer uid) throws UserException {
		try {
			return dao.updateuser(user,uid);
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public String deleteuser(Integer userid) throws Exception {
		try {
			
			User user = dao.getUserById(userid);
			System.out.println("From service class start");
			System.out.println(user);
			System.out.println("From service class");
			dao.deleteuser(userid);
			return user.getFirstName()+" removed successfully !";
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

}
