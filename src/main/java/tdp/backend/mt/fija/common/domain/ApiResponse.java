package tdp.backend.mt.fija.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.TechnicalAppointmentsResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse {
	@JsonProperty("TechnicalAppointmentsResponse")
	private TechnicalAppointmentsResponse response;
	
	   	
}

