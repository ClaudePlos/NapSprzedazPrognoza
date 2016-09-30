/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napsprzedazprognoza.services;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import napsprzedazprognoza.models.NapPodsumowanieKontraktowDTO;
import napsprzedazprognoza.models.NapSprzedazPrognozaVO;
import napsprzedazprognoza.models.NapSprzedazPrognozaWylVO;
import napsprzedazprognoza.models.NapvObiektyWPrzedsiebVO;
import napsprzedazprognoza.models.OkresyDTO;
import napsprzedazprognoza.models.WyliczKwotaRocznaDTO;

/**
 *
 * @author k.skowronski
 */

@Stateless
public class NapServices {
    
    private static final EntityManagerFactory emfInstance =
		        Persistence.createEntityManagerFactory("NapSprzedazPrognozaPU");
    private EntityManager em;
    
    
    
    public NapServices() {
        em = emfInstance.createEntityManager();
    }
    
    private static volatile NapServices instance = null;
    
    public static NapServices getInstance() {
        if (instance == null) {
          instance = new NapServices();
        }
        return instance;
    }
    
   
    
    public List<NapSprzedazPrognozaVO> listaSprzedazPrognoza()
    {
        
      List<NapSprzedazPrognozaVO> sprzPrognoza = new ArrayList<NapSprzedazPrognozaVO>();
      
      sprzPrognoza =  em.createQuery("select s from NapSprzedazPrognozaVO s order by s.id").getResultList();
      
      
      for ( NapSprzedazPrognozaVO sp : sprzPrognoza)
      {
         sp.setWyliczenia( listaSprzedazPrognozaWyl( sp.getId() ) );
      }
      
      
       return sprzPrognoza;
       
    }
    
    
    public List<NapSprzedazPrognozaWylVO> listaSprzedazPrognozaWyl( BigDecimal idUmowy)
    {
     
     List<NapSprzedazPrognozaWylVO> sprzPrognozaWyl = new ArrayList<NapSprzedazPrognozaWylVO>();   
        
     /*String okresy =  (String) em.createNativeQuery("select listagg (''''||to_char(okres,'YYYY-MM-DD')||'''',',')  WITHIN GROUP (ORDER BY okres) enames --into vRodzKosztu"
         +  " from nap_sprzedaz_prognoza_wyl" ).getSingleResult();*/
     
     sprzPrognozaWyl = em.createQuery( "select sw from NapSprzedazPrognozaWylVO sw where sw.idUmowa = " + idUmowy + " order by sw.okres").getResultList();
     
     return sprzPrognozaWyl;
     
    }
    
    
    public List<NapvObiektyWPrzedsiebVO> pobierzObiektyFirmy()
    {
     
     List<NapvObiektyWPrzedsiebVO> joList = new ArrayList<NapvObiektyWPrzedsiebVO>();   

     joList = em.createQuery( "select ob from NapvObiektyWPrzedsiebVO ob").getResultList();
     
     return joList;
     
    }
    
    public Vector listaOkresowWyliczonych()
    {
       
        Vector ok = null;  
        
        ok =   (Vector) em.createNativeQuery("select distinct okres from nap_sprzedaz_prognoza_wyl order by okres").getResultList();
        
        return ok;
    }
    
    
    
    
    
     public void przeliczDane( String naDzien, BigDecimal idUmowy )
     {
        EntityTransaction tx = em.getTransaction();
        
        String q  = "begin "
                      + "NAP_ZK_TOOLS.sprzedaz_prognoza_wyl( to_date('" + naDzien + "','DD-MM-YYYY'), " + idUmowy + " );"
                      + "end;";
        try {
            tx.begin();
              em.createNativeQuery(q).executeUpdate();
            tx.commit();
        } catch ( Exception e) {
            tx.rollback();
            e.printStackTrace();
            
        }
     }
     
     
     public void dodajZmianySprzedazPrognoza( NapSprzedazPrognozaVO sprzPrognoza )
     {
         EntityTransaction tx = em.getTransaction();
         
         
         
            try{
                tx.begin();
                NapSprzedazPrognozaVO merge = em.merge(sprzPrognoza);
                em.persist(merge);
                sprzPrognoza = merge;
                tx.commit();
            } catch (Exception e)
            {
                tx.begin();
                em.persist(sprzPrognoza);
                tx.commit();
            }
            
            
            try{
                tx.begin();
                em.flush();
                tx.commit();
            } catch (Exception e)
            {
                throw new RuntimeException("Nie mogę dodać");
            }
            
     
         
     }
     
