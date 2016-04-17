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

public class NapPodsumowanieKontraktowDTO  {
   
    
    private BigDecimal id;

    private String sk;

    private String obPelnyKod;

    private String miasto;

    private String opis;
 
    private String kontrakt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZakonczenia;

    private BigDecimal kwotaMiesieczna;
    
    private BigDecimal ilMiesiecy;

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

    public BigDecimal getIlMiesiecy() {
        return ilMiesiecy;
    }

    public void setIlMiesiecy(BigDecimal ilMiesiecy) {
        this.ilMiesiecy = ilMiesiecy;
    }
    
    

    

   
}
