package flightms.module1;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Entity
@Component
public class Flight {
	// Attributes for Flight
	@Id
	private BigInteger flightNumber;
	private String flightModel;
	private String carrierName;
	private	int seatCapacity;
	
	
	//Parameterized constructor
	
	public Flight() {}

	public Flight(BigInteger flightNumber, String flightModel, String carrierName, int seatCapacity) {
		this.flightNumber = flightNumber;
		this.flightModel = flightModel;
		this.carrierName = carrierName;
		this.seatCapacity = seatCapacity;
	}
	
	// Getter Setter
	
	public BigInteger getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(BigInteger flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightModel() {
		return flightModel;
	}
	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", flightModel=" + flightModel + ", carrierName=" + carrierName
				+ ", seatCapacity=" + seatCapacity + "]";
	}
	

}
