package tdp.backend.mt.fija.main.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.common.client.ClientResult;
import tdp.backend.mt.fija.common.domain.ApiResponse;
import tdp.backend.mt.fija.common.domain.Response;
import tdp.backend.mt.fija.common.util.ServiceConstants;
import tdp.backend.mt.fija.common.util.UtilMethods;
import tdp.backend.mt.fija.common.util.Xhttp;
import tdp.backend.mt.fija.main.fija.model.TdpOrder;
import tdp.backend.mt.fija.main.fija.model.TdpSva;
import tdp.backend.mt.fija.main.fija.service.IServiceCallEventsFijaService;
import tdp.backend.mt.fija.main.fija.service.ITdpOrderFijaService;
import tdp.backend.mt.fija.main.fija.service.ITdpSvaFijaService;
import tdp.backend.mt.fija.main.mt.model.HfcPsListMt;
import tdp.backend.mt.fija.main.mt.service.IHfcPsListMtService;
import tdp.backend.mt.fija.main.mt.service.IServiceCallEventsMtService;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.AvailabilityTechnicalAppointmentsClient;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.AvailabilityTechnicalAppointmentsRequest;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Body;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.Header;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.front.AvailabiltyTechAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.front.Sva;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.AvailabilityTechnicalAppointmentsResponse;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.Dates;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.ScheduleTechnicalAppointmentClient;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.ScheduleTechnicalAppointmentRequest;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.front.ScheduleTechnicalAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response.ScheduleTechnicalAppointmentResponse;
import tdp.backend.mt.fija.main.restclient.updateCustomer.UpdateCustomerClient;
import tdp.backend.mt.fija.main.restclient.updateCustomer.UpdateCustomerRequestFront;
import tdp.backend.mt.fija.main.restclient.updateCustomer.request.Contacts;
import tdp.backend.mt.fija.main.restclient.updateCustomer.request.UpdateCustomerRequest;
import tdp.backend.mt.fija.main.restclient.updateCustomer.response.UpdateCustomerResponse;
import tdp.backend.mt.fija.main.service.SchedulingService;

