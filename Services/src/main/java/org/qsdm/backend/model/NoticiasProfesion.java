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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alberto
 */
@Entity
@Table(name = "noticias_profesion")
@NamedQueries({
    @NamedQuery(name = "NoticiasProfesion.findAll", query = "SELECT n FROM NoticiasProfesion n"),
    @NamedQuery(name = "NoticiasProfesion.findByIdnoticiasProfesion", query = "SELECT n FROM NoticiasProfesion n WHERE n.idnoticiasProfesion = :idnoticiasProfesion"),
    @NamedQuery(name = "NoticiasProfesion.findByCodProfesion", query = "SELECT n FROM NoticiasProfesion n WHERE n.codProfesion = :codProfesion"),
    @NamedQuery(name = "NoticiasProfesion.findByNombreProfesion", query = "SELECT n FROM NoticiasProfesion n WHERE n.nombreProfesion = :nombreProfesion"),
    @NamedQuery(name = "NoticiasProfesion.findByFechaBaja", query = "SELECT n FROM NoticiasProfesion n WHERE n.fechaBaja = :fechaBaja")})
public class NoticiasProfesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnoticias_profesion")
    private Integer idnoticiasProfesion;
    @Column(name = "codProfesion")
    private Integer codProfesion;
    @Column(name = "nombreProfesion")
    private String nombreProfesion;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @JoinColumn(name = "idNoticia", referencedColumnName = "idnoticias")
    @ManyToOne
    private Noticias idNoticia;

    public NoticiasProfesion() {
    }

    public NoticiasProfesion(Integer idnoticiasProfesion) {
        this.idnoticiasProfesion = idnoticiasProfesion;
    }

    public Integer getIdnoticiasProfesion() {
        return idnoticiasProfesion;
    }

    public void setIdnoticiasProfesion(Integer idnoticiasProfesion) {
        this.idnoticiasProfesion = idnoticiasProfesion;
    }

    public Integer getCodProfesion() {
        return codProfesion;
    }

    public void setCodProfesion(Integer codProfesion) {
        this.codProfesion = codProfesion;
    }

    public String getNombreProfesion() {
        return nombreProfesion;
    }

    public void setNombreProfesion(String nombreProfesion) {
        this.nombreProfesion = nombreProfesion;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Noticias getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(Noticias idNoticia) {
        this.idNoticia = idNoticia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnoticiasProfesion != null ? idnoticiasProfesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NoticiasProfesion)) {
            return false;
        }
        NoticiasProfesion other = (NoticiasProfesion) object;
        if ((this.idnoticiasProfesion == null && other.idnoticiasProfesion != null) || (this.idnoticiasProfesion != null && !this.idnoticiasProfesion.equals(other.idnoticiasProfesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qsdm.NoticiasProfesion[ idnoticiasProfesion=" + idnoticiasProfesion + " ]";
    }
    
}
