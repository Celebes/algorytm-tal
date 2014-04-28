package pl.edu.wat.tal.algorytmy;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;

/*
 * ALGORYTM APROKSYMACYJNY - oparty na warstwach
 */

public class AlgorytmLayering {
	
	private Graf graf;
	private Set<Wierzcholek> fvs;
	
	private double minSumaWag = 0;

	public AlgorytmLayering(Graf graf) {
		this.graf = graf;
	}
	
	public void compute() {
		
		if(!graf.isWagowy()) {
			System.out.println("Algorytm warstwowy dzia³a jedynie dla grafów posiadaj¹cych wagi na wierzcho³kach!");
			return;
		}
		
		if(graf.getCyclomaticNumber() == 0) {
			System.out.println("GRAF JEST ACYKLICZNY!");
			return;
		}
		
		// utworz zbior wierzcholkow ktory bedzie przechowywal FVS
		fvs = new HashSet<Wierzcholek>();
		
		// utworz kopie grafu
		Graf grafH = this.graf.klonuj();
		
		// powtarzaj dopoki graf jest cykliczny
		while(grafH.getCyclomaticNumber() != 0) {
			
			// znajdz wszystkie wierzcholki, po ktorych usunieciu liczba cyklomatyczna grafu sie nie zmieni - usun je
			usunWierzcholkiKtoreNieZmieniajaLiczbyCyklomatycznej(grafH);
			
			// oblicz c, czyli minimum z (w(v)/spadek_cyklomatycznej_po_usunieciu_v) dla wszystkich wierzcholkow
			double c = obliczC(grafH);
			
			if(c == (-1)) {
				System.out.println("Cos poszlo nie tak podczas wyliczania C!");
				return;
			}
			
			// obliczamy nowe wagi bazujac na c
			obliczNoweWagiDlaPodgrafu(grafH, c);
			
			// usun wierzcholki z wagami rownymi 0
			usunZeroweWagi(grafH);
		}

		// oblicz sume wag fvs
		for(Wierzcholek w : fvs) {
			for(Wierzcholek w2 : graf.getWierzcholki()) {
				if(w.getNumer() == w2.getNumer()) {
					w.setWaga(w2.getWaga());
					minSumaWag += w2.getWaga();
					break;
				}
			}
		}
		
		System.out.println("\nZNALEZIONO ROZWIAZANIE W ALGORYTMIE WARSTWOWYM");
		System.out.println("MINIMALNY ZBIÓR ROZCYKLAJ¥CY = " + fvs);
		System.out.println("ROZMIAR ZBIORU [FVS] TO: " + fvs.size());
		System.out.println("SUMA WAG JEGO WIERZCHOLKOW = " + minSumaWag);
	}

	private void usunZeroweWagi(Graf grafH) {		
		List<Wierzcholek> listaWierzcholkowDoUsuniecia = new LinkedList<Wierzcholek>(); 
		
		for(Wierzcholek w : grafH.getWierzcholki()) {
			if(Math.abs(w.getWaga()) < 0.0001) {
				listaWierzcholkowDoUsuniecia.add(w);
				fvs.add(w);
			}
		}
		
		for(Wierzcholek w : listaWierzcholkowDoUsuniecia) {
			grafH.removeWierzcholek(w.getNumer());
		}
	}

	public void obliczNoweWagiDlaPodgrafu(Graf grafH, double c) {
		
		for(Wierzcholek w : grafH.getWierzcholki()) {
			double nowaWaga = 0.0;
			
			// oblicz t = c * spadek_cyklomatycznej_po_usunieciu_v, czyli 'largest cyclomatic weight function in w'
			double t = c * grafH.getCyclomaticNumberDecreaseAfterRemovingVertex(w.getNumer());
			nowaWaga = w.getWaga() - t;
			
			w.setWaga(nowaWaga);
		}
	}

	public double obliczC(Graf grafH) {
		boolean pierwszyRaz = true;
		double min = -1;
		
		for(Wierzcholek w : grafH.getWierzcholki()) {
			
			double wartoscDlaWierzcholka = (w.getWaga() / grafH.getCyclomaticNumberDecreaseAfterRemovingVertex(w.getNumer()));
			
			if(pierwszyRaz) {
				min = wartoscDlaWierzcholka;
				pierwszyRaz = false;
			} else {
				if(wartoscDlaWierzcholka < min) {
					min = wartoscDlaWierzcholka;
				}
			}
		}
		
		return min;
	}

	public void usunWierzcholkiKtoreNieZmieniajaLiczbyCyklomatycznej(Graf grafH) {		
		List<Wierzcholek> listaWierzcholkowDoUsuniecia = new LinkedList<Wierzcholek>(); 
		
		for(Wierzcholek w : grafH.getWierzcholki()) {
			if(grafH.getCyclomaticNumberDecreaseAfterRemovingVertex(w.getNumer()) == 0) {
				listaWierzcholkowDoUsuniecia.add(w);
			}
		}
		
		for(Wierzcholek w : listaWierzcholkowDoUsuniecia) {
			grafH.removeWierzcholek(w.getNumer());
		}
	}
	
}
