package tdp.backend.mt.fija.main.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tdp.backend.mt.fija.main.mt.model.HfcPsListMt;

public interface HfcPsListMtRepository extends JpaRepository<HfcPsListMt, Long>{
	@Query(value = "SELECT * FROM hfc_ps_list where des_ps = ?1", nativeQuery = true)
	HfcPsListMt findByPs(String desPs);
}
