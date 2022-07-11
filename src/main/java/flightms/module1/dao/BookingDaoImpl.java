package flightms.module1.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import flightms.module1.Booking;
import flightms.module1.Passenger;
import flightms.module1.ScheduledFlight;
import flightms.module1.controllers.UserController;
import flightms.module1.exceptions.AirportNotFoundException;
import flightms.module1.exceptions.BookingNotFoundException;
import flightms.module1.exceptions.InvalidException;
import flightms.module1.exceptions.LoginException;
import flightms.module1.repositories.AirportRepository;
import flightms.module1.repositories.BookingRepository;
import flightms.module1.repositories.PassengerRepository;
import flightms.module1.repositories.ScheduledFlightServiceRepository;
import flightms.module1.repositories.UserRepository;
import flightms.module1.serviceins.BookingServiceImpl;
import flightms.module1.exceptions.BookingNotFoundException;

@Component
@Transactional
public class BookingDaoImpl implements BookingServiceImpl{
	@Autowired
	private BookingRepository bRepo;
	@Autowired
	private AirportRepository aprepo;
	@Autowired
	private AiportDaoImpl apdao;
	@Autowired
	private PassengerRepository psRepo;
	@Autowired
	private ScheduledFlightServiceRepository scfRepo;
	@Autowired
	private ScheduledFlightDaoImpl scfDao;
	@Autowired
	private  UserRepository userRepo;
	
	
	public Booking addBooking(Booking book) {
		System.out.println(book.getUserId().getUserId()+"uid");
		userRepo.save(book.getUserId());
		for(Passenger p: book.getPassengerList()) {
			psRepo.save(p);
		}
		//decreasing the seats in the scheduledFlight object 
		scfRepo.updateSeats(book.getSchflight().getAvailableSeats()-book.getNoOfPassengers(), book.getSchflight().getScFlightid());
		Booking b=bRepo.save(book);
		//updating booking id of passengers
		for(Passenger p: book.getPassengerList()) {
			psRepo.updateBookingIdOfpassenger(book.getBookingId(), p.getPnrNumber());	
		}
//		System.out.println(book.getNoOfPassengers()+" no of pasengers");
//		System.out.println(book.getSchflight().getScFlightid());
		System.out.println(book.getUserId().getEmail());
		
		return b;
	}
	
	
	@Override
	@Transactional
	public Booking addBooking() throws LoginException, AirportNotFoundException, InvalidException {
		
		Booking booking=new Booking();
		Scanner sc=new Scanner(System.in);
		
		//checking user login successful or not
		//if(UserController.isLoginSuccess()) {
			
			booking.setUserId(UserController.getLoggedInUser());
			//taking the journey plan from user
			System.out.println("enter source airport location");
			String srcAP=sc.nextLine();
			//instead of break throw new null value exception
			
			if(!aprepo.existsById(srcAP)) {
				throw new AirportNotFoundException("Airport "+srcAP+" not available");
			}
			System.out.println("enter destination airport location");
			String dstAP=sc.nextLine();
			if(!aprepo.existsById(dstAP)) {
				throw new AirportNotFoundException("Airport "+dstAP+" not available");
			}
			System.out.println("enter date of travel in yyyy-mm-dd format");
			String travelDate=sc.nextLine();
			LocalDate tDate=LocalDate.parse(travelDate);
			
			//diaplaying all flights between source and destination on specified dates
			
			List<ScheduledFlight> suitableSchFlights =scfDao.viewScheduledFlights(srcAP, dstAP, tDate);
			//ScheduledFlight selectedSchFlight=new ScheduledFlight();
			if(suitableSchFlights.size()==0) {
				System.out.println("noflights found");
				throw new InvalidException("no flight found on specified date for specified route");
			}
			System.out.println("these are the flights available for the provided data");
			for(ScheduledFlight scf : suitableSchFlights){
				System.out.print(scf.getFlight());
				System.out.println(scf.getSchedule().getDepartureTime());
			}
			// user selects a flight
			System.out.println("enter flight id to select that flight");
			BigInteger flightNum=new BigInteger(sc.nextLine());
			
			//taking passengers data
			System.out.println("enter how many passengrs");
			List<Passenger> pList=new ArrayList<Passenger>();
			int n=Integer.parseInt(sc.nextLine());
			ScheduledFlight schFgh=null;
			//assigning the ScheduledFlight dependency to the booking object
			for(ScheduledFlight scf : suitableSchFlights) {
				if(scf.getFlight().getFlightNumber().equals(flightNum)) {
					//updating the available seats in the flight
					//scfRepo.updateSeats(scf.getAvailableSeats()-n, scf.getScFlightid());
					schFgh=scf;
					break;
				}
			}
			
			//checking for seats are available or not
			if(schFgh.getAvailableSeats()<n) {
				throw new InvalidException("no seats availble, there are only "+schFgh.getAvailableSeats()+" seats");
			}
			
			//this is no need may be
			schFgh.setAvailableSeats(schFgh.getAvailableSeats()-n);
			
			//booking.setSchflight(selectedSchFlight);
			booking.setNoOfPassengers(n);
			Date date =new Date();
			booking.setBookingDate(date);
			booking.setPassengerList(pList);
			
			booking.setTicketCost(n*schFgh.getTicketCost());
			for(int i=0;i<n;i++) {
				Passenger ps=new Passenger();
				System.out.println("enter passenger name");
				ps.setPassengerName(sc.nextLine());
				System.out.println("enter passerger age");
				ps.setPassengerAge(Integer.parseInt(sc.nextLine()));
				System.out.println("enter passenger uin");
				ps.setPassengerUIN(new BigInteger(sc.nextLine()));
				System.out.println("enter passenger luggage in kgs");
				ps.setLuggage(Double.parseDouble(sc.nextLine()));
				//generating passenger pnr
				String a1=String.valueOf(ps.getPassengerUIN()).substring(6);
				String a2=travelDate.substring(2,4)+travelDate.substring(5,7)+travelDate.substring(8);
				BigInteger pnr=new BigInteger(a1+a2);
				ps.setPnrNumber(pnr);
				//validaing the passenger data and then saving to database
				if(validatePassenger(ps)) {
					psRepo.save(ps);
				}
				booking.getPassengerList().add(ps);	
			}
			booking.setSchflight(schFgh);
			
			Booking book= bRepo.save(booking);
			
			//updating the foreign key booking id of passenger table
			for(Passenger ps: booking.getPassengerList()) {
				psRepo.updateBookingIdOfpassenger(book.getBookingId(), ps.getPnrNumber());
			}
			return book;
	
		//}
		//else {
		//	throw new LoginException("please login first");
		//}
	}
	
	
	@Override
	public Booking modifyBooking(Booking booking) throws LoginException, BookingNotFoundException {
		

		//checking for login 
		//if(UserController.isLoginSuccess() && UserController.getLoggedInUser().equals(booking.getUserId())) {
			if(bRepo.findById(booking.getBookingId()).isPresent()) {
				psRepo.saveAll(booking.getPassengerList());
				for(Passenger p: booking.getPassengerList()){
					psRepo.updateBookingIdOfpassenger(booking.getBookingId(), p.getPnrNumber());
				}
				return bRepo.save(booking);
			}
			else {throw new BookingNotFoundException("");		
			}
		//}
		//else {throw new LoginException("please login first");}
		
		
		
	}

