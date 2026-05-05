package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  Di Gasbarro Rocco (628758), Abballe Francesco (632026)
 * @see Partita
 * @version 1.0
 */
public class PartitaTest {
 
	private Partita partita;
	
	@BeforeEach
	void setUp() {
		partita=new Partita();
	}
	
	/*Test per metodo vinta()*/
	@Test
	void TestVinta_InizioPartita() {
		assertFalse(partita.vinta());
	}
	
	@Test
	void TestVinta_NonInizioPartita() {
		Stanza stanzaqualsiasi=new Stanza("stanza generica");
		partita.setStanzaCorrente(stanzaqualsiasi);
		assertFalse(partita.vinta());
	}
	
	@Test
	void TestVinta_StanzaFinale() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	/*Test per metodo isFinita()*/
	@Test
	void TestFine_CFUterminati() {
		Giocatore giocatore=new Giocatore();
		giocatore.setCfu(1);
		assertFalse(partita.isFinita());
	}
	
	@Test
	void TestFine_SetFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	
	@Test
	void TestFine_PartitaFinita() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.isFinita());
	}
}
