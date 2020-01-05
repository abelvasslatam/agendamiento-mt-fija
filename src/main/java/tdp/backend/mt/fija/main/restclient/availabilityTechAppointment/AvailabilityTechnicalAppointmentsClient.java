package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment;

import tdp.backend.mt.fija.common.client.AbstractClient;
import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.common.util.UtilMethods;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.TechnicalAppointmentsRequest;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.TechnicalAppointmentsResponse;

public class AvailabilityTechnicalAppointmentsClient
		extends AbstractClient<TechnicalAppointmentsRequest, TechnicalAppointmentsResponse> {

	private static final String urlendpoint = "schedule/beforesales/";
	private static final String urlmethod = "availability-technical-appointment";

	public AvailabilityTechnicalAppointmentsClient(ClientConfig config) {
		super(config);
		this.config = UtilMethods.buildConfigHeaderSchedule(urlendpoint, urlmethod);
	}

	@Override
	protected String getServiceCode() {
		return "SCHEDULE_AVAILAMENT";
	}


//	public TechnicalAppointmentsRequest getRequestDatatechnicalAppoiment() {
//		TechnicalAppointmentsRequest dataRequest = new TechnicalAppointmentsRequest();
//
//		dataRequest.setCodeOrigin("VF");
//		dataRequest.setCoordXclient("-77.0038904");
//		dataRequest.setCoordYclient("-12.0038901");
//		dataRequest.setCommercialOp("ALTA PURA");
//		dataRequest.setCategory("DUO BA");
//		dataRequest.setInternetTech("HFC");
//		dataRequest.setTheirProductCode("23240");
//		dataRequest.setLineEquipment("EQUIPO TELEFONICO");
//		dataRequest.setInternetEquipment("MODEM GPON COMODATO");
//		dataRequest.setTvEquipment("2 HD COMODATO");
//
//		return dataRequest;
//	}

}
