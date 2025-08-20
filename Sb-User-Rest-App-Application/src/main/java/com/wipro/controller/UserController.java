package com.wipro.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.dto.UserNameDTO;
import com.wipro.exception.UserException;
import com.wipro.model.User;
import com.wipro.service.userServiceJpa;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	//private userService service;
	private userServiceJpa service;
	//private Logger logger = LoggerFactory.getLogger(ControllerClass.class);
	//http://localhost:8082/api/v1/user/greet";
	@GetMapping("/user/greet")
	public String greetings() {
		return "Good Morning, Welcome!";
	}
	
	//		http://localhost:8082/api/user/get/1
	@GetMapping("/user/get/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer uid) {
		try {

			User user = service.getUserById(uid);
			//logger.info(user.toString());
			return new ResponseEntity<>(user,HttpStatus.OK);
			//log.info(user.toString());
		} catch (UserException e) {
			//			logger.error(e.getMessage(),e);

			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found",e);
			//e.printStackTrace();
		}
		//return null;
	}

	@GetMapping("/user/getAll")
	public ResponseEntity<List<User>> getAllUser(Model model) {
		try {
			List<User> list = service.getAllUsers();
			model.addAllAttributes(list);
			//	logger.info(list.toString());
			log.info(list.toString());
			return new ResponseEntity<>(list,HttpStatus.OK);
			//return list;

		} catch (UserException e) {
			//logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Users not found",e);
			//e.printStackTrace();
		}

	}

	@PostMapping("/user/add")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			User user1 = service.adduser(user);
			//logger.info(user1.toString());
			log.info(user1.toString());
			return new ResponseEntity<>(user1,HttpStatus.OK);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to add user",e);
		}

	}

	@PostMapping("/user/addall")
	public ResponseEntity<String> addAllUsers(@RequestBody List<User> list) {
		try {
			String msg = service.addUsers(list);
			return new ResponseEntity<>(msg,HttpStatus.OK);

		} catch (UserException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to add user list",e);
		}

	}

	@PutMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		try {
			//User u1 = service.getUserById(uid);
			//u1 = service.updateuser(user);
			User u1= service.adduser(user);
			//logger.info(u1.toString());
			log.info(u1.toString());
			return new ResponseEntity<>(u1,HttpStatus.OK);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Update user",e);
		}

	}
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
		try {
			String msg = service.deleteuser(id);
			//logger.info(msg);
			log.info(msg);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Delete user",e);
		}

	}

	//fetching by First name

	@GetMapping("/user/getbyfirstname/{name}")
	public ResponseEntity<List<User>> getUserByFirstName(@PathVariable("name") String fname) {
		try {

			List<User> userList = service.findByFirstName(fname);
			//logger.info(user.toString());
			if(userList.isEmpty()) {
				throw new UserException("No data found by the name "+fname);
			}
			return new ResponseEntity<>(userList,HttpStatus.OK);
			//log.info(user.toString());
		} catch (UserException e) {
			//			logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Get user First Name",e);
			//e.printStackTrace();
		}

	}

	//fetching by domain name

	@GetMapping("/user/getbydomain/{domain}")
	public ResponseEntity<List<User>> getUserByDomain(@PathVariable("domain") String domain) {
		try {

			List<User> userList = service.findByEmailContaining(domain);
			//logger.info(user.toString());
			if(userList.isEmpty()) {
				throw new UserException("No data found by the domain "+domain);
			}
			return new ResponseEntity<>(userList,HttpStatus.OK);
			//log.info(user.toString());
		} catch (UserException e) {
			//			logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Get user First Name",e);
			//e.printStackTrace();
		}

	}

	//fetching by domain name

	@GetMapping("/user/getbyaddress/{address}")
	public  ResponseEntity<List<User>> getUserByAddress(@PathVariable("address") String address) {
		try {

			List<User> userList = service.findByAddress(address);

			//logger.info(user.toString());
			if(userList.isEmpty()) {
				throw new UserException("No data found by the address "+address);
			}
			return new ResponseEntity<>(userList,HttpStatus.OK);
			//log.info(user.toString());
		} catch (UserException e) {
			//			logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Get user First Name",e);
			//e.printStackTrace();
		}

	}

	//fetching by birthdate 

	@GetMapping("/user/getbybirth/{startDate}/{endDate}")
	public ResponseEntity<List<User>> getUserByBirthdate(@PathVariable("startDate") LocalDate startDate ,@PathVariable("endDate") LocalDate endDate ) {
		try {

			List<User> userList = service.findBybirthdate(startDate, endDate);

			//logger.info(user.toString());
			if(userList.isEmpty()) {
				throw new UserException("No data found ");
			}
			return new ResponseEntity<>(userList,HttpStatus.OK);
			//log.info(user.toString());
		} catch (UserException e) {
			//			logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Get user First Name",e);
			//e.printStackTrace();
		}

	}
	@GetMapping("/user/page/{pageNo}/{pageSize}")
	public ResponseEntity<List<User>> findByPage(@PathVariable("pageNo") Integer pageNo ,@PathVariable("pageSize") Integer pageSize ) {
		try {

			List<User> userList = service.findAllByPage(pageNo, pageSize);

			//logger.info(user.toString());
			if(userList.isEmpty()) {
				throw new UserException("No data found ");
			}
			return new ResponseEntity<>(userList,HttpStatus.OK);
			//log.info(user.toString());
		} catch (UserException e) {
			//			logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Get user First Name",e);
			//e.printStackTrace();
		}

	}
	@GetMapping("/user/pagesort/{pageNo}/{pageSize}/{property}")
	public ResponseEntity<List<User>> findByPageSort(@PathVariable("pageNo") Integer pageNo ,@PathVariable("pageSize") Integer pageSize,@PathVariable("property") String property ) {
		try {

			List<User> userList = service.sortByProperty(pageNo, pageSize, property);

			//logger.info(user.toString());
			if(userList.isEmpty()) {
				throw new UserException("No data found ");
			}
			return new ResponseEntity<>(userList,HttpStatus.OK);
			//log.info(user.toString());
		} catch (UserException e) {
			//			logger.error(e.getMessage(),e);
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Get user First Name",e);
			//e.printStackTrace();
		}

	}
	@GetMapping("/user/nativequery")
	public ResponseEntity<List<String>> getUserFirstName(){
		try {
			List<String> list = service.getUserFirstName();
			return new ResponseEntity<>(list,HttpStatus.OK);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Unable to Get user First Name",e);
		}

	}
	//custom payload
	@GetMapping("/user/udto/{id}")
	public ResponseEntity<UserNameDTO> getUserNameDTO(@PathVariable("id") Integer id){
		try {
			UserNameDTO dto = service.findUserDTOName(id);
			return new ResponseEntity<>(dto,HttpStatus.OK);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"User not found",e);
		}

	}

}
