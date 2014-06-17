package pl.edu.wat.tal.algorytmy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;
import pl.edu.wat.tal.helper.CommonVariables;
import pl.edu.wat.tal.helper.SetHelper;
import pl.edu.wat.tal.helper.StatisticsAlgorithmsHelper;

/*
 * ALGORYTM APROKSYMACYJNY - oparty na warstwach
 */

public class AlgorytmLayering
{

    private Graf graf;
    private Set<Wierzcholek> fvs;

    private double minSumaWag = 0;
    private StringBuilder results;
    private StatisticsAlgorithmsHelper statisHelper;

    public AlgorytmLayering( Graf graf )
    {
        this.graf = graf;
        statisHelper = new StatisticsAlgorithmsHelper( CommonVariables.ALGORITHM_LAYERING );
    }

    public StringBuilder compute()
    {
        results = new StringBuilder();

        
        if ( !graf.isWagowy() )
        {
            //results.append( "Algorytm warstwowy dzia³a jedynie dla grafów posiadaj¹cych wagi na wierzcho³kach!\n" );
            System.out.println( "Algorytm warstwowy dzia³a jedynie dla grafów posiadaj¹cych wagi na wierzcho³kach!" );
            return results;
        }

        if ( graf.getCyclomaticNumber() == 0 )
        {
            //results.append( "GRAF JEST ACYKLICZNY!\n" );
            System.out.println( "GRAF JEST ACYKLICZNY!" );
            return results;
        }

        // utworz zbior wierzcholkow ktory bedzie przechowywal FVS
        fvs = new HashSet<Wierzcholek>();

        // utworz kopie grafu
        Graf grafH = this.graf.klonuj();

        // -----------
        // START POMIARÓW !!!!!
        // -----------
        statisHelper.startCalculateComplexity();
        // powtarzaj dopoki graf jest cykliczny
        
        // przechowujemy kolejne zbiory F
        //Set<Set<Wierzcholek>> Fwszystkie = new HashSet<Set<Wierzcholek>>();
        
        Map<Integer, List<Wierzcholek>> Fwszystkie = new HashMap<>();
        int aktualnaIteracja = 0;
        
        while ( grafH.getCyclomaticNumber() != 0 )
        {

            // znajdz wszystkie wierzcholki, po ktorych usunieciu liczba
            // cyklomatyczna grafu sie nie zmieni - usun je
            usunWierzcholkiKtoreNieZmieniajaLiczbyCyklomatycznej( grafH );

            // oblicz c, czyli minimum z
            // (w(v)/spadek_cyklomatycznej_po_usunieciu_v) dla wszystkich
            // wierzcholkow
            double c = obliczC( grafH );

            if ( c == ( -1 ) )
            {
                System.out.println( "Cos poszlo nie tak podczas wyliczania C!" );
                return results;
            }

            // obliczamy nowe wagi bazujac na c
            obliczNoweWagiDlaPodgrafu( grafH, c );

            // pobierz wierzcholki z wagami rownymi 0
            List<Wierzcholek> F = pobierzZeroweWagi( grafH );
            
            Fwszystkie.put(aktualnaIteracja, F);
            
            aktualnaIteracja++;
            
            // usun wierzcholki z wagami rownymi 0
            usunZeroweWagi(grafH);
        }
        
        // rozwijamy..
        // int k = aktualnaIteracja;
        aktualnaIteracja--;
        
        for(int k = aktualnaIteracja; k>=0; k--) {
        	// dodajemy od tylu uzyskane F, jesli zaden ich podzbior nie powoduje acyklicznosci, to dodajemy w calosci
        	
        	List<Wierzcholek> Faktualne = Fwszystkie.get(Integer.valueOf(k));
        	
        	Set<Set<Wierzcholek>> acykliczne = new HashSet<Set<Wierzcholek>>();
            Set<Set<Wierzcholek>> wszystkiePodzbiory = SetHelper.generujWszystkiePodzbiory(new HashSet<Wierzcholek>(Faktualne));
            
            for ( Set<Wierzcholek> s : wszystkiePodzbiory )
            {
                Graf temp = this.graf.klonuj();

                for ( Wierzcholek w : s )
                {
                    temp.removeWierzcholek( w.getNumer() );
                }

                if ( temp.getCyclomaticNumber() <= 0 )
                {
                    acykliczne.add( s );
                }
            }
            
            int min = Integer.MAX_VALUE;
            
            if(acykliczne.isEmpty()) {
            	fvs.addAll(Faktualne);
            	continue;
            }

            for ( Set<Wierzcholek> s : acykliczne )
            {
                if ( s.size() < min )
                {
                    min = s.size();
                }
            }
            
            for ( Set<Wierzcholek> s : acykliczne )
            {
                if ( s.size() == min )
                {
                    fvs.addAll(s);
                    break;
                }
            }
        }
        
        // -----------
        // KONIEC POMIARÓW !!!!!
        // -----------
        statisHelper.stopCalculateComplexity();
        

        // oblicz sume wag fvs
        for ( Wierzcholek w : fvs )
        {
            for ( Wierzcholek w2 : graf.getWierzcholki() )
            {
                if ( w.getNumer() == w2.getNumer() )
                {
                    w.setWaga( w2.getWaga() );
                    minSumaWag += w2.getWaga();
                    break;
                }
            }
        }

        /*results.append( "\nZNALEZIONO ROZWIAZANIE W ALGORYTMIE WARSTWOWYM" + "\n" );
        results.append( "MINIMALNY ZBIÓR ROZCYKLAJ¥CY = " + fvs + "\n" );
        results.append( "ROZMIAR ZBIORU [FVS] TO: " + fvs.size() + "\n" );
        results.append( "SUMA WAG JEGO WIERZCHOLKOW = " + minSumaWag + "\n" );*/
        results.append("			Wynik Layering: " + fvs + ", suma wag: [" + minSumaWag + "]\n" );
        System.out.println( "\nZNALEZIONO ROZWIAZANIE W ALGORYTMIE WARSTWOWYM" );
        System.out.println( "MINIMALNY ZBIÓR ROZCYKLAJ¥CY = " + fvs );
        System.out.println( "ROZMIAR ZBIORU [FVS] TO: " + fvs.size() );
        System.out.println( "SUMA WAG JEGO WIERZCHOLKOW = " + minSumaWag );

        return results;
    }

