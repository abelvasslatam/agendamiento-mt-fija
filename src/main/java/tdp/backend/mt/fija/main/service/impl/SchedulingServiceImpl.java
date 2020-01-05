package tdp.backend.mt.fija.main.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.common.client.ClientResult;
import tdp.backend.mt.fija.common.domain.ApiResponse;
import tdp.backend.mt.fija.common.domain.Response;
import tdp.backend.mt.fija.common.util.ServiceConstants;
import tdp.backend.mt.fija.common.util.Xhttp;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.AvailabilityTechnicalAppointmentsClient;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.AvailabiltyTechAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Body;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Header;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.TechnicalAppointmentsRequest;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.TechnicalAppointmentsResponse;
import tdp.backend.mt.fija.main.service.SchedulingService;

@Service
@Slf4j
public class SchedulingServiceImpl implements SchedulingService{

	@Override
	public Response<TechnicalAppointmentsResponse> getAvailabilityTechnicalAppointments(
			AvailabiltyTechAppointmentRequestFront request, Xhttp xhttp) {
		
		Response<TechnicalAppointmentsResponse> response = new Response<>();
		
		int retryCount = 3;
		response = callApi(request,retryCount, xhttp);

		return response;
	}
	
	private Response<TechnicalAppointmentsResponse> callApi(AvailabiltyTechAppointmentRequestFront request, int retryCount, Xhttp xhttp) {
		Response<TechnicalAppointmentsResponse> response = new Response<>();
		
		
		AvailabilityTechnicalAppointmentsClient client = new AvailabilityTechnicalAppointmentsClient(new ClientConfig());
		
		TechnicalAppointmentsRequest requestBody = new TechnicalAppointmentsRequest();
		//setear
		//lógica para armar el request
		
		//se obtiene algo como esto
		requestBody = getRequestBody();
		
		ClientResult<ApiResponse> result = client.post(requestBody);
		
		//result = null;
		if (result == null || !result.isSuccess() || result.getResult() == null) {
			if(retryCount > 0) {
				log.info("No se obtuvo respuesta correcta del servicio, se procede a reintentar");
				retryCount --;
				return callApi(request, retryCount, xhttp);
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
			
			TechnicalAppointmentsResponse responseData = apiResponse.getResponse();
			
			response.setResponseCode(ServiceConstants.SERVICE_SUCCESS);
			response.setResponseMessage("OK");
			response.setResponseData(responseData);
			
		}
		
		return response;
	}
	
	private TechnicalAppointmentsRequest getRequestBody() {
		TechnicalAppointmentsRequest requestBody = new TechnicalAppointmentsRequest();
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

}
