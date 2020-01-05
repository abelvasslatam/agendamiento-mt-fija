package tdp.backend.mt.fija;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Body;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Header;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.TechnicalAppointmentsRequest;

@SpringBootTest
@Slf4j
public class ApiTest {
	
	@Test
	void contextLoads() {
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
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-IBM-Client-Id", "53657561-e2cf-444c-8c3e-7d6dda7b6eb1");
		headers.set("X-IBM-Client-Secret", "0b4c6989-c4ad-4624-b444-df32d0233b8a");
		headers.set("Authorization", "Basic dHJhY2VhYmlsaXR5VXNlcjptMFYxc3RAUkBnM25kNG0xM250MA==");
		
		HttpEntity<TechnicalAppointmentsRequest> request = new HttpEntity<>(requestBody, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.postForObject( "https://agendamiento-trazabilidad-test.mybluemix.net/schedule/beforesales/availability-technical-appointment", request, String.class);
		
		log.info(json);
	}

}
