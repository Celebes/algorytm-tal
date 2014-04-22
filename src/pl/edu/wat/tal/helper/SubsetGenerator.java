package pl.edu.wat.tal.helper;

import java.util.Set;

/*
 * Klasa dla danego zbioru wierzcholkow generuje kolejne jego podzbiory zaczynajac od pustego, pozniej 1-elementowe, 2-elementowe itp..
 * Jest to potrzebne do algorytmu brute-force - przeszukiwanie od dolu. Robimy pelen przeglad patrzac od najmniejszych podzbiorow
 * Jesli ktorys z nich po usunieciu da liczbe cyklomatyczna grafu = 0, to znaczy, ze mamy wynik [FVS]
 */

public class SubsetGenerator<T> {

	private Set set;

	public <T> SubsetGenerator(Set<T> set) {
		this.set = set;
	}
	
	public Set<T> wygenerujZbiorOIndeksie(int i) {
		return null;
	}

}
