package pl.edu.wat.tal.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Krawedz;
import pl.edu.wat.tal.graf.Wierzcholek;

/*
 * Generuje graf o zadanych parametrach
 */

public class GrafGenerator
{

    Random rand = new Random();

    /*
     * Minimalna liczba wierzcholkow (n): 3
     * Min l. spojnych skladowych: 1
     * Max l. spojnych skladowych: (n - 2)
     */

    public Graf generujGrafCykliczny( int liczbaWierzcholkow, int iloscSpojnychSkladowych, boolean wagowy,
            boolean wagiRowneJeden )
    {

        if ( liczbaWierzcholkow < 3 )
        {
            System.out.println( "Do utworzenia grafu cyklicznego potrzebne sa minimum 3 wierzcholki!" );
            return null;
        }

        if ( iloscSpojnychSkladowych > liczbaWierzcholkow - 2 )
        {
            System.out.println( "Do utworzenia grafu cyklicznego z " + liczbaWierzcholkow + " potrzeba maksymalnie "
                                + ( liczbaWierzcholkow - 2 ) + " spojnych skladowych!" );
            return null;
        }

        if ( iloscSpojnychSkladowych > liczbaWierzcholkow )
        {
            System.out.println( "Ilosc spojnych skladowych nie moze byc wieksza od ilosci wierzcholkow!" );
            return null;
        }

        System.out.println( "ROZPOCZETO GENEROWANIE GRAFU O " + liczbaWierzcholkow + " WIERZCHOLKACH I "
                            + iloscSpojnychSkladowych + " SPOJNYCH SKLADOWYCH\n" );

        Graf g = new Graf();

        // utworz wierzcholki
        for ( int i = 1; i <= liczbaWierzcholkow; i++ )
        {
            Wierzcholek w = new Wierzcholek( i, String.valueOf( i ) );
            g.addWierzcholek( w );
        }

        // pogrupuj wierzcholki wedlug spojnych skladowych
        List<List<Wierzcholek>> listaListWierzcholkowDlaSpojnychSkladowych = new ArrayList<List<Wierzcholek>>();

        if ( iloscSpojnychSkladowych > 1 )
        {
            // wylosuj ktore wierzcholki beda w ktorej spojnej skladowej i
            // podziel je na podzbiory
            List<Integer> indeksy = g.pickRandom( liczbaWierzcholkow, liczbaWierzcholkow );

            // wylosuj ktore zakresy wierzcholkow beda nalezaly do spojnej
            // skladowej
            // np. jesli mamy 6 wierzcholkow o indeksach: 0,1,2,3,4,5 i chcemy
            // meic 2 spojne skladowe, to losujemy punkt podzialu, np. 3, wtedy
            // mamy dwie spojne skladowe: 0,1,2,3 oraz 4,5
            // linia podzialu np. = 0 oznacza, ze zawieraja sie w niej indeksy
            // od 0 do 0. Linia podzialu = 2 oznacza, ze indeksy 0,1,2 itp.
            List<Integer> liniePodzialu = new ArrayList<Integer>();

            // aby graf byl cykliczny to zawsze trzeba dac w jednej spojnej
            // minimum 3 wierzcholki
            // wiec generujemy wartosc od 3 do (iloscWierzcholkow -
            // iloscSpojnychSkladowych + 1)
            int minPierwszyPodzial = 2;
            int maxPierwszyPodzial = liczbaWierzcholkow - iloscSpojnychSkladowych + 1;

            int pierwszyPodzial = minPierwszyPodzial;

            if ( maxPierwszyPodzial > minPierwszyPodzial )
            {
                pierwszyPodzial = rand.nextInt( maxPierwszyPodzial - minPierwszyPodzial ) + minPierwszyPodzial;
            }

            liniePodzialu.add( pierwszyPodzial );

            // nastepnie losujemy od pierwszegoPodzialu do maxa
            List<Integer> temp = g.pickRandomBetween( iloscSpojnychSkladowych - 2, pierwszyPodzial + 1,
                    liczbaWierzcholkow - 1 );
            Collections.sort( temp );

            for ( Integer i : temp )
            {
                liniePodzialu.add( i );
            }

            // stosujemy podzial
            Integer[] indeksyJakoTablica = new Integer[indeksy.size()];
            indeksyJakoTablica = indeksy.toArray( indeksyJakoTablica );

            List<Wierzcholek> spojnaSkladowa;

            int dolnaGranica = 0;
            int gornaGranica = liniePodzialu.get( 0 );
            int licznik = 0;

            for ( int i = 0; i < iloscSpojnychSkladowych; i++ )
            {
                spojnaSkladowa = new ArrayList<Wierzcholek>();

                for ( int j = dolnaGranica; j <= gornaGranica; j++ )
                {
                    spojnaSkladowa.add( g.getWierzcholki().get( indeksyJakoTablica[j] ) );
                }

                listaListWierzcholkowDlaSpojnychSkladowych.add( spojnaSkladowa );

                licznik++;

                System.out.println( "UTWORZONO SPOJNA SKLADOWA NUMER " + licznik + ": " + spojnaSkladowa );

                dolnaGranica = gornaGranica + 1;

                if ( licznik >= liniePodzialu.size() )
                {
                    gornaGranica = liczbaWierzcholkow - 1;
                }
                else
                {
                    gornaGranica = liniePodzialu.get( licznik );
                }
            }

        }
        else
        {
            listaListWierzcholkowDlaSpojnychSkladowych.add( new ArrayList<Wierzcholek>( g.getWierzcholki() ) );
        }

        // utworz krawedzie

        // minimalna liczba krawedzi to suma ilosci wierzcholkow - 1 w
        // poszczegolnych spojnych skladowych i calosc powiekszona o 1 zeby
        // zapewnic chociaz 1 cykl
        int minimalnaLiczbaKrawedzi = 1;

        // maksymalna liczba krawedzi to suma max krawedzi w poszczegolnych
        // spojnych skladowych, liczona ze wzoru n*(n-1)/2, gdzie n to liczba
        // wierzcholkow
        int maksymalnaLiczbaKrawedzi = 0;

        for ( List<Wierzcholek> tempList : listaListWierzcholkowDlaSpojnychSkladowych )
        {
            minimalnaLiczbaKrawedzi += ( tempList.size() - 1 );
            maksymalnaLiczbaKrawedzi += ( tempList.size() * ( tempList.size() - 1 ) ) / 2;
        }

        // ------- GESTOSC GRAFU -----------

        // okresl minimalna gestosc
        double minimalnaGestosc = minimalnaLiczbaKrawedzi * 1.0 / maksymalnaLiczbaKrawedzi;

        // wylosuj na jej podstawie liczbe krawedzi

        int wylosowanaLiczbaKrawedzi;

        if ( minimalnaGestosc > CommonVariables.getInstance().GESTOSC_GRAFU )
        {
            wylosowanaLiczbaKrawedzi = minimalnaLiczbaKrawedzi;
        }
        else
        {
            wylosowanaLiczbaKrawedzi = (int) Math.floor( CommonVariables.getInstance().GESTOSC_GRAFU
                                                         * maksymalnaLiczbaKrawedzi );
        }

        /*if(maksymalnaLiczbaKrawedzi > minimalnaLiczbaKrawedzi) {
        	wylosowanaLiczbaKrawedzi = rand.nextInt(maksymalnaLiczbaKrawedzi - minimalnaLiczbaKrawedzi) + minimalnaLiczbaKrawedzi;
        }*/

        // utworz podstawowe drogi w grafie, tzn polacz ze soba w droge
        // wszystkie wierzcholki we wszystkich spojnych skladowych
        for ( List<Wierzcholek> tempList : listaListWierzcholkowDlaSpojnychSkladowych )
        {
            generujDroge( g, tempList );
        }

        // teraz mozna wyrzucic wszystkie listy wierzcholkow dla spojnych
        // skladowych, ktore maja 1 lub 2 wierzcholki - nie beda juz potrzebne,
        // bo nie moga utworzyc cyklu
        Iterator<List<Wierzcholek>> iterator = listaListWierzcholkowDlaSpojnychSkladowych.iterator();

        while ( iterator.hasNext() )
        {
            List<Wierzcholek> lista = iterator.next();

            if ( lista.size() < 3 )
            {
                iterator.remove();
            }
        }

        // utworz liste ilosci krawedzi dla kazdej spojnej skladowej
        List<Integer> iloscKrawedziDlaSpojnejSkladowej = new ArrayList<Integer>();
        int it = 0;

        for ( List<Wierzcholek> lista : listaListWierzcholkowDlaSpojnychSkladowych )
        {
            iloscKrawedziDlaSpojnejSkladowej.add( lista.size() - 1 );
            it++;
        }

        // nastepnie losuj pary wierzcholkow i tworz miedzy nimi krawedz, jesli
        // juz nie istnieje
        while ( g.getKrawedzie().size() < wylosowanaLiczbaKrawedzi )
        {
            // losuj spojna skladowa
            int wylosowanyIndeks = rand.nextInt( listaListWierzcholkowDlaSpojnychSkladowych.size() );
            List<Wierzcholek> lista = listaListWierzcholkowDlaSpojnychSkladowych.get( wylosowanyIndeks );

            // sprawdz czy mozna do niej jeszcze dodac krawedz
            int maxLiczbaKrawedziDlaDanejSpojnejSkladowej = ( lista.size() * ( lista.size() - 1 ) ) / 2;

            if ( iloscKrawedziDlaSpojnejSkladowej.get( wylosowanyIndeks ) == maxLiczbaKrawedziDlaDanejSpojnejSkladowej )
            {
                continue;
            }

            // losuj wierzcholek A
            Wierzcholek a = lista.get( rand.nextInt( lista.size() ) );

            // losuj wierzcholek B nalezacy do tej samej spojnej skladowej
            Wierzcholek b = lista.get( rand.nextInt( lista.size() ) );

            while ( true )
            {

                // upewnij sie, ze sa to rozne wierzcholki
                if ( a.equals( b ) )
                {
                    b = lista.get( rand.nextInt( lista.size() ) );
                    continue;
                }

                Krawedz tempK = new Krawedz( a, b );
                boolean powtorka = false;

                // jesli istnieje krawedz miedzy a i b, wylosuj nowe b i a
                for ( Krawedz k : g.getKrawedzie() )
                {
                    if ( tempK.equals( k ) )
                    {
                        powtorka = true;
                    }
                }

                if ( powtorka )
                {
                    break;
                }

                // dodaj krawedz do grafu
                g.addKrawedz( tempK );

                iloscKrawedziDlaSpojnejSkladowej.set( wylosowanyIndeks,
                        iloscKrawedziDlaSpojnejSkladowej.get( wylosowanyIndeks ) + 1 );

                break;
            }
        }

        if ( wagowy )
        {
            if ( wagiRowneJeden )
            {
                g.przyporzadkujWagiJeden();
            }
            else
            {
                g.przyporzadkujLosoweWagi();
            }
        }

        System.out.println( "\nWygenerowano graf: " + g );

        System.out.println( "\n*****************************************************" );

        return g;
    }

    public void generujDroge( Graf g, List<Wierzcholek> wierzcholki )
    {
        List<Wierzcholek> S = new ArrayList<Wierzcholek>( wierzcholki );
        Set<Wierzcholek> T = new HashSet<Wierzcholek>();

        // najpierw utworz drogi pomiedzy wierzcholkami
        Wierzcholek aktualnyWierzcholek = S.get( rand.nextInt( S.size() ) );
        S.remove( aktualnyWierzcholek );
        T.add( aktualnyWierzcholek );

        while ( !S.isEmpty() )
        {
            Wierzcholek nowySasiad = S.get( rand.nextInt( S.size() ) );

            if ( !T.contains( nowySasiad ) )
            {
                Krawedz k = new Krawedz( aktualnyWierzcholek, nowySasiad );
                g.addKrawedz( k );
                aktualnyWierzcholek = nowySasiad;
                S.remove( aktualnyWierzcholek );
                T.add( aktualnyWierzcholek );
            }

        }

    }
}
