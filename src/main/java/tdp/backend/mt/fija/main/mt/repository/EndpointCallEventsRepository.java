package tdp.backend.mt.fija.main.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdp.backend.mt.fija.main.mt.model.EndpointCallEvent;

public interface EndpointCallEventsRepository extends JpaRepository<EndpointCallEvent, Long>{

}
