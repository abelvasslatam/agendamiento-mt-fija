package tdp.backend.mt.fija.main.fija.model;

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
@Table(name = "service_call_events", schema = "ibmx_a07e6d02edaf552")
@Getter
@Setter
@ToString
public class ServiceCallEventsFija implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="event_datetime")
	private Timestamp eventDateTime;
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
	@Column(name="app_module")
	private String appModule;
	@Column(name="latitude_sales")
	private String latitudeSales;
	@Column(name="longitude_sales")
	private String longitudeSales;
	

}
