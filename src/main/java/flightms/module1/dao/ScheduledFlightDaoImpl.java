package flightms.module1.dao;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import flightms.module1.Airport;
import flightms.module1.Flight;
import flightms.module1.Schedule;
import flightms.module1.ScheduledFlight;
import flightms.module1.controllers.UserController;
import flightms.module1.exceptions.FlightNotFoundException;
import flightms.module1.exceptions.LoginException;
import flightms.module1.exceptions.ScheduledFlightNotFoundException;
import flightms.module1.repositories.FlightServiceRepository;
import flightms.module1.repositories.ScheduleRepository;
import flightms.module1.repositories.ScheduledFlightServiceRepository;
import flightms.module1.serviceins.ScheduledFlightSerciveImpl;

@Component
public class ScheduledFlightDaoImpl implements ScheduledFlightSerciveImpl{
	
	@Autowired
	private ScheduledFlightServiceRepository sfrep;
	@Autowired 
	private ScheduleRepository shrep;
	@Autowired
	private FlightServiceRepository frepo;
	@Override
	public ScheduledFlight scheduleFlight(ScheduledFlight sf) {
		//ScheduledFlight scf=sf;
		sfrep.save(sf);
		return sf;
	}
	
	public ScheduledFlight scheduleFlight() throws LoginException {
		Scanner sc =new Scanner(System.in);
	//	if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserType().equalsIgnoreCase("admin")) {
		System.out.println("these are the flights available");
			for(Flight f :sfrep.viewAllFlights()) {
				System.out.println(f);
			}
			System.out.println("enter flight to schedle it");
			BigInteger fid=new BigInteger(sc.nextLine());
			ScheduledFlight scf=new ScheduledFlight();
			for(Flight f : sfrep.viewAllFlights()) {
				if(f.getFlightNumber().equals(fid)) {
					scf.setFlight(f);
					scf.setAvailableSeats(f.getSeatCapacity());
					break;
				}
			}
			Schedule sch=new Schedule();
			System.out.println("these are the airports available");
			for(Airport a: sfrep.viewAllAirports()) {
				System.out.println(a);
			}
			System.out.println("enter source airport code");
			String srcap=sc.nextLine();
			System.out.println("enter destiation airport code");
			String dstap=sc.nextLine();
			for(Airport a :sfrep.viewAllAirports()) {
				if(a.getAirportCode().equals(srcap)) {
					sch.setSourceAirport(a);
				}
				if(a.getAirportCode().equals(dstap)) {
					sch.setDestinationAirport(a);
				}
			}
			System.out.println("departure date and time in yyyy/mm/dd/hh/mm format");
			String deptime=sc.nextLine();
			String[] depTime=deptime.split("/");
			LocalDateTime depld=LocalDateTime.of(Integer.parseInt(depTime[0]),Integer.parseInt(depTime[1]),Integer.parseInt(depTime[2]),Integer.parseInt(depTime[3]),Integer.parseInt(depTime[4]));
			System.out.println("arrival date and time in yyyy/mm/dd/hh/mm format");
			String arrtime=sc.nextLine();
			String[] arrTime=arrtime.split("/");
			LocalDateTime arrld=LocalDateTime.of(Integer.parseInt(arrTime[0]),Integer.parseInt(arrTime[1]),Integer.parseInt(arrTime[2]),Integer.parseInt(arrTime[3]),Integer.parseInt(arrTime[4]));
			sch.setDepartureTime(depld);
			sch.setArrivalTime(arrld);
			System.out.println("enter ticket cost");
			double cost=Double.parseDouble(sc.nextLine());
			scf.setTicketCost(cost);
			shrep.save(sch);
			scf.setSchedule(sch);
			
			//checking if scheduled flight is valid or not
			if(validateScheduledFlight(scf)){
				sfrep.save(scf);
			}
			else {//throw new invalidException()
				/*
				 * 
				 */
			}
			return scheduleFlight(scf);
		//}
		//else {throw new LoginException("please login with admin first");}
		
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(String airport, String airport1, LocalDate ld) throws LoginException {
		//if(UserController.isLoginSuccess()) {
			List<ScheduledFlight> scfList = sfrep.viewScheduledFlights();
			List<ScheduledFlight> rList = scfList.stream().filter(scf->(scf.getSchedule().getSourceAirport().getAirportLocation().equals(airport) && scf.getSchedule().getDestinationAirport().getAirportLocation().equals(airport1) &&  scf.getSchedule().getDepartureTime().toLocalDate().equals(ld))).collect(Collectors.toList());
			return rList;
		//}
		//else {throw new LoginException("please login first");

		//}
		//return scfList;
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(BigInteger flightNumber) throws LoginException {
		//if(UserController.isLoginSuccess()) {
			if(frepo.existsById(flightNumber)) {
				return sfrep.viewScheduledFlights(flightNumber);
			}
			else {//throw flight not found exception
				return null;
			}
		//}
		//else {throw new LoginException("please login first");
		//}

	}

	@Override
	public List<ScheduledFlight> viewScheduledFlight() throws LoginException {
//		if(UserController.isLoginSuccess()) {

			return sfrep.findAll();
//		}
//		else {throw new LoginException("please login first");
//		}
	}

	@Override
	@Transactional
	public void deleteScheduledFlight(BigInteger flightNumber) throws LoginException, FlightNotFoundException {
//		if(UserController.isLoginSuccess() &&  UserController.getLoggedInUser().getUserType().equalsIgnoreCase("admin")) {
			if(frepo.existsById(flightNumber)) {
				sfrep.removeScheduledFlight(flightNumber);
			}
			else {throw new FlightNotFoundException("no flight found with flight number "+flightNumber);
			}
//		}
//		else {throw new LoginException("please login with admin first");
//		}
	}

	@Override
	public boolean validateScheduledFlight(ScheduledFlight sf) {
		if(sf.getSchedule().getDepartureTime().isAfter(LocalDateTime.now())) {
			if(sf.getSchedule().getArrivalTime().isAfter(sf.getSchedule().getDepartureTime())) {
				return true;
			}
			return false;
		}
		return false;	
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScheduledFlight modifyscheduleflight(ScheduledFlight sf) throws LoginException, ScheduledFlightNotFoundException, FlightNotFoundException {
		
//		if(UserController.isLoginSuccess() && UserController.getLoggedInUser().getUserType().equalsIgnoreCase("admin")) {

			BigInteger flightNumber = sf.getFlight().getFlightNumber();
			Optional<Flight> opti = frepo.findById(flightNumber);
			if(opti.isPresent()) {
				Optional<ScheduledFlight> opt = sfrep.findById(sf.getScFlightid());
				ScheduledFlight schefli = null;
				if(opt.isPresent()) {
					schefli=opt.get();
					schefli.setScFlightid(sf.getScFlightid());
					schefli.setAvailableSeats(sf.getAvailableSeats());
					//schefli.setTicketCost(sf.getTicketCost());
					schefli.setFlight(sf.getFlight());
					schefli.setSchedule(sf.getSchedule());
					sfrep.save(schefli);
					return schefli;
				}
				else {
					//throw new NoScheduleException("No Schedule Flight is present for given Schedule Flight Id ");
					throw new ScheduledFlightNotFoundException("No Schedule Flight is present for given Schedule Flight Id ");
					
				}
			}else {
				System.out.println("no schedule");
				throw new FlightNotFoundException("No Flight is present for given Flight Number "+flightNumber);
			}
//		}
//		else {throw new LoginException("please login with admin to modify scheduledFlight");}
	
	}
	public ScheduledFlight getSCFbyId(Integer scfId) {
//		return sfrep.getSCFbyId(scfId);
		return sfrep.findById(scfId).get();
	}
}
