package com.wipro.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.dto.UserNameDTO;
import com.wipro.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//custom query
	List<User> findByFirstName(String firstName);
	List<User> findByAddress(String address);
	List<User> findByEmailContaining(String domainName);
	//Named query
	//return user born b/w two givin dates
	@Query("select u from User u where u.birthdate >=:startDate and u.birthdate<= :endDate")
	List<User> findBybirthdate(@Param ("startDate") LocalDate startDate,@Param ("endDate") LocalDate endDate);
	//Page findAll(Pageable page);
	//native query there direct sql queries 
	@Query(value  = "select first_Name from wipro_user ", nativeQuery = true)
	List<String> getUsersFirstName();
	
	
	//custom payload
	
	@Query("select new com.wipro.dto.UserNameDTO(u.userid,u.firstName,u.lastName) from User u where u.userid = :id")
	UserNameDTO findUserDTOName(@Param("id") Integer id);


	
}
