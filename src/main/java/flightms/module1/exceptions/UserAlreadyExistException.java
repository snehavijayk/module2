package flightms.module1.exceptions;

public class UserAlreadyExistException extends Exception{
	public UserAlreadyExistException(String msg) {
		super(msg);
	}
}
