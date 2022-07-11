package flightms.module1;

import java.math.BigInteger;
import javax.persistence.*;
import java.util.*;
import org.springframework.stereotype.Component;
@Entity
public class Booking {
	//primary key
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private BigInteger bookingId;
	@ManyToOne
	private User userId;
	private Date bookingDate;
	@OneToMany(mappedBy="bookingId")
	private List<Passenger> passengerList;
	private double ticketCost;
	@OneToOne
	private ScheduledFlight schflight;
	private Integer noOfPassengers;
	
	//default constructer
	public Booking() {}

	//parameterised constructer
	public Booking(User userId, Date bookingDate, List<Passenger> passengerList, double ticketCost, ScheduledFlight schflight) {
		this.userId = userId;
		this.bookingDate = bookingDate;
		this.passengerList = passengerList;
		this.ticketCost = ticketCost;
		this.schflight = schflight;
	}
	
	//getters and setters
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	public double getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}
	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public ScheduledFlight getSchflight() {
		return schflight;
	}
	public void setSchflight(ScheduledFlight schflight) {
		this.schflight = schflight;
	}

	public BigInteger getBookingId() {
		return bookingId;
	}

	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}
	
}
