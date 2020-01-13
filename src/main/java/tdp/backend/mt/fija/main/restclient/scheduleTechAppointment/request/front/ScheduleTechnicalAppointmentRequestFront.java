package tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request.front;

import lombok.Data;

@Data
public class ScheduleTechnicalAppointmentRequestFront {
	private String tipoCliente;
	private String saleCode;
	private String bucket;
	private String scheduleDate;
	private String scheduleRange;
	private String coordinateX;
	private String coordinateY;
	private String documentType;
	private String documentNumber;
	private String customerName;
	private String customerPatSurname;
	private String customerMatSurname;
	private String commercialOp;
	private String internetTech;
	private String technologyTV;
	private String codProductPSI;
}
