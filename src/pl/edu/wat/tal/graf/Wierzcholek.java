package pl.edu.wat.tal.graf;

public class Wierzcholek {
	
	private int numer;
	private String nazwa;

	public Wierzcholek(int numer, String nazwa) {
		this.numer = numer;
		this.nazwa = nazwa;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	@Override
	public String toString() {
		//return String.valueOf(numer);
		return nazwa;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		final Wierzcholek other = (Wierzcholek)obj;
		
		if(!this.nazwa.equals(other.getNazwa())) {
			return false;
		}
		
		if(this.numer != other.numer) {
			return false;
		}
		
		return true;
	}

}
