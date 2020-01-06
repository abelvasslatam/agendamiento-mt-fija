package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Dates {
	@JsonProperty("day")
	private String day;
	
	@JsonProperty("slot")
	private ArrayList<Slot> slot;
	
}