@Service
@Slf4j
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	IServiceCallEventsMtService serviceCallEventsMtService;
	
	@Autowired
	IServiceCallEventsFijaService serviceCallEventsFijaService;
	
	@Autowired
	IHfcPsListMtService hfcPsMTService;
	
	@Autowired
	ITdpOrderFijaService tdpOrderService;
	
	@Autowired
	ITdpSvaFijaService tdpSvaService;

	@Override
	public Response<AvailabilityTechnicalAppointmentsResponse> getAvailabilityTechnicalAppointments(
			AvailabiltyTechAppointmentRequestFront request, Xhttp xhttp) {
		
		Response<AvailabilityTechnicalAppointmentsResponse> response = new Response<>();
		
		int retryCount = 3;
		response = callApiAvailabilityTechnicalAppointment(request,retryCount, xhttp);

		return response;
	}
	
	
	
	private Response<AvailabilityTechnicalAppointmentsResponse> callApiAvailabilityTechnicalAppointment(AvailabiltyTechAppointmentRequestFront request, int retryCount, Xhttp xhttp) {
		Response<AvailabilityTechnicalAppointmentsResponse> response = new Response<>();
		
		
		AvailabilityTechnicalAppointmentsClient client = new AvailabilityTechnicalAppointmentsClient(new ClientConfig());
		
		AvailabilityTechnicalAppointmentsRequest requestBody = new AvailabilityTechnicalAppointmentsRequest();

		Map<String, Object> object = null;
		if(request.getTipoCliente().equals("MT")) {
			object = buildRequestAvailabilityMT(request);
		} else if(request.getTipoCliente().equals("FIJA")) {
			object = buildRequestAvailabilityFija(request);
		}
		
		
		Boolean requiereVisitaTecnica = (Boolean) object.get("requiereVisitaTecnica");
		
		if(!requiereVisitaTecnica) {
			response.setResponseCode("2");
			response.setResponseMessage("No se necesita de visita técnica.");
			return response;
		} 
		
		requestBody = (AvailabilityTechnicalAppointmentsRequest) object.get("availabilityTechnicalAppointmentsRequest");
		
		//PARA FINES DE PRUEBA TRAZA!!!!!!
		//requestBody.getBody().setCodeOrigin("VF");
		//requestBody.getBody().setCodProductPSI("P004"); //003 ó 004
		
		//requestBody = getRequestBodyAva(); //mock
		
		Timestamp dateTimeRequest = UtilMethods.getFechaActual();
		
		ClientResult<ApiResponse> result = client.post(requestBody, request.getTipoCliente());
		
		Timestamp dateTimeResponse = UtilMethods.getFechaActual();
		
		
		if(request.getTipoCliente().equals("MT")) {
			
			result.getSceMt().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceMt().setSourceApp(xhttp.getAppSource());;
			result.getSceMt().setDocNumber(xhttp.getCustomer());;
			result.getSceMt().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceMt().setUsername(xhttp.getUsuario());
			result.getSceMt().setTag("SERVICE_CALL");
			result.getSceMt().setDateTimeRequest(dateTimeRequest);
			result.getSceMt().setDateTimeResponse(dateTimeResponse);
			
			serviceCallEventsMtService.saveOrUpdate(result.getSceMt());
		} else if (request.getTipoCliente().equals("FIJA")) {
			result.getSceFija().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceFija().setSourceApp(xhttp.getAppSource());;
			result.getSceFija().setDocNumber(xhttp.getCustomer());;
			result.getSceFija().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceFija().setUsername(xhttp.getUsuario());
			result.getSceFija().setTag("SERVICE_CALL");
			result.getSceFija().setEventDateTime(dateTimeRequest);
			serviceCallEventsFijaService.saveOrUpdate(result.getSceFija());
		}
		
		if (result == null || !result.isSuccess() || result.getResult() == null) {
			if(retryCount > 0) {
				log.info("No se obtuvo respuesta correcta del servicio, se procede a reintentar");
				retryCount --;
				return callApiAvailabilityTechnicalAppointment(request, retryCount, xhttp);
			} else {
				log.error("Error en la petición del servicio, reintentos culminados");
				response.setResponseCode(ServiceConstants.SERVICE_ERROR);
                response.setResponseMessage("Error del servidor en el consumo del api: /schedule/beforesales/availability-technical-appointment");
                response.setResponseData(null);
                return response;
				
			}
		} else {
			//aqui cuando se tiene la respuesta del api
			
			ApiResponse apiResponse = result.getResult();
			
			AvailabilityTechnicalAppointmentsResponse responseData = apiResponse.getResponseAvailability();
			
			//retirar slots con cantidad 0 tanto en AM y PM	
			List<Dates> dates = responseData.getBody().getCapacityFicticious().getDate();
			dates.stream()
			.filter(s -> Integer.parseInt(s.getSlot().get(0).getQuantity()) > 0 && Integer.parseInt(s.getSlot().get(1).getQuantity()) > 0)
			.collect(Collectors.toList());
			
			responseData.getBody().getCapacityFicticious().setDate(dates);
						
			
			response.setResponseCode("0");
			response.setResponseMessage("OK");
			response.setResponseData(responseData);
			response.getResponseData().getBody().setCodProductPSI(requestBody.getBody().getCodProductPSI());
			response.getResponseData().getBody().setCommercialOp(requestBody.getBody().getCommercialOp());
		}
		
		return response;
	}
	
	private Map<String, Object> buildRequestAvailabilityFija(AvailabiltyTechAppointmentRequestFront request) {
		Map<String, Object> requestObject = new HashMap<String, Object>();
		
		AvailabilityTechnicalAppointmentsRequest availabilityTechnicalAppointmentsRequest = new AvailabilityTechnicalAppointmentsRequest();
		Header headerAvailability = new Header();
		Body bodyAvailability = new Body();
		
		//default
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
		
		headerAvailability.setTimestamp(timeStamp);
		headerAvailability.setMessageId(UUID.randomUUID().toString());
		headerAvailability.setOperation("availabilityAppointment");
		headerAvailability.setAppName("VENTASFIJA");
		headerAvailability.setUser("user_ventasFija");
		
		bodyAvailability.setCodeOrigin("VF"); //estamos en el build de FIJA	
		
		//coordenadas
		if(request.getFijaRequest().getCoordinateX() != null) {
			bodyAvailability.setCoordXclient(request.getFijaRequest().getCoordinateX());
			bodyAvailability.setCoordYclient(request.getFijaRequest().getCoordinateY());
		} else {
			//hacer algo cuando el front no envia xy
		}
		
		boolean requiereVisitaTecnica = false;
		
		boolean isSvaConVisita = false;
		boolean hasOrderEquipment = false;

		//LOGICA PARA EL PSI Y OPERACION COMERCIAL
		TdpOrder tdpOrder = null;
		
		String message = "";
		
		try {
			tdpOrder = tdpOrderService.findById(request.getFijaRequest().getOrderId());
			if(tdpOrder == null) {
				requestObject.put("requiereVisitaTecnica", requiereVisitaTecnica);
				requestObject.put("mensaje","OrderID:  "+ request.getFijaRequest().getOrderId() + ", no encontrado en la base de datos.");
				return requestObject;
			}
			
			//VERIFICAR OPERACION COMERCIAL
			//OPERACIONES COMERCIALES
			
			//traer de bd
			String opComercialesValidas = "ALTA PURA,ALTA COMPONENTE BA,COMPLETA NAKED,ALTA COMPONENTE SOBRE TV,ALTA COMPONENTE TV,ALTA COMPONENTE LINEA,MIGRACION,SVAS";
			List<String> listOpComercialesValidas = Arrays.asList(opComercialesValidas.split(","));
			
			if(listOpComercialesValidas.contains(tdpOrder.getOrderOperationCommercial().toUpperCase())) {
				
				bodyAvailability.setCommercialOp(tdpOrder.getOrderOperationCommercial().toUpperCase());
				bodyAvailability.setCodProductPSI(getPsiCode(tdpOrder));
				
			} else {
				//NO SOPORTA LA OP COMERCIAL
				requestObject.put("mensaje","La operación comercial: "+ tdpOrder.getOrderOperationCommercial() + ", no es soportado para el agendamiento.");		
				requestObject.put("requiereVisitaTecnica", requiereVisitaTecnica);
				return requestObject;
			}
	
			bodyAvailability.setInternetTech(tdpOrder.getProductTecInter());
			bodyAvailability.setTechnologyTV(tdpOrder.getProductTecTv());
			
			
			List<String> svaCodes = new ArrayList<String>();
			
			//solo svas que tengan un codigo
			if(tdpOrder.getSvaCodigo1() != null) svaCodes.add(tdpOrder.getSvaCodigo1());
			if(tdpOrder.getSvaCodigo2() != null) svaCodes.add(tdpOrder.getSvaCodigo2());
			if(tdpOrder.getSvaCodigo3() != null) svaCodes.add(tdpOrder.getSvaCodigo3());
			if(tdpOrder.getSvaCodigo4() != null) svaCodes.add(tdpOrder.getSvaCodigo4());
			if(tdpOrder.getSvaCodigo5() != null) svaCodes.add(tdpOrder.getSvaCodigo5());
			if(tdpOrder.getSvaCodigo6() != null) svaCodes.add(tdpOrder.getSvaCodigo6());
			if(tdpOrder.getSvaCodigo7() != null) svaCodes.add(tdpOrder.getSvaCodigo7());
			if(tdpOrder.getSvaCodigo8() != null) svaCodes.add(tdpOrder.getSvaCodigo8());
			if(tdpOrder.getSvaCodigo9() != null) svaCodes.add(tdpOrder.getSvaCodigo9());
			if(tdpOrder.getSvaCodigo10() != null) svaCodes.add(tdpOrder.getSvaCodigo10());
			
			isSvaConVisita = isSvaFijaConVisita(svaCodes);
			hasOrderEquipment = verificarEquipamiento(tdpOrder);
			
			if(isSvaConVisita || hasOrderEquipment) {
				requiereVisitaTecnica = true;
				availabilityTechnicalAppointmentsRequest.setHeader(headerAvailability);
				availabilityTechnicalAppointmentsRequest.setBody(bodyAvailability);
				
				requestObject.put("availabilityTechnicalAppointmentsRequest", availabilityTechnicalAppointmentsRequest);
				message = "OK";
			} else {
				message = "NO SE REQUIERE DE VISITA TECNICA";
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		requestObject.put("requiereVisitaTecnica", requiereVisitaTecnica);
		requestObject.put("mensaje", message);
		
		return requestObject;
	}
	
	private String getPsiCode(TdpOrder tdpOrder) {
		String psiCodeString = null;
		
		String opComercial = tdpOrder.getOrderOperationCommercial().toUpperCase();
		String tecInternet = tdpOrder.getProductTecInter().toUpperCase();
		String tecTv = tdpOrder.getProductTecTv();
		
		switch (opComercial) {
		case "ALTA PURA":
			if(tecInternet.equals("ADSL")) {
				psiCodeString = "P002";
			} else if(tecInternet.equals("HFC")) {
				psiCodeString = "P003";
			} else if(tecInternet.equals("FTTH")) {
				psiCodeString = "P004";
			} else if(tecTv.equals("CATV")) {
				psiCodeString = "P005";
			} else if(tecTv.equals("DTH")) {
				psiCodeString = "P006";
			}
			break;
		case "ALTA COMPONENTE BA":
		case "COMPLETA NAKED":
			if(tecInternet.equals("ADSL")) {
				psiCodeString = "P002";
			} else if(tecInternet.equals("HFC")) {
				psiCodeString = "P003";
			} else if(tecInternet.equals("FTTH")) {
				psiCodeString = "P004";
			}
			break;
		case "ALTA COMPONENTE SOBRE TV":
		case "ALTA COMPONENTE TV":
			if(tecTv.equals("CATV")) {
				psiCodeString = "P005";
			} else if(tecTv.equals("DTH")) {
				psiCodeString = "P006";
			}
			break;
		case "ALTA COMPONENTE LINEA":
			if(tecInternet.equals("HFC") || tecInternet.equals("FTTH")) {
				psiCodeString = "P007";
			}
			break;
		case "MIGRACION":
			if(tecInternet.equals("HFC")) {
				psiCodeString = "P003";
			} else if(tecInternet.equals("FTTH")) {
				psiCodeString = "P004";
			} else if(tecTv.equals("CATV")) {
				psiCodeString = "P005";
			} else if(tecTv.equals("DTH")) {
				psiCodeString = "P006";
			}
			break;
		case "SVAS":
			if(tecInternet.equals("ADSL")) {
				psiCodeString = "P002";
			} else if(tecInternet.equals("HFC")) {
				psiCodeString = "P003";
			} else if(tecInternet.equals("FTTH")) {
				psiCodeString = "P004";
			} else if(tecTv.equals("CATV")) {
				psiCodeString = "P005";
			} else if(tecTv.equals("DTH")) {
				psiCodeString = "P006";
			}
			break;
		default:
			break;
		}
		
		
		return psiCodeString;
	}
	
	private boolean verificarEquipamiento(TdpOrder tdpOrder) {
		boolean hasOrderEquipment = false;
		
		boolean equipmentTv = false;
		boolean equipmentInternet = false;
		boolean equipmentLinea = false;
		
		if(!StringUtils.isBlank(tdpOrder.getProductEquipTv())) {
			equipmentTv = true;
		}
		if(!StringUtils.isBlank(tdpOrder.getProductEquipInter())) {
			equipmentInternet = true;
		}
		if(!StringUtils.isBlank(tdpOrder.getProductEquipLine())) {
			equipmentLinea = true;
		}
		
		if(equipmentTv || equipmentInternet || equipmentLinea) {
			hasOrderEquipment = true;
		}
		
		return hasOrderEquipment;
	}
	
	private boolean isSvaFijaConVisita(List<String> svaCodes) {
		boolean svaConVisitaTecnica = false;
		TdpSva tdpSva = null;
		String svaValidos = "REPETIDOR SMART WIFI,PUNTO ADICIONAL SMART HD"; //traer de la base de datos
		
		//convertirlo a una lista
		List<String> listSvaValidos = Arrays.asList(svaValidos.split(","));
		for(String svaCode : svaCodes) {
			try {
				tdpSva = tdpSvaService.findBySvaCode(svaCode);
				if(listSvaValidos.contains(tdpSva.getDescription().toUpperCase())) {
					svaConVisitaTecnica = true;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return svaConVisitaTecnica;
	}
	
	private void getOperacionComercial() {
		String operacionComercial = "";
	}
	
	private Map<String, Object> buildRequestAvailabilityMT(AvailabiltyTechAppointmentRequestFront request) {
		
		Map<String, Object> requestObject = new HashMap<String, Object>();
		
		AvailabilityTechnicalAppointmentsRequest availabilityTechnicalAppointmentsRequest = new AvailabilityTechnicalAppointmentsRequest();
		
		Body bodyAvailability = new Body();
		Header headerAvailability = new Header();
		
		//default
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
		
		headerAvailability.setTimestamp(timeStamp);
		headerAvailability.setMessageId(UUID.randomUUID().toString());
		headerAvailability.setOperation("availabilityAppointment");
		headerAvailability.setAppName("MOVISTARTOTAL");
		headerAvailability.setUser("user_movistarTotal");
		
		bodyAvailability.setCodeOrigin("MT"); //estamos en el build de MT
	
		
		boolean requiereVisitaTecnica = false;
		
		
		String techInternetActual = "";
		
		if(request.getMtRequest().getFijaInicial().getParkType().equals("ATIS")) { //PROBAR CON SOLO MONOS
			techInternetActual = getTecnologiaInternet(request.getMtRequest().getFijaInicial().getPsPrincipal());
		} else {
			techInternetActual = "ADSL";
		}
		
		
		boolean isCambioTecnologia = isCambioTecnologia(techInternetActual, request.getMtRequest().getTechInternetFinal());
		
		List<Sva> svas = request.getMtRequest().getSvas();
		boolean isSvaConVisita = isSvaMtConVisita(svas);
		
		
		bodyAvailability.setCoordXclient(request.getMtRequest().getFijaInicial().getCoordinateX());
		bodyAvailability.setCoordYclient(request.getMtRequest().getFijaInicial().getCoordinateY());
		
		bodyAvailability.setInternetTech(request.getMtRequest().getTechInternetFinal());
		bodyAvailability.setCodProductPSI("P001"); //en MT este código es para todos
		
		
		if(request.getMtRequest().getOperacionComercial().equals("ALTA")) {
			bodyAvailability.setCommercialOp("ALTA PURA");
			requiereVisitaTecnica = true;
		} else if (request.getMtRequest().getOperacionComercial().equals("MIGRA")) {
			
			if(!request.getMtRequest().getFijaInicial().getTipoProducto().equals("Trío")) {	
				bodyAvailability.setCommercialOp("MIGRACION");
				requiereVisitaTecnica = true;
				
				//revisar bien 
//				if(request.getFijaInicial().getParkType().equals("CMS")) { // techInternetActual = "", no es trio es solo TV
//					requiereVisitaTecnica = true;
//				} else { 
//					if (techInternetActual.equals("ADSL") || techInternetActual.equals("HFC")) {
//						requiereVisitaTecnica = true;
//					}
//				}
				 
			} else {//si tiene trio evaluar si agrega svas o cambio de tecnologia
				//si solo cambia de tecnologia bodyAvailability.setCommercialOp("MIGRACION");
				//si solo agrega svas bodyAvailability.setCommercialOp("SVAS");
				//si cambia de tecnologia y agrega svas bodyAvailability.setCommercialOp("MIGRACION");
				
				if(isSvaConVisita) {
					bodyAvailability.setCommercialOp("SVAS");
					requiereVisitaTecnica = true;
				}
				
				if(isCambioTecnologia) {
					bodyAvailability.setCommercialOp("MIGRACION");
					requiereVisitaTecnica = true;
				}
					
			}
		} else if (request.getMtRequest().getOperacionComercial().equals("GP-MANTIENE")) {
			
			if(isSvaConVisita) {
				bodyAvailability.setCommercialOp("SVAS");
				requiereVisitaTecnica = true;
			}
			
		} else if (request.getMtRequest().getOperacionComercial().equals("GP-MIGRA")) {
			
			if(isSvaConVisita) {
				bodyAvailability.setCommercialOp("SVAS");
				requiereVisitaTecnica = true;
			}
			
			if(isCambioTecnologia) {
				bodyAvailability.setCommercialOp("MIGRACION");
				requiereVisitaTecnica = true;
			}
		}
		
		availabilityTechnicalAppointmentsRequest.setHeader(headerAvailability);
		availabilityTechnicalAppointmentsRequest.setBody(bodyAvailability);
		
		requestObject.put("requiereVisitaTecnica", requiereVisitaTecnica);
		requestObject.put("availabilityTechnicalAppointmentsRequest", availabilityTechnicalAppointmentsRequest);
		
		return requestObject;
		
	}
	
	private boolean isSvaMtConVisita(List<Sva> svas) {
		boolean svaConVisitaTecnica = false;
		
		if(svas != null) {
			for(Sva s : svas) {
				if(s.getCodigoSva().equals("23026") || s.getCodigoSva().equals("23269")) {
					svaConVisitaTecnica = true;
				}
			}
		}
		return svaConVisitaTecnica;
	}
	
	private boolean isCambioTecnologia(String tecnologiaActual, String tecnologiaNueva) {
		boolean isCambioTecnologia = false;
		
		if(tecnologiaNueva.equals("FTTH")) {
			if(tecnologiaActual.equals("ADSL") || tecnologiaActual.equals("HFC")) {
				isCambioTecnologia = true;
			}
		} else if (tecnologiaNueva.equals("HFC")) {
			if(tecnologiaActual.equals("ADSL")) {
				isCambioTecnologia = true;
			}
		}

		return isCambioTecnologia;
	}
	
	private String getTecnologiaInternet(String ps) {
		String techInternetActual = "";
		
		
		HfcPsListMt hfcPs = null;
		try {
			hfcPs = hfcPsMTService.findByPs(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (hfcPs != null) {
			techInternetActual = hfcPs.getDesTechnology();
		} else {
			techInternetActual = "ADSL";
		}
		
		return techInternetActual;
	}
	
	
	
	private AvailabilityTechnicalAppointmentsRequest getRequestBodyAva() {//para borrar
		AvailabilityTechnicalAppointmentsRequest requestBody = new AvailabilityTechnicalAppointmentsRequest();
		Header headerBody =  new Header();
		
		headerBody.setAppName("MOVISTARTOTAL");
		headerBody.setUser("user_movistarTotal");
		headerBody.setOperation("availabilityAppointment");
		headerBody.setMessageId("");
		headerBody.setTimestamp("");
		
		Body body = new Body();
		body.setCodeOrigin("VF");
		body.setCoordXclient("-77.0902642");
		body.setCoordYclient("-12.0386869");
		body.setCommercialOp("MIGRACION");
		body.setInternetTech("HFC");
		body.setCodProductPSI("P004");
		
		
		requestBody.setHeader(headerBody);
		requestBody.setBody(body);
		
		return requestBody;
	}

	@Override
	public Response<ScheduleTechnicalAppointmentResponse> getScheduleTechnicalAppointment(
			ScheduleTechnicalAppointmentRequestFront request, Xhttp xhttp) {
		

		
		Response<ScheduleTechnicalAppointmentResponse> response = new Response<>();
		
		int retryCount = 3;
		response = callApiScheduleTechnicalAppointment(request,retryCount, xhttp);

		return response;
	}
	
	private Response<ScheduleTechnicalAppointmentResponse> callApiScheduleTechnicalAppointment(ScheduleTechnicalAppointmentRequestFront request, int retryCount, Xhttp xhttp) {
		Response<ScheduleTechnicalAppointmentResponse> response = new Response<>();
		
		
		ScheduleTechnicalAppointmentClient client = new ScheduleTechnicalAppointmentClient(new ClientConfig());
		
		ScheduleTechnicalAppointmentRequest requestBody = new ScheduleTechnicalAppointmentRequest();
		
		
		if(request.getTipoCliente().equals("MT")) {
			requestBody = buildRequestScheduleMT(request);
		} else if(request.getTipoCliente().equals("FIJA")) {
			//buildRequestScheduleFija(request);
		}

		
		
		//requestBody = getRequestBodySch(); //mock
		
		Timestamp dateTimeRequest = UtilMethods.getFechaActual();
		
		ClientResult<ApiResponse> result = client.post(requestBody, request.getTipoCliente());
		
		Timestamp dateTimeResponse = UtilMethods.getFechaActual();
		
		if(request.getTipoCliente().equals("MT")) {
			
			result.getSceMt().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceMt().setSourceApp(xhttp.getAppSource());;
			result.getSceMt().setDocNumber(xhttp.getCustomer());;
			result.getSceMt().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceMt().setUsername(xhttp.getUsuario());
			result.getSceMt().setTag("SERVICE_CALL");
			result.getSceMt().setDateTimeRequest(dateTimeRequest);
			result.getSceMt().setDateTimeResponse(dateTimeResponse);
			
			serviceCallEventsMtService.saveOrUpdate(result.getSceMt());
		} else if (request.getTipoCliente().equals("FIJA")) {
			result.getSceFija().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceFija().setSourceApp(xhttp.getAppSource());;
			result.getSceFija().setDocNumber(xhttp.getCustomer());;
			result.getSceFija().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceFija().setUsername(xhttp.getUsuario());
			result.getSceFija().setTag("SERVICE_CALL");
			result.getSceFija().setEventDateTime(dateTimeRequest);
			serviceCallEventsFijaService.saveOrUpdate(result.getSceFija());
		}
		
		
		if (result == null || !result.isSuccess() || result.getResult() == null) {
			if(retryCount > 0) {
				log.info("No se obtuvo respuesta correcta del servicio, se procede a reintentar");
				retryCount --;
				return callApiScheduleTechnicalAppointment(request, retryCount, xhttp);
			} else {
				log.error("Error en la petición del servicio, reintentos culminados");
				response.setResponseCode(ServiceConstants.SERVICE_ERROR);
                response.setResponseMessage("Error del servidor en el consumo del api: /schedule/beforesales/schedule-technical-appointments");
                response.setResponseData(null);
                return response;
				
			}
		} else {
			//aqui cuando se tiene la respuesta del api
			
			ApiResponse apiResponse = result.getResult();
			
			ScheduleTechnicalAppointmentResponse responseData = apiResponse.getResponseSchedule();
			
			response.setResponseCode(ServiceConstants.SERVICE_SUCCESS);
			response.setResponseMessage("OK");
			response.setResponseData(responseData);
			
		}
		
		return response;
		
		
		
		
	}
	
	private ScheduleTechnicalAppointmentRequest buildRequestScheduleMT(ScheduleTechnicalAppointmentRequestFront requestFront) {
		ScheduleTechnicalAppointmentRequest scheduleTechnicalAppointmentRequest = new ScheduleTechnicalAppointmentRequest();
		
		tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Header headerSchedule = new tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Header();
		tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Body bodySchedule = new tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Body();
		
		//default
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
		log.info(timeStamp);
		
		headerSchedule.setAppName("MOVISTARTOTAL");
		headerSchedule.setUser("user_movistarTotal");
		headerSchedule.setOperation("scheduleAppointments");
		headerSchedule.setMessageId("57559b0c-5755-5755-5755-57559b0ceb0e");
		headerSchedule.setTimestamp(timeStamp);
		
		bodySchedule.setOriginCode("MT");
		bodySchedule.setSaleCode(requestFront.getSaleCode());
		bodySchedule.setBucket(requestFront.getBucket());
		bodySchedule.setScheduleDate(requestFront.getScheduleDate());
		bodySchedule.setScheduleRange(requestFront.getScheduleRange());
		bodySchedule.setCoordinateX(requestFront.getCoordinateX());
		bodySchedule.setCoordinateY(requestFront.getCoordinateY());
		bodySchedule.setDocumentType(requestFront.getDocumentType());
		bodySchedule.setDocumentNumber(requestFront.getDocumentNumber());
		bodySchedule.setCustomerName(requestFront.getCustomerName());
		bodySchedule.setCustomerPatSurname(requestFront.getCustomerPatSurname());
		bodySchedule.setCustomerMatSurname(requestFront.getCustomerMatSurname());
		bodySchedule.setCommercialOp(requestFront.getCommercialOp());
		bodySchedule.setInternetTech(requestFront.getInternetTech());
		bodySchedule.setTechnologyTV(requestFront.getTechnologyTV());
		bodySchedule.setCodProductPSI(requestFront.getCodProductPSI());


		scheduleTechnicalAppointmentRequest.setHeader(headerSchedule);
		scheduleTechnicalAppointmentRequest.setBody(bodySchedule);
		
		return scheduleTechnicalAppointmentRequest;
		
	}
	
	
	private ScheduleTechnicalAppointmentRequest getRequestBodySch() {
		ScheduleTechnicalAppointmentRequest requestBody = new ScheduleTechnicalAppointmentRequest();
		
		tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Header headerBody =  new tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Header();
		
		
		headerBody.setAppName("MOVISTARTOTAL");
		headerBody.setUser("user_movistarTotal");
		headerBody.setOperation("scheduleAppointments");
		headerBody.setMessageId("57559b0c-5755-5755-5755-57559b0ceb0e");
		headerBody.setTimestamp("2019-11-21T04:21:32.518");

		
		tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Body body = new tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.Body();
		body.setOriginCode("VF");
		body.setSaleCode("-LpeiZcPQKfAfiw4slNe");
		body.setBucket("BK_COBRA_ZONA5_SJ_MASIVO");
		body.setScheduleDate("2020-01-05");
		body.setScheduleRange("AM");
		body.setCoordinateX("-77.0902642");
		body.setCoordinateY("-12.0386869");
		body.setDocumentType("DNI");
		body.setDocumentNumber("23451623");
		body.setCustomerName("RODRIGO");
		body.setCustomerPatSurname("PORTILLA");
		body.setCustomerMatSurname("IBARRA");
		body.setCommercialOp("Alta Componente BA");
		body.setInternetTech(null);
		body.setTechnologyTV(null);
		body.setCodProductPSI("P004");
		
		requestBody.setHeader(headerBody);
		requestBody.setBody(body);
		
		return requestBody;
	}

	@Override
	public Response<UpdateCustomerResponse> getUpdateCustomer(UpdateCustomerRequestFront request, Xhttp xhttp) {
		
		
		Response<UpdateCustomerResponse> response = new Response<>();
		
		int retryCount = 3;
		response = callApiUpdateCustomer(request,retryCount, xhttp);

		return response;

	}
	
	private Response<UpdateCustomerResponse> callApiUpdateCustomer(UpdateCustomerRequestFront request, int retryCount, Xhttp xhttp) {
		Response<UpdateCustomerResponse> response = new Response<>();
		
		UpdateCustomerClient client = new UpdateCustomerClient(new ClientConfig());
		
		UpdateCustomerRequest requestBody = new UpdateCustomerRequest();
		
		if(request.getTipoCliente().equals("MT")) {
			requestBody = buildRequestUpdateCustomerMT(request);
		} else if(request.getTipoCliente().equals("FIJA")) {
			//buildRequestUpdateCustomerFija(request);
		}
		//requestBody = getRequestBodyUpdate(); //mock
		
		Timestamp dateTimeRequest = UtilMethods.getFechaActual();
		
		ClientResult<ApiResponse> result = client.post(requestBody, request.getTipoCliente());
		
		Timestamp dateTimeResponse = UtilMethods.getFechaActual();
		
		if(request.getTipoCliente().equals("MT")) {
			
			result.getSceMt().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceMt().setSourceApp(xhttp.getAppSource());;
			result.getSceMt().setDocNumber(xhttp.getCustomer());;
			result.getSceMt().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceMt().setUsername(xhttp.getUsuario());
			result.getSceMt().setTag("SERVICE_CALL");
			result.getSceMt().setDateTimeRequest(dateTimeRequest);
			result.getSceMt().setDateTimeResponse(dateTimeResponse);
			
			serviceCallEventsMtService.saveOrUpdate(result.getSceMt());
		} else if (request.getTipoCliente().equals("FIJA")) {
			result.getSceFija().setSourceAppVersion(xhttp.getAppVersion());
			result.getSceFija().setSourceApp(xhttp.getAppSource());;
			result.getSceFija().setDocNumber(xhttp.getCustomer());;
			result.getSceFija().setOrderId(xhttp.getOrdenMT() != null ? !xhttp.getOrdenMT().equals("") ? ("MT" + xhttp.getOrdenMT()) : "" : "");
			result.getSceFija().setUsername(xhttp.getUsuario());
			result.getSceFija().setTag("SERVICE_CALL");
			result.getSceFija().setEventDateTime(dateTimeRequest);
			serviceCallEventsFijaService.saveOrUpdate(result.getSceFija());
		}
		
		
		if (result == null || !result.isSuccess() || result.getResult() == null) {
			if(retryCount > 0) {
				log.info("No se obtuvo respuesta correcta del servicio, se procede a reintentar");
				retryCount --;
				return callApiUpdateCustomer(request, retryCount, xhttp);
			} else {
				log.error("Error en la petición del servicio, reintentos culminados");
				response.setResponseCode(ServiceConstants.SERVICE_ERROR);
                response.setResponseMessage("Error del servidor en el consumo del api: /provision/update-customer");
                response.setResponseData(null);
                return response;
				
			}
		} else {
			//aqui cuando se tiene la respuesta del api
			
			ApiResponse apiResponse = result.getResult();
			
			UpdateCustomerResponse responseData = apiResponse.getResponseUpdateCustomer();
			
			response.setResponseCode(ServiceConstants.SERVICE_SUCCESS);
			response.setResponseMessage("OK");
			response.setResponseData(responseData);
			
		}
		
		return response;
		
	}
	
	private UpdateCustomerRequest buildRequestUpdateCustomerMT(UpdateCustomerRequestFront requestFront) {
		UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
		
		tdp.backend.mt.fija.main.restclient.updateCustomer.request.Header headerUpdate = new tdp.backend.mt.fija.main.restclient.updateCustomer.request.Header();
		tdp.backend.mt.fija.main.restclient.updateCustomer.request.Body bodyUpdate = new tdp.backend.mt.fija.main.restclient.updateCustomer.request.Body();
		
		//default
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
		log.info(timeStamp);
		
		headerUpdate.setAppName("MOVISTARTOTAL");
		headerUpdate.setUser("user_movistarTotal");
		headerUpdate.setOperation("updateContact");
		headerUpdate.setMessageId("57559b0c-5755-5755-5755-57559b0ceb0e");
		headerUpdate.setTimestamp(timeStamp);
		
		bodyUpdate.setPsiCode(requestFront.getPsiCode());
		bodyUpdate.setEmail(requestFront.getEmail());
		bodyUpdate.setContacts(requestFront.getContacts());
		
		updateCustomerRequest.setHeader(headerUpdate);
		updateCustomerRequest.setBody(bodyUpdate);

		
		return updateCustomerRequest;
		
	}
	
	private UpdateCustomerRequest getRequestBodyUpdate() {
		
		UpdateCustomerRequest requestBody = new UpdateCustomerRequest();
		
		tdp.backend.mt.fija.main.restclient.updateCustomer.request.Header headerBody =  new tdp.backend.mt.fija.main.restclient.updateCustomer.request.Header();
		
		headerBody.setAppName("VENTASFIJA");
		headerBody.setUser("user_ventasFija");
		headerBody.setOperation("updateContact");
		headerBody.setMessageId("57559b0c-5755-5755-5755-57559b0ceb0e");
		headerBody.setTimestamp("2019-02-11T17:13:11.533");
		
		tdp.backend.mt.fija.main.restclient.updateCustomer.request.Body body = new tdp.backend.mt.fija.main.restclient.updateCustomer.request.Body();

		body.setPsiCode("VF902");
		body.setEmail("prueba@gmail.com");
		
		Contacts contact = new Contacts();
		contact.setFullName("wilson lazo");
		contact.setPhoneNumber("984101010");

		
		List<Contacts> contacts = new ArrayList<Contacts>();
		contacts.add(contact);
		
		body.setContacts(contacts);
		
		requestBody.setHeader(headerBody);
		requestBody.setBody(body);
		
		return requestBody;
	}
}
