package flightms.module1.serviceins;

import java.math.BigInteger;
import java.util.List;
import flightms.module1.User;
import flightms.module1.exceptions.LoginException;
import flightms.module1.exceptions.UserAlreadyExistException;
import flightms.module1.exceptions.UserNotFoundException;

public interface UserServiceImpl {
    User addUser( User user) throws UserAlreadyExistException;
	
	User viewUser (BigInteger userId) throws UserNotFoundException;
	
	List<User> viewUser();
	
	void updateUser (User user) throws LoginException, UserNotFoundException;
	
	void deleteUser (BigInteger userId) throws LoginException, UserNotFoundException;

	boolean validateUser(String userMail, String ps) throws LoginException; 

}
