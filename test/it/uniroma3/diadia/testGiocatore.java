package it.uniroma3.diadia;

/*
 *  Classe di Test per la classe Giocatore
 *  3 metodi di test per prendi e posa 
 *  
 *   @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 *  @see Giocatore, Stanza, Attrezzo
 *  @version 1.0
*/
import static org.junit.jupiter.api.Assertions.*;  //istruzioni per importare JUnit
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class testGiocatore {

	private Giocatore giocatore;
	private Attrezzo spada;
	private Attrezzo incudine;
	private Stanza stanza;
	
	//metodo da eseguire prima di ogni test
		@BeforeEach
		public void setUp() {
			this.stanza = new Stanza("aula");
			this.giocatore = new Giocatore();
			this.spada = new Attrezzo("spada", 5);
			this.incudine = new Attrezzo("incudine", 20);
		}
		
		// TEST PRENDI
		
		@Test
	    public void testPrendi_AttrezzoVero() {
	        stanza.addAttrezzo(spada);
	        giocatore.prendi("spada", stanza); 
	        assertTrue(giocatore.getBorsa().hasAttrezzo("spada"));
	        assertFalse(stanza.hasAttrezzo("spada"));
	    }

	    @Test
	    public void testPrendi_AttrezzoFalso() {
	        giocatore.prendi("martello", stanza);
	        assertFalse(giocatore.getBorsa().hasAttrezzo("martello"));
	    }

	    @Test
	    public void testPrendi_BorsaTroppoPiena() {
	        stanza.addAttrezzo(incudine);
	        giocatore.prendi("incudine", stanza); 
	        assertFalse(giocatore.getBorsa().hasAttrezzo("incudine"));
	        assertTrue(stanza.hasAttrezzo("incudine"));
	    }
	    
	    
	    // TEST POSA
	    
	    
	    @Test
	    public void testPosa_AttrezzoInBorsa() {
	        stanza.addAttrezzo(spada);
	        giocatore.prendi("spada", stanza); 
	        giocatore.posa("spada", stanza); 
	        assertFalse(giocatore.getBorsa().hasAttrezzo("spada"));
	        assertTrue(stanza.hasAttrezzo("spada"));
	    }

	    @Test
	    public void testPosa_AttrezzoNonInBorsa() {
	        giocatore.posa("lanterna", stanza);
	        assertFalse(stanza.hasAttrezzo("lanterna"));
	    }

	    @Test
	    public void testPosa_StanzaVuotaDopoPosa() {
	        giocatore.getBorsa().addAttrezzo(spada); 
	        giocatore.posa("spada", stanza);
	        assertEquals(spada, stanza.getAttrezzo("spada")); 
	    }
		
}
