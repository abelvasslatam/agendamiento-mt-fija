package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dates {
	@JsonProperty("day")
	private String day;
	
	@JsonProperty("slot")
	private ArrayList<Slot> slot;

	@JsonProperty("day")
	public String getDay() {
		return day;
	}

	@JsonProperty("day")
	public void setDay(String day) {
		this.day = day;
	}

	@JsonProperty("slot")
	public ArrayList<Slot> getSlot() {
		return slot;
	}

	@JsonProperty("slot")
	public void setSlot(ArrayList<Slot> slot) {
		this.slot = slot;
	}
	
	
}
