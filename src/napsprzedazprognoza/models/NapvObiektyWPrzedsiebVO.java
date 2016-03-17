/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napsprzedazprognoza.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k.skowronski
 */
@Entity
@Table(name = "NAPV_OBIEKTY_W_PRZEDSIEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NapvObiektyWPrzedsiebVO.findAll", query = "SELECT n FROM NapvObiektyWPrzedsiebVO n"),
    @NamedQuery(name = "NapvObiektyWPrzedsiebVO.findByObKod", query = "SELECT n FROM NapvObiektyWPrzedsiebVO n WHERE n.obKod = :obKod"),
    @NamedQuery(name = "NapvObiektyWPrzedsiebVO.findByObNazwa", query = "SELECT n FROM NapvObiektyWPrzedsiebVO n WHERE n.obNazwa = :obNazwa"),
    @NamedQuery(name = "NapvObiektyWPrzedsiebVO.findByObObId", query = "SELECT n FROM NapvObiektyWPrzedsiebVO n WHERE n.obObId = :obObId"),
    @NamedQuery(name = "NapvObiektyWPrzedsiebVO.findByObPelnyKod", query = "SELECT n FROM NapvObiektyWPrzedsiebVO n WHERE n.obPelnyKod = :obPelnyKod"),
    @NamedQuery(name = "NapvObiektyWPrzedsiebVO.findByObSkrot", query = "SELECT n FROM NapvObiektyWPrzedsiebVO n WHERE n.obSkrot = :obSkrot")})
public class NapvObiektyWPrzedsiebVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "OB_ID")
    private BigDecimal obId;
    
    @Column(name = "OB_KOD")
    private String obKod;
    @Column(name = "OB_NAZWA")
    private String obNazwa;
    @Column(name = "OB_OB_ID")
    private Long obObId;
    @Column(name = "OB_PELNY_KOD")
    private String obPelnyKod;
    @Column(name = "OB_SKROT")
    private String obSkrot;

    public NapvObiektyWPrzedsiebVO() {
    }

    public BigDecimal getObId() {
        return obId;
    }

    public void setObId(BigDecimal obId) {
        this.obId = obId;
    }

    public String getObKod() {
        return obKod;
    }

    public void setObKod(String obKod) {
        this.obKod = obKod;
    }

    public String getObNazwa() {
        return obNazwa;
    }

    public void setObNazwa(String obNazwa) {
        this.obNazwa = obNazwa;
    }

    public Long getObObId() {
        return obObId;
    }

    public void setObObId(Long obObId) {
        this.obObId = obObId;
    }

    public String getObPelnyKod() {
        return obPelnyKod;
    }

    public void setObPelnyKod(String obPelnyKod) {
        this.obPelnyKod = obPelnyKod;
    }

    public String getObSkrot() {
        return obSkrot;
    }

    public void setObSkrot(String obSkrot) {
        this.obSkrot = obSkrot;
    }
    
}
