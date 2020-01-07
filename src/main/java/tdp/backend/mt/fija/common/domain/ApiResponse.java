package tdp.backend.mt.fija.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.AvailabilityTechnicalAppointmentsResponse;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response.ScheduleTechnicalAppointmentResponse;
import tdp.backend.mt.fija.main.restclient.updateCustomer.response.UpdateCustomerResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse {
	@JsonProperty("AvailabilityTechnicalAppointmentsResponse")
	private AvailabilityTechnicalAppointmentsResponse responseAvailability;
	
	@JsonProperty("ScheduleTechnicalAppointmentResponse")
	private ScheduleTechnicalAppointmentResponse responseSchedule;
	
	@JsonProperty("UpdateCustomerResponse")
	private UpdateCustomerResponse responseUpdateCustomer;
	
	   	
}

