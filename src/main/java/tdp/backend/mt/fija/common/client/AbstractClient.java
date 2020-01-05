package tdp.backend.mt.fija.common.client;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.common.domain.ApiResponse;

@Slf4j
public abstract class AbstractClient<T, U> {
    protected ClientConfig config;
    
    private Class<U> typeOfU;
    
        
    @SuppressWarnings("unchecked")
	protected AbstractClient(ClientConfig config) {
        super();
        this.config = config;
        this.typeOfU = (Class<U>)
                ((ParameterizedType)getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[1];
    }
    
    protected abstract String getServiceCode();
    

    
    protected ApiResponse getResponseObject(String json) throws JsonParseException, JsonMappingException, IOException {
    	
    	String classResponse = typeOfU.getName(); //example: tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.TechnicalAppointmentsRequest
    	
    	//get name class response
    	String[] bits = classResponse.split("\\.");
    	String classNameResponse = bits[bits.length-1];
    	log.info("Name class response: " + classNameResponse);
    	
    	//build json parse
    	String responseJson = "{" + 
				"\""+classNameResponse+"\":";
		responseJson += json;
		responseJson += "}";
		
		log.info("json to parse: "+ responseJson);
    	
        ObjectMapper mapper = new ObjectMapper();
        ApiResponse object = mapper.readValue(responseJson, new TypeReference<ApiResponse>() {});
        return object;
    }
    
    public ClientResult<ApiResponse> post(T body) {
        ClientResult<ApiResponse> result = new ClientResult<>();

        try {
            String json = doRequest(body);
            ApiResponse apiResponse = getResponseObject(json);
            result.setResult(apiResponse);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
        } finally {
        }
        return result;
    }
    
    protected String doRequest(T body) throws URISyntaxException {
        URI uri = new URI(config.getUrl());
        String jsonSMS = Api.jerseyPOSTSchedule(uri, body, config.getApiId(), config.getApiSecret(), config.getAuthorization());
        return jsonSMS;
    }
    
    

}
