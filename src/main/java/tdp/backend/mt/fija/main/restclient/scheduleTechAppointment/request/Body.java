package tdp.backend.mt.fija.main.restclient.scheduleTechAppointment.request;

import lombok.Data;

@Data
public class Body {
	private String originCode;
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