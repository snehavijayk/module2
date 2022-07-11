package flightms.module1.controllers;

import flightms.module1.Airport;
import flightms.module1.dao.AiportDaoImpl;
import flightms.module1.exceptions.AirportNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class AiportController {
	@Autowired
	private AiportDaoImpl apdao;
	
	@GetMapping(path="/airport/{airportloc}")
	public Airport viewAirport(@PathVariable String airportloc) throws AirportNotFoundException{
		return apdao.viewAirport(airportloc);
	}
	
	@GetMapping(path="/airport/all")
	public List<Airport> viewAirport(){
		System.out.println("airport called");
		
		List<Airport> aplist=apdao.viewAirport();
		System.out.println(aplist.get(0).getAirportCode());
//		return apdao.viewAirport();
		return aplist; 
	}

}
