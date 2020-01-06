package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AvailabilityTechnicalAppointmentsResponse {
	@JsonProperty("header")
	private Header header;
	
	@JsonProperty("body")
	private Body body;

}
