package tdp.backend.mt.fija.main.mt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tdp.backend.mt.fija.main.fija.model.ServiceCallEventsFija;
import tdp.backend.mt.fija.main.fija.service.IServiceCallEventsFijaService;
import tdp.backend.mt.fija.main.mt.model.ServiceCallEventsMt;
import tdp.backend.mt.fija.main.mt.service.IServiceCallEventsMtService;

@RestController
public class ScheduleController {
	
	@Autowired
	IServiceCallEventsMtService service;
	
	@Autowired
	IServiceCallEventsFijaService serviceFija;
	
	@GetMapping(value = "/schedule", produces = "application/json; charset=UTF-8")
	public void getTechnicalAppointments() {
		try {
			ServiceCallEventsMt s = service.findById(10919L);
			System.out.println(s);
			
			ServiceCallEventsFija sf = serviceFija.findById(4160382L);
			System.out.println(sf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
