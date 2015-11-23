package org.qsdm.backend.dao;

import org.qsdm.backend.model.Noticias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoticiaRepository extends PagingAndSortingRepository<Noticias, Integer> {
	
	
	@Query("Select a from Noticias a inner join a.noticiasProfesionSet nps where nps.codProfesion=?1 and a.fechaBaja is null")
	Iterable<Noticias> findNoticiasByProfesion(Integer codProfesion);
	@Query("Select a from Noticias a where a.descripcion like ?1 or a.titulo like ?1 and a.fechaBaja is null")
	Iterable<Noticias> findOnNoticias(String txt);
	@Query("Select a from Noticias a where a.usuarioPublicacion = ?1 and a.fechaBaja is null")
	Iterable<Noticias> findNoticiasByUsuario(String email);
	Page<Noticias> findAll(Pageable pageable);
    
}
