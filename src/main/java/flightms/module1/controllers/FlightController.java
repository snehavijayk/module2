package flightms.module1.controllers;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import flightms.module1.Flight;
import flightms.module1.dao.FlightDaoImpl;
import flightms.module1.exceptions.FlightNotFoundException;
import flightms.module1.exceptions.LoginException;
@CrossOrigin
@RestController
public class FlightController {
	@Autowired
	private FlightDaoImpl fdao;
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(FlightController.class);
	
	@PostMapping(path="/flight/add")
	public Flight addFlight(@RequestBody Flight flight) throws LoginException {
		Flight f=fdao.addFlight(flight);
		logger.info(f.getFlightModel()+ " "+f.getCarrierName()+" "+f.getSeatCapacity()+" "+f.getFlightNumber());
		return f;
		
	}
	
	@PutMapping(path="/flight/modify")
	public Flight modfyFlight(@RequestBody Flight flight) throws LoginException {
		logger.info(flight.getFlightModel()+ " "+flight.getCarrierName()+" "+flight.getSeatCapacity()+" "+flight.getFlightNumber()+" -modified");
		System.out.println(flight);
		return fdao.modifyFlight(flight);
	}
	
	@GetMapping(path="/flight/{id}")
	public Flight viewFlight(@PathVariable BigInteger id) throws FlightNotFoundException, LoginException {
		System.out.println(id);
		System.out.println(fdao.viewFlight(id));
		return fdao.viewFlight(id);
	}
	
	@GetMapping(path="/flight/all")
	public List<Flight> viewFlight() throws LoginException {
//		System.out.println("called");
		return fdao.viewFlight();
	}
	
	@DeleteMapping(path="/flight/delete/{id}")
	public void deleteFlight(@PathVariable BigInteger id) throws FlightNotFoundException {
		fdao.deleteFlight(id);
		logger.info("flight deleted of id "+ id);
	}
}
