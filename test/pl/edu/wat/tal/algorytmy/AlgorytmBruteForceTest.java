package pl.edu.wat.tal.algorytmy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;

public class AlgorytmBruteForceTest {

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
	public void testPobierzZbiorWierzcholkowNaPodstawieMaskiPustej() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		
		AlgorytmBruteForce abf = new AlgorytmBruteForce(g);
		boolean maska[] = new boolean[3];
		
		Set<Wierzcholek> wynik = abf.pobierzZbiorWierzcholkowNaPodstawieMaski(maska);
		
		assertTrue(wynik.isEmpty());
	}
	
	@Test
	public void testPobierzZbiorWierzcholkowNaPodstawieMaski() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		
		AlgorytmBruteForce abf = new AlgorytmBruteForce(g);
		boolean maska[] = new boolean[3];
		maska[0] = true;
		maska[2] = true;
		
		Set<Wierzcholek> wynik = abf.pobierzZbiorWierzcholkowNaPodstawieMaski(maska);
		// konwersja na liste, zeby mozna bylo uzyc metody 'contains()' (uzycie jej na HashSet nic nie da, bo on przede wszystkim porownuje po HashCode)
		List<Wierzcholek> lista = new ArrayList<Wierzcholek>(wynik);
		
		assertTrue(lista.size() == 2);
		assertTrue(lista.contains(new Wierzcholek(1, "1")));
		assertTrue(lista.contains(new Wierzcholek(3, "3")));
		assertFalse(lista.contains(new Wierzcholek(2, "2")));
	}
	
	@Test
	public void testGenerujWarunekZwiekszeniaLiczbyElementowWPodzbiorzeDlaZera() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		
		AlgorytmBruteForce abf = new AlgorytmBruteForce(g);
		
		boolean[] wynik = abf.generujWarunekZwiekszeniaLiczbyElementowWPodzbiorze(0);
		boolean[] spodziewanyWynik = new boolean[3];
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
	}
	
	@Test
	public void testGenerujWarunekZwiekszeniaLiczbyElementowWPodzbiorze() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		
		AlgorytmBruteForce abf = new AlgorytmBruteForce(g);
		
		boolean[] wynik = abf.generujWarunekZwiekszeniaLiczbyElementowWPodzbiorze(2);
		boolean[] spodziewanyWynik = new boolean[] { true, true, false };
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
	}
	
	@Test
	public void testGenerujMaskeDlaDanejLiczbyElementowPodzbioruRownejZero() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		
		AlgorytmBruteForce abf = new AlgorytmBruteForce(g);
		
		boolean[] wynik = abf.generujMaskeDlaDanejLiczbyElementowPodzbioru(0);
		boolean[] spodziewanyWynik = new boolean[3];
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
	}
	
	@Test
	public void testGenerujMaskeDlaDanejLiczbyElementowPodzbioru() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		
		AlgorytmBruteForce abf = new AlgorytmBruteForce(g);
		
		boolean[] wynik = abf.generujMaskeDlaDanejLiczbyElementowPodzbioru(1);
		boolean[] spodziewanyWynik = new boolean[] {false, false, true};
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
		
		wynik = abf.generujMaskeDlaDanejLiczbyElementowPodzbioru(2);
		spodziewanyWynik = new boolean[] {false, true, true};
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
	}
	
	@Test
	public void testGenerujNastepnyPodzbior() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("3 4");
		
		AlgorytmBruteForce abf = new AlgorytmBruteForce(g);
		
		// maska z samymi FALSE da w wyniku to samo
		boolean[] maska = new boolean[4];
		boolean[] wynik = abf.generujNastepnyPodzbior(maska);
		
		assertTrue(Arrays.equals(maska, wynik));
		
		// maska z samymi TRUE da tez to samo
		Arrays.fill(maska, Boolean.TRUE);
		wynik = abf.generujNastepnyPodzbior(maska);
		
		assertTrue(Arrays.equals(maska, wynik));
		
		// [0001] -> [0010]
		maska = new boolean[] {false, false, false, true};
		boolean[] spodziewanyWynik = new boolean[] {false, false, true, false};
		wynik = abf.generujNastepnyPodzbior(maska);
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
		
		// [1101] -> [1110]
		maska = new boolean[] {true, true, false, true};
		spodziewanyWynik = new boolean[] {true, true, true, false};
		wynik = abf.generujNastepnyPodzbior(maska);
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
		
		// [0101] -> [1001]
		maska = new boolean[] {false, true, false, true};
		spodziewanyWynik = new boolean[] {true, false, false, true};
		wynik = abf.generujNastepnyPodzbior(maska);
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
		
		// [1001] -> [1010]
		maska = new boolean[] {true, false, false, true};
		spodziewanyWynik = new boolean[] {true, false, true, false};
		wynik = abf.generujNastepnyPodzbior(maska);
		
		assertTrue(Arrays.equals(wynik, spodziewanyWynik));
	}

}
