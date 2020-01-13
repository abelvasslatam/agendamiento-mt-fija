package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Body {
	
	@JsonProperty("header")
	private String header;
	
	@JsonProperty("capacityFicticious")
	private CapacityFicticious capacityFicticious;
	
	private String codProductPSI;
	private String commercialOp;
}
