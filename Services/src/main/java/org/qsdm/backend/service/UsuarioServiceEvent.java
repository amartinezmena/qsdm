package org.qsdm.backend.service;

import org.springframework.context.ApplicationEvent;

public class UsuarioServiceEvent  extends ApplicationEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My HotelService Event";
    }

}
