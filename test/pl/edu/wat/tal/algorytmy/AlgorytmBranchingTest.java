package pl.edu.wat.tal.algorytmy;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.wat.tal.graf.Graf;
import pl.edu.wat.tal.graf.Wierzcholek;

public class AlgorytmBranchingTest {

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

	/*
	 * Test dla przykladu z ksiazki 'Exact Exponential Algorithms', strona 108, rysunek 'Fig. 6.2'
	 */
	@Test
	public void testCompressPierwszy() {
		Graf g = new Graf();
		AlgorytmBranching ab = new AlgorytmBranching(g);
		Set<Wierzcholek> t = new HashSet<Wierzcholek>();
		
		g.createWierzcholekFromLine("1 x");
		g.createWierzcholekFromLine("2 y");
		g.createWierzcholekFromLine("3 z");
		g.createWierzcholekFromLine("4 u");
		g.createWierzcholekFromLine("5 a");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("3 2");
		g.createKrawedzFromLine("2 4");
		g.createKrawedzFromLine("2 5");
		g.createKrawedzFromLine("4 5");
		
		t.add(new Wierzcholek(1, "x"));
		t.add(new Wierzcholek(2, "y"));
		t.add(new Wierzcholek(3, "z"));
		t.add(new Wierzcholek(4, "u"));

		Graf wynikowy = ab.compress(g, t);
		
		assertTrue(wynikowy.getWierzcholki().size() == 1);
		assertTrue(wynikowy.getKrawedzie().size() == 0);
	}
	
	/*
	 * Test dla przykladu z ksiazki 'Exact Exponential Algorithms', strona 110, rysunek 'Fig. 6.3'
	 */
	@Test
	public void testCompressDrugi() {
		Graf g = new Graf();
		AlgorytmBranching ab = new AlgorytmBranching(g);
		Set<Wierzcholek> t = new HashSet<Wierzcholek>();
		
		g.createWierzcholekFromLine("1 a");
		g.createWierzcholekFromLine("2 b");
		g.createWierzcholekFromLine("3 c");
		g.createWierzcholekFromLine("4 d");
		g.createWierzcholekFromLine("5 e");
		g.createWierzcholekFromLine("6 f");
		g.createWierzcholekFromLine("7 g");
		
		g.createKrawedzFromLine("1 5");
		g.createKrawedzFromLine("5 6");
		g.createKrawedzFromLine("5 2");
		g.createKrawedzFromLine("6 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("2 7");
		
		t.add(new Wierzcholek(2, "b"));
		t.add(new Wierzcholek(3, "c"));

		Graf wynikowy = ab.compress(g, t);
		
		System.out.println(wynikowy);
		
		assertTrue(wynikowy.getWierzcholki().size() == 6);
		assertTrue(wynikowy.getKrawedzie().size() == 6);
	}

}
