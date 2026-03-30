package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Questa classe Testa i vari metodi della classe Labirinto
 * 
 * @author  Di Gasbarro Rocco (628758), Abballe Francesco (632026)
 * @see Labirinto
 * @version 1.0
 */

public class LabirintoTest {
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
		labirinto=new Labirinto();
	}
	
	/*Test per metodo getStanzaIniziale*/
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
	
	/*Test per metodo getStanzaVincente*/
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
