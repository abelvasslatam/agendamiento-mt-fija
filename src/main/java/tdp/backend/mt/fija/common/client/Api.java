package tdp.backend.mt.fija.common.client;

import java.net.URI;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Body;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Header;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.TechnicalAppointmentsRequest;

@Slf4j
public class Api {
	public static String jerseyPOSTSchedule(URI uri, Object request, String apiId, String apiSecret, String auth) {
		Date timeStamp = new Date();
		ObjectMapper mapper = new ObjectMapper();
		String requestJson;
		try {
			requestJson = mapper.writeValueAsString(request);
			log.info(String.format("%s consulta a %s: %s", timeStamp.getTime(), uri, requestJson));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		//consumo
		
		TechnicalAppointmentsRequest requestBody = (TechnicalAppointmentsRequest) request;

		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-IBM-Client-Id", apiId);
		headers.set("X-IBM-Client-Secret", apiSecret);
		headers.set("Authorization", auth);
		
		HttpEntity<Object> requestApi = new HttpEntity<>(request, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.postForObject( uri, requestApi, String.class);
		
		//fin consumo

		log.info(String.format("%s respuesta de %s: %s", timeStamp.getTime(), uri, json));
		return json;
	}
}
