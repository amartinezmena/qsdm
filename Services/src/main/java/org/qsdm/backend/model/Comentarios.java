/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qsdm.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Alberto
 */
@Entity
@Table(name = "comentarios")
@NamedQueries({
    @NamedQuery(name = "Comentarios.findAll", query = "SELECT c FROM Comentarios c"),
    @NamedQuery(name = "Comentarios.findByIdcomentarios", query = "SELECT c FROM Comentarios c WHERE c.idcomentarios = :idcomentarios"),
    @NamedQuery(name = "Comentarios.findByEmailUsuarioPublicacion", query = "SELECT c FROM Comentarios c WHERE c.emailUsuarioPublicacion = :emailUsuarioPublicacion"),
    @NamedQuery(name = "Comentarios.findByFechaBaja", query = "SELECT c FROM Comentarios c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Comentarios.findByPublicado", query = "SELECT c FROM Comentarios c WHERE c.publicado = :publicado")})
@JsonIgnoreProperties(value = { "idNoticiaRelacionada","publicado","fechaBaja" })
public class Comentarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomentarios")
    private Integer idcomentarios;
    @Lob
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fechaAlta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "emailUsuarioPublicacion")
    private String emailUsuarioPublicacion;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Column(name = "publicado")
    private Integer publicado;
    @JoinColumn(name = "idNoticiaRelacionada", referencedColumnName = "idnoticias")
    @ManyToOne
    private Noticias idNoticiaRelacionada;
    @JoinColumn(name = "usuarioPublicacion", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario usuarioPublicacion;

    public Comentarios() {
    }

    public Comentarios(Integer idcomentarios) {
        this.idcomentarios = idcomentarios;
    }

    public Integer getIdcomentarios() {
        return idcomentarios;
    }

    public void setIdcomentarios(Integer idcomentarios) {
        this.idcomentarios = idcomentarios;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
 

    public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getEmailUsuarioPublicacion() {
        return emailUsuarioPublicacion;
    }

    public void setEmailUsuarioPublicacion(String emailUsuarioPublicacion) {
        this.emailUsuarioPublicacion = emailUsuarioPublicacion;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Integer getPublicado() {
        return publicado;
    }

    public void setPublicado(Integer publicado) {
        this.publicado = publicado;
    }

    public Noticias getIdNoticiaRelacionada() {
        return idNoticiaRelacionada;
    }

    public void setIdNoticiaRelacionada(Noticias idNoticiaRelacionada) {
        this.idNoticiaRelacionada = idNoticiaRelacionada;
    }

    public Usuario getUsuarioPublicacion() {
        return usuarioPublicacion;
    }

    public void setUsuarioPublicacion(Usuario usuarioPublicacion) {
        this.usuarioPublicacion = usuarioPublicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomentarios != null ? idcomentarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentarios)) {
            return false;
        }
        Comentarios other = (Comentarios) object;
        if ((this.idcomentarios == null && other.idcomentarios != null) || (this.idcomentarios != null && !this.idcomentarios.equals(other.idcomentarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qsdm.Comentarios[ idcomentarios=" + idcomentarios + " ]";
    }
    
}
