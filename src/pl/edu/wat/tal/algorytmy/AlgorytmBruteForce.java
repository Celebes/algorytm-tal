package pl.edu.wat.tal.algorytmy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;
import pl.edu.wat.tal.helper.SetHelper;

/*
 * ALGORYTM DOKLADNY - oparty na brute force
 */

public class AlgorytmBruteForce {
	
	private Graf graf;
	private Set<Set<Wierzcholek>> wszystkiePodzbiory;

	public AlgorytmBruteForce(Graf graf) {
		this.graf = graf;
	}
	
	/*
	 * Zlozonosc pamieciowa 2^n - generuje wszystkie podzbiory w pamieci i przeszukuje je wszystkie
	 */
	public void computeZlozonePamieciowo() {
		// zbior wszystkich zbiorow z liczba cyklometryczna <= 0
		Set<Set<Wierzcholek>> acykliczne = new HashSet<Set<Wierzcholek>>();
		
		// tworzy liste wszystkich mozliwych podzbiorow wierzcholkow
		wszystkiePodzbiory = SetHelper.generujWszystkiePodzbiory(new HashSet<Wierzcholek>(graf.getWierzcholki()));
		
		// kazdy z podzbiorow usun z grafu i oblicz jego liczbe cyklomatyczna
		for(Set<Wierzcholek> s : wszystkiePodzbiory) {
			Graf temp = this.graf.klonuj();
			
			for(Wierzcholek w : s) {
				temp.removeWierzcholek(w.getNumer());
			}
			
			if(temp.getCyclomaticNumber() <= 0) {
				acykliczne.add(s);
			}
		}
		
		if(acykliczne.isEmpty()) {
			System.out.println("Dany graf nie posiada minimalnego zbioru rozcyklaj¹cego");
		}
		
		System.out.println("\n***\nLista wszystkich zbiorów z liczb¹ cyklomatyczn¹ <= 0:\n");
		
		for(Set<Wierzcholek> s : acykliczne) {

			System.out.println(s);

		}
		
		// znajdz najmniejsza liczbe elementow sposrod zbiorow dajacych acykliczny graf
		int min = 0;
		boolean firstTime = true;
		
		for(Set<Wierzcholek> s : acykliczne) {
			if(firstTime) {
				firstTime = false;
				min = s.size();
				continue;
			}
			
			if(s.size() < min) {
				min = s.size();
			}
		}
		
		// wypisz wszystkie minimalne zbiory acykliczne FVS
		
		System.out.println("\n***\nLista minimalnych zbiorów rozcyklaj¹cych [FVS]:\n");
		
		for(Set<Wierzcholek> s : acykliczne) {
			if(s.size() == min) {
				System.out.println(s);
			}
		}
		
		System.out.println();
		
	}
	
	
	/*
	 * Mala zlozonosc pamieciowa - kolejne podzbiory sa generowane na biezaco, sprawdzane sa najpierw 0-elementowe, pozniej 1-elementowe itp.
	 * Dzieki temu nie trzeba przeszukiwac bezsensownie duzych podzbiorow, gdy znalazlo sie juz minimalny zbior rozcyklajacy
	 */
	public void compute() {
		// zaczynamy od maski z samych zer, wiec po prostu pusty zbior
		// wielkosc maski = ilosc wierzcholkow, bo bedzie 2^n zbiorow
		boolean[] maska = new boolean[this.graf.getWierzcholki().size()];
		
		// warunek stopu to maska zawierajace same TRUE
		boolean[] warunekStopu = new boolean[this.graf.getWierzcholki().size()];
		Arrays.fill(warunekStopu, Boolean.TRUE);
				
		// zaczynamy od zbiorow 0-elementowych (pusty), pozniej wszystkich 1-elementowych itp..
		int liczbaElementowPodzbioru = 0;
		
		// zbior wszystkich zbiorow z liczba cyklometryczna <= 0
		Set<Set<Wierzcholek>> acykliczne;
		
		while(true) {
			// dla kazdej liczby elementow w podzbiorze tworzymy nowy zbior acyklicznych zbiorow
			acykliczne = new HashSet<Set<Wierzcholek>>();
			
			// warunek zwiekszenia liczby elementow w podzbiorze
			boolean[] warunekZwiekszeniaLiczbyElementowWPodzbiorze = generujWarunekZwiekszeniaLiczbyElementowWPodzbiorze(liczbaElementowPodzbioru);
			
			// dla kazdego k-elementowego podzbioru..
			while(true) {
				// pobierz wierzcholki na podstawie maski
				Set<Wierzcholek> wierzcholkiNaPodstawieMaski = pobierzZbiorWierzcholkowNaPodstawieMaski(maska);
				
				// oblicz liczbe cyklomatyczna
				int liczbaCyklomatyczna = obliczLiczbeCyklomatycznaBezWierzcholkow(wierzcholkiNaPodstawieMaski);
				
				// jesli graf sie staje acykliczny to zapamietaj zbior FVS
				if(liczbaCyklomatyczna <= 0) {
					acykliczne.add(wierzcholkiNaPodstawieMaski);
				}
				
				// wygeneruj nastepny podzbior
				maska = generujNastepnyPodzbior(maska);
				
				if(Arrays.equals(maska, warunekZwiekszeniaLiczbyElementowWPodzbiorze)) {
					break;
				}
			}
			
			// sprawdz czy mamy rozwiazanie
			if(acykliczne.size() > 0) {
				System.out.println("ZNALEZIONO ROZWIAZANIE W ALGORYTMIE BRUTE-FORCE");
				System.out.println("ROZMIAR ZBIORU [FVS] TO: " + liczbaElementowPodzbioru);
				
				System.out.println("\n***\nLISTA MINIMALNYCH ZBIOROW ROZCYKLAJACYCH [FVS]:\n");
				
				for(Set<Wierzcholek> s : acykliczne) {
					System.out.println(s);
				}
				
				//break;
			}
			
			if(Arrays.equals(maska, warunekStopu)) {
				break;
			}
			
			// zwieksz liczbe elementow w podzbiorze
			liczbaElementowPodzbioru++;
			
			// generuj nowa maske dla tylu elementow
			maska = generujMaskeDlaDanejLiczbyElementowPodzbioru(liczbaElementowPodzbioru);
		}
	}
	
