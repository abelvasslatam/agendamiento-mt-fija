package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
	
	@JsonProperty("appName")
	public String getAppName() {
		return appName;
	}
	
	@JsonProperty("appName")
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	@JsonProperty("dateTime")
	public String getDateTime() {
		return dateTime;
	}
	
	@JsonProperty("dateTime")
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	@JsonProperty("operation")
	public String getOperation() {
		return operation;
	}
	
	@JsonProperty("operation")
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@JsonProperty("resultCode")
	public String getResultCode() {
		return resultCode;
	}
	
	@JsonProperty("resultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}
	
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}
	
	@JsonProperty("messageId")
	public String getMessageId() {
		return messageId;
	}
	
	@JsonProperty("messageId")
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	@JsonProperty("timestamp")
	public String getTimestamp() {
		return timestamp;
	}
	
	@JsonProperty("timestamp")
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	

}
