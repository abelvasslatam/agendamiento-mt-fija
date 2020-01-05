package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Body {
	
	@JsonProperty("header")
	private String header;
	
	@JsonProperty("capacityFicticious")
	private CapacityFicticious capacityFicticious;

	
	@JsonProperty("header")
	public String getHeader() {
		return header;
	}

	
	@JsonProperty("header")
	public void setHeader(String header) {
		this.header = header;
	}

	@JsonProperty("capacityFicticious")
	public CapacityFicticious getCapacityFicticious() {
		return capacityFicticious;
	}

	@JsonProperty("capacityFicticious")
	public void setCapacityFicticious(CapacityFicticious capacityFicticious) {
		this.capacityFicticious = capacityFicticious;
	}
	
	
}
