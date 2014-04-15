package pl.edu.wat.tal.algorytmy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;

/*
 * ALGORYTM DOKLADNY - oparty na brute force
 */

public class AlgorytmBruteForce {
	
	private Graf graf;
	private Set<Set<Wierzcholek>> wszystkiePodzbiory;

	public AlgorytmBruteForce(Graf graf) {
		this.graf = graf;
	}
	
	public void compute() {
		// zbior wszystkich zbiorow z liczba cyklometryczna <= 0
		Set<Set<Wierzcholek>> acykliczne = new HashSet<Set<Wierzcholek>>();
		
		// tworzy liste wszystkich mozliwych podzbiorow wierzcholkow
		wszystkiePodzbiory = generujPodzbiory(new HashSet<Wierzcholek>(graf.getWierzcholki()));
		
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
		
		System.out.println("\n***\nLista wszystkich zbiorow z cyklomatyczna <= 0:\n");
		
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
		
		System.out.println("\n***\nLista minimalnych zbiorow rozcyklajacych [FVS]:\n");
		
		for(Set<Wierzcholek> s : acykliczne) {
			if(s.size() == min) {
				System.out.println(s);
			}
		}
		
		System.out.println();
		
	}
	
	public Set<Set<Wierzcholek>> generujPodzbiory(Set<Wierzcholek> wierzcholki) {
		Set<Set<Wierzcholek>> podzbiory = new HashSet<Set<Wierzcholek>>();
		
		if(wierzcholki.isEmpty()) {
			podzbiory.add(new HashSet<Wierzcholek>());
			return podzbiory;
		}
		
		List<Wierzcholek> list = new ArrayList<Wierzcholek>(wierzcholki);
		Wierzcholek head = list.get(0);
		Set<Wierzcholek> rest = new HashSet<Wierzcholek>(list.subList(1, list.size()));
		
		for(Set<Wierzcholek> zbior : generujPodzbiory(rest)) {
			Set<Wierzcholek> nowyZbior = new HashSet<Wierzcholek>();
			nowyZbior.add(head);
			nowyZbior.addAll(zbior);
			podzbiory.add(nowyZbior);
			podzbiory.add(zbior);
		}
		
		return podzbiory;
	}
	
}
