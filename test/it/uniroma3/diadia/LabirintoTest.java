package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

/**
 * Classe di test per la classe Labirinto.
 */
public class LabirintoTest {
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirnto();
	}
	
	@Test
	void TestgetStanzaIniziale_NotNull() {
		assertNotNull(labirinto.getStanzaIniziale());
	}
	
	@Test
	void TestgetStanzaIniziale_Atrio() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	void TestgetStanzaIniziale_StanzaDiversaDaAtrio() {
		assertNotEquals("Biblioteca", labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	void TestgetStanzaVincente_NotNull() {
		assertNotNull(labirinto.getStanzaVincente());
	}
	
	@Test
	void TestgetStanzaVincente_Biblioteca() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	void TestgetStanzaVincente_StanzaDiversaDaBiblioteca() {
		assertNotEquals("Atrio", labirinto.getStanzaVincente().getNome());
	}
}