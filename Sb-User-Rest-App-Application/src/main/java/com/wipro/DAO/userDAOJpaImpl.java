package com.wipro.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wipro.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class userDAOJpaImpl implements UserJpaDAO  {
	@PersistenceContext
	private EntityManager manager;
	@Override
	public User getUserById(Integer userid) throws Exception {
		try {

			User user = manager.find(User.class, 1);
			return user;
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		String jql = "select u from User u";
		try {
			TypedQuery<User> query = manager.createQuery(jql,User.class);
			List<User> userList = query.getResultList();
			return userList;
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public User adduser(User user) throws Exception {
		try {
			manager.persist(user);
			return user;
		}catch(Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public User updateuser(User user, Integer id) throws Exception {
		try {
			User orgUser = manager.find(User.class, id);
			if(orgUser == null) {
				throw new Exception ("User id not found ");
			}
			else {
					
				manager.merge(orgUser);
				return orgUser;
			}
		}catch(Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public String deleteuser(Integer userid) throws Exception {
		try {
			User orgUser = manager.find(User.class, userid);
			manager.remove(orgUser);
			return orgUser.getFirstName()+"User removed successfully";
		}catch(Exception e) {
			throw new Exception(e);
		}
	}

}
