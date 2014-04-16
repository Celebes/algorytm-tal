package pl.edu.wat.tal.algorytmy;

import java.util.HashSet;
import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Krawedz;
import pl.edu.wat.tal.graf.Wierzcholek;
import pl.edu.wat.tal.helper.SetHelper;

/*
 * ALGORYTM DOKLADNY - oparty na rozgalezianiu
 */

public class AlgorytmBranching {
	
	private Graf graf;
	
	public AlgorytmBranching(Graf graf) {
		this.graf = graf;
	}
	
	public int maximumInducedForest(Graf g, Set<Wierzcholek> f) {
		
		return 0;
	}
	
	public Graf compress(Graf G, Set<Wierzcholek> T) {
		// nowy graf, ktory zostanie zwrocony
		Graf grafWynikowy = G.klonuj();
		
		// tworzymy nowy wierzcholek, do ktorego zostana skompresowane pozostale
		Wierzcholek t = new Wierzcholek(G.getWierzcholki().size() + 1, "t");
		
		// dodajemy nowy wierzcholek do grafu wynikowego
		grafWynikowy.addWierzcholek(t);
		
		// dla kazdego wierzcholka W w T znajdz powiazania z sasiadami nie nalezacymi do T i przypisz je do wierzcholka t, a nastepnie usun W
		for(Wierzcholek w : T) {
			// znajdz sasiadow
			Set<Wierzcholek> sasiedziW = G.pobierzWszystkichSasiadowWierzcholka(w);
			
			// pomin nalezacych do T
			SetHelper.roznicaZbiorow(sasiedziW, T);
			
			Set<Krawedz> krawedzieDlaNowegoWierzcholka = new HashSet<Krawedz>();
			
			for(Wierzcholek wierzcholek : sasiedziW) {
				Krawedz k = new Krawedz(t, wierzcholek);
				grafWynikowy.addKrawedz(k);
			}
		}
		
		// na koncu usun wszystkie wierzcholki T z grafu wynikowego
		for(Wierzcholek w : T) {
			grafWynikowy.removeWierzcholek(w.getNumer());
		}
		
		// usun wierzcholki powiazane z t, ktore maja wiecej niz 1 krawedz laczaca je z t
		for(Wierzcholek w : grafWynikowy.pobierzWszystkichSasiadowWierzcholka(t)) {
			int licznik = 0;
			
			for(Krawedz k : grafWynikowy.getKrawedzie()) {
				if(k.zawieraWierzcholek(t) && k.zawieraWierzcholek(w)) {
					licznik++;
				}
			}
			
			if(licznik > 1) {
				grafWynikowy.removeWierzcholek(w.getNumer());
			}
		}
		
		return grafWynikowy;
	}
	
	public boolean checkIfWierzcholekIsInSet(Set<Wierzcholek> set, Wierzcholek w) {
		return false;
	}

}
