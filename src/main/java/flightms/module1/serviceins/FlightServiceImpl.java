package flightms.module1.serviceins;

import java.math.BigInteger;
import java.util.List;

import flightms.module1.*;
import flightms.module1.exceptions.FlightNotFoundException;
import flightms.module1.exceptions.LoginException;

public interface FlightServiceImpl {
	
		public Flight addFlight(Flight flight) throws LoginException;
	   //Adds a new flight which can be scheduled.
		
		Flight modifyFlight(Flight flight) throws LoginException;
		//Modify the details of a flight.
		
		Flight viewFlight(BigInteger flightNumber) throws FlightNotFoundException, LoginException;
		//Shows the details of a flight specified by the flight number.
		
		List<Flight> viewFlight() throws LoginException;
		//View the details of all flights.
		
		void deleteFlight(BigInteger flightNumber ) throws FlightNotFoundException;
		// delete flight
		
		void validateFlight(Flight flight);
		// validate the attributes of flight.
		

}
