package flightms.module1.controllers;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import flightms.module1.Password;
import flightms.module1.User;
import flightms.module1.dao.UserDaoImpl;
import flightms.module1.exceptions.LoginException;
import flightms.module1.exceptions.UserAlreadyExistException;
import flightms.module1.exceptions.UserNotFoundException;
import flightms.module1.repositories.UserRepository;

import java.util.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserDaoImpl udao;
	@Autowired
	private UserRepository uRepos;
	
	private static boolean loginSuccess;
	private static User loggedInUser;
	
	
	public static User getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(User user) {
		UserController.loggedInUser = user;
	}

	public static boolean isLoginSuccess() {
		return loginSuccess;
	}

	public static void setLoginSuccess(boolean ls) {
		loginSuccess = ls;
	}

	@PostMapping (path = "/user/add")
	public User addUser(@Valid @RequestBody User user) throws UserAlreadyExistException {
		//System.out.println("adding");
		return udao.addUser(user);
	}
	
	@GetMapping(path = "/user/{userId}")
	public User viewUser(@PathVariable BigInteger userId) throws UserNotFoundException {
		
		return udao.viewUser(userId);
	}
	
	@GetMapping (path = "/user/all")
	public List<User> viewUser (){
		
		return udao.viewUser();
	}
	
	@PutMapping (path = "/user/update")
	public void updateUser(@RequestBody User user) throws LoginException, UserNotFoundException {
		
		udao.updateUser(user);
	}
	//@JsonIgnoreProperties
	@DeleteMapping (path = "/user/delete/{userId}")
	public void deleteUser(@PathVariable BigInteger userId ) throws LoginException, UserNotFoundException {
		
		udao.deleteUser(userId);
	}
	
//	@PostMapping(path="/user/login/{userid}/{password}")
//	public String userLogin(@PathVariable String userid, @PathVariable String password) throws LoginException, UserNotFoundException {
//		BigInteger uid=new BigInteger(userid);
//		if(udao.validateUser(userid,password).equals(udao.viewUser(uid))){
//			UserController.setLoginSuccess(true);
//			UserController.setLoggedInUser(uRepos.getById(uid));
//			//System.out.println("welcome "+getLoggedInUser().getUserName());
//			System.out.println( "welcome "+getLoggedInUser().getUserName());
//			return "welcome "+getLoggedInUser().getUserName();
//		}
//		else {return "login failed";}
//	}
	
	//second login method for front end with mail id
	@PostMapping(path="/user/login/{userMail}/{password}")
	public boolean userMailLogin(@PathVariable String userMail, @PathVariable String password) throws LoginException, UserNotFoundException {
		return udao.validateUser(userMail, password);
	}
	
	
	
	@PutMapping(path="/user/logout")
	public void logoutUser() {
		UserController.setLoggedInUser(null);
		UserController.setLoginSuccess(false);
	}
	
	@PutMapping(path="/user/changepassword/{userid}")
	public void changePassword(@PathVariable BigInteger userid) throws LoginException {
		udao.changePassword(userid);
	}
	
	@GetMapping(path="/user/get/{mail}")
	public User getUserByMail(@PathVariable String mail) {
		User u=udao.getUserByMail(mail);
//		return udao.getUserByMail(mail);
		System.out.println(u.getUserType());
		return u;
		}
}
