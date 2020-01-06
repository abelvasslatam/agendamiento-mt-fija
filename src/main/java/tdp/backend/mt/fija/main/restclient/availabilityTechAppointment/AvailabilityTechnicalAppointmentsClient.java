package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment;

import tdp.backend.mt.fija.common.client.AbstractClient;
import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.common.util.UtilMethods;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.AvailabilityTechnicalAppointmentsRequest;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.AvailabilityTechnicalAppointmentsResponse;

public class AvailabilityTechnicalAppointmentsClient extends AbstractClient<AvailabilityTechnicalAppointmentsRequest, AvailabilityTechnicalAppointmentsResponse> {

	private static final String urlendpoint = "schedule/beforesales/";
	private static final String urlmethod = "availability-technical-appointment";

	public AvailabilityTechnicalAppointmentsClient(ClientConfig config) {
		super(config);
		this.config = UtilMethods.buildConfigAvailabilityTechnicalHeader(urlendpoint, urlmethod);
	}

	@Override
	protected String getServiceCode() {
		return "SCHED_AVAILABILITY";
	}



}
