package pl.edu.wat.tal.graf;

/*
 * Dla tego problemu wszystkie krawedzie sa zawsze dwukierunkowe
 */

public class Krawedz {
	
	private Wierzcholek a;
	private Wierzcholek b;
	
	public Krawedz(Wierzcholek a, Wierzcholek b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "[" + a.getNumer() + "]<--->[" + b.getNumer() + "]";
	}

	public Wierzcholek getA() {
		return a;
	}

	public void setA(Wierzcholek a) {
		this.a = a;
	}

	public Wierzcholek getB() {
		return b;
	}

	public void setB(Wierzcholek b) {
		this.b = b;
	}	
	
	/*
	 * Jesli wierzcholek a = 1, a wierzcholek b = 2, to wywolanie 'zwrocDrugiWierzcholek(1)' zwroci 2
	 */
	public Wierzcholek zwrocDrugiWierzcholek(Wierzcholek w) {
		if(w.equals(a)) {
			return b;
		} else if(w.equals(b)) {
			return a;
		} else {
			return null;
		}
	}
	
	public boolean zawieraWierzcholek(Wierzcholek w) {
		
		if(w.equals(a) || w.equals(b)) {
			return true;
		}
		
		return false;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		final Krawedz other = (Krawedz)obj;
		
		if((this.getA() == other.getA() && this.getB() == other.getB()) || (this.getA() == other.getB() && this.getB() == other.getA())) {
			return true;
		}
		
		return false;
	}

}
