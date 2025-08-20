package com.wipro.service;

import java.time.LocalDate;
import java.util.List;

import com.wipro.dto.UserNameDTO;
import com.wipro.exception.UserException;
import com.wipro.model.User;

public interface userServiceJpa {
	public abstract User getUserById(Integer userid) throws UserException;
	List<User> getAllUsers() throws UserException;
	User adduser(User user) throws UserException;
	String addUsers(List<User> list)throws UserException;
	User updateuser(User user) throws UserException;
	String deleteuser(Integer userid) throws Exception;
	List<User> findByFirstName(String firstName)throws UserException;
	List<User> findByAddress(String address) throws UserException;
	List<User> findByEmailContaining(String domainName)throws UserException;
	List<User> findBybirthdate(LocalDate startDate,LocalDate endDate)throws UserException;
	List<User> findAllByPage(Integer pageNO,Integer pageSize);
	List<User> sortByProperty(Integer pageNo,Integer pageSize,String property);
	List<String> getUserFirstName()throws UserException;
	
	//custom payload
	UserNameDTO findUserDTOName(Integer id)throws UserException;
	
}
