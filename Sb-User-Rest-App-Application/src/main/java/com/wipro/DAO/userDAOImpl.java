package com.wipro.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wipro.model.User;
@Repository
public class userDAOImpl implements userDAO {
	private static List<User> userList = new ArrayList<>();
	static {
		userList.add(new User(1,"siva","subramanian",LocalDate.of(2025, 12, 12),"chennai",1234567890L,"siva@gmail.com"));
		userList.add(new User(2,"meena","lochani",LocalDate.of(2024, 10, 27),"madurai",9300787468L,"meena@gmail.com"));
		userList.add(new User(3,"ganesha","moorthy",LocalDate.of(2023, 5, 10),"trichy",9540787448L,"ganesh@gmail.com"));
		userList.add(new User(4,"nandhini","moorthy",LocalDate.of(2022, 7, 9),"karaikudi",9940787433L,"nandhini@gmail.com"));
	}
	@Override
	public User getUserById(Integer userid) throws Exception {
		try {
			System.out.println(userid);
			for(User u : userList) {
				if(u.getUserid().equals(userid)) {
					return u;
				}
				
			}
			throw new Exception("Invalid User id");
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		try {
			List<User> list = new ArrayList<>();
			if(userList.isEmpty()) {
				throw new Exception("No users found in List");
			}
			else {
				for(User u: userList) {
					list.add(u);
				}
			}
			return list;
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public User adduser(User user) throws Exception {
		try {
			userList.add(user);
			System.out.println(userList.get(0));
			return user;
		}catch(Exception e ) {
			throw e;
		}
		//return null;
	}

	@Override
	public User updateuser(User user,Integer id) throws Exception {
		try {
				User u = userList.get(id-1);
				if(u == null) {
					throw new Exception ("User id not found ");
				}
				else {
					u.setFirstName(user.getFirstName());
					u.setLastName(user.getLastName());
					u.setAddress(user.getAddress());
					u.setBirthdate(user.getBirthdate());
					u.setEmail(user.getEmail());
					u.setMobile(user.getMobile());	
				}
				return u;
		}catch(Exception e) {
			throw e;
		}
	//	return null;
	}

	@Override
	public String deleteuser(Integer userid) throws Exception {
		try {
			User user = userList.get(userid-1);
			System.out.println("from dao "+userid);
			//Integer index = 0;
			if(user == null) {
				throw new Exception(" User id not found ");
			}
			else {
				userList.remove(userid-1);
			}
			/*
			for(User u :userList) {
				if(u.getUserid().equals(userid)) {
					System.out.println("check ");
					Integer index = userList.indexOf(u);
					System.out.println("index check " +index);
					userList.remove(index);
					//user = u;
					//System.out.println(u.getUserid()-1);
					
				}
				else {
					throw new Exception(" User id not found ");
				}
			}*/
			return "user removed";
			//userList.remove(index);
			
		}catch(Exception e) {
			throw e;
		}
		//return null;
	}


}
