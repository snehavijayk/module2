package flightms.module1.controllers;

import java.math.BigInteger;
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

//import flightms.module1.controllers.UserController;
import flightms.module1.Booking;
import flightms.module1.Passenger;
import flightms.module1.ScheduledFlight;
import flightms.module1.dao.BookingDaoImpl;
import flightms.module1.exceptions.AirportNotFoundException;
import flightms.module1.exceptions.BookingNotFoundException;
import flightms.module1.exceptions.InvalidException;
import flightms.module1.exceptions.LoginException;
@CrossOrigin
@RestController
public class BookingController {
	
	@Autowired
	private BookingDaoImpl bdao;
	
	//to book a flight
	@PostMapping(path="/booking/add")
    public Booking  addBooking(@RequestBody Booking booking) throws LoginException, AirportNotFoundException, InvalidException{
//		System.out.println("corntoller "+ booking.getSchflight().getScFlightid());
		return bdao.addBooking(booking);
    	
    }
	
	//to modify a booking
	@PutMapping(path="/booking/modify")
	public Booking modifyBooking(@RequestBody Booking booking) throws LoginException, BookingNotFoundException {
		return bdao.modifyBooking(booking);
		
	}
	
	//to see the booking details
	@GetMapping(path="/bookingById/{id}")
	public Booking viewBookingBYId(@PathVariable BigInteger id) throws LoginException{
		Booking b = bdao.viewBookingById(id);
		System.out.println(b.getPassengerList().get(0).getPnrNumber());
		System.out.println(b.getSchflight().getFlight());
		return b;
		
	}
	
	//to see all booking details
	@GetMapping(path="/booking/{mail}")
	public List<Booking>viewBooking(@PathVariable String mail) throws LoginException{
		return bdao.viewBooking(mail);
	}
	
	@GetMapping(path="/passengers/{bookingId}")
	public List<Passenger> getPassengersbybookingId(@PathVariable BigInteger bookingId){
		List<Passenger> psList = bdao.getPassengersByBookingId(bookingId);
//		System.out.println(psList.get(0).getPassengerName());
		System.out.println(bookingId);
		return psList;
	}
	
	@GetMapping(path="/passenger/{pnr}")
	public Passenger getPasseengerByPNR(@PathVariable BigInteger pnr) {
		return bdao.getPasseengerByPNR(pnr);
	}
	
	//to cancel a booking
	@DeleteMapping(path="/booking/delete/{id}")
	public void deleteBooking(@PathVariable BigInteger id) throws LoginException {
		bdao.deleteBooking(id);
	}
	
	@PutMapping(path="passenger/update")
	public Passenger updatePassenger(@RequestBody Passenger psng) {
		return bdao.updatePassenger(psng);
	}
	
	//Extra
	//to search flights
	@GetMapping(path="/flights/{srcAirport}/{dstAirport}/{date}")
	public List<ScheduledFlight> searchFlights(@PathVariable String srcAirport, @PathVariable String dstAirport,  @PathVariable String date ) throws LoginException{
		System.out.println("search called"+srcAirport+dstAirport+date);
		return bdao.searchFlights(srcAirport, dstAirport, date);
	}
}
