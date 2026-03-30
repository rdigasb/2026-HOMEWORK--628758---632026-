package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Questa classe Testa i vari metodi della classe Borsa
 * 
 * @author  Di Gasbarro Rocco (628758), Abballe Francesco (632026)
 * @see Borsa,Attrezzo
 * @version 1.0
 */

public class BorsaTest {
	private Borsa borsa;
	private Attrezzo attrezzoLeggero;
	private Attrezzo attrezzoPesante;
	private Attrezzo attrezzoNullo;
	
	@BeforeEach
	void setUp() {
		borsa=new Borsa(10);
		attrezzoLeggero=new Attrezzo("piuma", 1);
		attrezzoPesante=new Attrezzo("martellone", 11);
		attrezzoNullo=new Attrezzo("niente",0);
	}
	
	/*Test metodo addAttrezzo*/
	@Test
	void TestaddAttrezzo_ABuonFine() {
		assertTrue(borsa.addAttrezzo(attrezzoLeggero));
	}
	
	@Test
	void TestaddAttrezzo_Fallito() {
		assertFalse(borsa.addAttrezzo(attrezzoPesante));
	}
	
	@Test
	void TestaddAttrezzo_OltreIlMax() {
		for(int i=0; i<10; i++) {
			borsa.addAttrezzo(attrezzoNullo);
		}
		assertFalse(borsa.addAttrezzo(attrezzoLeggero));
	}
	
	/*Test metodo getAttrezzo*/
	@Test
	void TestgetAttrezzo_Esistente() {
		borsa.addAttrezzo(attrezzoLeggero);
		assertEquals(attrezzoLeggero,borsa.getAttrezzo("piuma"));
	}
	
	@Test
	void TestgetAttrezzo_NonGiusto() {
		borsa.addAttrezzo(attrezzoLeggero);
		assertNotEquals(attrezzoNullo,borsa.getAttrezzo("piuma"));
	}
	
	@Test
	void TestgetAttrezzo_Inesistente() {
		assertNull(borsa.getAttrezzo("piuma"));
	}
	
	/*Test metodo removeAttrezzo*/
	@Test
	void TestremoveAttrezzo_Successo() {
		borsa.addAttrezzo(attrezzoLeggero);
		assertEquals(attrezzoLeggero, borsa.removeAttrezzo("piuma"));
		assertNull(borsa.getAttrezzo("piuma"));
	}
	@Test
	void TestremoveAttrezzo_Insuccesso() {
		borsa.addAttrezzo(attrezzoLeggero);
		assertNull(borsa.getAttrezzo("niente"));
	}
	@Test
	void TestremoveAttrezzo_ParametroNull() {
		assertNull(borsa.getAttrezzo(null));
	}
}
