package tdp.backend.mt.fija.main.mt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdp.backend.mt.fija.main.mt.model.HfcPsListMt;
import tdp.backend.mt.fija.main.mt.repository.HfcPsListMtRepository;
import tdp.backend.mt.fija.main.mt.service.IHfcPsListMtService;

@Service
public class HfcPsListMTServiceImpl implements IHfcPsListMtService{

	@Autowired
	HfcPsListMtRepository hfcRepository;
	@Override
	public HfcPsListMt findByPs(String ps) {
		return hfcRepository.findByPs(ps);
	}

}
