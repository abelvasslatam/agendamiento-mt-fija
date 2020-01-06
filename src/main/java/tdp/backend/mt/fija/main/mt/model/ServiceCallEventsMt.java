package tdp.backend.mt.fija.main.mt.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "service_call_events", schema = "public")
@Getter
@Setter
@ToString
public class ServiceCallEventsMt implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="datetime_request")
	private Timestamp dateTimeRequest;
	@Column(name="datetime_response")
	private Timestamp dateTimeResponse;
	private String tag;
	private String username;
	private String msg;
	@Column(name="orderid")
	private String orderId;
	@Column(name="docnumber")
	private String docNumber;
	@Column(name="servicecode")
	private String serviceCode;
	@Column(name="serviceurl")
	private String serviceUrl;
	@Column(name="servicerequest")
	private String serviceRequest;
	@Column(name="serviceresponse")
	private String serviceResponse;
	@Column(name="sourceapp")
	private String sourceApp;
	@Column(name="sourceappversion")
	private String sourceAppVersion;
	private String result;

	
	public ServiceCallEventsMt(ServiceCallEventsMt event) {
		super();
		this.id = event.getId();
		this.username = event.getUsername();
		this.msg = event.getMsg();
		this.orderId = event.getOrderId();
		this.docNumber = event.getDocNumber();
		this.serviceCode = event.getServiceCode();
		this.serviceUrl = event.getServiceUrl();
		this.serviceRequest = event.getServiceRequest();
		this.serviceResponse = event.getServiceResponse();
		this.sourceApp = event.getSourceApp();
		this.sourceAppVersion = event.getSourceAppVersion();
		this.result = event.getResult();
	}
	
	public ServiceCallEventsMt() {}


}
