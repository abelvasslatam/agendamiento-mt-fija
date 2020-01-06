package tdp.backend.mt.fija.common.client;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    	String jsonClient = buildJsonClient(classNameResponse, json);
    	
        ObjectMapper mapper = new ObjectMapper();
        ApiResponse object = mapper.readValue(jsonClient, new TypeReference<ApiResponse>() {});
        return object;
    }
    
    protected String buildJsonClient(String classNameResponse, String json) {
    	String jsonClient = "{" + 
				"\""+classNameResponse+"\":";
		jsonClient += json;
		jsonClient += "}";
		
		log.info("Json client to parse: "+ jsonClient);
		return jsonClient;
    }
    
    
    
    public ClientResult<ApiResponse> post(T body, String tipoSCE) {
    	
    	
    	
        ClientResult<ApiResponse> result = new ClientResult<>();
               
        String jsonRequest;
        try {
            jsonRequest = new ObjectMapper().writeValueAsString(body);
        } catch (JsonProcessingException e1) {
            jsonRequest = "N/A";
        }

        try {
            String json = doRequest(body);
            
            if(tipoSCE.equals("MT")) result.getSceMt().setServiceResponse(json);
        	else if (tipoSCE.equals("FIJA")) result.getSceFija().setServiceResponse(json);
            
            ApiResponse apiResponse = getResponseObject(json);
            result.setResult(apiResponse);
            result.setSuccess(true);          
            
            if(tipoSCE.equals("MT")) {
            	result.getSceMt().setResult("OK");
            	result.getSceMt().setMsg("OK"); 		
        	} else if (tipoSCE.equals("FIJA")) {
        		result.getSceFija().setResult("OK");
        		result.getSceFija().setMsg("OK");
        	}

        } catch (Exception e) {
            result.setSuccess(false);
            
            if(tipoSCE.equals("MT")) {
            	result.getSceMt().setResult("ERROR");
            	result.getSceMt().setMsg(e.getMessage());
            	if(result.getSceMt().getServiceResponse() == null) {
            		result.getSceMt().setServiceResponse("N/A");
            	}
        	} else if (tipoSCE.equals("FIJA")) {
        		result.getSceFija().setResult("ERROR");
        		result.getSceFija().setMsg(e.getMessage());
        		if(result.getSceFija().getServiceResponse() == null) {
            		result.getSceFija().setServiceResponse("N/A");
            	}
        	}
            
        } finally {
        	
        	if(tipoSCE.equals("MT")) {
        		result.getSceMt().setServiceRequest(jsonRequest);
        		result.getSceMt().setServiceUrl(config.getUrl());
        		result.getSceMt().setServiceCode(getServiceCode());
        	} else if (tipoSCE.equals("FIJA")) {
        		result.getSceFija().setServiceRequest(jsonRequest);
        		result.getSceFija().setServiceUrl(config.getUrl());
        		result.getSceFija().setServiceCode(getServiceCode());
        	}
        	
        }
        return result;
    }
    
    
    protected String doRequest(T body) throws URISyntaxException {
        URI uri = new URI(config.getUrl());
        String jsonSMS = Api.jerseyPOSTSchedule(uri, body, config.getApiId(), config.getApiSecret(), config.getAuthorization());
        return jsonSMS;
    }
    
    

}
