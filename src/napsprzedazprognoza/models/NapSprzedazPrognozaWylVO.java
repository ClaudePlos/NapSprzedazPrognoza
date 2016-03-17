/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napsprzedazprognoza.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k.skowronski
 */
@Entity
@Table(name = "NAP_SPRZEDAZ_PROGNOZA_WYL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findAll", query = "SELECT n FROM NapSprzedazPrognozaWylVO n"),
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findById", query = "SELECT n FROM NapSprzedazPrognozaWylVO n WHERE n.id = :id"),
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findBySk", query = "SELECT n FROM NapSprzedazPrognozaWylVO n WHERE n.sk = :sk"),
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findByObPelnyKod", query = "SELECT n FROM NapSprzedazPrognozaWylVO n WHERE n.obPelnyKod = :obPelnyKod"),
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findByMiasto", query = "SELECT n FROM NapSprzedazPrognozaWylVO n WHERE n.miasto = :miasto"),
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findByDataZakonczenia", query = "SELECT n FROM NapSprzedazPrognozaWylVO n WHERE n.dataZakonczenia = :dataZakonczenia"),
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findByOkres", query = "SELECT n FROM NapSprzedazPrognozaWylVO n WHERE n.okres = :okres"),
    @NamedQuery(name = "NapSprzedazPrognozaWylVO.findByKwota", query = "SELECT n FROM NapSprzedazPrognozaWylVO n WHERE n.kwota = :kwota")})
public class NapSprzedazPrognozaWylVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "SK")
    private String sk;
    @Column(name = "OB_PELNY_KOD")
    private String obPelnyKod;
    @Column(name = "MIASTO")
    private String miasto;
    @Column(name = "DATA_ZAKONCZENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZakonczenia;
    @Column(name = "OKRES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date okres;
    @Column(name = "KWOTA")
    private BigDecimal kwota;  
    @Column(name = "ID_UMOWA")
    private BigDecimal idUmowa;

    public NapSprzedazPrognozaWylVO() {
    }

    public NapSprzedazPrognozaWylVO(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getObPelnyKod() {
        return obPelnyKod;
    }

    public void setObPelnyKod(String obPelnyKod) {
        this.obPelnyKod = obPelnyKod;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public Date getOkres() {
        return okres;
    }

    public void setOkres(Date okres) {
        this.okres = okres;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public BigDecimal getIdUmowa() {
        return idUmowa;
    }

    public void setIdUmowa(BigDecimal idUmowa) {
        this.idUmowa = idUmowa;
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
        if (!(object instanceof NapSprzedazPrognozaWylVO)) {
            return false;
        }
        NapSprzedazPrognozaWylVO other = (NapSprzedazPrognozaWylVO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    
}
