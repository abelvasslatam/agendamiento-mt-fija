package tdp.backend.mt.fija.main.fija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tdp.backend.mt.fija.main.fija.model.TdpSva;

public interface TdpSvaRepository extends JpaRepository<TdpSva, Integer>{

	@Query(value = "select * from ibmx_a07e6d02edaf552.tdp_sva WHERE sva_codigo = ?1", nativeQuery = true)
	TdpSva findBySvaCode(String svaCode);
	
}
