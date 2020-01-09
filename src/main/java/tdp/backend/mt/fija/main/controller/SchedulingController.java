package tdp.backend.mt.fija.main.controller;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.common.domain.Response;
import tdp.backend.mt.fija.common.util.LogVass;
import tdp.backend.mt.fija.common.util.ServiceConstants;
import tdp.backend.mt.fija.common.util.UtilMethods;
import tdp.backend.mt.fija.common.util.Xhttp;
import tdp.backend.mt.fija.main.fija.model.ServiceCallEventsFija;
import tdp.backend.mt.fija.main.fija.service.IServiceCallEventsFijaService;
import tdp.backend.mt.fija.main.mt.model.EndpointCallEvent;
import tdp.backend.mt.fija.main.mt.model.ServiceCallEventsMt;
import tdp.backend.mt.fija.main.mt.service.IEndpointCallEvents;
import tdp.backend.mt.fija.main.mt.service.IServiceCallEventsMtService;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.front.AvailabiltyTechAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.AvailabilityTechnicalAppointmentsResponse;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.ScheduleTechnicalAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response.ScheduleTechnicalAppointmentResponse;
import tdp.backend.mt.fija.main.restclient.updateCustomer.UpdateCustomerRequestFront;
import tdp.backend.mt.fija.main.restclient.updateCustomer.response.UpdateCustomerResponse;
import tdp.backend.mt.fija.main.service.SchedulingService;

@RestController
@RequestMapping("/schedule")
@Slf4j
public class SchedulingController {
	
	@Autowired
	IServiceCallEventsMtService service;
	
	@Autowired
	IServiceCallEventsFijaService serviceFija;
	
	@Autowired
	SchedulingService schedulingService;
	
	@Autowired
	IEndpointCallEvents endpointCallEventsService;
	
	private static final Logger logger = LogManager.getLogger();
	
	@PostMapping(value = "/availability-technical-appointments", produces = "application/json; charset=UTF-8")
	public Response<AvailabilityTechnicalAppointmentsResponse> 
	getAvailabilityTechnicalAppointments
	(@RequestBody AvailabiltyTechAppointmentRequestFront request,
		HttpServletRequest httpResquest,
		@RequestHeader(name = "X_HTTP_APPSOURCE") String X_HTTP_APPSOURCE,
	    @RequestHeader(name = "X_HTTP_APPVERSION") String X_HTTP_APPVERSION,
	    @RequestHeader(name = "X_HTTP_CUSTOMER") String X_HTTP_CUSTOMER,
	    @RequestHeader(name = "X_HTTP_ORDERMT") String X_HTTP_ORDERMT,
	    @RequestHeader(name = "X_HTTP_USUARIO") String X_HTTP_USUARIO) {
		
		Response<AvailabilityTechnicalAppointmentsResponse> response = new Response<>();
		
		
		Xhttp xhttp = new Xhttp();
        xhttp.setAppSource(X_HTTP_APPSOURCE);
        xhttp.setAppVersion(X_HTTP_APPVERSION);
        xhttp.setCustomer(X_HTTP_CUSTOMER);
        xhttp.setOrdenMT(X_HTTP_ORDERMT);
        xhttp.setUsuario(X_HTTP_USUARIO);
        
        Timestamp dateTimeRequest = UtilMethods.getFechaActual();
        
        try {
			response = schedulingService.getAvailabilityTechnicalAppointments(request, xhttp);
		} catch (Exception e) {
			log.error("Error method getAvailabilityTechnicalAppointments -> SchedulingController", e);
			response.setResponseMessage("Error getTechnicalAppointments -> SchedulingController, problemas en el servidor MT-fija agendamiento");
            response.setResponseCode(ServiceConstants.SERVICE_ERROR);
		}
        
        EndpointCallEvent endpointCallEvent = new EndpointCallEvent();
        Timestamp dateTimeResponse = UtilMethods.getFechaActual();
        endpointCallEvent = UtilMethods.JavaToJson(request, response, httpResquest.getRequestURI(), dateTimeRequest, response.getResponseCode(), response.getResponseCode(), xhttp);
		
        endpointCallEvent.setDatetimeResponse(dateTimeResponse);
        endpointCallEventsService.saveOrUpdate(endpointCallEvent);
        
        LogVass.finishController(logger, "getAvailabilityTechnicalAppointments", response);
		return response;
		
	}
	
