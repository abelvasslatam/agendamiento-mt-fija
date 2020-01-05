package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request;

import lombok.Data;

@Data
public class Header {
	
	private String appName;
	private String user;
	private String operation;
	private String messageId;
	private String timestamp;
}
