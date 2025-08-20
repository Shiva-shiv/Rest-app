package com.wipro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.DAO.UserRepository;
import com.wipro.dto.UserNameDTO;
import com.wipro.exception.UserException;
import com.wipro.model.User;
@Service
@Transactional
public class UserServiceJpaImpl implements userServiceJpa {
	@Autowired
	private UserRepository repo;
	@Override
	public User getUserById(Integer userid) throws UserException {
		User user = repo.findById(userid).orElseThrow(()-> new UserException("User not found"));
		return user;
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		try {
			List<User> list = repo.findAll();
			if(list.isEmpty()) {
				throw new UserException("no data found");
			}
			else {
				return list;
			}
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public User adduser(User user) throws UserException {
		try {
			return repo.save(user);
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}

	}

	@Override
	public User updateuser(User user) throws UserException {
		try {
			return repo.save(user);
		}
		catch(Exception e){
			throw new UserException(e.getMessage(),e);
		}

	}

	@Override
	public String deleteuser(Integer userid) throws Exception {
		try {
			repo.deleteById(userid);
			return userid+" removed succesfully";
		}
		catch(Exception e){
			throw new UserException(e.getMessage(),e);
		}		
	}

	@Override
	public List<User> findByFirstName(String firstName) throws UserException {
		try {
			List<User> listFname = repo.findByFirstName(firstName);
			if(listFname.isEmpty()) {
				throw new UserException("No Users found by this address");
			}
			else {
				return listFname;
			}
		}
		catch(UserException e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public List<User> findByAddress(String address) throws UserException  {
		try {
			List<User> listAddr = repo.findByAddress(address);
			if(listAddr.isEmpty()) {
				throw new UserException("No Users found by this address");
			}
			else {
				return listAddr;
			}
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public List<User> findByEmailContaining(String domainName) throws UserException  {
		try {
			List<User> listDomain =  repo.findByEmailContaining(domainName);
			if(listDomain.isEmpty()) {
				throw new UserException("No Users found by this address");
			}
			else {
				return listDomain;
			}
		}
		catch(UserException e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public List<User> findBybirthdate(LocalDate startDate, LocalDate endDate) throws UserException {
		try {
			List<User> listBirth =  repo.findBybirthdate(startDate, endDate);
			if(listBirth.isEmpty()) {
				throw new UserException("No Users found by this address");
			}
			else {
				return listBirth;
			}
		}
		catch(UserException e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public String addUsers(List<User> list) throws UserException {
		try {
			repo.saveAll(list);
			return "All users saved successfully";
		}
		catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public List<User> findAllByPage(Integer pageNO, Integer pageSize) {
		try {
			Pageable page = PageRequest.of(pageNO-1, pageSize);
			List<User> list = repo.findAll(page).toList();
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<User> sortByProperty(Integer pageNo,Integer pageSize,String property) {
		try {
			Pageable page = PageRequest.of(pageNo-1,pageSize,Sort.by(property));
			List<User> list = repo.findAll(page).toList();
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<String> getUserFirstName() throws UserException {
		List<String> userFirstNameList = repo.getUsersFirstName();
		return userFirstNameList;
	}

	@Override
	public UserNameDTO findUserDTOName(Integer id) throws UserException {
		
		return repo.findUserDTOName(id);
	}



}
