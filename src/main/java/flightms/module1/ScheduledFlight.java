package flightms.module1;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ScheduledFlight {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer scFlightid;
	@OneToOne
	private Flight flight;
	private int availableSeats;
	@OneToOne
	private Schedule schedule;
	private double ticketCost;
	
	
	public ScheduledFlight() {
		// TODO Auto-generated constructor stub
	}
	public ScheduledFlight(Flight flight, int availableSeats, Schedule schedule) {
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
	}
	public Integer getScFlightid() {
		return scFlightid;
	}
	public void setScFlightid(Integer scFlightid) {
		this.scFlightid = scFlightid;
	}
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public double getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}
	
}
