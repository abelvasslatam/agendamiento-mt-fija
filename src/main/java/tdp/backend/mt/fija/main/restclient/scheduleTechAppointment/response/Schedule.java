package tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Schedule {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("requestId")
	private String requestId;
	
	@JsonProperty("requestType")
	private String requestType;
	
	@JsonProperty("requestName")
	private String requestName;
	
	@JsonProperty("orderCode")
	private String orderCode;
	
	@JsonProperty("stpsiCode")
	private String stpsiCode;
	
	@JsonProperty("scheduleDate")
	private String scheduleDate;
	
	@JsonProperty("scheduleTimeRange") 
	private String scheduleTimeRange;
	
	@JsonProperty("scheduleRange")
	private String scheduleRange;
	
	@JsonProperty("scheduleStatus")
	private String scheduleStatus;
	
	@JsonProperty("reminderStatus")
	private String reminderStatus;
	
	@JsonProperty("mapUrl")
	private String mapUrl;
	
	@JsonProperty("technicalRequestId")
	private String technicalRequestId;
	
	@JsonProperty("technicalId")
	private String technicalId;
	
	@JsonProperty("petitionCode")
	private String petitionCode;
	
	@JsonProperty("requirementCode")
	private String requirementCode;
	
	@JsonProperty("workOrderRequirement")
	private String workOrderRequirement;
	
	@JsonProperty("saleFlag")
	private String saleFlag;
	
	@JsonProperty("workZone")
	private String workZone;
	
	@JsonProperty("insertDate")
	private String insertDate;
	
	@JsonProperty("insertTime")
	private String insertTime;
	
	@JsonProperty("activeStatus")
	private String activeStatus;
	
	@JsonProperty("customer")
	private Customer customer;
	
	@JsonProperty("originCode")
	private String originCode;
	
	@JsonProperty("saleCode")
	private String saleCode;
	
	@JsonProperty("idFictitious")
	private String idFictitious;
	
	@JsonProperty("dummyStPsiCode")
	private String dummyStPsiCode;
	
	@JsonProperty("productCode")
	private String productCode;
	
	@JsonProperty("coordX")
	private String coordX;
	
	@JsonProperty("coordY")
	private String coordY;
	
	@JsonProperty("commercialOp")
	private String commercialOp;
}
