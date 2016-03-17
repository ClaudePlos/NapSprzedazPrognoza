/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napsprzedazprognoza.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k.skowronski
 */
@Entity
@Table(name = "NAP_SPRZEDAZ_PROGNOZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NapSprzedazPrognozaVO.findAll", query = "SELECT n FROM NapSprzedazPrognozaVO n"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findById", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.id = :id"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findBySk", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.sk = :sk"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findByObPelnyKod", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.obPelnyKod = :obPelnyKod"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findByMiasto", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.miasto = :miasto"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findByOpis", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.opis = :opis"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findByKontrakt", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.kontrakt = :kontrakt"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findByDataZakonczenia", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.dataZakonczenia = :dataZakonczenia"),
    @NamedQuery(name = "NapSprzedazPrognozaVO.findByKwotaMiesieczna", query = "SELECT n FROM NapSprzedazPrognozaVO n WHERE n.kwotaMiesieczna = :kwotaMiesieczna")})
public class NapSprzedazPrognozaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator( name="napSprzProg_seq", sequenceName = "napSprzProg_seq", allocationSize = 1 )
    @Id @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "napSprzProg_seq" )
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "SK")
    private String sk;
    @Column(name = "OB_PELNY_KOD")
    private String obPelnyKod;
    @Column(name = "MIASTO")
    private String miasto;
    @Column(name = "OPIS")
    private String opis;
    @Column(name = "KONTRAKT")
    private String kontrakt;
    @Column(name = "DATA_ZAKONCZENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZakonczenia;
    @Column(name = "KWOTA_MIESIECZNA")
    private BigDecimal kwotaMiesieczna;
    
    @OneToMany (cascade =  CascadeType.ALL , fetch= FetchType.EAGER)
    @JoinColumn(name = "ID_UMOWA")
    private List<NapSprzedazPrognozaWylVO> wyliczenia;
    

    public NapSprzedazPrognozaVO() {
    }

    public NapSprzedazPrognozaVO(BigDecimal id) {
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getKontrakt() {
        return kontrakt;
    }

    public void setKontrakt(String kontrakt) {
        this.kontrakt = kontrakt;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public BigDecimal getKwotaMiesieczna() {
        return kwotaMiesieczna;
    }

    public void setKwotaMiesieczna(BigDecimal kwotaMiesieczna) {
        this.kwotaMiesieczna = kwotaMiesieczna;
    }

    public List<NapSprzedazPrognozaWylVO> getWyliczenia() {
        return wyliczenia;
    }

    public void setWyliczenia(List<NapSprzedazPrognozaWylVO> wyliczenia) {
        this.wyliczenia = wyliczenia;
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
        if (!(object instanceof NapSprzedazPrognozaVO)) {
            return false;
        }
        NapSprzedazPrognozaVO other = (NapSprzedazPrognozaVO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
}
