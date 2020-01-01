package tdp.backend.mt.fija.main.mt.service;

import java.util.List;

import tdp.backend.mt.fija.main.mt.model.ServiceCallEventsMt;

public interface IServiceCallEventsMtService{

	public void registerEvent();
	public ServiceCallEventsMt findById(Long id);
	
	
}
