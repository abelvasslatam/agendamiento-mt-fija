package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.front;

import lombok.Data;

@Data
public class AvailabiltyTechAppointmentRequestFront {
	MtRequest mtRequest;
	FijaRequest fijaRequest;
	private String tipoCliente;
}

