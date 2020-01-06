package tdp.backend.mt.fija.main.mt.service;

import tdp.backend.mt.fija.main.mt.model.ServiceCallEventsMt;

public interface IServiceCallEventsMtService{

	ServiceCallEventsMt findById(Long id);
	ServiceCallEventsMt saveOrUpdate(ServiceCallEventsMt sceMt);
	
	
}
