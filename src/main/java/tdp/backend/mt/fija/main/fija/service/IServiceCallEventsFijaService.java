package tdp.backend.mt.fija.main.fija.service;

import tdp.backend.mt.fija.main.fija.model.ServiceCallEventsFija;

public interface IServiceCallEventsFijaService{
	ServiceCallEventsFija findById(Long id);
	ServiceCallEventsFija saveOrUpdate(ServiceCallEventsFija sceFija);
}