    private List<Wierzcholek> pobierzZeroweWagi( Graf grafH )
    {
        List<Wierzcholek> listaWierzcholkowDoUsuniecia = new LinkedList<Wierzcholek>();

        for ( Wierzcholek w : grafH.getWierzcholki() )
        {
            if ( Math.abs( w.getWaga() ) < 0.0001 )
            {
                listaWierzcholkowDoUsuniecia.add( w );
                //fvs.add( w );
            }
        }

        /*for ( Wierzcholek w : listaWierzcholkowDoUsuniecia )
        {
            grafH.removeWierzcholek( w.getNumer() );
        }*/
        
        return listaWierzcholkowDoUsuniecia;
    }
    
    private void usunZeroweWagi( Graf grafH )
    {
        List<Wierzcholek> listaWierzcholkowDoUsuniecia = new LinkedList<Wierzcholek>();

        for ( Wierzcholek w : grafH.getWierzcholki() )
        {
            if ( Math.abs( w.getWaga() ) < 0.0001 )
            {
                listaWierzcholkowDoUsuniecia.add( w );
            }
        }

        for ( Wierzcholek w : listaWierzcholkowDoUsuniecia )
        {
            grafH.removeWierzcholek( w.getNumer() );
        }
    }

    public void obliczNoweWagiDlaPodgrafu( Graf grafH, double c )
    {

        for ( Wierzcholek w : grafH.getWierzcholki() )
        {
            double nowaWaga = 0.0;

            // oblicz t = c * spadek_cyklomatycznej_po_usunieciu_v, czyli
            // 'largest cyclomatic weight function in w'
            double t = c * grafH.getCyclomaticNumberDecreaseAfterRemovingVertex( w.getNumer() );
            nowaWaga = w.getWaga() - t;

            w.setWaga( nowaWaga );
        }
    }

    public double obliczC( Graf grafH )
    {
        boolean pierwszyRaz = true;
        double min = -1;

        for ( Wierzcholek w : grafH.getWierzcholki() )
        {

            double wartoscDlaWierzcholka = ( w.getWaga() / grafH.getCyclomaticNumberDecreaseAfterRemovingVertex( w.getNumer() ) );

            if ( pierwszyRaz )
            {
                min = wartoscDlaWierzcholka;
                pierwszyRaz = false;
            }
            else
            {
                if ( wartoscDlaWierzcholka < min )
                {
                    min = wartoscDlaWierzcholka;
                }
            }
        }

        return min;
    }

    public void usunWierzcholkiKtoreNieZmieniajaLiczbyCyklomatycznej( Graf grafH )
    {
        List<Wierzcholek> listaWierzcholkowDoUsuniecia = new LinkedList<Wierzcholek>();

        for ( Wierzcholek w : grafH.getWierzcholki() )
        {
            if ( grafH.getCyclomaticNumberDecreaseAfterRemovingVertex( w.getNumer() ) == 0 )
            {
                listaWierzcholkowDoUsuniecia.add( w );
            }
        }

        for ( Wierzcholek w : listaWierzcholkowDoUsuniecia )
        {
            grafH.removeWierzcholek( w.getNumer() );
        }
    }
    
    public Long getCalculateComplexityWrapper()
    {

        return statisHelper.showResult();
    }

}
