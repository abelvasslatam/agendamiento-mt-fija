package tdp.backend.mt.fija.main.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.common.client.ClientResult;
import tdp.backend.mt.fija.common.domain.ApiResponse;
import tdp.backend.mt.fija.common.domain.Response;
import tdp.backend.mt.fija.common.util.ServiceConstants;
import tdp.backend.mt.fija.common.util.UtilMethods;
import tdp.backend.mt.fija.common.util.Xhttp;
import tdp.backend.mt.fija.main.fija.service.IServiceCallEventsFijaService;
import tdp.backend.mt.fija.main.mt.service.IServiceCallEventsMtService;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.AvailabilityTechnicalAppointmentsClient;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.AvailabiltyTechAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Body;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Header;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.AvailabilityTechnicalAppointmentsRequest;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.AvailabilityTechnicalAppointmentsResponse;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.ScheduleTechnicalAppointmentClient;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.ScheduleTechnicalAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.ScheduleTechnicalAppointmentRequest;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response.ScheduleTechnicalAppointmentResponse;
import tdp.backend.mt.fija.main.service.SchedulingService;

@Service
@Slf4j
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	IServiceCallEventsMtService serviceCallEventsMtService;
	
	@Autowired
	IServiceCallEventsFijaService serviceCallEventsFijaService;

	@Override
	public Response<AvailabilityTechnicalAppointmentsResponse> getAvailabilityTechnicalAppointments(
			AvailabiltyTechAppointmentRequestFront request, Xhttp xhttp) {
		
		Response<AvailabilityTechnicalAppointmentsResponse> response = new Response<>();
		
		int retryCount = 3;
		response = callApiAvailabilityTechnicalAppointment(request,retryCount, xhttp);

		return response;
	}
	
	private Response<AvailabilityTechnicalAppointmentsResponse> callApiAvailabilityTechnicalAppointment(AvailabiltyTechAppointmentRequestFront request, int retryCount, Xhttp xhttp) {
		Response<AvailabilityTechnicalAppointmentsResponse> response = new Response<>();
		
		
		AvailabilityTechnicalAppointmentsClient client = new AvailabilityTechnicalAppointmentsClient(new ClientConfig());
		
		AvailabilityTechnicalAppointmentsRequest requestBody = new AvailabilityTechnicalAppointmentsRequest();
		//setear
		//lógica para armar el request
		
		//se obtiene algo como esto
		requestBody = getRequestBodyAva();
		
		Timestamp dateTimeRequest = UtilMethods.getFechaActual();
		
		ClientResult<ApiResponse> result = client.post(requestBody, request.getTipoCliente());
		
		Timestamp dateTimeResponse = UtilMethods.getFechaActual();
		
		
		if(request.getTipoCliente().equals("MT")) {
			
			result.getSceMt().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceMt().setSourceApp(xhttp.getAppSource());;
			result.getSceMt().setDocNumber(xhttp.getCustomer());;
			result.getSceMt().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceMt().setUsername(xhttp.getUsuario());
			result.getSceMt().setTag("SERVICE_CALL");
			result.getSceMt().setDateTimeRequest(dateTimeRequest);
			result.getSceMt().setDateTimeResponse(dateTimeResponse);
			
			serviceCallEventsMtService.saveOrUpdate(result.getSceMt());
		} else if (request.getTipoCliente().equals("FIJA")) {
			result.getSceFija().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceFija().setSourceApp(xhttp.getAppSource());;
			result.getSceFija().setDocNumber(xhttp.getCustomer());;
			result.getSceFija().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceFija().setUsername(xhttp.getUsuario());
			result.getSceFija().setTag("SERVICE_CALL");
			result.getSceFija().setEventDateTime(dateTimeRequest);
			serviceCallEventsFijaService.saveOrUpdate(result.getSceFija());
		}
		
		if (result == null || !result.isSuccess() || result.getResult() == null) {
			if(retryCount > 0) {
				log.info("No se obtuvo respuesta correcta del servicio, se procede a reintentar");
				retryCount --;
				return callApiAvailabilityTechnicalAppointment(request, retryCount, xhttp);
			} else {
				log.error("Error en la petición del servicio, reintentos culminados");
				response.setResponseCode(ServiceConstants.SERVICE_ERROR);
                response.setResponseMessage("Error del servidor en el consumo del api: /schedule/beforesales/availability-technical-appointment");
                response.setResponseData(null);
                return response;
				
			}
		} else {
			//aqui cuando se tiene la respuesta del api
			
			ApiResponse apiResponse = result.getResult();
			
			AvailabilityTechnicalAppointmentsResponse responseData = apiResponse.getResponseAvailability();
			
			response.setResponseCode(ServiceConstants.SERVICE_SUCCESS);
			response.setResponseMessage("OK");
			response.setResponseData(responseData);
			
		}
		
		return response;
	}
	
	private AvailabilityTechnicalAppointmentsRequest getRequestBodyAva() {
		AvailabilityTechnicalAppointmentsRequest requestBody = new AvailabilityTechnicalAppointmentsRequest();
		Header headerBody =  new Header();
		
		headerBody.setAppName("APP_WEB_FRONT_TRAZABILIDAD");
		headerBody.setUser("USER_TRAZABILIDAD");
		headerBody.setOperation("OPER_GET_SCHEDULE_AVAILABILITY");
		headerBody.setMessageId("");
		headerBody.setTimestamp("");
		
		Body body = new Body();
		body.setCodeOrigin("VF");
		body.setCoordXclient("-77.0902642");
		body.setCoordYclient("-12.0386869");
		body.setCommercialOp("Alta Componente BA");
		body.setCategory("");
		body.setInternetTech("HFC");
		body.setTheirProductCode("");
		body.setLineEquipment("");
		body.setInternetEquipment("");
		body.setTvEquipment("");
		body.setCodProductPSI("P004");
		
		
		requestBody.setHeader(headerBody);
		requestBody.setBody(body);
		
		return requestBody;
	}

	@Override
	public Response<ScheduleTechnicalAppointmentResponse> getScheduleTechnicalAppointment(
			ScheduleTechnicalAppointmentRequestFront request, Xhttp xhttp) {
		

		
		Response<ScheduleTechnicalAppointmentResponse> response = new Response<>();
		
		int retryCount = 3;
		response = callApiScheduleTechnicalAppointment(request,retryCount, xhttp);

		return response;
	}
	
	private Response<ScheduleTechnicalAppointmentResponse> callApiScheduleTechnicalAppointment(ScheduleTechnicalAppointmentRequestFront request, int retryCount, Xhttp xhttp) {
		Response<ScheduleTechnicalAppointmentResponse> response = new Response<>();
		
		
		ScheduleTechnicalAppointmentClient client = new ScheduleTechnicalAppointmentClient(new ClientConfig());
		
		ScheduleTechnicalAppointmentRequest requestBody = new ScheduleTechnicalAppointmentRequest();
		//setear
		//lógica para armar el request
		
		//se obtiene algo como esto
		requestBody = getRequestBodySch();
		
		Timestamp dateTimeRequest = UtilMethods.getFechaActual();
		
		ClientResult<ApiResponse> result = client.post(requestBody, request.getTipoCliente());
		
		Timestamp dateTimeResponse = UtilMethods.getFechaActual();
		
		if(request.getTipoCliente().equals("MT")) {
			
			result.getSceMt().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceMt().setSourceApp(xhttp.getAppSource());;
			result.getSceMt().setDocNumber(xhttp.getCustomer());;
			result.getSceMt().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceMt().setUsername(xhttp.getUsuario());
			result.getSceMt().setTag("SERVICE_CALL");
			result.getSceMt().setDateTimeRequest(dateTimeRequest);
			result.getSceMt().setDateTimeResponse(dateTimeResponse);
			
			serviceCallEventsMtService.saveOrUpdate(result.getSceMt());
		} else if (request.getTipoCliente().equals("FIJA")) {
			result.getSceFija().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceFija().setSourceApp(xhttp.getAppSource());;
			result.getSceFija().setDocNumber(xhttp.getCustomer());;
			result.getSceFija().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceFija().setUsername(xhttp.getUsuario());
			result.getSceFija().setTag("SERVICE_CALL");
			result.getSceFija().setEventDateTime(dateTimeRequest);
			serviceCallEventsFijaService.saveOrUpdate(result.getSceFija());
		}
		
		
		if (result == null || !result.isSuccess() || result.getResult() == null) {
			if(retryCount > 0) {
				log.info("No se obtuvo respuesta correcta del servicio, se procede a reintentar");
				retryCount --;
				return callApiScheduleTechnicalAppointment(request, retryCount, xhttp);
			} else {
				log.error("Error en la petición del servicio, reintentos culminados");
				response.setResponseCode(ServiceConstants.SERVICE_ERROR);
                response.setResponseMessage("Error del servidor en el consumo del api: /schedule/beforesales/schedule-technical-appointments");
                response.setResponseData(null);
                return response;
				
			}
		} else {
			//aqui cuando se tiene la respuesta del api
			
			ApiResponse apiResponse = result.getResult();
			
			ScheduleTechnicalAppointmentResponse responseData = apiResponse.getResponseSchedule();
			
			response.setResponseCode(ServiceConstants.SERVICE_SUCCESS);
			response.setResponseMessage("OK");
			response.setResponseData(responseData);
			
		}
		
		return response;
		
		
		
		
	}
	
	
	private ScheduleTechnicalAppointmentRequest getRequestBodySch() {
		ScheduleTechnicalAppointmentRequest requestBody = new ScheduleTechnicalAppointmentRequest();
		
		tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Header headerBody =  new tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Header();
		
		
		headerBody.setAppName("MOVISTARTOTAL");
		headerBody.setUser("user_movistarTotal");
		headerBody.setOperation("scheduleAppointments");
		headerBody.setMessageId("57559b0c-5755-5755-5755-57559b0ceb0e");
		headerBody.setTimestamp("2019-11-21T04:21:32.518");

		
		tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Body body = new tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Body();
		body.setOriginCode("VF");
		body.setSaleCode("-LpeiZcPQKfAfiw4slNe");
		body.setBucket("BK_COBRA_ZONA5_SJ_MASIVO");
		body.setScheduleDate("2020-01-05");
		body.setScheduleRange("AM");
		body.setCoordinateX("-77.0902642");
		body.setCoordinateY("-12.0386869");
		body.setDocumentType("DNI");
		body.setDocumentNumber("23451623");
		body.setCustomerName("RODRIGO");
		body.setCustomerPatSurname("PORTILLA");
		body.setCustomerMatSurname("IBARRA");
		body.setCommercialOp("Alta Componente BA");
		body.setInternetTech(null);
		body.setTechnologyTV(null);
		body.setCodProductPSI("P004");
		
		requestBody.setHeader(headerBody);
		requestBody.setBody(body);
		
		return requestBody;
	}
	
	
	
	

}