	@PostMapping(value = "/schedule-technical-appointments", produces = "application/json; charset=UTF-8")
	public Response<ScheduleTechnicalAppointmentResponse> 
	getScheduleTechnicalAppointments
	(@RequestBody ScheduleTechnicalAppointmentRequestFront request,
		HttpServletRequest httpResquest,
		@RequestHeader(name = "X_HTTP_APPSOURCE") String X_HTTP_APPSOURCE,
	    @RequestHeader(name = "X_HTTP_APPVERSION") String X_HTTP_APPVERSION,
	    @RequestHeader(name = "X_HTTP_CUSTOMER") String X_HTTP_CUSTOMER,
	    @RequestHeader(name = "X_HTTP_ORDERMT") String X_HTTP_ORDERMT,
	    @RequestHeader(name = "X_HTTP_USUARIO") String X_HTTP_USUARIO) {
		
		Response<ScheduleTechnicalAppointmentResponse> response = new Response<>();
		
		
		Xhttp xhttp = new Xhttp();
        xhttp.setAppSource(X_HTTP_APPSOURCE);
        xhttp.setAppVersion(X_HTTP_APPVERSION);
        xhttp.setCustomer(X_HTTP_CUSTOMER);
        xhttp.setOrdenMT(X_HTTP_ORDERMT);
        xhttp.setUsuario(X_HTTP_USUARIO);
		
        
        try {
			response = schedulingService.getScheduleTechnicalAppointment(request, xhttp);
		} catch (Exception e) {
			log.error("Error method getScheduleTechnicalAppointments -> SchedulingController", e);
			response.setResponseMessage("Error getScheduleTechnicalAppointments -> SchedulingController, problemas en el servidor MT-fija agendamiento");
            response.setResponseCode(ServiceConstants.SERVICE_ERROR);
		}
		
        LogVass.finishController(logger, "getScheduleTechnicalAppointments", response);
		return response;
		
	}
	
	@PostMapping(value = "/update-customer", produces = "application/json; charset=UTF-8")
	public Response<UpdateCustomerResponse> 
	getUpdateCustomer
	(@RequestBody UpdateCustomerRequestFront request,
		HttpServletRequest httpResquest,
		@RequestHeader(name = "X_HTTP_APPSOURCE") String X_HTTP_APPSOURCE,
	    @RequestHeader(name = "X_HTTP_APPVERSION") String X_HTTP_APPVERSION,
	    @RequestHeader(name = "X_HTTP_CUSTOMER") String X_HTTP_CUSTOMER,
	    @RequestHeader(name = "X_HTTP_ORDERMT") String X_HTTP_ORDERMT,
	    @RequestHeader(name = "X_HTTP_USUARIO") String X_HTTP_USUARIO) {
		
		Response<UpdateCustomerResponse> response = new Response<>();
		
		
		Xhttp xhttp = new Xhttp();
        xhttp.setAppSource(X_HTTP_APPSOURCE);
        xhttp.setAppVersion(X_HTTP_APPVERSION);
        xhttp.setCustomer(X_HTTP_CUSTOMER);
        xhttp.setOrdenMT(X_HTTP_ORDERMT);
        xhttp.setUsuario(X_HTTP_USUARIO);
		
        
        try {
			response = schedulingService.getUpdateCustomer(request, xhttp);
		} catch (Exception e) {
			log.error("Error method getUpdateCustomer -> SchedulingController", e);
			response.setResponseMessage("Error getUpdateCustomer -> SchedulingController, problemas en el servidor MT-fija agendamiento");
            response.setResponseCode(ServiceConstants.SERVICE_ERROR);
		}
		
        LogVass.finishController(logger, "getUpdateCustomer", response);
		return response;
		
	}
	
	
	
	@GetMapping(value = "/sc", produces = "application/json; charset=UTF-8")
	public void getTechnicalAppointments() {
		try {
			ServiceCallEventsMt s = service.findById(10919L);
			System.out.println(s);
			
			ServiceCallEventsFija sf = serviceFija.findById(4160382L);
			System.out.println(sf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
