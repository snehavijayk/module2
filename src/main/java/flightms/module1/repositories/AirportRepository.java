package flightms.module1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import flightms.module1.Airport;
@Component
public interface AirportRepository extends JpaRepository<Airport, String>{
	
	@Query(value="select * from airport where airport_location=?1",nativeQuery=true)
	public Airport viewAirportByLoc(String loc);
	
	@Query(value="select * from airport" ,nativeQuery=true)
	public List<Airport> viewAllAirports();
	
}
