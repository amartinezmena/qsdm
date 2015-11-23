package org.qsdm.backend.dao;

import java.util.List;

import org.qsdm.backend.model.Profesiones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfesionesRepository extends PagingAndSortingRepository<Profesiones, Integer> {
	
	
	@Query("Select a from Profesiones a where a.cod=?1")
	Iterable<Profesiones> findProfesionesByCod(String codProfesion);
	@Query("Select a from Profesiones a where a.cod in ?1")
	Iterable<Profesiones> findProfesionesByCods(List<String> codProfesion);
	Page<Profesiones> findAll(Pageable pageable);
    
}