     public String zapiszZmianySprzedazPrognozaRow( NapSprzedazPrognozaVO sprzPrognozaRow )
     {
         EntityTransaction tx = em.getTransaction();
         
          try{
                tx.begin();
                NapSprzedazPrognozaVO merge = em.merge(sprzPrognozaRow);
                em.persist(merge);
                sprzPrognozaRow = merge;
                tx.commit();
            } catch (Exception e)
            {
                tx.begin();
                em.persist(sprzPrognozaRow);
                tx.commit();
            }
            
            
            try{
                tx.begin();
                em.flush();
                tx.commit();
            } catch (Exception e)
            {
                throw new RuntimeException("Nie mogę zapisać -2");
            }
            
            return "OK";
         
     }
     
     
     public void zapiszZmianySprzedazPrognoza( List<NapSprzedazPrognozaVO> sprzPrognoza )
     {
         EntityTransaction tx = em.getTransaction();
         
         
         for ( NapSprzedazPrognozaVO sp : sprzPrognoza )
         {
            try{
                tx.begin();
                NapSprzedazPrognozaVO merge = em.merge(sp);
                em.persist(merge);
                sp = merge;
                tx.commit();
            } catch (Exception e)
            {
                tx.begin();
                em.persist(sp);
                tx.commit();
            }
            
            
            try{
                tx.begin();
                em.flush();
                tx.commit();
            } catch (Exception e)
            {
                throw new RuntimeException("Nie mogę zapisać");
            }
            
           
         }
         
     }
     
     
     
     public void usunZmianySprzedazPrognoza( NapSprzedazPrognozaVO sprzPrognoza )
     {
         EntityTransaction tx = em.getTransaction();

         
            try{
                tx.begin();
                NapSprzedazPrognozaVO merge = em.merge(sprzPrognoza);
                em.remove(merge);
                sprzPrognoza = merge;
                tx.commit();
            } catch (Exception e)
            {
                tx.begin();
                em.persist(sprzPrognoza);
                tx.commit();
            }
            
            
            try{
                tx.begin();
                em.flush();
                tx.commit();
            } catch (Exception e)
            {
                throw new RuntimeException("Nie mogę uzunąć");
            }
            
     
         
     }
   
     
     
