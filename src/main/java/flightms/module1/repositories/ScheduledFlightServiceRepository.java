package flightms.module1.repositories;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import flightms.module1.Airport;
import flightms.module1.Flight;
import flightms.module1.Schedule;
import flightms.module1.ScheduledFlight;

@Component
public interface ScheduledFlightServiceRepository extends  JpaRepository<ScheduledFlight, Integer> {
	
	//@Query("select sf.flight from ScheduledFlight sf where sf.schedule.sourceAirport=?1 AND sf.schedule.destinationAirort=?2 AND sf.schedule.depatureTime.toLocalDate()=?3")
	//public List<ScheduledFlight> viewScheduledFlights(Airport airport, Airport airport1, LocalDate ld);
	
	@Query("select sf from ScheduledFlight sf where sf.flight.flightNumber=?1")
	public List<ScheduledFlight> viewScheduledFlights(BigInteger flightNumber);
	
	@Query(value="select * from Scheduled_Flight",nativeQuery=true)
	public List<ScheduledFlight> viewScheduledFlights();
	
	//@Query("update ScheduledFlight set flight=?1, schedule=?2, availableSeats=?3 where scFlighid=?4")
	//public ScheduledFlight modifyScheduledFlight(Flight flight ,Schedule sc, Integer seat,int scheduleId);
	
	
	
	@Modifying
	//@Query("delete from ScheduledFlight sf where sf.flight.flightNumber=?1")
	@Query(value="delete from scheduled_flight sf where sf.flight_flight_number=?1", nativeQuery=true)
	public void removeScheduledFlight(BigInteger flightNumber);
	
	@Query("select f from Flight f")
	public List<Flight> viewAllFlights();
	
	@Query("select a from Airport a")
	public List<Airport> viewAllAirports();
	
	@Modifying
	@Query(value="update scheduled_flight set available_seats=?1 where sc_flightid=?2",nativeQuery=true)
	public void updateSeats(int seats, int scId);
	
//	@Query(value="select * from scheduled_flight where sc_flightid=?1",nativeQuery=true)
	@Query(value="Hibernate: select scheduledf0_.sc_flightid as sc_fligh1_6_0_, scheduledf0_.available_seats as availabl2_6_0_, scheduledf0_.flight_flight_number as flight_f4_6_0_, scheduledf0_.schedule_schedule_id as schedule5_6_0_, scheduledf0_.ticket_cost as ticket_c3_6_0_, flight1_.flight_number as flight_n1_2_1_, flight1_.carrier_name as carrier_2_2_1_, flight1_.flight_model as flight_m3_2_1_, flight1_.seat_capacity as seat_cap4_2_1_, schedule2_.schedule_id as schedule1_5_2_, schedule2_.arrival_time as arrival_2_5_2_, schedule2_.departure_time as departur3_5_2_, schedule2_.destination_airport_airport_location as destinat4_5_2_, schedule2_.source_airport_airport_location as source_a5_5_2_, airport3_.airport_location as airport_1_0_3_, airport3_.airport_code as airport_2_0_3_, airport3_.airport_name as airport_3_0_3_, airport4_.airport_location as airport_1_0_4_, airport4_.airport_code as airport_2_0_4_, airport4_.airport_name as airport_3_0_4_ from scheduled_flight scheduledf0_ left outer join flight flight1_ on scheduledf0_.flight_flight_number=flight1_.flight_number left outer join schedule schedule2_ on scheduledf0_.schedule_schedule_id=schedule2_.schedule_id left outer join airport airport3_ on schedule2_.destination_airport_airport_location=airport3_.airport_location left outer join airport airport4_ on schedule2_.source_airport_airport_location=airport4_.airport_location where scheduledf0_.sc_flightid=?",nativeQuery=true)
	public ScheduledFlight getSCFbyId(Integer scfId);
}
