package tdp.backend.mt.fija.main.mt.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "endpoint_call_events", schema = "public")
@Data
@ToString
public class EndpointCallEvent {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
	
	@Column(name = "tag")
    private String tag;
	
	@Column(name = "datetime_request")
    private Timestamp datetimeRequest;
	
	@Column(name = "datetime_response")
    private Timestamp datetimeResponse;
	
	@Column(name = "uriendpoint")
    private String uriendpoint;
	
	@Column(name = "request")
    private String request;
	
	@Column(name = "response")
    private String response;
	
	@Column(name = "msg")
    private String msg;
	
	@Column(name = "result")
    private String result;
	
	@Column(name = "orderid")
    private String orderid;
	
	@Column(name = "dnicustomer")
    private String dnicustomer;
	
	@Column(name = "codatis")
    private String codatis;

   
}
