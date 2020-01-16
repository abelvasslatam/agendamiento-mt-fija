package tdp.backend.mt.fija.main.fija.service;

import tdp.backend.mt.fija.main.fija.model.TdpSva;

public interface ITdpSvaFijaService {
	TdpSva findBySvaCode(String svaCode);
}
