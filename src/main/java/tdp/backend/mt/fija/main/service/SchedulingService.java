package tdp.backend.mt.fija.main.service;

import tdp.backend.mt.fija.common.domain.Response;
import tdp.backend.mt.fija.common.util.Xhttp;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.AvailabiltyTechAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.response.AvailabilityTechnicalAppointmentsResponse;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.ScheduleTechnicalAppointmentRequestFront;
import tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.response.ScheduleTechnicalAppointmentResponse;
import tdp.backend.mt.fija.main.restclient.updateCustomer.UpdateCustomerRequestFront;
import tdp.backend.mt.fija.main.restclient.updateCustomer.response.UpdateCustomerResponse;

public interface SchedulingService {
	public Response<AvailabilityTechnicalAppointmentsResponse> getAvailabilityTechnicalAppointments(AvailabiltyTechAppointmentRequestFront request,Xhttp xhttp);
	public Response<ScheduleTechnicalAppointmentResponse> getScheduleTechnicalAppointment(ScheduleTechnicalAppointmentRequestFront request, Xhttp xhttp);
	public Response<UpdateCustomerResponse> getUpdateCustomer(UpdateCustomerRequestFront request, Xhttp xhttp);
}
