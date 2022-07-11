package flightms.module1;

import java.math.BigInteger;

import javax.persistence.*;

@Entity
public class Passenger {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private BigInteger pnrNumber;
	private String passengerName;
	private int passengerAge;
	private BigInteger passengerUIN;
	private double luggage;
	
	 @ManyToOne
	 private Booking bookingId;
	 
	
	
	
	/**
	 * @param pnrNumber
	 * @param passengerName
	 * @param passengerAge
	 * @param passengerUIN
	 * @param luggage
	 */
	
	
	//Parameterized constructor
	
	public Passenger(BigInteger pnrNumber, String passengerName, int passengerAge, BigInteger passengerUIN,
			double luggage) {
		this.pnrNumber = pnrNumber;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerUIN = passengerUIN;
		this.luggage = luggage;
	}
	
	// Getter Setter
	
	public Passenger() {
	}

	public BigInteger getPnrNumber() {
		return pnrNumber;
	}
	
	
	public void setPnrNumber(BigInteger pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	
	
	public String getPassengerName() {
		return passengerName;
	}
	
	
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	
	public int getPassengerAge() {
		return passengerAge;
	}
	
	
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	
	
	public BigInteger getPassengerUIN() {
		return passengerUIN;
	}
	
	
	public void setPassengerUIN(BigInteger passengerUIN) {
		this.passengerUIN = passengerUIN;
	}
	
	
	public double getLuggage() {
		return luggage;
	}
	
	
	public void setLuggage(double luggage) {
		this.luggage = luggage;
	}
}


