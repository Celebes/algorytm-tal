package pl.edu.wat.tal.graf;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KrawedzTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		Wierzcholek w1 = new Wierzcholek(2, "2");
		Wierzcholek w2 = new Wierzcholek(5, "5");
		Krawedz k = new Krawedz(w1, w2);
		
		assertTrue(k.toString().equals("[2]<--->[5]"));
	}
	
	@Test
	public void testEqualsDlaDwochIdentycznych() {
		Wierzcholek w1 = new Wierzcholek(2, "2");
		Wierzcholek w2 = new Wierzcholek(5, "5");
		
		Krawedz k1 = new Krawedz(w1, w2);
		Krawedz k2 = new Krawedz(w1, w2);
		
		// dwie takie same
		assertTrue(k1.equals(k2));
	}
	
	@Test
	public void testEqualsDlaDwochIdentycznychWOdwrotnejKolejnosci() {
		Wierzcholek w1 = new Wierzcholek(2, "2");
		Wierzcholek w2 = new Wierzcholek(5, "5");
		
		Krawedz k1 = new Krawedz(w1, w2);
		Krawedz k2 = new Krawedz(w2, w1);
		
		// dwie takie same z odwrocona kolejnoscia wierzcholkow, np. [1]<--->[2] jest rowne z [2]<--->[1]
		assertTrue(k1.equals(k2));
	}
	
	@Test
	public void testEqualsDlaDwochRoznych() {
		Wierzcholek w1 = new Wierzcholek(2, "2");
		Wierzcholek w2 = new Wierzcholek(5, "5");
		Wierzcholek w3 = new Wierzcholek(13, "13");
		
		Krawedz k1 = new Krawedz(w1, w2);
		Krawedz k2 = new Krawedz(w2, w3);
		
		assertFalse(k1.equals(k2));
	}
	
	@Test
	public void testEqualsDlaNulla() {
		Wierzcholek w1 = new Wierzcholek(2, "2");
		Wierzcholek w2 = new Wierzcholek(5, "5");
		
		Krawedz k1 = new Krawedz(w1, w2);
		
		assertFalse(k1.equals(null));
	}
	
	@Test
	public void testEqualsDlaNiepoprawnejKlasy() {
		Wierzcholek w1 = new Wierzcholek(2, "2");
		Wierzcholek w2 = new Wierzcholek(5, "5");
		
		Krawedz k1 = new Krawedz(w1, w2);
		
		assertFalse(k1.equals(new String("TEST")));
	}

}
