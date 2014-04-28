package pl.edu.wat.tal.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Krawedz;
import pl.edu.wat.tal.graf.Wierzcholek;

/*
 * Generuje graf o zadanych parametrach
 */

public class GrafGenerator {

	Random rand = new Random();
	
	/*
	 * Minimalna liczba wierzcholkow (n): 3
	 * Min l. spojnych skladowych: 1
	 * Max l. spojnych skladowych: (n - 2)
	 */
	
	public Graf generujGrafCykliczny(int liczbaWierzcholkow, int iloscSpojnychSkladowych, boolean wagowy) {
		
		if(liczbaWierzcholkow < 3) {
			System.out.println("Do utworzenia grafu cyklicznego potrzebne sa minimum 3 wierzcholki!");
			return null;
		}
		
		if(iloscSpojnychSkladowych > liczbaWierzcholkow - 2) {
			System.out.println("Do utworzenia grafu cyklicznego z " + liczbaWierzcholkow + " potrzeba maksymalnie " + (liczbaWierzcholkow - 2) + " spojnych skladowych!");
			return null;
		}
		
		if(iloscSpojnychSkladowych > liczbaWierzcholkow) {
			System.out.println("Ilosc spojnych skladowych nie moze byc wieksza od ilosci wierzcholkow!");
			return null;
		}
		
		Graf g = new Graf();
		
		// utworz wierzcholki
		for(int i=1; i<=liczbaWierzcholkow; i++) {
			Wierzcholek w = new Wierzcholek(i, String.valueOf(i));
			g.addWierzcholek(w);
		}
		
		System.out.println("Utworzono wierzcholki: " + g.getWierzcholki());
		
		// pogrupuj wierzcholki wedlug spojnych skladowych
		List<List<Wierzcholek>> listaListWierzcholkowDlaSpojnychSkladowych = new ArrayList<List<Wierzcholek>>();
		
		if(iloscSpojnychSkladowych > 1) {
			// wylosuj ktore wierzcholki beda w ktorej spojnej skladowej i podziel je na podzbiory
			List<Integer> indeksy = g.pickRandom(liczbaWierzcholkow, liczbaWierzcholkow);
			
			System.out.println("Wylosowano nastepujace indeksy: " + indeksy);
			
			// wylosuj ktore zakresy wierzcholkow beda nalezaly do spojnej skladowej
			// np. jesli mamy 6 wierzcholkow o indeksach: 0,1,2,3,4,5 i chcemy meic 2 spojne skladowe, to losujemy punkt podzialu, np. 3, wtedy mamy dwie spojne skladowe: 0,1,2,3 oraz 4,5
			// linia podzialu np. = 0 oznacza, ze zawieraja sie w niej indeksy od 0 do 0. Linia podzialu = 2 oznacza, ze indeksy 0,1,2 itp.
			List<Integer> liniePodzialu = new ArrayList<Integer>();
			
			// aby graf byl cykliczny to zawsze trzeba dac w jednej spojnej minimum 3 wierzcholki
			// wiec generujemy wartosc od 3 do (iloscWierzcholkow - iloscSpojnychSkladowych + 1)
			int minPierwszyPodzial = 2;
			int maxPierwszyPodzial = liczbaWierzcholkow - iloscSpojnychSkladowych + 1;
			
			int pierwszyPodzial = minPierwszyPodzial;
			
			if(maxPierwszyPodzial > minPierwszyPodzial) {
				pierwszyPodzial = rand.nextInt(maxPierwszyPodzial - minPierwszyPodzial) + minPierwszyPodzial;
			}
			
			liniePodzialu.add(pierwszyPodzial);
			
			// nastepnie losujemy od pierwszegoPodzialu do maxa
			List<Integer> temp = g.pickRandomBetween(iloscSpojnychSkladowych-2, pierwszyPodzial+1, liczbaWierzcholkow-1);
			Collections.sort(temp);
			
			for(Integer i : temp) {
				liniePodzialu.add(i);
			}
			
			System.out.println("Wylosowano nastepujace linie podzialu: " + liniePodzialu);
			
			// stosujemy podzial
			Integer[] indeksyJakoTablica = new Integer[indeksy.size()];
			indeksyJakoTablica = indeksy.toArray(indeksyJakoTablica);
			
			List<Wierzcholek> spojnaSkladowa;
			
			int dolnaGranica = 0;
			int gornaGranica = liniePodzialu.get(0);
			int licznik = 0;
			
			for(int i=0; i<iloscSpojnychSkladowych; i++) {
				spojnaSkladowa = new ArrayList<Wierzcholek>();
				
				for(int j=dolnaGranica; j<=gornaGranica; j++) {
					spojnaSkladowa.add(g.getWierzcholki().get(indeksyJakoTablica[j]));
				}
				
				listaListWierzcholkowDlaSpojnychSkladowych.add(spojnaSkladowa);
				System.out.println("Utworzono spojna skladowa: " + spojnaSkladowa);
				
				licznik++;
				
				dolnaGranica = gornaGranica + 1;
				
				System.out.println("licznik = " + licznik + " | " + liniePodzialu.size());
				
				if(licznik >= liniePodzialu.size()) {
					gornaGranica = liczbaWierzcholkow-1;
				} else {
					gornaGranica = liniePodzialu.get(licznik);
				}
			}

		} else {
			listaListWierzcholkowDlaSpojnychSkladowych.add(new ArrayList<Wierzcholek>(g.getWierzcholki()));
		}
		
		// utworz krawedzie
		//generujKrawedzie(g, g.getWierzcholki(), liczbaKrawedzi, iloscSpojnychSkladowych);
		
		System.out.println("Wygenerowano graf: " + g);
		
		return g;
	}
	
	public void generujKrawedzie(Graf g, List<Wierzcholek> wierzcholki, int liczbaKrawedzi, int iloscSpojnychSkladowych) {
		
		List<Wierzcholek> S = null;
		Set<Wierzcholek> T = new HashSet<Wierzcholek>();
		
		
		
		// najpierw utworz drogi pomiedzy wierzcholkami
		Wierzcholek aktualnyWierzcholek = S.get(rand.nextInt(S.size()));
		S.remove(aktualnyWierzcholek);
		T.add(aktualnyWierzcholek);
		
		while(!S.isEmpty()) {
			
			if(g.getKrawedzie().size() == liczbaKrawedzi) {
				break;
			}
			
			Wierzcholek nowySasiad = S.get(rand.nextInt(S.size()));
			
			if(!T.contains(nowySasiad)) {
				Krawedz k = new Krawedz(aktualnyWierzcholek, nowySasiad);
				g.addKrawedz(k);
				aktualnyWierzcholek = nowySasiad;
				S.remove(aktualnyWierzcholek);
				T.add(aktualnyWierzcholek);
			}
			
		}
		
		// nastepnie losuj pary wierzcholkow i tworz miedzy nimi krawedz, jesli juz nie istnieje
		
		while(g.getKrawedzie().size() < liczbaKrawedzi) {
			// losuj wierzcholek A
			
			// losuj wierzcholek B nalezacy do tej samej spojnej skladowej
			
			// sprawdz czy krawedz miedzy nimi istnieje - jesli nie, to utworz ja
			
			// dodaj krawedz do grafu
			break;
		}
	}
}
