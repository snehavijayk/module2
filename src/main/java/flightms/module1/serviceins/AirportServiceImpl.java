package flightms.module1.serviceins;

import java.util.List;
import flightms.module1.Airport;
import flightms.module1.exceptions.AirportNotFoundException;

public interface AirportServiceImpl {
	List<Airport> viewAirport();
	Airport viewAirport(String airportCode) throws AirportNotFoundException;

}
