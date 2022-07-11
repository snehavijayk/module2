package flightms.module1.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import flightms.module1.User;
import flightms.module1.controllers.UserController;
import flightms.module1.exceptions.LoginException;
import flightms.module1.exceptions.UserAlreadyExistException;
import flightms.module1.exceptions.UserNotFoundException;
import flightms.module1.repositories.PasswordRepository;
import flightms.module1.repositories.UserRepository;
import flightms.module1.serviceins.UserServiceImpl;
@Transactional
@Component
public class UserDaoImpl implements UserServiceImpl{
	@Autowired
	private UserRepository uRepos;
	@Autowired
	private PasswordRepository psRepo;
	
	@Override
	public User addUser(User user) throws UserAlreadyExistException {
		if((uRepos.getEmail(user.getEmail())==null)) {
			psRepo.save(user.getUserPassword());
			uRepos.save(user);
			return user;			
		}
		else { throw new UserAlreadyExistException("email already present in database");
		}
	}
	
	@Override
	public User viewUser(BigInteger userId) throws UserNotFoundException {
		if(uRepos.existsById(userId)) {
			User u =uRepos.getById(userId);
			return u;
		}
		else {throw new UserNotFoundException("user with id "+ userId +", is not present");
		}
		
		
	}
	public User viewUser(String userMail) {
		return uRepos.getUserByMail(userMail);
	}
	
	@Override
	public List<User> viewUser() {
		
		List<User> list=uRepos.findAll();
		return list;
	}
	
	@Override
	public void updateUser(User user) throws LoginException, UserNotFoundException {
		if(uRepos.existsById(user.getUserId())) {
			if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserId().equals(user.getUserId())) {
				uRepos.updateUser(user.getUserType(), user.getUserName(), user.getUserPhone(), user.getEmail(),user.getUserId());
			}
			else {throw new LoginException("please login with your account first");
			}
		}
		else {throw new UserNotFoundException("user with id "+ user.getUserId() +", is not present");
		}
	}

	@Override
	public void deleteUser(BigInteger userId) throws LoginException, UserNotFoundException {
		if(uRepos.existsById(userId))
			if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserId().equals(userId)) {
				BigInteger psdId=uRepos.getById(userId).getUserPassword().getId();
				try {
					uRepos.deleteById(userId);
					psRepo.deleteById(psdId);
				}
				catch(Exception e) {}
			}
			else {throw new LoginException("please login with your account first");
			}
		else {throw new UserNotFoundException("user with id "+ userId +", is not present");
		}
	}
	
	
	@Override
	public boolean validateUser(String userMail, String ps) throws LoginException {
//		Scanner sc=new Scanner(System.in);
		//System.out.println("enter password");
		//String ps=sc.nextLine();
		if(uRepos.getUserByMail(userMail)==null) {
			return false;
		}
		if(uRepos.getUserByMail(userMail).getUserPassword().getPassword().equals(ps)) {
//			return uRepos.getUserByMail(userMail);
			return true;
		}
		else {
//			throw new LoginException("login failed, wrong password");
			return false;}	
	}
	
	public void changePassword(BigInteger userId) throws LoginException {
		Scanner sc=new Scanner(System.in);
		User u=uRepos.getById(userId);
		System.out.println("enter old password");
		String oldPs=sc.nextLine();
		if(u.getUserPassword().getPassword().equals(oldPs)) {
			System.out.println("enter new password");
			String newPs=sc.nextLine();
			psRepo.changePassword(newPs,u.getUserPassword().getId());
			
		}
		else {throw new LoginException("you entered wrong old password");
		}
	}
	
	public User getUserByMail(String mail) {
		
		User u=uRepos.getUserByMail(mail);
		System.out.println(u.getUserName());
		return u;
				
	}
}
