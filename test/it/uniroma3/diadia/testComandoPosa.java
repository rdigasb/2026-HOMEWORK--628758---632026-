package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.attrezzi.*;

public class testComandoPosa {
	
	private IO io;
	private LabirintoBuilder builder;
	private AbstractComando posa;
    
	@BeforeEach
	public void SetUp() {
		this.io = new IOSimulator(); 
		this.builder = new LabirintoBuilder();
		this.posa = new ComandoPosa();
	}

	@Test
	public void esegui_null() {
		Labirinto monolocale = builder
				.addStanzaIniziale("stanza")
				.addStanzaVincente("stanza")
				.getLabirnto(); 
		Partita partita = new Partita(monolocale);
		
		posa.setParametro(null);
		posa.esegui(partita, io);
		
		assertTrue(partita.getStanzaCorrente().getAttrezzi().isEmpty());
	}
	
	@Test
	public void esegui_nonValido() {
		Labirinto monolocale = builder
				.addStanzaIniziale("stanza")
				.addStanzaVincente("stanza")
				.getLabirnto();
				
		Partita partita = new Partita(monolocale);
		
		Attrezzo chiave = new Attrezzo("chiave", 1);
		partita.getGiocatore().getBorsa().addAttrezzo(chiave);
		
		posa.setParametro("spada");
		posa.esegui(partita, io);
		
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("chiave"));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
	}
	
	@Test
	public void esegui_Valido() {
		Labirinto monolocale = builder
				.addStanzaIniziale("stanza")
				.addStanzaVincente("stanza")
				.getLabirnto();
				
		Partita partita = new Partita(monolocale);
		
		@SuppressWarnings("unused")
		Attrezzo chiave = new Attrezzo("chiave", 1);
		partita.getGiocatore().getBorsa().addAttrezzo(chiave);
		
		posa.setParametro("chiave");
		posa.esegui(partita, io);
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("chiave"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
	}
}