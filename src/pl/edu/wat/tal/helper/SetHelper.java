package pl.edu.wat.tal.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Operacje na zbiorach - roznica, suma, iloczyn
 */

public class SetHelper {
	
	public static <T> Set<T> roznicaZbiorow(Set<T> A, Set<T> B) {
		A.removeAll(B);
		return A;
	}

	public static <T> Set<T> sumaZbiorow(Set<T> A, Set<T> B) {
		A.addAll(B);
		return A;
	}
	
	public static <T> Set<T> iloczynZbiorow(Set<T> A, Set<T> B) {
		Set<T> iloczyn = new HashSet<T>();
		
		for(T element : A) {
			if(elementZnajdujeSieWZbiorze(B, element)) {
				iloczyn.add(element);
			}
		}
		
		return iloczyn;
	}
	
	public static <T> boolean elementZnajdujeSieWZbiorze(Set<T> zbior, T element) {
		
		for(T t : zbior) {
			if(t == element) {
				return true;
			}
		}
		
		return false;
	}
	
	public static <T> Set<Set<T>> generujWszystkiePodzbiory(Set<T> zbiorWejsciowy) {
		Set<Set<T>> podzbiory = new HashSet<Set<T>>();
		
		if(zbiorWejsciowy.isEmpty()) {
			podzbiory.add(new HashSet<T>());
			return podzbiory;
		}
		
		List<T> list = new ArrayList<T>(zbiorWejsciowy);
		T head = list.get(0);
		Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
		
		for(Set<T> zbior : generujWszystkiePodzbiory(rest)) {
			Set<T> nowyZbior = new HashSet<T>();
			nowyZbior.add(head);
			nowyZbior.addAll(zbior);
			podzbiory.add(nowyZbior);
			podzbiory.add(zbior);
		}
		
		return podzbiory;
	}
	
}
