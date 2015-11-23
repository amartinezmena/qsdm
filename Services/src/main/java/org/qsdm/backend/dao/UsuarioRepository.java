package org.qsdm.backend.dao;

import org.qsdm.backend.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {
	
	
	@Query("Select a from Usuario a where a.email=?1 and a.password=?2")
	Usuario findUsuarioByEmailAndPassword(String email,String password);
	Usuario findUsuarioByIdUsuario(Integer idUsuario);
	Usuario findUsuarioByEmail(String email);
    Page<Usuario> findAll(Pageable pageable);
    
}
