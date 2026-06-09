package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe di test per la classe Partita.
 */
public class PartitaTest {
 
	private Partita partita;
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
		// Creiamo un labirinto minimale per evitare NullPointerException
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirnto();
		this.partita = new Partita(labirinto);
	}
	
	@Test
	void TestVinta_InizioPartita() {
		assertFalse(partita.vinta());
	}
	
	@Test
	void TestVinta_NonInizioPartita() {
		Stanza stanzaqualsiasi = new Stanza("stanza generica");
		partita.setStanzaCorrente(stanzaqualsiasi);
		assertFalse(partita.vinta());
	}
	
	@Test
	void TestVinta_StanzaFinale() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	@Test
	void TestFine_CFUterminati() {
		// Modifichiamo il giocatore REALE della partita corrente
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
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