package it.uniroma3.diadia;

/*
 * Classe -> Test Stanza
 * Una Stanza � un luogo fisico del gioco.
 * Questa classe ha il ruolo di fornire dei test per verificare
 * la corretta funzionalit� dei metodi di Stanza.
 * 
 * @Author: Francesco Abballe, Matricola: 632026
 * @Author: Rocco Di Gasbarro, Matricola: 628758
 * @see: Stanza, Attrezzo
 * @version: 1.1
*/


import static org.junit.jupiter.api.Assertions.*;  //istruzioni per importare JUnit
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaProtected;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanza {

	private StanzaProtected stanza;
	private Attrezzo lancia;
	private Attrezzo chiave;
	
	//metodo da eseguire prima di ogni test
	@BeforeEach
	public void setUp() {
		stanza = new StanzaProtected("aulaN0");
		lancia = new Attrezzo("lancia", 4);
		chiave = new Attrezzo("chiave", 1);
	}
	
	//serie di test per il metodo addAttrezzo ed il metodo hasAttrezzo
	//combinare i test dei due metodi in questo caso conveniva in ottica di ottimizzazione del codice
	@Test
	public void test_add_e_has_AttrezzoSingolo() {
		assertTrue(stanza.addAttrezzo(lancia));
		assertTrue(stanza.hasAttrezzo("lancia"));
	}
	
	@Test
	public void test_add_e_has_AttrezzoMultipli() {
	    assertTrue(stanza.addAttrezzo(lancia));
        assertTrue(stanza.addAttrezzo(chiave));
        assertTrue(stanza.hasAttrezzo("lancia"));
		assertTrue(stanza.hasAttrezzo("chiave"));
    }
	
	@Test
	public void test_addAttrezzo_False() {
	  for(int i = 0; i < 10; i++) {
        stanza.addAttrezzo(new Attrezzo("elem" + i, i));
        }
        
	    assertFalse(stanza.addAttrezzo(new Attrezzo("extra", 1)));
	    assertFalse(stanza.hasAttrezzo("extra"));
	}
	
	
	
	//serie di test per i due metodi impostaStanzaAdiacente e getStanzaAdiacente
	//combinare i test dei due metodi in questo caso conveniva in ottica di ottimizzazione del codice
	@Test
    public void test_ImpostaeGet_StanzaAdiacente() {
        StanzaProtected nuova = new StanzaProtected("M1");
        stanza.impostaStanzaAdiacente("M1", nuova);

        assertEquals(nuova, stanza.getStanzaAdiacente("M1"));
    }

    @Test
    public void test_GetStanzaAdiacenteFalse() {
        assertNull(stanza.getStanzaAdiacente("M1"));
    }

    @Test
    public void test_AggiornaStanzaAdiacente() {
        StanzaProtected nord1 = new StanzaProtected("Nord1");
        StanzaProtected nord2 = new StanzaProtected("Nord2");

        stanza.impostaStanzaAdiacente("nord", nord1);
        stanza.impostaStanzaAdiacente("nord", nord2);

        assertEquals(nord2, stanza.getStanzaAdiacente("nord"));
    }
}