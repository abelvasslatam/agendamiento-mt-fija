package tdp.backend.mt.fija.main.mt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdp.backend.mt.fija.main.mt.model.ServiceCallEventsMt;
import tdp.backend.mt.fija.main.mt.repository.ServiceCallEventsMtRepository;
import tdp.backend.mt.fija.main.mt.service.IServiceCallEventsMtService;


//@Primary
@Service
public class ServiceCallEventsMtServiceImpl implements IServiceCallEventsMtService{
	
	@Autowired
	private ServiceCallEventsMtRepository serviceCallEventsMtRepository;


	@Override
	public ServiceCallEventsMt findById(Long id) {
		return serviceCallEventsMtRepository.findById(id).get();
	}

	@Override
	public ServiceCallEventsMt saveOrUpdate(ServiceCallEventsMt sceMt) {
		return serviceCallEventsMtRepository.save(sceMt);
	}


	
}
