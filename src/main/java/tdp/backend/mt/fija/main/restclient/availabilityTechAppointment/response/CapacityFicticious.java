package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapacityFicticious {

	@JsonProperty("location")
	private String location;
	@JsonProperty("date")
	private ArrayList<Dates> date;
	
	@JsonProperty("location")
	public String getLocation() {
		return location;
	}
	
	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
	}
	
	@JsonProperty("date")
	public ArrayList<Dates> getDate() {
		return date;
	}
	
	@JsonProperty("date")
	public void setDate(ArrayList<Dates> date) {
		this.date = date;
	}
	
}
