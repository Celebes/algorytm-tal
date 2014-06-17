package pl.edu.wat.tal.graf;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GrafTest {

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
	public void testCreateWierzcholekFromLine() {
		Graf g = new Graf();
		String line = "1 raz";
		
		g.createWierzcholekFromLine(line);
		
		Wierzcholek w = g.getWierzcholki().get(0);

		// utworzony zostal 1 wierzcholek w grafie
		assertTrue(g.getWierzcholki().size() == 1);
		
		// ma on numer 1
		assertTrue(w.getNumer() == 1);
		
		// oraz nazwe "raz"
		assertTrue(w.getNazwa().equals("raz"));
	}
	
	@Test
	public void testCreateKrawedzFromLine() {
		Graf g = new Graf();
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		
		g.createKrawedzFromLine("1 2");
		
		Krawedz k = g.getKrawedzie().get(0);
		
		// utworzono 2 wierzcholki
		assertTrue(g.getWierzcholki().size() == 2);
		
		// utworzono 1 krawedz
		assertTrue(g.getKrawedzie().size() == 1);
		
		// numer pierwszego wierzcholka w krawedzi to 1, a drugiego 2
		assertTrue(k.getA().getNumer() == 1);
		assertTrue(k.getB().getNumer() == 2);
	}
	
	@Test
	public void testCyclomaticNumberDlaGrafuZCyklami() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");
		g.createWierzcholekFromLine("5 5");
		g.createWierzcholekFromLine("6 6");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("2 4");
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("4 5");
		g.createKrawedzFromLine("4 6");
		
		// 6 wierzcholkow, 6 krawedzi: 6 - 6 + 1 = 1
		assertTrue(g.getCyclomaticNumber() == 1);
	}
	
	@Test
	public void testCyclomaticNumberDlaGrafuBezCykli() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");
		g.createWierzcholekFromLine("5 5");
		g.createWierzcholekFromLine("6 6");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("4 5");
		g.createKrawedzFromLine("4 6");
		
		// 6 wierzcholkow, 5 krawedzi: 6 - 5 + 1 = 0
		assertTrue(g.getCyclomaticNumber() == 0);
	}
	
	@Test
	public void testCyclomaticNumberWithoutVertex() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");
		g.createWierzcholekFromLine("5 5");
		g.createWierzcholekFromLine("6 6");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("2 4");
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("4 5");
		g.createKrawedzFromLine("4 6");
		
		// taki graf ma 1 cykl, po usunieciu wierzcholka 3 powinien miec 0
		assertTrue(g.getCyclomaticNumber() == 1);
		assertTrue(g.getCyclomaticNumberWithoutVertex(3) == 0);
	}
	
	@Test
	public void testRemoveVertex() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("1 3");
		g.createKrawedzFromLine("2 3");
		
		// przed usunieciem - 3 krawedzie, 3 wierzcholki
		assertTrue(g.getWierzcholki().size() == 3);
		assertTrue(g.getKrawedzie().size() == 3);
		
		// usuwamy wierzcholek 3 i przylegajace do niego krawedzie
		g.removeWierzcholek(3);
		
		// po usunieciu - 2 wierzcholki, 1 krawedz
		assertTrue(g.getWierzcholki().size() == 2);
		assertTrue(g.getKrawedzie().size() == 1);
	}
	
	@Test
	public void testPobierzWszystkichSasiadowWierzcholka() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");
		g.createWierzcholekFromLine("5 5");
		g.createWierzcholekFromLine("6 6");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("2 4");
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("4 5");
		g.createKrawedzFromLine("4 6");
		
		Set<Wierzcholek> set = g.pobierzWszystkichSasiadowWierzcholka(new Wierzcholek(4, "4"));
		// konwertuje na liste, bo HashSet przede wszystkim porownuje po HashCode..
		List<Wierzcholek> lista = new ArrayList<Wierzcholek>(set);
		
		// wierzcholek 4 ma 4 sasiadow: 2, 3, 5, 6
		assertTrue(lista.size() == 4);

		assertTrue(lista.contains(new Wierzcholek(2, "2")));
		assertTrue(lista.contains(new Wierzcholek(3, "3")));
		assertTrue(lista.contains(new Wierzcholek(5, "5")));
		assertTrue(lista.contains(new Wierzcholek(6, "6")));
		
		assertFalse(lista.contains(new Wierzcholek(1, "1")));
	}
	
	@Test
	public void testPobierzWszystkichSasiadowWierzcholkaBezSasiadow() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		
		g.createKrawedzFromLine("1 2");
		
		Set<Wierzcholek> set = g.pobierzWszystkichSasiadowWierzcholka(new Wierzcholek(3, "3"));
		
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void testZnajdzSpojneSkladoweGrafu() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");
		g.createWierzcholekFromLine("5 5");
		
		g.createKrawedzFromLine("1 2");
		
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("4 5");
		g.createKrawedzFromLine("5 3");
		
		System.out.println(g.iloscSpojnychSkladowych());
		System.out.println(g.iloscSpojnychSkladowych());
		
		assertTrue(true);
	}
	
	@Test
	public void testGetCyclomaticNumberDecreaseAfterRemovingVertex() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		g.createWierzcholekFromLine("3 3");
		g.createWierzcholekFromLine("4 4");
		g.createWierzcholekFromLine("5 5");
		g.createWierzcholekFromLine("6 6");
		
		g.createKrawedzFromLine("1 2");
		g.createKrawedzFromLine("2 3");
		g.createKrawedzFromLine("2 4");
		g.createKrawedzFromLine("3 4");
		g.createKrawedzFromLine("4 5");
		g.createKrawedzFromLine("4 6");
		
		// taki graf ma liczbe cyklomatyczna == 1
		assertTrue(g.getCyclomaticNumber() == 1);
		
		// po usunieciu wierzcholka numer 3 ma liczbe cyklomatyczna == 0
		assertTrue(g.getCyclomaticNumberWithoutVertex(3) == 0);
		
		// czyli spadek liczby cyklomatycznej po usunieciu wierzcholka numer 3 == 1
		assertTrue(g.getCyclomaticNumberDecreaseAfterRemovingVertex(3) == 1);
	}
	
	@Test
	public void cyklomatycznaTest2() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		
		assertTrue(g.getCyclomaticNumber() == 0);
	}
	
	@Test
	public void cyklomatycznaTest3() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		
		g.createKrawedzFromLine("1 2");
		
		assertTrue(g.getCyclomaticNumber() == 0);
	}
	
	@Test
	public void testPickRandom() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		
		g.createKrawedzFromLine("1 2");
		
		List<Integer> lista = g.pickRandom(3, 5);
		System.out.println(lista);
	}
	
	@Test
	public void testObliczStopienWierzcholka() {
		Graf g = new Graf();
		
		g.createWierzcholekFromLine("1 1");
		g.createWierzcholekFromLine("2 2");
		
		g.createKrawedzFromLine("1 2");
		
		Wierzcholek w = g.findWierzcholekByNumer(1);
		assertTrue(g.obliczStopienWierzcholka(w) == 1);
	}

}
