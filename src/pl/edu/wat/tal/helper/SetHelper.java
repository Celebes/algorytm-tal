package pl.edu.wat.tal.helper;

import java.util.HashSet;
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
	
}
