package tdp.backend.mt.fija.main.service;

import tdp.backend.mt.fija.common.domain.Response;
import tdp.backend.mt.fija.common.util.Xhttp;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.AvailabiltyTechAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.TechnicalAppointmentsResponse;

public interface SchedulingService {
	public Response<TechnicalAppointmentsResponse> getAvailabilityTechnicalAppointments(AvailabiltyTechAppointmentRequestFront request,Xhttp xhttp);
}
