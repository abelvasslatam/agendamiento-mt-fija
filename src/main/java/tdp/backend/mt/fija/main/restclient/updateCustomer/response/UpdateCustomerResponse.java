package tdp.backend.mt.fija.main.restclient.updateCustomer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response.Header;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UpdateCustomerResponse {
	@JsonProperty("header")
	private Header header;
	
	@JsonProperty("body")
	private String body;
}
