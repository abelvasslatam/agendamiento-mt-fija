package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Slot {
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("quantity")
	private String quantity;

	
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("quantity")
	public String getQuantity() {
		return quantity;
	}
	
	@JsonProperty("quantity")
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
	

}
