package org.qsdm.backend.service;

import org.qsdm.backend.dao.UsuarioRepository;
import org.qsdm.backend.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {
	

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Autowired
	    private CounterService counterService;

	   
	    public UsuariosService() {
	    }

	    public Usuario createUsuario(Usuario usuario) {
	        return usuarioRepository.save(usuario);
	    }

	    public Usuario getUsuario(Integer id) {
	        return usuarioRepository.findOne(id);
	    }
	    public Usuario checkUsuario(String email,String password) {
	        return usuarioRepository.findUsuarioByEmailAndPassword(email, password);
	    }
	    public void updateUsuario(Usuario hotel) {
	    	usuarioRepository.save(hotel);
	    }

	    public void deleteUsuario(Integer id) {
	    	usuarioRepository.delete(id);
	    }

	    public Page<Usuario> getAllUsuarios(Integer page, Integer size) {
	        Page<Usuario> pageOfHotels = usuarioRepository.findAll(new PageRequest(page, size));
	        if (size > 50) {
	            counterService.increment("qsdm.UsuariosService.getAll.largePayload");
	        }
	        return pageOfHotels;
	    }
}
