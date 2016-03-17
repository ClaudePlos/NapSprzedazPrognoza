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
@Table(name = "NAP_SPRZEDAZ_PLAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NapSprzedazPlanVO.findAll", query = "SELECT n FROM NapSprzedazPlanVO n"),
    @NamedQuery(name = "NapSprzedazPlanVO.findById", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.id = :id"),
    @NamedQuery(name = "NapSprzedazPlanVO.findByRok", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.rok = :rok"),
    @NamedQuery(name = "NapSprzedazPlanVO.findByWartosc", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.wartosc = :wartosc"),
    @NamedQuery(name = "NapSprzedazPlanVO.findByDywizja", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.dywizja = :dywizja"),
    @NamedQuery(name = "NapSprzedazPlanVO.findByObPelnyKod", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.obPelnyKod = :obPelnyKod"),
    @NamedQuery(name = "NapSprzedazPlanVO.findByOkresOd", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.okresOd = :okresOd"),
    @NamedQuery(name = "NapSprzedazPlanVO.findByOkresDo", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.okresDo = :okresDo"),
    @NamedQuery(name = "NapSprzedazPlanVO.findByKwartal", query = "SELECT n FROM NapSprzedazPlanVO n WHERE n.kwartal = :kwartal")})
public class NapSprzedazPlanVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ROK")
    private BigInteger rok;
    @Column(name = "WARTOSC")
    private BigInteger wartosc;
    @Column(name = "DYWIZJA")
    private String dywizja;
    @Column(name = "OB_PELNY_KOD")
    private String obPelnyKod;
    @Column(name = "OKRES_OD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date okresOd;
    @Column(name = "OKRES_DO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date okresDo;
    @Column(name = "KWARTAL")
    private String kwartal;

    public NapSprzedazPlanVO() {
    }

    public NapSprzedazPlanVO(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getRok() {
        return rok;
    }

    public void setRok(BigInteger rok) {
        this.rok = rok;
    }

    public BigInteger getWartosc() {
        return wartosc;
    }

    public void setWartosc(BigInteger wartosc) {
        this.wartosc = wartosc;
    }

    public String getDywizja() {
        return dywizja;
    }

    public void setDywizja(String dywizja) {
        this.dywizja = dywizja;
    }

    public String getObPelnyKod() {
        return obPelnyKod;
    }

    public void setObPelnyKod(String obPelnyKod) {
        this.obPelnyKod = obPelnyKod;
    }

    public Date getOkresOd() {
        return okresOd;
    }

    public void setOkresOd(Date okresOd) {
        this.okresOd = okresOd;
    }

    public Date getOkresDo() {
        return okresDo;
    }

    public void setOkresDo(Date okresDo) {
        this.okresDo = okresDo;
    }

    public String getKwartal() {
        return kwartal;
    }

    public void setKwartal(String kwartal) {
        this.kwartal = kwartal;
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
        if (!(object instanceof NapSprzedazPlanVO)) {
            return false;
        }
        NapSprzedazPlanVO other = (NapSprzedazPlanVO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "napsprzedazprognoza.models.NapSprzedazPlanVO[ id=" + id + " ]";
    }
    
}
