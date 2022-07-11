package flightms.module1.repositories;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import flightms.module1.Booking;

public interface BookingRepository extends JpaRepository<Booking, BigInteger>{
	
	//overriding the getById method
	@Query(value="select *  from booking  where booking_id=?1",nativeQuery=true)
	public Booking getById(BigInteger bookingId);

}
