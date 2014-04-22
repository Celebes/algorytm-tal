package pl.edu.wat.tal.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SetHelperTest {

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
	public void testRoznicaZbiorowIdentycznych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(1);
		b.add(2);
		
		assertTrue(SetHelper.roznicaZbiorow(a, b).isEmpty());
	}
	
	@Test
	public void testRoznicaZbiorowRozlacznych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(3);
		b.add(4);
		
		Set<Integer> wynik = SetHelper.roznicaZbiorow(a, b);
		
		assertTrue(wynik.equals(a));
	}
	
	@Test
	public void testRoznicaZbiorowPodobnych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(2);
		b.add(3);
		
		Set<Integer> spodziewanyWynik = new HashSet<Integer>();
		spodziewanyWynik.add(1);
		
		assertTrue(SetHelper.roznicaZbiorow(a, b).equals(spodziewanyWynik));
	}
	
	@Test
	public void testElementZnajdujeSieWZbiorze() {
		Set<Integer> a = new HashSet<Integer>();
		a.add(1);
		a.add(2);
		
		assertTrue(SetHelper.elementZnajdujeSieWZbiorze(a, 1));
		assertFalse(SetHelper.elementZnajdujeSieWZbiorze(a, 4));
	}
	
	@Test
	public void testSumaZbiorowIdentycznych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(1);
		b.add(2);
		
		assertTrue(SetHelper.sumaZbiorow(a, b).equals(a));
	}
	
	@Test
	public void testSumaZbiorowRozlacznych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(3);
		b.add(4);
		
		Set<Integer> spodziewanyWynik = new HashSet<Integer>();
		spodziewanyWynik.add(1);
		spodziewanyWynik.add(2);
		spodziewanyWynik.add(3);
		spodziewanyWynik.add(4);
		
		assertTrue(SetHelper.sumaZbiorow(a, b).equals(spodziewanyWynik));
	}
	
	@Test
	public void testSumaZbiorowPodobnych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(2);
		b.add(3);
		
		Set<Integer> spodziewanyWynik = new HashSet<Integer>();
		spodziewanyWynik.add(1);
		spodziewanyWynik.add(2);
		spodziewanyWynik.add(3);
		
		assertTrue(SetHelper.sumaZbiorow(a, b).equals(spodziewanyWynik));
	}
	
	@Test
	public void testIloczynZbiorowIdentycznych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(1);
		b.add(2);
		
		assertTrue(SetHelper.iloczynZbiorow(a, b).equals(a));
	}
	
	@Test
	public void testloczynZbiorowRozlacznych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(3);
		b.add(4);
		
		assertTrue(SetHelper.iloczynZbiorow(a, b).isEmpty());
	}
	
	@Test
	public void testloczynZbiorowPodobnych() {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		b.add(2);
		b.add(3);
		
		Set<Integer> spodziewanyWynik = new HashSet<Integer>();
		spodziewanyWynik.add(2);
		
		assertTrue(SetHelper.iloczynZbiorow(a, b).equals(spodziewanyWynik));
	}
	
	@Test
	public void testGenerujWszystkiePodzbiory() {
		Set<Integer> a = new HashSet<Integer>();
		
		a.add(1);
		a.add(2);
		
		Set<Set<Integer>> podzbiory = SetHelper.generujWszystkiePodzbiory(a);
		
		// wygenerowane podzbiory zbioru a={1,2}, to: {}, {1}, {2}, {1,2}
		
		Set<Integer> a0 = new HashSet<Integer>();
		
		Set<Integer> a1 = new HashSet<Integer>();
		a1.add(1);
		
		Set<Integer> a2 = new HashSet<Integer>();
		a2.add(2);
		
		Set<Integer> a3 = new HashSet<Integer>();
		a3.add(1);
		a3.add(2);
		
		assertTrue(podzbiory.size() == 4);
		assertTrue(podzbiory.contains(a0));
		assertTrue(podzbiory.contains(a1));
		assertTrue(podzbiory.contains(a2));
		assertTrue(podzbiory.contains(a3));
	}

}
