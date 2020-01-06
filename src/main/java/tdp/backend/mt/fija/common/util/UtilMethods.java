package tdp.backend.mt.fija.common.util;

import java.sql.Timestamp;
import java.util.Date;

import tdp.backend.mt.fija.common.client.ClientConfig;


public class UtilMethods {

	//SCHEDULE
    public static ClientConfig buildConfigHeaderSchedule(String serviceEndpoint, String serviceName) {
        ClientConfig config = new ClientConfig();
        String serviceUrl = ServiceUrlUtil.BASE_SERVICE_URL_SCHEDULE + serviceEndpoint+serviceName;
        String apikey = ServiceUrlUtil.BASE_SERVICE_API_KEY_SCHEDULE;
        String apiSecret = ServiceUrlUtil.BASE_SERVICE_API_SECRET_SCHEDULE;
        String apiAuthorization = ServiceUrlUtil.BASE_SERVICE_API_AUTHORIZATION_SCHEDULE;
        String operation = Constants.OPERATION;

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
