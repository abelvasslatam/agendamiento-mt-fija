package tdp.backend.mt.fija.main.restclient.availabilityTechAppointment.request;

import lombok.Data;

@Data
public class Body {
	private String codeOrigin;
    private String coordXclient;
    private String coordYclient;
    private String commercialOp;
    private String internetTech;
    private String technologyTV;
    private String codProductPSI;
    
    
//    private String category;
//    private String theirProductCode;
//    private String lineEquipment;
//    private String internetEquipment;
//    private String tvEquipment;
}
