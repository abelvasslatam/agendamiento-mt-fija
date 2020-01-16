package tdp.backend.mt.fija.main.fija.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdp.backend.mt.fija.main.fija.model.TdpSva;
import tdp.backend.mt.fija.main.fija.repository.TdpSvaRepository;
import tdp.backend.mt.fija.main.fija.service.ITdpSvaFijaService;

@Service
public class TdpSvaFijaServiceImpl implements ITdpSvaFijaService{

	@Autowired
	TdpSvaRepository tdpSvaRepository;

	@Override
	public TdpSva findBySvaCode(String svaCode) {
		return tdpSvaRepository.findBySvaCode(svaCode);
	}
	

}
