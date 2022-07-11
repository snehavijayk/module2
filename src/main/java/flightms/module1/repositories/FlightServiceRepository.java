package flightms.module1.repositories;

import flightms.module1.*;
import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
@Component
public interface FlightServiceRepository extends JpaRepository<Flight, BigInteger>{
	
	//@Query(value="")
	//public Flight addFlight(Flight flight);
	
	//public Flight viewFlight(BigInteger flightno) throws FlightNotFoundException;
	//public Set<Flight> viewAllFlights();
	
	//@Query(value="delete flight where flight_number=?1",nativeQuery=true)
	//public Flight removeFlight(BigInteger flightno);
	
	@Modifying
	@Query(value="update flight set flight_model=?1, carrier_name=?2, seat_capacity=?3 where flight_number=?4",nativeQuery=true)
	//public void updateFlight(Flight flight)
	public void updateFlight(String flightModel, String carrierName,int seatCapacity, BigInteger flightNumber);

	@Query("select flight from Flight flight where flightNumber=?1")
	public Optional<Flight> findByflightNumber(BigInteger flightNumber);
}
