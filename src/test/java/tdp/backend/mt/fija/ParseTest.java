package tdp.backend.mt.fija;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.common.domain.ApiResponse;

@SpringBootTest
@Slf4j
public class ParseTest {
	
	@Test
	void parseTest() {
		String json = "{\r\n" + 
				"  \"TechnicalAppointmentsResponse\": {\r\n" + 
				"    \"header\": {\r\n" + 
				"        \"appName\": \"APP_AGENDAMIENTO\",\r\n" + 
				"        \"dateTime\": \"2020-01-04T09:58:33.239-0500\",\r\n" + 
				"        \"operation\": \"OPER_GET_SCHEDULE_AVAILABILITY\",\r\n" + 
				"        \"resultCode\": \"200\",\r\n" + 
				"        \"message\": \"OK\",\r\n" + 
				"        \"messageId\": \"\",\r\n" + 
				"        \"timestamp\": \"04-01-2020 09:58:33.2\"\r\n" + 
				"    },\r\n" + 
				"    \"body\": {\r\n" + 
				"        \"header\": null,\r\n" + 
				"        \"capacityFicticious\": {\r\n" + 
				"            \"location\": \"BK_COBRA_ZONA5_SJ_MASIVO\",\r\n" + 
				"            \"date\": [\r\n" + 
				"                {\r\n" + 
				"                    \"day\": \"2020-01-06\",\r\n" + 
				"                    \"slot\": [\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"PM\",\r\n" + 
				"                            \"quantity\": \"3\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"AM\",\r\n" + 
				"                            \"quantity\": \"2\"\r\n" + 
				"                        }\r\n" + 
				"                    ]\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                    \"day\": \"2020-01-07\",\r\n" + 
				"                    \"slot\": [\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"PM\",\r\n" + 
				"                            \"quantity\": \"3\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"AM\",\r\n" + 
				"                            \"quantity\": \"2\"\r\n" + 
				"                        }\r\n" + 
				"                    ]\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                    \"day\": \"2020-01-08\",\r\n" + 
				"                    \"slot\": [\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"PM\",\r\n" + 
				"                            \"quantity\": \"3\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"AM\",\r\n" + 
				"                            \"quantity\": \"2\"\r\n" + 
				"                        }\r\n" + 
				"                    ]\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                    \"day\": \"2020-01-09\",\r\n" + 
				"                    \"slot\": [\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"PM\",\r\n" + 
				"                            \"quantity\": \"3\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"AM\",\r\n" + 
				"                            \"quantity\": \"2\"\r\n" + 
				"                        }\r\n" + 
				"                    ]\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                    \"day\": \"2020-01-10\",\r\n" + 
				"                    \"slot\": [\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"PM\",\r\n" + 
				"                            \"quantity\": \"3\"\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                            \"name\": \"AM\",\r\n" + 
				"                            \"quantity\": \"2\"\r\n" + 
				"                        }\r\n" + 
				"                    ]\r\n" + 
				"                }\r\n" + 
				"            ]\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		
		
		String responseJson = "{\r\n" + 
				"\"TechnicalAppointmentsResponse\":";
		responseJson += json;
		responseJson += "}";
		ObjectMapper mapper = new ObjectMapper();
        try {
			ApiResponse object = mapper.readValue(json, new TypeReference<ApiResponse>() {});
			log.info(""+object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
