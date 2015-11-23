package org.qsdm.backend.dao;

import org.qsdm.backend.model.NoticiasProfesion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoticiasProfesionesRepository extends PagingAndSortingRepository<NoticiasProfesion, Integer> {
	
	
	
	
	Page<NoticiasProfesion> findAll(Pageable pageable);
    
}
