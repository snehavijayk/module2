package flightms.module1.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import flightms.module1.exceptions.AirportNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import flightms.module1.Airport;
import flightms.module1.repositories.AirportRepository;
import flightms.module1.serviceins.AirportServiceImpl;

@Component
public class AiportDaoImpl implements AirportServiceImpl{
	
	@Autowired
	private AirportRepository aprepo;;

	@Override
	public List<Airport> viewAirport() {
		List<Airport> airportList = aprepo.viewAllAirports();
//				new ArrayList<Airport>();
//		for(Airport a:aprepo.findAll()){
//			airportList.add(a);
//		}
		return airportList;
	}

	@Override
	public Airport viewAirport(String airportloc) throws AirportNotFoundException {
		Optional<Airport> airport=aprepo.findById(airportloc);
		if(airport.isPresent()) {
			return airport.get();
		}
		else {throw new AirportNotFoundException("no airport in location "+airportloc+" is found");
		
		}
	}
	
	public Airport viewAirportByLoc(String loc) {
		return aprepo.viewAirportByLoc(loc);
	}
	
	
}
