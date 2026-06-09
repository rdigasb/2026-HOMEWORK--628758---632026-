package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import java.util.List;
import java.util.SortedSet;
import java.util.Map;
import java.util.Set;

/**
 * Questa classe Testa i vari metodi della classe Borsa
 * 
 * @author  Di Gasbarro Rocco (628758), Abballe Francesco (632026)
 * @see Borsa,Attrezzo
 * @version 1.2
 */

public class BorsaTest {
	private Borsa borsa;
	private Attrezzo attrezzoLeggero;
	private Attrezzo attrezzoPesante;
	private Attrezzo martello;
	private Attrezzo attrezzoNullo;
	private Attrezzo altroAttrezzo;
	
	@BeforeEach
	void setUp() {
		borsa=new Borsa(10);
		attrezzoLeggero=new Attrezzo("piuma", 1);
		attrezzoPesante=new Attrezzo("martellone", 11);
		martello=new Attrezzo("martello",10);
		attrezzoNullo=new Attrezzo("niente",0);
		altroAttrezzo=new Attrezzo("Farfalla", 1);
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
	
	/*Test del metodo getContenutoOrdinatoPerPeso*/
	@Test
	void getContenutoOrdinatoPerPeso() {
		Borsa borsaLocale= new Borsa(30);
		borsaLocale.addAttrezzo(attrezzoLeggero);
		borsaLocale.addAttrezzo(martello);
		borsaLocale.addAttrezzo(attrezzoNullo);
		borsaLocale.addAttrezzo(altroAttrezzo);
		List<Attrezzo> lista=borsaLocale.getContenutoOrdinatoPerPeso();
		assertEquals(4,lista.size());
		assertEquals(attrezzoNullo,lista.get(0));
		assertEquals(attrezzoLeggero,lista.get(2));
		assertEquals(altroAttrezzo,lista.get(1));
		assertEquals(martello,lista.get(3));
	}
	
	/*Test del metodo getContenutoOrdinatoPerNome*/
	@Test
	void testgetContenutoOrdinatoPerNome() {
		Borsa borsaLocale= new Borsa(30);
		borsaLocale.addAttrezzo(attrezzoLeggero);
		borsaLocale.addAttrezzo(martello);
		borsaLocale.addAttrezzo(attrezzoNullo);
		borsaLocale.addAttrezzo(altroAttrezzo);
		SortedSet<Attrezzo> set=borsaLocale.getContenutoOrdinatoPerNome();
		assertEquals(4,set.size());
		assertEquals(altroAttrezzo,set.first());
		assertEquals(attrezzoLeggero,set.last());
	}
	/*Test del metodo getSortedSetOrdinatoPerPeso*/
	@Test
	void testGetSortedSetOrdinatoPerPeso_StessoPesoMaNomiDiversiRimangonoDistinti() {
		Borsa borsaLocale= new Borsa(30);
		borsaLocale.addAttrezzo(attrezzoLeggero);
		borsaLocale.addAttrezzo(martello);
		borsaLocale.addAttrezzo(attrezzoNullo);
		borsaLocale.addAttrezzo(altroAttrezzo);
		SortedSet<Attrezzo> setPerPeso=borsaLocale.getSortedSetOrdinatoPerPeso();
		assertEquals(4,setPerPeso.size(),"Errore: non ha distinto gli oggetti");
		Object[] array=setPerPeso.toArray();
		assertEquals(attrezzoNullo,array[0]);
		assertEquals(attrezzoLeggero,array[2]);
		assertEquals(altroAttrezzo,array[1]);
		assertEquals(martello,array[3]);
	}
	/*Test del metodo getContenutoRaggruppatoPerPeso*/
	@Test
	void testGetContenutoRaggruppatoPerPeso() {
		Borsa borsaLocale= new Borsa(30);
		borsaLocale.addAttrezzo(attrezzoLeggero);
		borsaLocale.addAttrezzo(martello);
		borsaLocale.addAttrezzo(attrezzoNullo);
		borsaLocale.addAttrezzo(altroAttrezzo);
		Map<Integer, Set<Attrezzo>> mappa=borsaLocale.getContenutoRaggruppatoPerPeso();
		assertEquals(3,mappa.size());
		
		assertEquals(2,mappa.get(1).size());
		assertTrue(mappa.get(1).contains(attrezzoLeggero));
		assertTrue(mappa.get(1).contains(altroAttrezzo));
		
		assertEquals(1,mappa.get(0).size());
		assertTrue(mappa.get(0).contains(attrezzoNullo));
		
		assertEquals(1,mappa.get(10).size());
		assertTrue(mappa.get(10).contains(martello));
	}
	
}
