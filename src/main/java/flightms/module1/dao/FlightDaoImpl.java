package flightms.module1.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import flightms.module1.exceptions.FlightNotFoundException;
import flightms.module1.exceptions.LoginException;
import flightms.module1.Flight;
import flightms.module1.controllers.UserController;
import flightms.module1.repositories.FlightServiceRepository;
import flightms.module1.serviceins.FlightServiceImpl;

@Transactional
@Component
public class FlightDaoImpl implements FlightServiceImpl {
	@Autowired
	private FlightServiceRepository frepo;

	@Override
	public Flight addFlight(Flight flight) throws LoginException {
//		if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserType().equalsIgnoreCase("admin")) {
			frepo.save(flight);
			return flight;
//		}
//		else {throw new LoginException("please login with admin account to add flight");
//		}
	}

	@Override
	public Flight modifyFlight(Flight flight) throws LoginException {
//		if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserType().equalsIgnoreCase("admin")) {
//			try {
			frepo.updateFlight(flight.getFlightModel(),flight.getCarrierName(),flight.getSeatCapacity(),flight.getFlightNumber());
			return flight;
//			}
			
//			catch(Exception e) {}
//		}
//		else {
//			throw new LoginException("please login with admin account to add flight");
//		}
		
	}

	@Override
	public Flight viewFlight(BigInteger flightNumber) throws FlightNotFoundException, LoginException {
//		if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserType().equalsIgnoreCase("admin")) {
			Optional<Flight> flight=frepo.findByflightNumber(flightNumber);
			if(flight.isPresent()) {
				return flight.get();
			}
			else {throw new FlightNotFoundException("no flight of flight nnumber "+flightNumber+" is found");
			}
//		}
//		else { throw new LoginException("please login with admin account to view all flights");
//		
//		}
	}

	@Override
	public List<Flight> viewFlight() throws LoginException  {
//		if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserType().equalsIgnoreCase("admin")) {
			return frepo.findAll();
//		}
//		else {
//			throw new LoginException("please login with admin account to view all flights");
//		}
	}

	@Override
	public void deleteFlight(BigInteger flightNumber) throws FlightNotFoundException {
		if(frepo.existsById(flightNumber)) {
			frepo.deleteById(flightNumber);
			//frepo.removeFlight(flightNumber);
		}
		else {throw new FlightNotFoundException("no flight found with flight number "+flightNumber);
		}
	}

	@Override
	public void validateFlight(Flight flight) {	
	}
}
