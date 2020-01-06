package tdp.backend.mt.fija.main.restclient.scheduleTechAppointment;

import tdp.backend.mt.fija.common.client.AbstractClient;
import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.common.util.UtilMethods;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.ScheduleTechnicalAppointmentRequest;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response.ScheduleTechnicalAppointmentResponse;

public class ScheduleTechnicalAppointmentClient 

	extends AbstractClient<ScheduleTechnicalAppointmentRequest, ScheduleTechnicalAppointmentResponse>{

	public ScheduleTechnicalAppointmentClient(ClientConfig config) {
		super(config);
		this.config = UtilMethods.buildConfigScheduleTechnicalHeader(urlendpoint, urlmethod);
	}
	private static final String urlendpoint = "schedule/beforesales/";
	private static final String urlmethod = "schedule-technical-appointments";
	
	@Override
	protected String getServiceCode() {
		return "SCHED_TECHNICAL";
	}
}
