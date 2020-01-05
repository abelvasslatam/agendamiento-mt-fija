package tdp.backend.mt.fija.common.util;

import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.List;

/**
 * Funciones para grabar logs
 *
 * @author Jhair Lozano
 * @date 2017-12-07
 */
public class LogVass {
    /**
     * Función para grabar el request del controller
     *
     * @author Jhair Lozano
     * @date 2017-12-07
     * @Logger logger       Hereda Configuración de la Clase
     * @String controller   Nombre del método
     * @Object obj          Request recibido por el método
     */
    public static void startController(Logger logger, String controller, Object obj) {
        logger.info("Inicio " + controller + " Controller");
        JSONObject jsonRequest = new JSONObject(obj);
        logger.info("REQUEST => " + jsonRequest.toString());
    }

    /**
     * Función para grabar el response del controller
     *
     * @author Jhair Lozano
     * @date 2017-12-07
     * @Logger logger       Hereda Configuración de la Clase
     * @String controller   Nombre del método
     * @Object obj          Request recibido por el método
     */
    public static void finishController(Logger logger, String controller, Object obj) {
        JSONObject jsonRequest = new JSONObject(obj);
        logger.info("RESPONSE => " + jsonRequest.toString());
        logger.info("Fin " + controller + " Controller");
    }

    /**
     * Función para grabar el response del servicio
     *
     * @author Jhair Lozano
     * @date 2017-12-07
     * @Logger logger       Hereda Configuración de la Clase
     * @String service      Nombre del método
     * @Object obj          Request recibido por el método
     */
    public static void serviceResponse(Logger logger, String service, Object obj) {
        JSONObject jsonResponse = new JSONObject();
        if (obj != null)
            jsonResponse = new JSONObject(obj);
        logger.info(service + " => " + jsonResponse.toString());
    }

    /**
     * Función para grabar log de las listas en forma de json
     *
     * @author Jhair Lozano
     * @date 2017-12-07
     * @Logger logger       Hereda Configuración de la Clase
     * @String service      Nombre del método
     * @String variable     Nombre de la variable
     * @Object obj          Request recibido por el método
     */
    public static void serviceResponseArray(Logger logger, String service, String variable, List obj) {
        JSONArray jsonResponse = new JSONArray();
        if (obj != null)
            jsonResponse = new JSONArray(obj);
        logger.info(service + " => " + variable + "(" + jsonResponse.length() + "): " + jsonResponse.toString());
    }

    /**
     * Función para grabar log de las listas HashMap en forma de json
     *
     * @author Jhair Lozano
     * @date 2018-03-06
     * @Logger logger       Hereda Configuración de la Clase
     * @String service      Nombre del método
     * @String variable     Nombre de la variable
     * @Object obj          Request recibido por el método
     */
    public static void serviceResponseHashMap(Logger logger, String service, String variable, HashMap<String, MessageEntities> obj) {
        JSONObject jsonResponse = new JSONObject();
        if (obj != null)
            jsonResponse = new JSONObject(obj);
        logger.info(service + " => " + variable + "(" + jsonResponse.length() + "): " + jsonResponse.toString());
    }

    /**
     * Función para grabar log de los objetos en forma de json
     *
     * @author Jhair Lozano
     * @date 2017-12-07
     * @Logger logger       Hereda Configuración de la Clase
     * @String service      Nombre del método
     * @String variable     Nombre de la variable
     * @Object obj          Request recibido por el método
     */
    public static void serviceResponseObject(Logger logger, String service, String variable, Object obj) {
        JSONObject jsonResponse = new JSONObject();
        if (obj != null)
            jsonResponse = new JSONObject(obj);
        logger.info(service + " => " + variable + ": " + jsonResponse.toString());
    }

    /**
     * Función para grabar el query del dao
     *
     * @author Jhair Lozano
     * @date 2017-12-07
     * @Logger logger       Hereda Configuración de la Clase
     * @String stmt         Sentendia del PreparedStatement
     */
    public static void daoQueryDAO(Logger logger, String metodo, String variable, String stmt) {
        logger.info(metodo + " => " + variable + ": " + getQuery(stmt));
    }

    /**
     * Función para grabar el query del dao
     *
     * @author Jhair Lozano
     * @date 2017-12-07
     * @Logger logger       Hereda Configuración de la Clase
     * @String stmt         Sentendia del PreparedStatement
     */
    public static void daoQuery(Logger logger, String stmt) {
        logger.info("QUERY => " + getQuery(stmt));
    }

    public static String getQuery(String query) {
        int position = 0;

        if (query.indexOf("SELECT") >= 0) {
            position = query.indexOf("SELECT");
        } else if (query.indexOf("INSERT") >= 0) {
            position = query.indexOf("INSERT");
        } else if (query.indexOf("UPDATE") >= 0) {
            position = query.indexOf("UPDATE");
        }

        return query.substring(position);
    }
}
