package tdp.backend.mt.fija.main.mt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tdp.backend.mt.fija.main.mt.model.ServiceCallEventsMt;
import tdp.backend.mt.fija.main.mt.repository.ServiceCallEventsMtRepository;
import tdp.backend.mt.fija.main.mt.service.IServiceCallEventsMtService;


//@Primary
@Service
public class ServiceCallEventsMtServiceImpl implements IServiceCallEventsMtService{
	
	@Autowired
	private ServiceCallEventsMtRepository repository;

	@Override
	public void registerEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceCallEventsMt findById(Long id) {
		return repository.findById(id).get();
	}


	
}
