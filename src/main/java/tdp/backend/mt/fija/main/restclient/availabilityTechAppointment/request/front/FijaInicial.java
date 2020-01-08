package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.front;

import lombok.Data;

@Data
public class FijaInicial {
	private String parkType;
	private String coordinateX;
	private String coordinateY;
	private String tipoProducto;
	private String hfc;
	private String psPrincipal;
}