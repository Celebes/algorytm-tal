package pl.edu.wat.tal.algorytmy;

import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;

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
		// tworzymy nowy wierzcholek, do ktorego zostana skompresowane pozostale
		Wierzcholek t = new Wierzcholek(G.getWierzcholki().size() + 1, "t");
		
		// dla kazdego wierzcholka w T znajdz powiazania z sasiadami nie nalezacymi do T i przypisz je do wierzcholka t
		for(Wierzcholek w : T) {
			
		}
		
		return null;
	}
	
	public boolean checkIfWierzcholekIsInSet(Set<Wierzcholek> set, Wierzcholek w) {
		return false;
	}

}
