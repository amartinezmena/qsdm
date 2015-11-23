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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alberto
 */
@Entity
@Table(name = "profesiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesiones.findAll", query = "SELECT p FROM Profesiones p"),
    @NamedQuery(name = "Profesiones.findById", query = "SELECT p FROM Profesiones p WHERE p.id = :id"),
    @NamedQuery(name = "Profesiones.findByCod", query = "SELECT p FROM Profesiones p WHERE p.cod = :cod"),
    @NamedQuery(name = "Profesiones.findByNombrePpal", query = "SELECT p FROM Profesiones p WHERE p.nombrePpal = :nombrePpal"),
    @NamedQuery(name = "Profesiones.findByDescripcion", query = "SELECT p FROM Profesiones p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Profesiones.findByEstudiosAsoc", query = "SELECT p FROM Profesiones p WHERE p.estudiosAsoc = :estudiosAsoc"),
    @NamedQuery(name = "Profesiones.findByPPast", query = "SELECT p FROM Profesiones p WHERE p.pPast = :pPast"),
    @NamedQuery(name = "Profesiones.findByPPresent", query = "SELECT p FROM Profesiones p WHERE p.pPresent = :pPresent"),
    @NamedQuery(name = "Profesiones.findByPFuture", query = "SELECT p FROM Profesiones p WHERE p.pFuture = :pFuture"),
    @NamedQuery(name = "Profesiones.findBySPast", query = "SELECT p FROM Profesiones p WHERE p.sPast = :sPast"),
    @NamedQuery(name = "Profesiones.findBySPresent", query = "SELECT p FROM Profesiones p WHERE p.sPresent = :sPresent"),
    @NamedQuery(name = "Profesiones.findBySFuture", query = "SELECT p FROM Profesiones p WHERE p.sFuture = :sFuture"),
    @NamedQuery(name = "Profesiones.findByCMemoria", query = "SELECT p FROM Profesiones p WHERE p.cMemoria = :cMemoria"),
    @NamedQuery(name = "Profesiones.findByCCreatividad", query = "SELECT p FROM Profesiones p WHERE p.cCreatividad = :cCreatividad"),
    @NamedQuery(name = "Profesiones.findByCComunicacion", query = "SELECT p FROM Profesiones p WHERE p.cComunicacion = :cComunicacion"),
    @NamedQuery(name = "Profesiones.findByCFormaFisica", query = "SELECT p FROM Profesiones p WHERE p.cFormaFisica = :cFormaFisica"),
    @NamedQuery(name = "Profesiones.findByCLogica", query = "SELECT p FROM Profesiones p WHERE p.cLogica = :cLogica"),
    @NamedQuery(name = "Profesiones.findByUltimaActualizacion", query = "SELECT p FROM Profesiones p WHERE p.ultimaActualizacion = :ultimaActualizacion")})
public class Profesiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cod")
    private Integer cod;
    @Basic(optional = false)
    @Column(name = "nombre_ppal")
    private String nombrePpal;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estudios_asoc")
    private String estudiosAsoc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "p_past")
    private Float pPast;
    @Column(name = "p_present")
    private Float pPresent;
    @Column(name = "p_future")
    private Float pFuture;
    @Column(name = "s_past")
    private Float sPast;
    @Column(name = "s_present")
    private Float sPresent;
    @Column(name = "s_future")
    private Float sFuture;
    @Column(name = "c_memoria")
    private Float cMemoria;
    @Column(name = "c_creatividad")
    private Float cCreatividad;
    @Column(name = "c_comunicacion")
    private Float cComunicacion;
    @Column(name = "c_forma_fisica")
    private Float cFormaFisica;
    @Column(name = "c_logica")
    private Float cLogica;
    @Basic(optional = false)
    @Column(name = "ultima_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActualizacion;

    public Profesiones() {
    }

    public Profesiones(Integer id) {
        this.id = id;
    }

    public Profesiones(Integer id, String nombrePpal, Date ultimaActualizacion) {
        this.id = id;
        this.nombrePpal = nombrePpal;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNombrePpal() {
        return nombrePpal;
    }

    public void setNombrePpal(String nombrePpal) {
        this.nombrePpal = nombrePpal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstudiosAsoc() {
        return estudiosAsoc;
    }

    public void setEstudiosAsoc(String estudiosAsoc) {
        this.estudiosAsoc = estudiosAsoc;
    }

    public Float getPPast() {
        return pPast;
    }

    public void setPPast(Float pPast) {
        this.pPast = pPast;
    }

    public Float getPPresent() {
        return pPresent;
    }

    public void setPPresent(Float pPresent) {
        this.pPresent = pPresent;
    }

    public Float getPFuture() {
        return pFuture;
    }

    public void setPFuture(Float pFuture) {
        this.pFuture = pFuture;
    }

    public Float getSPast() {
        return sPast;
    }

    public void setSPast(Float sPast) {
        this.sPast = sPast;
    }

    public Float getSPresent() {
        return sPresent;
    }

    public void setSPresent(Float sPresent) {
        this.sPresent = sPresent;
    }

    public Float getSFuture() {
        return sFuture;
    }

    public void setSFuture(Float sFuture) {
        this.sFuture = sFuture;
    }

    public Float getCMemoria() {
        return cMemoria;
    }

    public void setCMemoria(Float cMemoria) {
        this.cMemoria = cMemoria;
    }

    public Float getCCreatividad() {
        return cCreatividad;
    }

    public void setCCreatividad(Float cCreatividad) {
        this.cCreatividad = cCreatividad;
    }

    public Float getCComunicacion() {
        return cComunicacion;
    }

    public void setCComunicacion(Float cComunicacion) {
        this.cComunicacion = cComunicacion;
    }

    public Float getCFormaFisica() {
        return cFormaFisica;
    }

    public void setCFormaFisica(Float cFormaFisica) {
        this.cFormaFisica = cFormaFisica;
    }

    public Float getCLogica() {
        return cLogica;
    }

    public void setCLogica(Float cLogica) {
        this.cLogica = cLogica;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesiones)) {
            return false;
        }
        Profesiones other = (Profesiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qsdm.Profesiones[ id=" + id + " ]";
    }
    
}