	public boolean[] generujNastepnyPodzbior(boolean[] maska) {
		boolean[] wynik = Arrays.copyOf(maska, maska.length);
		
		// znajdz najblizszy TRUE poprzedzony przez co najmniej jeden FALSE
		int indeksPierwszegoTrue = -1;
		int falseCounter = 0;
		
		for(int i=0; i<maska.length; i++) {
			if(maska[i] == true) {
				if(falseCounter == 0) {
					continue;
				} else {
					indeksPierwszegoTrue = i;
					break;
				}
			} else {
				falseCounter++;
			}
		}
		
		// idac od indeksu pierwszego TRUE, ktory mozna przerzucic w lewo, znajdz indeks pierwszego FALSE, z ktorym sie zamieni miejscami
		int indeksPierwszegoFalseZLewej = -1;
		for(int i=indeksPierwszegoTrue; i >= 0; i--) {
			if(maska[i] == false) {
				indeksPierwszegoFalseZLewej = i;
				break;
			}
		}
		
		if(indeksPierwszegoFalseZLewej != (-1)) {
			wynik[indeksPierwszegoFalseZLewej] = true;
			wynik[indeksPierwszegoTrue] = false;
		}
		
		return wynik;
	}
	
	public boolean[] generujMaskeDlaDanejLiczbyElementowPodzbioru(int liczbaElementowPodzbioru) {
		boolean[] tablicaZwrotna = new boolean[this.graf.getWierzcholki().size()];
		
		if(liczbaElementowPodzbioru == 0) {
			return tablicaZwrotna;
		}
		
		for(int i=(tablicaZwrotna.length-1); i>=(tablicaZwrotna.length-liczbaElementowPodzbioru); i--) {
			tablicaZwrotna[i] = true;
		}
		
		return tablicaZwrotna;
	}
	
	public boolean[] generujWarunekZwiekszeniaLiczbyElementowWPodzbiorze(int liczbaElementowPodzbioru) {
		boolean[] tablicaZwrotna = new boolean[this.graf.getWierzcholki().size()];
		
		if(liczbaElementowPodzbioru == 0) {
			return tablicaZwrotna;
		}
		
		for(int i=0; i<liczbaElementowPodzbioru; i++) {
			tablicaZwrotna[i] = true;
		}
		
		return tablicaZwrotna;
	}

	public Set<Wierzcholek> pobierzZbiorWierzcholkowNaPodstawieMaski(boolean[] maska) {
		Set<Wierzcholek> zbiorZwrotny = new HashSet<Wierzcholek>();
		
		// najpierw sprawdzamy czy nie mamy do czynienia ze zbiorem pustym - jesli tak, zwracamy taki
		boolean[] sameFalse = new boolean[this.graf.getWierzcholki().size()];
		Arrays.fill(sameFalse, Boolean.FALSE);
		
		// jesli maska ma same false, to zwracamy zbior pusty
		if(Arrays.equals(maska, sameFalse)) {
			return zbiorZwrotny;
		}
		
		// jesli nie - zwracamy co trzeba
		int index;
		Wierzcholek[] wierzcholkiGrafu = new Wierzcholek[this.graf.getWierzcholki().size()];
		wierzcholkiGrafu = (Wierzcholek[]) this.graf.getWierzcholki().toArray(wierzcholkiGrafu);
		
		for(index=0; index<maska.length; index++) {
			if(maska[index] == true) {
				zbiorZwrotny.add(wierzcholkiGrafu[index]);
			}
		}
		
		return zbiorZwrotny;
	}
	
	public int obliczLiczbeCyklomatycznaBezWierzcholkow(Set<Wierzcholek> wierzcholkiDoWyrzucenia) {
		Graf temp = this.graf.klonuj();
		
		for(Wierzcholek w : wierzcholkiDoWyrzucenia) {
			temp.removeWierzcholek(w.getNumer());
		}
		
		return temp.getCyclomaticNumber();
	}

}
