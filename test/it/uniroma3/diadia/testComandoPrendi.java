package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;
import java.util.Scanner;

public class testComandoPrendi {

	private IO io;
	private LabirintoBuilder builder;
	private AbstractComando prendi;
	private Scanner scannerDiLinee;
    
	@BeforeEach
	public void SetUp () {
		this.io = new IOConsole(scannerDiLinee);
		this.builder = new LabirintoBuilder();
		this.prendi = new ComandoPrendi();
	}
	
	@Test
	public void esegui_null () {
		Labirinto monolocale = builder
				.addStanzaIniziale("stanza")
				.addStanzaVincente("stanza")
				.getLabirnto();
				
		Partita partita = new Partita(monolocale);
		
		prendi.setParametro(null);
		prendi.esegui(partita, io);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void esegui_NonValido () {
		Labirinto monolocaleConSpada = builder
				.addStanzaIniziale("stanza")
				.addAttrezzo("spada", 3)
				.addStanzaVincente("stanza")
				.getLabirnto();
				
		Partita partita = new Partita(monolocaleConSpada);
		
		prendi.setParametro("lanterna");
		prendi.esegui(partita, io);
		
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void esegui_Valido() {
		Labirinto monolocaleConSpada = builder
				.addStanzaIniziale("stanza")
				.addAttrezzo("spada", 3)
				.addStanzaVincente("stanza")
				.getLabirnto();

		Partita partita = new Partita(monolocaleConSpada);
		prendi.setParametro("spada");
		prendi.esegui(partita, io);

		assertFalse(partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
}