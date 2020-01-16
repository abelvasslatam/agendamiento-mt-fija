package tdp.backend.mt.fija.main.fija.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdp.backend.mt.fija.main.fija.model.TdpOrder;
import tdp.backend.mt.fija.main.fija.repository.TdpOrderFijaRepository;
import tdp.backend.mt.fija.main.fija.service.ITdpOrderFijaService;

@Service
public class TdpOrderServiceImpl implements ITdpOrderFijaService{
	
	@Autowired
	TdpOrderFijaRepository tdpOrderFijaRepository;

	@Override
	public TdpOrder findById(String id) {
		return tdpOrderFijaRepository.findById(id).orElse(null);
	}

}
