/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qsdm.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Alberto
 */
@Entity
@Table(name = "noticias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticias.findAll", query = "SELECT n FROM Noticias n"),
    @NamedQuery(name = "Noticias.findByIdnoticias", query = "SELECT n FROM Noticias n WHERE n.idnoticias = :idnoticias"),
    @NamedQuery(name = "Noticias.findByFechaAlta", query = "SELECT n FROM Noticias n WHERE n.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Noticias.findByFechaBaja", query = "SELECT n FROM Noticias n WHERE n.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Noticias.findByPublicada", query = "SELECT n FROM Noticias n WHERE n.publicada = :publicada"),
    @NamedQuery(name = "Noticias.findByUrlFuente", query = "SELECT n FROM Noticias n WHERE n.urlFuente = :urlFuente"),
    @NamedQuery(name = "Noticias.findByUsuarioPublicacion", query = "SELECT n FROM Noticias n WHERE n.usuarioPublicacion = :usuarioPublicacion")})
@JsonIgnoreProperties(value = { "publicada","fechaBaja"})
public class Noticias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnoticias")
    private Integer idnoticias;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Column(name = "publicada")
    private Integer publicada;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "titulo")
    private String titulo;
    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "urlFuente")
    private String urlFuente;
    @Column(name = "usuarioPublicacion")
    private String usuarioPublicacion;
    @OneToMany(mappedBy = "idNoticia")
    private Set<NoticiasProfesion> noticiasProfesionSet;

    public Noticias() {
    }

    public Noticias(Integer idnoticias) {
        this.idnoticias = idnoticias;
    }

    public Integer getIdnoticias() {
        return idnoticias;
    }

    public void setIdnoticias(Integer idnoticias) {
        this.idnoticias = idnoticias;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Integer getPublicada() {
        return publicada;
    }

    public void setPublicada(Integer publicada) {
        this.publicada = publicada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlFuente() {
        return urlFuente;
    }

    public void setUrlFuente(String urlFuente) {
        this.urlFuente = urlFuente;
    }

    public String getUsuarioPublicacion() {
        return usuarioPublicacion;
    }

    public void setUsuarioPublicacion(String usuarioPublicacion) {
        this.usuarioPublicacion = usuarioPublicacion;
    }

    @XmlTransient
    public Set<NoticiasProfesion> getNoticiasProfesionSet() {
        return noticiasProfesionSet;
    }

    public void setNoticiasProfesionSet(Set<NoticiasProfesion> noticiasProfesionSet) {
        this.noticiasProfesionSet = noticiasProfesionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnoticias != null ? idnoticias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticias)) {
            return false;
        }
        Noticias other = (Noticias) object;
        if ((this.idnoticias == null && other.idnoticias != null) || (this.idnoticias != null && !this.idnoticias.equals(other.idnoticias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qsdm.Noticias[ idnoticias=" + idnoticias + " ]";
    }
    
}
