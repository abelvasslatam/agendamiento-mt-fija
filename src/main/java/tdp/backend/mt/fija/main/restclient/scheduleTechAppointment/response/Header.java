package tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Header {
	@JsonProperty("appName")
	private String appName;
	@JsonProperty("dateTime")
	private String dateTime;
	@JsonProperty("operation")
	private String operation;
	@JsonProperty("resultCode")
	private String resultCode;
	@JsonProperty("message")
	private String message;
	@JsonProperty("messageId")
	private String messageId;
	@JsonProperty("timestamp")
	private String timestamp;
}
