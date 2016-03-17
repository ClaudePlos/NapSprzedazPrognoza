/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napsprzedazprognoza.models;

import java.math.BigDecimal;

/**
 *
 * @author k.skowronski
 */
public class WyliczKwotaRocznaDTO {
    
    
    
    private int rok;
    
    private BigDecimal kwotaIlosc;
    
    private String rodzaj;
    
 

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public BigDecimal getKwotaIlosc() {
        return kwotaIlosc;
    }

    public void setKwotaIlosc(BigDecimal kwotaIlosc) {
        this.kwotaIlosc = kwotaIlosc;
    }


    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    
    
    
    
    
    
}
