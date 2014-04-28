package pl.edu.wat.tal.graf;

public class Wierzcholek {
	
	private int numer;
	private String nazwa;
	private double waga;
	private boolean posiadaWage = false;

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
		//return "[" + numer + "|" + nazwa + "|" + waga + "]";
		return "(" + numer + "|" + waga + ")";
	}

	public double getWaga() {
		return waga;
	}

	public void setWaga(double waga) {
		this.waga = waga;
		this.posiadaWage = true;
	}

	public boolean isPosiadaWage() {
		return posiadaWage;
	}

	public void setPosiadaWage(boolean posiadaWage) {
		this.posiadaWage = posiadaWage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nazwa == null) ? 0 : nazwa.hashCode());
		result = prime * result + numer;
		result = prime * result + (posiadaWage ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(waga);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		
		final Wierzcholek other = (Wierzcholek)obj;
		
		if(!this.nazwa.equals(other.getNazwa())) {
			return false;
		}
		
		if(this.numer != other.numer) {
			return false;
		}
		
		if(this.posiadaWage != other.posiadaWage) {
			return false;
		}
		
		if(this.posiadaWage) {
			if(this.waga != other.waga) {
				return false;
			}
		}
		
		return true;
	}

}
