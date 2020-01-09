package tdp.backend.mt.fija.main.mt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdp.backend.mt.fija.main.mt.model.EndpointCallEvent;
import tdp.backend.mt.fija.main.mt.repository.EndpointCallEventsRepository;
import tdp.backend.mt.fija.main.mt.service.IEndpointCallEvents;

@Service
public class EndpointCallEventsServiceImpl implements IEndpointCallEvents{
	
	@Autowired
	EndpointCallEventsRepository endpointCallEventsRepository;

	@Override
	public EndpointCallEvent saveOrUpdate(EndpointCallEvent e) {
		return endpointCallEventsRepository.save(e);
	}

}
