package flightms.module1.controllers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

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
import flightms.module1.Schedule;
import flightms.module1.ScheduledFlight;
import flightms.module1.dao.ScheduledFlightDaoImpl;
import flightms.module1.exceptions.FlightNotFoundException;
import flightms.module1.exceptions.LoginException;
import flightms.module1.exceptions.ScheduledFlightNotFoundException;
@CrossOrigin
@RestController
public class ScheduledFlightController {
	
	@Autowired
	private ScheduledFlightDaoImpl sfDao;
	
	@PostMapping(path="/schedule-a-flight/")
	public ScheduledFlight scheduleFlight(/*@RequestBody ScheduledFlight scf*/) throws LoginException {
		return sfDao.scheduleFlight(/*scf*/);
	}
	
	/*@GetMapping(path="/schedule/{airport1}/{airport2}/{ld}")
	public List<ScheduledFlight> viewScheduledFlight(@PathVariable String airport1, @PathVariable String airport2, @PathVariable String ld) throws LoginException{    
		return sfDao.viewScheduledFlights(airport1, airport2, LocalDate.parse(ld));
	}*/
	
	@GetMapping(path="/schedules/all")
	public List<ScheduledFlight> viewScheduledFlight() throws LoginException{
		return sfDao.viewScheduledFlight();
	}
	
	/*@GetMapping(path="/scheduled-flight/{flightnum}")
	public List<ScheduledFlight> viewScheduledFlight(@PathVariable BigInteger flightnum) throws LoginException {
		return sfDao.viewScheduledFlights(flightnum);
	}*/
	
	@PutMapping(path="/schedule/modify")
	public ScheduledFlight modifyScheduledFlight(@RequestBody ScheduledFlight scf) throws LoginException, ScheduledFlightNotFoundException, FlightNotFoundException {
		return sfDao.modifyscheduleflight(scf);
	}
	
	@DeleteMapping(path="/schedule/delete/{flightnum}")
	public void deleteScheduled(@PathVariable BigInteger flightnum) throws LoginException, FlightNotFoundException {
		sfDao.deleteScheduledFlight(flightnum);
	}
	@GetMapping(path="/schedule/{scfId}")
	public ScheduledFlight getSCFbyId(@PathVariable Integer scfId) {
		return sfDao.getSCFbyId(scfId);
	}
}
