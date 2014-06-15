package pl.edu.wat.tal.algorytmy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
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
	
	private boolean WYSWIETLAJ_WSZYSTKIE_PODZBIORY = false;
	private boolean wyswietlonoNajmniejszy = false;
	private Graf graf;
	private Set<Set<Wierzcholek>> wszystkiePodzbiory;
	
	private double minSumaWag = -1;
	private Set<Wierzcholek> optymalnyWagowoFVS = null;
	private int minFVS = -1;

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
		
		if(graf.getCyclomaticNumber() == 0) {
			System.out.println("GRAF JEST ACYKLICZNY!");
			return;
		}
		
		boolean znalezionoFVS = false;
		
		// zaczynamy od maski z samych zer, wiec po prostu pusty zbior
		// wielkosc maski = ilosc wierzcholkow, bo bedzie 2^n zbiorow
		boolean[] maska = new boolean[this.graf.getWierzcholki().size()];
				
		// zaczynamy od zbiorow 0-elementowych (pusty), pozniej wszystkich 1-elementowych itp..
		int liczbaElementowPodzbioru = 0;
		
		// zbior wszystkich zbiorow z liczba cyklometryczna <= 0
		Set<Set<Wierzcholek>> acykliczne;
		
		// maska z iloscia wierzcholkow w grafie
		int block_mask = element0(this.graf.getWierzcholki().size());
		
		// -----------
		// START POMIARÓW !!!!!
		// -----------
		
		// dla kazdej mozliwej liczby wierzcholkow w podzbiorze k, gdzie k = 0, 1, ... ilosc_wierzcholkow 
		for(int i=0; i<(this.graf.getWierzcholki().size()); i++) {
			// dla kazdej liczby elementow w podzbiorze tworzymy nowy zbior acyklicznych zbiorow
			acykliczne = new HashSet<Set<Wierzcholek>>();
			
			// inicjalna maska - sluzy do generowania kolejnych permutacji
			int subsetPermutationMask = element0(liczbaElementowPodzbioru);
			
			// ilosc podzbiorow istniejaca dla danego k
			long iloscMozliwychPodzbiorowKElementowych = obliczIloscMozliwychPodzbiorowKElementowych(liczbaElementowPodzbioru);
			
			// dla kazdego k-elementowego podzbioru, gdzie k = 0, 1, ... ilosc_wierzcholkow
			for(long j=0; j<iloscMozliwychPodzbiorowKElementowych; j++) {

				// oblicz maske na podstawie wygenerowanej permutacji. Metoda, ktora pozyskuje ustawione bity na podstawie subsetPermutationMask
				maska = generujNowaMaskeNaPodstawiePermutacji(subsetPermutationMask);
				
				// pobierz wierzcholki na podstawie maski
				Set<Wierzcholek> wierzcholkiNaPodstawieMaski = pobierzZbiorWierzcholkowNaPodstawieMaski(maska);
				
				// oblicz liczbe cyklomatyczna
				int liczbaCyklomatyczna = obliczLiczbeCyklomatycznaBezWierzcholkow(wierzcholkiNaPodstawieMaski);
				
				// jesli graf sie staje acykliczny to zapamietaj zbior FVS
				if(liczbaCyklomatyczna <= 0) {
					acykliczne.add(wierzcholkiNaPodstawieMaski);
				}
				
				// dla 0 istnieje tylko zbior pusty, wiec nie ma potrzeby generowania jego permutacji
				if(subsetPermutationMask == 0) {
					break;
				}
				
				// wygeneruj nastepny podzbior
				subsetPermutationMask = nextPerm(subsetPermutationMask) & block_mask;
			}
			
			// sprawdz czy mamy rozwiazanie
			if(acykliczne.size() > 0) {			
				
				for(Set<Wierzcholek> s : acykliczne) {
					obliczSumeWag(s);
				}
				
				if(minFVS == (-1)) {
					minFVS = liczbaElementowPodzbioru;
				}
				
				znalezionoFVS = true;
			}			
			
			// zwieksz liczbe elementow w podzbiorze
			liczbaElementowPodzbioru++;
			
			// generuj nowa maske dla tylu elementow
			maska = generujMaskeDlaDanejLiczbyElementowPodzbioru(liczbaElementowPodzbioru);
		}
		
		// -----------
		// KONIEC POMIARÓW !!!!!
		// -----------
		
		System.out.println();
		System.out.println("ZNALEZIONO ROZWIAZANIE W ALGORYTMIE BRUTE-FORCE");
		
		if(!znalezionoFVS) {
			System.out.println("Zbiór [FVS] pokrywa siê ze wszystkimi " + liczbaElementowPodzbioru + " wierzcho³kami grafu i jest nastêpuj¹cy: ");
			System.out.println(this.graf.getWierzcholki());
			obliczSumeWag(new HashSet<Wierzcholek>(this.graf.getWierzcholki()));
		} else {
			System.out.println("ROZMIAR OPTYMALNEGO POD WZGLÊDEM MINIMALNEJ SUMY WAG [FVS] TO: " + optymalnyWagowoFVS.size());
			System.out.println("OPTYMALNY POD WZGLÊDEM MINIMALNEJ SUMY WAG [FVS] TO:");
			System.out.println(optymalnyWagowoFVS);
			System.out.println("SUMA JEGO WAG WYNOSI: " + minSumaWag);
		}		
	}
	
	public void obliczSumeWag(Set<Wierzcholek> s) {
		int suma = 0;
		for(Wierzcholek w : s) {
			suma += w.getWaga();
		}
		
		if(minSumaWag == (-1)) {
			minSumaWag = suma;
			optymalnyWagowoFVS = s;
		} else {
			if(suma < minSumaWag) {
				minSumaWag = suma;
				optymalnyWagowoFVS = s;
			}
		}
	}

	public long obliczIloscMozliwychPodzbiorowKElementowych(int liczbaElementowPodzbioru) {
		int iloscZer = this.graf.getWierzcholki().size() - liczbaElementowPodzbioru;
		return obliczPermutacjeBezPowtorzen(iloscZer, liczbaElementowPodzbioru);
	}
	
	public long obliczPermutacjeBezPowtorzen(int zero, int jeden) {
		return silnia(zero + jeden) / (silnia(zero) * silnia(jeden));
	}
	
	public long silnia(int x) {
		BigInteger result = BigInteger.ONE;
		BigInteger n = BigInteger.valueOf((long)x);
		
		while (!n.equals(BigInteger.ZERO)) {
	        result = result.multiply(n);
	        n = n.subtract(BigInteger.ONE);
	    }
		
		return result.longValue();
	}

	public boolean[] generujNowaMaskeNaPodstawiePermutacji(int subsetPermutationMask) {
		boolean[] wynik = new boolean[this.graf.getWierzcholki().size()];
		BitSet bs = fromInt(subsetPermutationMask);
		
		for(int i=bs.nextSetBit(0); i>=0; i=bs.nextSetBit(i+1)) {
			wynik[i] = true;
		}
		
		return wynik;
	}

	public static int nextPerm(int v) {
		int t = (v | (v-1)) + 1;
		int w = t | ((((t & -t) / (v & -v)) >> 1) - 1);
		return w;
	}
	
	public static int element0(int c) {
		return (1 << c) - 1;
	}
	
	public BitSet fromInt(int num) {
	    BitSet bs = new BitSet();
	    for (int k = 0; k < Integer.SIZE; k++) {
	        if (((num >> k) & 1) == 1) {
	            bs.set(k);
	        }
	    }
	    return bs;
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
