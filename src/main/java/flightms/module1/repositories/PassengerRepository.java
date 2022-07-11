package flightms.module1.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import flightms.module1.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, BigInteger>{
	
	@Modifying
	@Query(value="update passenger set booking_id_booking_id=?1 where pnr_number=?2",nativeQuery=true)
	public void updateBookingIdOfpassenger(BigInteger bookingId, BigInteger pnr);

	@Query(value="select * from passenger p where p.booking_id_booking_id=?1",nativeQuery=true)
	public List<Passenger> getPassenersByBookingId(BigInteger bookingId);
	
	
	@Query(value="select * from passenger p where p.pnr_number=?1",nativeQuery=true)
	public Passenger getPasseengerByPNR(BigInteger pnr);
}