	@Override
	public List<Booking> viewBooking(String mail) throws LoginException {
		//checking for login 
		//if(UserController.isLoginSuccess()) {
			//fetching booking data from database and filtering it for specefic user
			
				List<Booking> bList=bRepo.findAll().stream().filter(b->b.getUserId().getEmail().equals(mail)).collect(Collectors.toList());
				
				return bList;
			
			//System.out.println(UserController.getLoggedInUser());
			
		//}
		//else {throw new LoginException("please login first");}
	}
	public Booking viewBookingById(BigInteger id) throws LoginException {
		//List<Booking> bList=viewBooking();
		//List<Booking> bList2=bList.stream().filter(b->b.getBookingId().equals(id)).collect(Collectors.toList());
		return bRepo.getById(id);
		//return bList2;
	}
	
	public List<Passenger> getPassengersByBookingId(BigInteger bookingId){
		return psRepo.getPassenersByBookingId(bookingId);
	}
	
	public Passenger getPasseengerByPNR(BigInteger pnr) {
		return psRepo.getPasseengerByPNR(pnr);
	}
	
	public Passenger updatePassenger(Passenger psng) {
		return psRepo.save(psng);
	}

	@Override
	public void deleteBooking(BigInteger bookingId) throws LoginException {
//		if(UserController.isLoginSuccess()) {
				for(Passenger p: bRepo.getById(bookingId).getPassengerList()) {
					psRepo.deleteById(p.getPnrNumber());
				}
				bRepo.deleteById(bookingId);
			
			
//		}
//		else {throw new LoginException("please login first");
//			
//		}
		
		
	}

	@Override
	//if departure time is later than booking time or not
	public boolean validateBooking(Booking booking) throws InvalidException {
		boolean state=true;
		if(booking.getSchflight().getSchedule().getDepartureTime().isAfter(LocalDateTime.now())) {
			for(Passenger p: booking.getPassengerList()) {
				if(validatePassenger(p)==false) {
					state=false;
					break;
				}
			}
		}
		else {throw new InvalidException("you cant book a flight that already departed");}
		return state;
	}
	
	//passenger uin shold be 12 digits
	//passenger age should be greater than 0
	//name should not be null
	@Override
	public boolean validatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		if(passenger.getPassengerAge()>0  && !passenger.getPassengerName().equals("") && String.valueOf(passenger.getPassengerUIN()).length()==12){
			//if(passenger.getPassengerAge()>0)System.out.println("age good");
			//if(passenger.getPassengerName().equals(""))System.out.println("name good");
			//if(String.valueOf(passenger.getPassengerUIN()).length()==12)System.out.println("uin good");
			return true;
		}
		return false;
	}
	
	//Extra
	@Override
	public List<ScheduledFlight> searchFlights(String airport1name, String airport2name, String date) throws LoginException{
		LocalDate ldDate=LocalDate.parse(date);
		List<ScheduledFlight> schList = scfDao.viewScheduledFlights(airport1name, airport2name, ldDate);
		return schList;
		
		
	}

}
