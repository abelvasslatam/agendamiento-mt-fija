package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request.front;

import java.util.List;

import lombok.Data;

@Data
public class MtRequest {
	private String techInternetFinal; //ADSL //HFC // FTTH
	private String operacionComercial; //ALTA PURA //MIGRACION //SVAS
	private List<Sva> svas;
	private FijaInicial fijaInicial;
}
