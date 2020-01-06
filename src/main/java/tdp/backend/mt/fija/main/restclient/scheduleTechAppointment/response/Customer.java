package tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Customer {
	@JsonProperty("documentType")
	private String documentType;

	@JsonProperty("documentNumber")
	private String documentNumber;

	@JsonProperty("fullName")
	private String fullName;
	
	@JsonProperty("patSurname")
	private String patSurname;
	
	@JsonProperty("matSurname")
	private String matSurname;

	@JsonProperty("email")
	private String email;

	@JsonProperty("cellphone")
	private String cellphone;
	
	@JsonProperty("cellphoneIsMovistar")
	private String cellphoneIsMovistar;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("productName")
	private String productName;

}