package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TechnicalAppointmentsResponse {
	@JsonProperty("header")
	private Header header;
	
	@JsonProperty("body")
	private Body body;

	@JsonProperty("header")
	public Header getHeader() {
		return header;
	}

	@JsonProperty("header")
	public void setHeader(Header header) {
		this.header = header;
	}

	@JsonProperty("body")
	public Body getBody() {
		return body;
	}

	@JsonProperty("body")
	public void setBody(Body body) {
		this.body = body;
	}
	
	


}
