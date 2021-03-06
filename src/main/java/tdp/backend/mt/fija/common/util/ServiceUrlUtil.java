package tdp.backend.mt.fija.common.util;

public class ServiceUrlUtil {


    private static String PROP_FILE = (System.getenv("PROPERTIES")!=null?System.getenv("PROPERTIES"):"setup.properties");
    //private static String PROP_FILE = "setup.properties";

    //AVAILABILITY TECHNICAL - SCHEDULE TECHNICAL
    public static String BASE_SERVICE_URL_AVAILABILITY_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.base.url.schedule");
    public static String BASE_SERVICE_API_KEY_AVAILABILITY_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.key.schedule");
    public static String BASE_SERVICE_API_SECRET_AVAILABILITY_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.secret.key.schedule");
    public static String BASE_SERVICE_API_AUTHORIZATION_AVAILABILITY_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.authorization.key.schedule");

    //UPDATE CLIENT
    
    public static String BASE_SERVICE_URL_UPDATE_CLIENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.base.url.update.customer");
    public static String BASE_SERVICE_API_KEY_UPDATE_CLIENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.key.update.customer");
    public static String BASE_SERVICE_API_SECRET_UPDATE_CLIENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.secret.key.update.customer");
    public static String BASE_SERVICE_API_AUTHORIZATION_UPDATE_CLIENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.authorization.key.update.customer");
    
    //SCHEDULE TECHNICAL
    
//    public static String BASE_SERVICE_URL_SCHEDULE_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.base.url.schedule");
//    public static String BASE_SERVICE_API_KEY_SCHEDULE_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.key.schedule");
//    public static String BASE_SERVICE_API_SECRET_SCHEDULE_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.secret.key.schedule");
//    public static String BASE_SERVICE_API_AUTHORIZATION_SCHEDULE_TECHNICAL_APPOINTMENT = PropertiesUtil.getPropertyFromFile(PROP_FILE,"api.connect.authorization.key.schedule");
    
    
}

