package pl.edu.wat.tal.graf;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WierzcholekTest {

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
		Wierzcholek w = new Wierzcholek(4, "Czesio");
		assertEquals("toString() na wierzcholku ma zwracac jego numer", "4", w.toString());
	}
	
	@Test
	public void testEqualsDlaDwochIdentycznych() {
		Wierzcholek w1 = new Wierzcholek(4, "Raz");
		Wierzcholek w2 = new Wierzcholek(4, "Raz");
		
		// dwa takie same
		assertTrue("Dwa takie same wierzcholki maja zwracac true w equals()", w1.equals(w2));
	}
	
	@Test
	public void testEqualsDlaDwochRoznych() {
		Wierzcholek w1 = new Wierzcholek(4, "Raz");
		Wierzcholek w3 = new Wierzcholek(5, "dwa");
		
		// dwa rozne
		assertFalse("Dwa rozne wierzcholki maja zwracac false w equals()", w1.equals(w3));
	}
	
	@Test
	public void testEqualsDlaNulla() {
		Wierzcholek w1 = new Wierzcholek(4, "Raz");
		
		// jeden z nich to null
		assertFalse("Gdy jeden z wierzcholkow jest nullem to equals() ma zwrocic false", w1.equals(null));
	}
	
	@Test
	public void testEqualsDlaNiepoprawnejKlasy() {
		Wierzcholek w1 = new Wierzcholek(4, "Raz");
		
		// jeden z nich nie jest klasy Wierzcholek
		assertFalse("Gdy jeden z nich nie jest klasy Wierzcholek, equals() ma zwrocic false", w1.equals(new String("TEST")));
	}

}
