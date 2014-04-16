package pl.edu.wat.tal.helper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

}
