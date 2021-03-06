package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CapacityFicticious {

	@JsonProperty("location")
	private String location;
	@JsonProperty("date")
	private List<Dates> date;
	
}
