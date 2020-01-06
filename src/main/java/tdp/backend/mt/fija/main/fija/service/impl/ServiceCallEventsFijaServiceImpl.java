package tdp.backend.mt.fija.main.fija.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdp.backend.mt.fija.main.fija.model.ServiceCallEventsFija;
import tdp.backend.mt.fija.main.fija.repository.ServiceCallEventsFijaRepository;
import tdp.backend.mt.fija.main.fija.service.IServiceCallEventsFijaService;

@Service
public class ServiceCallEventsFijaServiceImpl implements IServiceCallEventsFijaService{
	
	@Autowired
	ServiceCallEventsFijaRepository serviceCallEventsFijaRepository;

	@Override
	public ServiceCallEventsFija findById(Long id) {
		return serviceCallEventsFijaRepository.findById(id).get();
	}

	@Override
	public ServiceCallEventsFija saveOrUpdate(ServiceCallEventsFija sceFija) {
		return serviceCallEventsFijaRepository.save(sceFija);
	}

}
