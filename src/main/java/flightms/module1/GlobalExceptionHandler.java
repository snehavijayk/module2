package flightms.module1;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import flightms.module1.exceptions.*;

@ControllerAdvice
public class GlobalExceptionHandler{
ErrorInfo erinfo =null;
	
	@ExceptionHandler(FlightAlreadyExistException.class)
	
	public @ResponseBody ErrorInfo FlightAlreadyExistsExceptionInfo(FlightAlreadyExistException e,HttpServletRequest req) {
		
		
		erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	
	
	
	@ExceptionHandler(FlightNotFoundException.class)
	public @ResponseBody ErrorInfo NoSuchFlightFoundExceptionInfo(FlightNotFoundException e,HttpServletRequest req) {
		//erinfo.setSuggestion("Check Flight Number");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
    public @ResponseBody ErrorInfo UserAlreadyExistExceptionInfo(UserAlreadyExistException e,HttpServletRequest req) {
		
		//erinfo.setSuggestion("Check User Details");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
		
	}
	
	@ExceptionHandler (UserNotFoundException.class)
	public @ResponseBody ErrorInfo  NoSuchUserFoundExceptionInfo (UserNotFoundException e,HttpServletRequest req)
	{
		//erinfo.setSuggestion("Check User Id");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	@ExceptionHandler (AirportNotFoundException.class)
	public @ResponseBody ErrorInfo  NoSuchAirportFoundExceptionInfo (AirportNotFoundException e,HttpServletRequest req)
	{
		//erinfo.setSuggestion("Check User Id");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	@ExceptionHandler (BookingNotFoundException.class)
	public @ResponseBody ErrorInfo  NoSuchBookingFoundExceptionInfo (BookingNotFoundException e,HttpServletRequest req)
	{
		//erinfo.setSuggestion("Check User Id");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	@ExceptionHandler (ScheduledFlightNotFoundException.class)
	public @ResponseBody ErrorInfo  NoSuchScheduledFlightFoundExceptionInfo (ScheduledFlightNotFoundException e,HttpServletRequest req)
	{
		//erinfo.setSuggestion("Check User Id");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	@ExceptionHandler (LoginException.class)
	public @ResponseBody ErrorInfo UserNotLoggedIn(LoginException e,HttpServletRequest req) {
		//erinfo.setSuggestion("Check Flight Number");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	
	@ExceptionHandler (InvalidException.class)
	public @ResponseBody ErrorInfo InvalidException(InvalidException e,HttpServletRequest req) {
		//erinfo.setSuggestion("Check Flight Number");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ErrorInfo  MethodArgumentWrongPatterInfo (MethodArgumentNotValidException e,HttpServletRequest req)
	{
		//erinfo.setSuggestion("Check User Id");
		 erinfo = new ErrorInfo(LocalDateTime.now(),e.getBindingResult().toString(),req.getRequestURI());
		 System.out.println(e.getBindingResult().toString());
		 return erinfo;
	}	
}

