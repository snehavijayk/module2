package flightms.module1;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Airport {
	@NotNull
	private String airportName;
	@NotNull
	@Id
	private String airportLocation;
	@NotNull
	private String airportCode;
	
	
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getAirportLocation() {
		return airportLocation;
	}
	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	@Override
	public String toString() {
		return "Airport [airportName=" + airportName + ", airportLocation=" + airportLocation + ", airportCode="
				+ airportCode + "]";
	}
}
