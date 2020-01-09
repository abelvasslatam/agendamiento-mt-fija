package tdp.backend.mt.fija.common.util;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.main.mt.model.EndpointCallEvent;

public class UtilMethods {

	public static ClientConfig buildConfigAvailabilityTechnicalHeader(String serviceEndpoint, String serviceName) {
		ClientConfig config = new ClientConfig();
		String serviceUrl = ServiceUrlUtil.BASE_SERVICE_URL_AVAILABILITY_TECHNICAL_APPOINTMENT + serviceEndpoint
				+ serviceName;
		String apikey = ServiceUrlUtil.BASE_SERVICE_API_KEY_AVAILABILITY_TECHNICAL_APPOINTMENT;
		String apiSecret = ServiceUrlUtil.BASE_SERVICE_API_SECRET_AVAILABILITY_TECHNICAL_APPOINTMENT;
		String apiAuthorization = ServiceUrlUtil.BASE_SERVICE_API_AUTHORIZATION_AVAILABILITY_TECHNICAL_APPOINTMENT;
		String operation = Constants.OPERATION; // REVISAR

		config.setUrl(serviceUrl);
		config.setApiId(apikey);
		config.setApiSecret(apiSecret);
		config.setAuthorization(apiAuthorization);
		config.setOperation(operation);

		return config;
	}

	public static ClientConfig buildConfigScheduleTechnicalHeader(String serviceEndpoint, String serviceName) {
		ClientConfig config = new ClientConfig();

		String serviceUrl = ServiceUrlUtil.BASE_SERVICE_URL_AVAILABILITY_TECHNICAL_APPOINTMENT + serviceEndpoint
				+ serviceName;
		String apikey = ServiceUrlUtil.BASE_SERVICE_API_KEY_AVAILABILITY_TECHNICAL_APPOINTMENT;
		String apiSecret = ServiceUrlUtil.BASE_SERVICE_API_SECRET_AVAILABILITY_TECHNICAL_APPOINTMENT;
		String apiAuthorization = ServiceUrlUtil.BASE_SERVICE_API_AUTHORIZATION_AVAILABILITY_TECHNICAL_APPOINTMENT;
		String operation = Constants.OPERATION; // REVISAR

		config.setUrl(serviceUrl);
		config.setApiId(apikey);
		config.setApiSecret(apiSecret);
		config.setAuthorization(apiAuthorization);
		config.setOperation(operation);
		return config;
	}

	public static ClientConfig buildConfigScheduleUpdateCustomer(String serviceEndpoint, String serviceName) {
		ClientConfig config = new ClientConfig();

		String serviceUrl = ServiceUrlUtil.BASE_SERVICE_URL_UPDATE_CLIENT + serviceEndpoint + serviceName;
		String apikey = ServiceUrlUtil.BASE_SERVICE_API_KEY_UPDATE_CLIENT;
		String apiSecret = ServiceUrlUtil.BASE_SERVICE_API_SECRET_UPDATE_CLIENT;
		String apiAuthorization = ServiceUrlUtil.BASE_SERVICE_API_AUTHORIZATION_UPDATE_CLIENT;
		String operation = Constants.OPERATION; // REVISAR

		config.setUrl(serviceUrl);
		config.setApiId(apikey);
		config.setApiSecret(apiSecret);
		config.setAuthorization(apiAuthorization);
		config.setOperation(operation);

		return config;
	}

	public static Timestamp getFechaActual() {
		Timestamp fecha = new Timestamp(System.currentTimeMillis() - 18000000); // -5 horas
		return fecha;
	}

	public static EndpointCallEvent JavaToJson(Object objectRequest, Object objectResponse, String uri, Timestamp t,
			String message, String result, Xhttp xhttp) {
		EndpointCallEvent endpoint = new EndpointCallEvent();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			endpoint.setDatetimeRequest(t);
			endpoint.setUriendpoint(uri);
			endpoint.setRequest(objectMapper.writeValueAsString(objectRequest));
			endpoint.setResponse(objectMapper.writeValueAsString(objectResponse));
			endpoint.setMsg(message);
			endpoint.setResult(result);
			endpoint.setOrderid(xhttp.getOrdenMT());
			endpoint.setDnicustomer(xhttp.getCustomer());
			endpoint.setCodatis(xhttp.getCodatis());
			endpoint.setTag("ENDPOINT_CALL");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return endpoint;
	}

}