    public WyliczKwotaRocznaDTO zestawienieRodznePodaj( int rok, String rodzaj, String dywizja, String naDzien )
    {
        
        WyliczKwotaRocznaDTO kwotaRoczna = new WyliczKwotaRocznaDTO();
        
        BigDecimal ret = null;  
        BigDecimal umow = null;
        BigDecimal odnowien = null;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        if ( naDzien.equals("_null"))
        {
            naDzien = dateFormat.format(date);
        }
        
        
        if ( dywizja.equals("ALL") )
        {
            if ( rodzaj.equals("kontrakty"))
            {
                
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from (select sk, data_zakonczenia, okres\n" +
                    ", case when okres <= '" + naDzien + "' and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
                    " else kwota end kwota\n" +
                    " from nap_sprzedaz_prognoza_wyl nspw\n" +
                    " where to_char(okres,'YYYY') = '" + rok + "'\n" +
                    " and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))").getSingleResult();  
              
            }
            else if( rodzaj.equals("odnowienie"))
            {
                
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from (select sk, data_zakonczenia, okres\n" +
                    ", case when data_zakonczenia < '" + naDzien + "' then 0\n" +
                    " else kwota end kwota\n" +
                    " from nap_sprzedaz_prognoza_wyl nspw\n" +
                    " where to_char(okres,'YYYY') = '" + rok + "'\n" +
                    " and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))").getSingleResult();  
            
            }
            else if( rodzaj.equals("umow"))
            {
                 ret =  (BigDecimal) em.createNativeQuery("select count(1) from nap_sprzedaz_prognoza where to_char(data_zakonczenia,'YYYY') > " + rok).getSingleResult();
            }
            else if( rodzaj.equals("odnowien"))
            {    
                 ret =  (BigDecimal) em.createNativeQuery("select count(1) from nap_sprzedaz_prognoza where to_char(data_zakonczenia,'YYYY') = " + rok).getSingleResult();
            }
            else if( rodzaj.equals("plan"))
            {    
                 ret =  (BigDecimal) em.createNativeQuery("select wartosc from nap_sprzedaz_plan where dywizja = 'ALL' and kwartal = 'ROK' and rok = " + rok ).getSingleResult();
            }

            kwotaRoczna.setRok(rok);
            kwotaRoczna.setRodzaj(rodzaj);
            kwotaRoczna.setKwotaIlosc( ret );
        }
        
        
        //TODO - popraw jak dal całego
        if ( dywizja.equals("C") )
        {
            if ( rodzaj.equals("kontraktyC"))
            {
                 ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from (select sk, data_zakonczenia, okres\n" +
                    ", case when okres <= '" + naDzien + "' and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
                    " else kwota end kwota\n" +
                    " from nap_sprzedaz_prognoza_wyl nspw\n" +
                    " where nspw.sk like 'C%' and to_char(okres,'YYYY') = '" + rok + "'\n" +
                    " and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))").getSingleResult(); 
                
               /*  ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from nap_sprzedaz_prognoza_wyl nspw\n" +
                     "    where nspw.sk like 'C%' and to_char(okres,'YYYY') = '" + rok +"'\n" +
                     "    and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa )").getSingleResult();    */
            }
            else if( rodzaj.equals("odnowienieC"))
            {
                
                 ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from (select sk, data_zakonczenia, okres\n" +
                    ", case when data_zakonczenia < '" + naDzien + "' then 0\n" +
                    " else kwota end kwota\n" +
                    " from nap_sprzedaz_prognoza_wyl nspw\n" +
                    " where nspw.sk like 'C%' and to_char(okres,'YYYY') = '" + rok + "'\n" +
                    " and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))").getSingleResult();  
                
                 /*ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from nap_sprzedaz_prognoza_wyl nspw\n" +
                     "    where nspw.sk like 'C%' and to_char(okres,'YYYY') = '" + rok +"'\n" +
                     "    and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa )").getSingleResult();*/
            }
            else if( rodzaj.equals("umowC"))
            {
                 ret =  (BigDecimal) em.createNativeQuery("select count(1) from nap_sprzedaz_prognoza where sk like 'C%' and  to_char(data_zakonczenia,'YYYY') > " + rok).getSingleResult();
            }
            else if( rodzaj.equals("odnowienC"))
            {    
                 ret =  (BigDecimal) em.createNativeQuery("select count(1) from nap_sprzedaz_prognoza where sk like 'C%'and to_char(data_zakonczenia,'YYYY') = " + rok).getSingleResult();
            }
            else if( rodzaj.equals("planC"))
            {    
                 ret =  (BigDecimal) em.createNativeQuery("select sum(wartosc) from nap_sprzedaz_plan where dywizja = 'C'  and kwartal = 'ROK' and rok = " + rok  ).getSingleResult();
            }

            kwotaRoczna.setRok(rok);
            kwotaRoczna.setRodzaj(rodzaj);
            kwotaRoczna.setKwotaIlosc( ret );
        }
        
        
        
        if ( dywizja.equals("Z") )
        {
            if ( rodzaj.equals("kontraktyZ"))
            {
                 ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from (select sk, data_zakonczenia, okres\n" +
                    ", case when okres <= '" + naDzien + "' and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
                    " else kwota end kwota\n" +
                    " from nap_sprzedaz_prognoza_wyl nspw\n" +
                    " where nspw.sk like 'Z%' and to_char(okres,'YYYY') = '" + rok + "'\n" +
                    " and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))").getSingleResult();  
                
                 /*ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from nap_sprzedaz_prognoza_wyl nspw\n" +
                     "    where nspw.sk like 'Z%' and to_char(okres,'YYYY') = '" + rok +"'\n" +
                     "    and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa )").getSingleResult();  */  
            }
            else if( rodzaj.equals("odnowienieZ"))
            {
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from (select sk, data_zakonczenia, okres\n" +
                    ", case when data_zakonczenia < '" + naDzien + "' then 0\n" +
                    " else kwota end kwota\n" +
                    " from nap_sprzedaz_prognoza_wyl nspw\n" +
                    " where nspw.sk like 'Z%' and to_char(okres,'YYYY') = '" + rok + "'\n" +
                    " and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))").getSingleResult();  
                
                /*ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) from nap_sprzedaz_prognoza_wyl nspw\n" +
                     "    where nspw.sk like 'Z%' and to_char(okres,'YYYY') = '" + rok +"'\n" +
                     "    and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa )").getSingleResult();*/
            }
            else if( rodzaj.equals("umowZ"))
            {
                 ret =  (BigDecimal) em.createNativeQuery("select count(1) from nap_sprzedaz_prognoza where sk like 'Z%' and  to_char(data_zakonczenia,'YYYY') > " + rok).getSingleResult();
            }
            else if( rodzaj.equals("odnowienZ"))
            {    
                 ret =  (BigDecimal) em.createNativeQuery("select count(1) from nap_sprzedaz_prognoza where sk like 'Z%' and to_char(data_zakonczenia,'YYYY') = " + rok).getSingleResult();
            }
            else if( rodzaj.equals("planZ"))
            {    
                 ret =  (BigDecimal) em.createNativeQuery("select wartosc from nap_sprzedaz_plan where dywizja = 'Z' and kwartal = 'ROK' and rok = " + rok ).getSingleResult();
            }

            kwotaRoczna.setRok(rok);
            kwotaRoczna.setRodzaj(rodzaj);
            kwotaRoczna.setKwotaIlosc( ret );
        }
        
        if ( kwotaRoczna.getKwotaIlosc() == null )
        {
            kwotaRoczna.setKwotaIlosc( new BigDecimal(BigInteger.ZERO));
        }
        
        return kwotaRoczna;
        
        
			
    }
    
    
    
    
    public WyliczKwotaRocznaDTO zestawienieRodznePodajSzczegolowe( int rok, String rodzaj, String dywizja, String naDzien, String kwartal, String obKod )
    {
        WyliczKwotaRocznaDTO kwotaRoczna = new WyliczKwotaRocznaDTO();

        BigDecimal ret = BigDecimal.ZERO;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        if ( naDzien.equals("_null"))
        {
            naDzien = dateFormat.format(date);
        }
        
        
        if ( dywizja.equals("ALL") )
        {
            if ( rodzaj.equals("kontrakt"))
            {
                
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) WARTOSC from (\n" +
                    "select ob_pelny_kod\n" +
                    ", case when to_char(okres, 'MM') in ('01','02','03') then 'Q1' \n" +
                    "       when to_char(okres, 'MM') in ('04','05','06') then 'Q2' \n" +
                    "	   when to_char(okres, 'MM') in ('07','08','09') then 'Q3' \n" +
                    "  else 'Q4' end kwartal\n" +
                    "   , case when okres <= SYSDATE and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
                    "    else kwota end kwota\n" +
                    "    from nap_sprzedaz_prognoza_wyl nspw\n" +
                    "    where to_char(okres,'YYYY') = " + rok + " and ob_pelny_kod = '" + obKod + "' \n" +
                    "    and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
                    "where kwartal = '" + kwartal + "'").getSingleResult();  
         
            }
            else if( rodzaj.equals("odnowienie"))
            {
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) wartosc from (\n" +
                    "select ob_pelny_kod\n" +
                    ", case when to_char(okres, 'MM') in ('01','02','03') then 'Q1' \n" +
                    "       when to_char(okres, 'MM') in ('04','05','06') then 'Q2' \n" +
                    "	   when to_char(okres, 'MM') in ('07','08','09') then 'Q3' \n" +
                    "  else 'Q4' end kwartal\n" +
                    "    , case when data_zakonczenia < SYSDATE then 0\n" +
                    "     else kwota end kwota\n" +
                    "     from nap_sprzedaz_prognoza_wyl nspw\n" +
                    "     where to_char(okres,'YYYY') = " + rok + " and ob_pelny_kod = '" + obKod + "' \n" +
                    "     and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
                    "where kwartal = '" + kwartal + "'").getSingleResult();  
            }
            else if( rodzaj.equals("plan"))
            {
                //System.err.println( obKod + " " + kwartal );
                ret =   (BigDecimal) em.createNativeQuery("select sum(wartosc) from nap_sprzedaz_plan "
                        + "where rok = " + rok
                        + "and dywizja in ('Z','C') "
                        + "and kwartal = '" + kwartal +"' "
                        + "and ob_pelny_kod = '" + obKod + "'").getSingleResult();  
            }
            
