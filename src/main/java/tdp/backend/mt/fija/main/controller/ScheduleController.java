package tdp.backend.mt.fija.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
	
	@GetMapping(value = "/schedule", produces = "application/json; charset=UTF-8")
	public void getTechnicalAppointments() {
		System.out.println("holi");
	}

}
