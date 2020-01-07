package tdp.backend.mt.fija.common.util;

import java.sql.Timestamp;

import tdp.backend.mt.fija.common.client.ClientConfig;


public class UtilMethods {

	
    public static ClientConfig buildConfigAvailabilityTechnicalHeader(String serviceEndpoint, String serviceName) {
        ClientConfig config = new ClientConfig();
        String serviceUrl = ServiceUrlUtil.BASE_SERVICE_URL_AVAILABILITY_TECHNICAL_APPOINTMENT + serviceEndpoint+serviceName;
        String apikey = ServiceUrlUtil.BASE_SERVICE_API_KEY_AVAILABILITY_TECHNICAL_APPOINTMENT;
        String apiSecret = ServiceUrlUtil.BASE_SERVICE_API_SECRET_AVAILABILITY_TECHNICAL_APPOINTMENT;
        String apiAuthorization = ServiceUrlUtil.BASE_SERVICE_API_AUTHORIZATION_AVAILABILITY_TECHNICAL_APPOINTMENT;
        String operation = Constants.OPERATION; //REVISAR

        config.setUrl(serviceUrl);
        config.setApiId(apikey);
        config.setApiSecret(apiSecret);
        config.setAuthorization(apiAuthorization);
        config.setOperation(operation);

        return config;
    }
    

    public static ClientConfig buildConfigScheduleTechnicalHeader(String serviceEndpoint, String serviceName) {
    	ClientConfig config = new ClientConfig();
    	
    	String serviceUrl = ServiceUrlUtil.BASE_SERVICE_URL_AVAILABILITY_TECHNICAL_APPOINTMENT + serviceEndpoint+serviceName;
        String apikey = ServiceUrlUtil.BASE_SERVICE_API_KEY_AVAILABILITY_TECHNICAL_APPOINTMENT;
        String apiSecret = ServiceUrlUtil.BASE_SERVICE_API_SECRET_AVAILABILITY_TECHNICAL_APPOINTMENT;
        String apiAuthorization = ServiceUrlUtil.BASE_SERVICE_API_AUTHORIZATION_AVAILABILITY_TECHNICAL_APPOINTMENT;
        String operation = Constants.OPERATION; //REVISAR

        config.setUrl(serviceUrl);
        config.setApiId(apikey);
        config.setApiSecret(apiSecret);
        config.setAuthorization(apiAuthorization);
        config.setOperation(operation);
    	return config;
    }
    
    public static ClientConfig buildConfigScheduleUpdateCustomer(String serviceEndpoint, String serviceName) {
    	ClientConfig config = new ClientConfig();
    	
    	String serviceUrl = ServiceUrlUtil.BASE_SERVICE_URL_UPDATE_CLIENT + serviceEndpoint+serviceName;
        String apikey = ServiceUrlUtil.BASE_SERVICE_API_KEY_UPDATE_CLIENT;
        String apiSecret = ServiceUrlUtil.BASE_SERVICE_API_SECRET_UPDATE_CLIENT;
        String apiAuthorization = ServiceUrlUtil.BASE_SERVICE_API_AUTHORIZATION_UPDATE_CLIENT;
        String operation = Constants.OPERATION; //REVISAR

        config.setUrl(serviceUrl);
        config.setApiId(apikey);
        config.setApiSecret(apiSecret);
        config.setAuthorization(apiAuthorization);
        config.setOperation(operation);
    	
    	return config;
    }
    
    public static Timestamp getFechaActual(){
        Timestamp fecha = new Timestamp(System.currentTimeMillis() - 18000000); // -5 horas
        return fecha;
    }

}
