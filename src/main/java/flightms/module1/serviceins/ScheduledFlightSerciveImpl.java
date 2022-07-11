package flightms.module1.serviceins;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import flightms.module1.*;
import flightms.module1.exceptions.FlightNotFoundException;
import flightms.module1.exceptions.LoginException;
import flightms.module1.exceptions.ScheduledFlightNotFoundException;

public interface ScheduledFlightSerciveImpl {
	
	ScheduledFlight  scheduleFlight(ScheduledFlight sf);
	//Schedules a flight alongwith its timings, locations and capacity
	
	List <ScheduledFlight> viewScheduledFlights(String airport, String airport1, LocalDate ld) throws LoginException;
	//Returns a list of flights between two airports on a specified date.
	
	List<ScheduledFlight> viewScheduledFlights(BigInteger flightNumber) throws LoginException;
	//Returns a list of a scheduled flight identifiable by flight number.
	
	List<ScheduledFlight> viewScheduledFlight() throws LoginException;
	//Shows all the details and status of all flights.
	
	ScheduledFlight	modifyscheduleflight(ScheduledFlight scf) throws LoginException, ScheduledFlightNotFoundException, FlightNotFoundException;
	//Modifies the details of a scheduled flight.
	
	void deleteScheduledFlight(BigInteger flightNumber) throws LoginException, FlightNotFoundException;
	//Removes a flight from the available flights.
	 
	boolean validateScheduledFlight(ScheduledFlight sf);
	//Validates the attributes of a scheduled Flight.


}
