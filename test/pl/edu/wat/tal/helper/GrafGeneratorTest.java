package pl.edu.wat.tal.helper;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.wat.tal.graf.Krawedz;
import pl.edu.wat.tal.graf.Wierzcholek;

public class GrafGeneratorTest {

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
	public void test() {
		GrafGenerator gg = new GrafGenerator();
		
		gg.generujGrafCykliczny(5, 2, true);
	}

}
