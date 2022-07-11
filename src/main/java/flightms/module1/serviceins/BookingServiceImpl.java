package flightms.module1.serviceins;

import java.math.BigInteger;
import java.util.List;

import flightms.module1.Booking;
import flightms.module1.Passenger;
import flightms.module1.ScheduledFlight;
import flightms.module1.exceptions.AirportNotFoundException;
import flightms.module1.exceptions.BookingNotFoundException;
import flightms.module1.exceptions.InvalidException;
import flightms.module1.exceptions.LoginException;

public interface BookingServiceImpl {

	
	Booking addBooking() throws LoginException, AirportNotFoundException, InvalidException;
	
	Booking modifyBooking(Booking booking) throws LoginException, BookingNotFoundException;
	
	//List<Booking>viewBooking() throws LoginException;
	
	void deleteBooking(BigInteger bookingId) throws LoginException;
    
	//extra
	List<ScheduledFlight> searchFlights(String airport1name, String airport2name, String date) throws LoginException;
	
	boolean validateBooking(Booking booking) throws InvalidException;
	
    boolean validatePassenger(Passenger passenger);

    //extra
	List<Booking> viewBooking(String mail) throws LoginException;


	
    
}
