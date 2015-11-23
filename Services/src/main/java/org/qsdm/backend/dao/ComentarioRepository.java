package org.qsdm.backend.dao;

import org.qsdm.backend.model.Comentarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComentarioRepository extends PagingAndSortingRepository<Comentarios, Integer> {
	
	
	@Query("Select a from Comentarios a where a.idNoticiaRelacionada.idnoticias=?1 and a.fechaBaja is null")
	Iterable<Comentarios> findComentarioByNoticia(Integer idNoticia);
	Page<Comentarios> findAll(Pageable pageable);
    
}