            kwotaRoczna.setRok(rok);
            kwotaRoczna.setRodzaj(rodzaj);
            if ( ret == null )
                ret = BigDecimal.ZERO;
            kwotaRoczna.setKwotaIlosc( ret );
        }
        
        if ( dywizja.equals("C") )
        {
            if ( rodzaj.equals("kontrakt"))
            {
                
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) WARTOSC from (\n" +
                    "select ob_pelny_kod\n" +
                    ", case when to_char(okres, 'MM') in ('01','02','03') then 'Q1' \n" +
                    "       when to_char(okres, 'MM') in ('04','05','06') then 'Q2' \n" +
                    "	   when to_char(okres, 'MM') in ('07','08','09') then 'Q3' \n" +
                    "  else 'Q4' end kwartal\n" +
                    "   , case when okres <= SYSDATE and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
                    "    else kwota end kwota\n" +
                    "    from nap_sprzedaz_prognoza_wyl nspw\n" +
                    "    where to_char(okres,'YYYY') = " + rok + " and nspw.sk like 'C%' and ob_pelny_kod = '" + obKod + "' \n" +
                    "    and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
                    "where kwartal = '" + kwartal + "'").getSingleResult();  
         
            }
            else if( rodzaj.equals("odnowienie"))
            {
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) wartosc from (\n" +
                    "select ob_pelny_kod\n" +
                    ", case when to_char(okres, 'MM') in ('01','02','03') then 'Q1' \n" +
                    "       when to_char(okres, 'MM') in ('04','05','06') then 'Q2' \n" +
                    "	   when to_char(okres, 'MM') in ('07','08','09') then 'Q3' \n" +
                    "  else 'Q4' end kwartal\n" +
                    "    , case when data_zakonczenia < SYSDATE then 0\n" +
                    "     else kwota end kwota\n" +
                    "     from nap_sprzedaz_prognoza_wyl nspw\n" +
                    "     where to_char(okres,'YYYY') = " + rok + " and nspw.sk like 'C%' and ob_pelny_kod = '" + obKod + "' \n" +
                    "     and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
                    "where kwartal = '" + kwartal + "'").getSingleResult();  
            }
            else if( rodzaj.equals("plan"))
            {
                try {
                   ret =   (BigDecimal) em.createNativeQuery("select wartosc from nap_sprzedaz_plan "
                        + "where rok = " + rok
                        + "and dywizja = 'C' "
                        + "and kwartal = '" + kwartal +"' "
                        + "and ob_pelny_kod = '" + obKod + "'").getSingleResult();  
                }catch(Exception e){
                    //This catch block catches all the exceptions
                }
                 
            }
            
            kwotaRoczna.setRok(rok);
            kwotaRoczna.setRodzaj(rodzaj);
            if ( ret == null )
                ret = BigDecimal.ZERO;
            kwotaRoczna.setKwotaIlosc( ret );
        }
        
        
        if ( dywizja.equals("Z") )
        {
            if ( rodzaj.equals("kontrakt"))
            {
                
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) WARTOSC from (\n" +
                    "select ob_pelny_kod\n" +
                    ", case when to_char(okres, 'MM') in ('01','02','03') then 'Q1' \n" +
                    "       when to_char(okres, 'MM') in ('04','05','06') then 'Q2' \n" +
                    "	   when to_char(okres, 'MM') in ('07','08','09') then 'Q3' \n" +
                    "  else 'Q4' end kwartal\n" +
                    "   , case when okres <= SYSDATE and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
                    "    else kwota end kwota\n" +
                    "    from nap_sprzedaz_prognoza_wyl nspw\n" +
                    "    where to_char(okres,'YYYY') = " + rok + " and nspw.sk like 'Z%' and ob_pelny_kod = '" + obKod + "' \n" +
                    "    and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
                    "where kwartal = '" + kwartal + "'").getSingleResult();  
         
            }
            else if( rodzaj.equals("odnowienie"))
            {
                ret =   (BigDecimal) em.createNativeQuery("select sum(kwota) wartosc from (\n" +
                    "select ob_pelny_kod\n" +
                    ", case when to_char(okres, 'MM') in ('01','02','03') then 'Q1' \n" +
                    "       when to_char(okres, 'MM') in ('04','05','06') then 'Q2' \n" +
                    "	   when to_char(okres, 'MM') in ('07','08','09') then 'Q3' \n" +
                    "  else 'Q4' end kwartal\n" +
                    "    , case when data_zakonczenia < SYSDATE then 0\n" +
                    "     else kwota end kwota\n" +
                    "     from nap_sprzedaz_prognoza_wyl nspw\n" +
                    "     where to_char(okres,'YYYY') = " + rok + " and nspw.sk like 'Z%' and ob_pelny_kod = '" + obKod + "' \n" +
                    "     and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
                    "where kwartal = '" + kwartal + "'").getSingleResult();  
            }
            else if( rodzaj.equals("plan"))
            {
                //System.err.println( obKod + " " + kwartal );
                ret =   (BigDecimal) em.createNativeQuery("select wartosc from nap_sprzedaz_plan "
                        + "where rok = " + rok
                        + "and dywizja = 'Z' "
                        + "and kwartal = '" + kwartal +"' "
                        + "and ob_pelny_kod = '" + obKod + "'").getSingleResult();  
            }
            
            kwotaRoczna.setRok(rok);
            kwotaRoczna.setRodzaj(rodzaj);
            if ( ret == null )
                ret = BigDecimal.ZERO;
            kwotaRoczna.setKwotaIlosc( ret );
        }
        
        
        return kwotaRoczna;
    }
    
    
    
    public List<NapSprzedazPrognozaVO> listaSprzedazPrognozaFiltr( String rok, String dywizja, String rodzaj )
    {
      List<NapSprzedazPrognozaVO> sprzPrognoza = new ArrayList<NapSprzedazPrognozaVO>();
      
      List<Object[]> sprzPrognoza2 = null;
        
      String quary = null;  

      
      quary = "select * from nap_sprzedaz_prognoza ";
      
      if ( rodzaj.subSequence(0, 1).equals("A") )
      {
        quary = quary + "where to_char(data_zakonczenia,'YYYY') != '" + rok + "' "; 
      }
      
      if ( rodzaj.subSequence(0, 1).equals("K") )
      {
        quary = quary + "where to_char(data_zakonczenia,'YYYY') = '" + rok + "' "; 
      }
      
      if ( !dywizja.equals("ALL") )
      {
        quary = quary + "and substr(sk,0,1) = '" + dywizja + "' ";   
      }
      
         
      quary = quary + "order by id"; 
      
      
      sprzPrognoza2 =  em.createNativeQuery(quary).getResultList();
      
        for ( Object[] sp : sprzPrognoza2)
        {
          NapSprzedazPrognozaVO s = new NapSprzedazPrognozaVO();
          s.setId( (BigDecimal) sp[0]);
          s.setSk((String) sp[1]);
          s.setObPelnyKod((String) sp[2]);
          s.setMiasto((String) sp[3]);
          s.setOpis((String) sp[4]);
          s.setKontrakt((String) sp[5]);
          s.setDataZakonczenia((Date) sp[6]);
          s.setKwotaMiesieczna((BigDecimal) sp[7]);
          sprzPrognoza.add(s);
        }
      

      
      /*for ( NapSprzedazPrognozaVO sp : sprzPrognoza)
      {
         sp.setWyliczenia( listaSprzedazPrognozaWyl( sp.getId() ) );
      }*/
      
      
       return sprzPrognoza;
       
    }
     
    public List<NapSprzedazPrognozaVO> listaSprzedazPrognozaNaDzienFiltr( String miesiac, String dywizja, String rodzaj )
    {
      List<NapSprzedazPrognozaVO> sprzPrognoza = new ArrayList<NapSprzedazPrognozaVO>();
      
      List<Object[]> sprzPrognoza2 = null;
        
      String quary = null;  

      
      quary = "select * from nap_sprzedaz_prognoza ";
      
      if ( rodzaj.subSequence(0, 1).equals("A") )
      {
        quary = quary + "where data_zakonczenia > substr('" + miesiac + "',0,7)||'-01'"; 
      }
      
      if ( rodzaj.subSequence(0, 1).equals("K") )
      {
        quary = quary + "where data_zakonczenia < last_day('" + miesiac + "')"; 
      }
      
      if ( !dywizja.equals("ALL") )
      {
        quary = quary + "and substr(sk,0,1) = '" + dywizja + "' ";  
      }
      
         
      quary = quary + "order by id"; 
      
      
      sprzPrognoza2 =  em.createNativeQuery(quary).getResultList();
      
        for ( Object[] sp : sprzPrognoza2)
        {
          NapSprzedazPrognozaVO s = new NapSprzedazPrognozaVO();
          s.setId( (BigDecimal) sp[0]);
          s.setSk((String) sp[1]);
          s.setObPelnyKod((String) sp[2]);
          s.setMiasto((String) sp[3]);
          s.setOpis((String) sp[4]);
          s.setKontrakt((String) sp[5]);
          s.setDataZakonczenia((Date) sp[6]);
          s.setKwotaMiesieczna((BigDecimal) sp[7]);
          sprzPrognoza.add(s);
        }
      

      
      /*for ( NapSprzedazPrognozaVO sp : sprzPrognoza)
      {
         sp.setWyliczenia( listaSprzedazPrognozaWyl( sp.getId() ) );
      }*/
      
      
       return sprzPrognoza;
       
    } 
     
    
    public String wyliczSprawdzenie( String rok )
    {
      BigDecimal kwota1 = null;
      BigDecimal kwota2 = null;
      BigDecimal sprawdzenie = null;
      
      if ( !rok.equals("2016"))
      {
          rok = "2016";
      }
        
    /*  String quary = "select sum(kwota) from (\n" +
            "select sk, kwota_miesieczna*12 kwota from nap_sprzedaz_prognoza where id in (\n" +
            "select distinct id_umowa from nap_sprzedaz_prognoza_wyl nspw\n" +
            "                        where to_char(okres,'YYYY') = '" + rok + "'\n" +
            "                        and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa )))";  

      
      
      kwota1 =  (BigDecimal) em.createNativeQuery(quary).getSingleResult();
      
      String quary2 = "select sum(kwota) from nap_sprzedaz_prognoza_wyl where to_char(okres,'YYYY') = '" + rok + "' and id_umowa is not null";  

      
      
      kwota2 =  (BigDecimal) em.createNativeQuery(quary2).getSingleResult();*/

      
      //sprawdzenie = kwota1.subtract(kwota2);
      
       String quary = "select count(1) from nap_sprzedaz_prognoza_wyl where id_umowa is null";  

      
      
      sprawdzenie =  (BigDecimal) em.createNativeQuery(quary).getSingleResult();
      
      if ( sprawdzenie.intValue() != 0 )
      {
          return  "Odśwież wyl. planu !!!";
      }
      
      return  "";
       
    } 
    
    

    
   public List<NapSprzedazPrognozaVO> podajUmowyDlaRodzaju( int rok, String naDzien , String rodzaj)
   {
      List<Object[]> dane = null; 
      String quary = null; 
       
      List<NapSprzedazPrognozaVO> umowy = new ArrayList<NapSprzedazPrognozaVO>();
      
      if ( rodzaj.equals("kontrakty") )
      {
        quary = "select sk, data_zakonczenia, sum(kwota) kwota from (\n" +
            "select sk, data_zakonczenia, okres\n" +
            ", case when okres <= '" + naDzien + "' and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
            " else kwota end kwota\n" +
            " from nap_sprzedaz_prognoza_wyl nspw\n" +
            " where to_char(okres,'YYYY') = " + rok + " --and sk = 'Z224'\n" +
            " and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
            " group by sk, data_zakonczenia\n" +
            " order by 1";  
      }
      
      if ( rodzaj.equals("odnowienie") )
      {
        quary = "select sk, data_zakonczenia, sum(kwota) kwota from (\n" +
            "select sk, data_zakonczenia, okres\n" +
            ", case when data_zakonczenia < '" + naDzien + "' then 0\n" +
            " else kwota end kwota\n" +
            " from nap_sprzedaz_prognoza_wyl nspw\n" +
            " where to_char(okres,'YYYY') = " + rok + "\n" +
            " and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
            " group by sk, data_zakonczenia\n" +
            " order by 1";  
      }
        
      
        dane =   em.createNativeQuery(quary).getResultList();
      
        for ( Object[] sp : dane)
        {
          NapSprzedazPrognozaVO s = new NapSprzedazPrognozaVO();
          s.setSk((String) sp[0]);
          s.setDataZakonczenia((Date) sp[1]);
          s.setKwotaMiesieczna((BigDecimal) sp[2]);
          umowy.add(s);
        }
    
      
      return umowy;
       
    }   
   
   
   
   public List<NapSprzedazPrognozaWylVO> podajOkresyDlaUmowy( int rok, String naDzien , String sk)
   {
      List<Object[]> dane = null; 
      List<Object[]> dane2 = null; 
      String quary = null;
      String quary2 = null;
       
      List<NapSprzedazPrognozaWylVO> okresy = new ArrayList<NapSprzedazPrognozaWylVO>();
      
      
        quary = "select 'kontrakt' opis, sk, okres, kwota, rodzaj from (\n" +
            "select sk, data_zakonczenia, okres\n" +
            ", case when okres <= '" + naDzien + "' and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' then nvl(nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk), kwota)\n" +
            " else kwota end kwota\n" +
            ", case when okres <= '" + naDzien + "' and nap_kg_tools.CZY_OKRES_ZABLOKOWANY(to_char(okres,'YYYY-MM')) = 'ZAM' and nap_kg_tools.PODAJ_SPRZEDAZ_DLA_OBIEKTU_MC( to_char(okres,'MM-YYYY'), sk) is not null then 'E'\n" +
            " else 'P' end rodzaj\n" +    
            " from nap_sprzedaz_prognoza_wyl nspw\n" +
            " where to_char(okres,'YYYY') = " + rok + " and sk = '" + sk + "'\n" +
            " and okres <=  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
            " order by 3";  
      
        dane =   em.createNativeQuery(quary).getResultList();
     
        quary2 = "select 'odnowienie' opis, sk, okres, kwota, rodzaj from (\n" +
            "select sk, data_zakonczenia, okres\n" +
            ", case when data_zakonczenia < '" + naDzien + "' then 0\n" +
            " else kwota end kwota\n" +
            ", 'P' rodzaj\n" +    
            " from nap_sprzedaz_prognoza_wyl nspw\n" +
            " where to_char(okres,'YYYY') = " + rok + " and sk = '" + sk + "'\n" +
            " and okres >  ( select data_zakonczenia from nap_sprzedaz_prognoza where id = nspw.id_umowa ))\n" +
            " order by 3";  
      
        dane2 =   em.createNativeQuery(quary2).getResultList();
      
        
      
        for ( Object[] sp : dane)
        {
          NapSprzedazPrognozaWylVO s = new NapSprzedazPrognozaWylVO();
          s.setMiasto((String) sp[0]);
          s.setSk((String) sp[1]);
          s.setDataZakonczenia((Date) sp[2]);
          s.setKwota((BigDecimal) sp[3]);
          s.setObPelnyKod((String) sp[4]);
          okresy.add(s);
        }
        
        for ( Object[] sp : dane2)
        {
          NapSprzedazPrognozaWylVO s = new NapSprzedazPrognozaWylVO();
          s.setMiasto((String) sp[0]);
          s.setSk((String) sp[1]);
          s.setDataZakonczenia((Date) sp[2]);
          s.setKwota((BigDecimal) sp[3]);
          s.setObPelnyKod((String) sp[4]);
          okresy.add(s);
        }
    
      
      return okresy;
       
    } 
    
    public List<NapPodsumowanieKontraktowDTO> podajPodsumowanieKontraktow( String naDzien ){
     
    List<Object[]> dane = null;            
    String quary = null;
    List<NapPodsumowanieKontraktowDTO> podsumowanieKontraktow = new ArrayList<NapPodsumowanieKontraktowDTO>();
    
    quary = "select sp.sk, sp.ob_pelny_kod, sp.miasto, opis, kontrakt, sp.data_zakonczenia, sum(kwota), count(1) il_mc \n" +
            "from nap_sprzedaz_prognoza sp, nap_sprzedaz_prognoza_wyl spw\n" +
            "where sp.id = spw.ID_UMOWA\n" +
            "and okres <= sp.data_zakonczenia \n" +
            "and okres >= to_date(to_char(to_date('" + naDzien + "','YYYY-MM-DD'),'YYYY-MM'),'YYYY-MM')\n" +
            "group by sp.sk, sp.ob_pelny_kod, sp.miasto, opis, kontrakt, sp.data_zakonczenia";
    
    dane =   em.createNativeQuery(quary).getResultList();
    
        for ( Object[] sp : dane)
        {
          NapPodsumowanieKontraktowDTO s = new NapPodsumowanieKontraktowDTO();
          s.setSk((String) sp[0]);
          s.setObPelnyKod((String) sp[1]);
          s.setMiasto((String) sp[2]);
          s.setOpis((String) sp[3]);
          s.setKontrakt((String) sp[4]);
          s.setDataZakonczenia((Date) sp[5]);
          s.setKwotaMiesieczna((BigDecimal) sp[6]);
          s.setIlMiesiecy((BigDecimal) sp[7]);
          //s.set
          podsumowanieKontraktow.add(s);
        }
        
        return podsumowanieKontraktow;
    }
   
    
    
}
