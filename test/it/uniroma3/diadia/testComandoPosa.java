package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.giocatore.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.attrezzi.*;
public class testComandoPosa {
	
	Partita partita;
	IO io;
	Borsa borsa;
	Stanza stanza;
	
	@BeforeEach
	public void SetUp () {
		partita = new Partita();
		io = new IOConsole();
		borsa = new Borsa();
		stanza = new Stanza("stanza");
		partita.setStanzaCorrente(stanza);
	}

	@Test
	public void esegui_null() {
		
		ComandoPosa posa = new ComandoPosa();
		
		
		posa.setParametro(null);
		posa.esegui(partita, io);
		
		assertFalse(stanza.hasAttrezzo("lanterna"));
	}
	
	
	@Test
	public void esegui_nonValido () {
		
		ComandoPosa posa = new ComandoPosa();
		
		Attrezzo chiave = new Attrezzo("chiave", 1);
		
		borsa.addAttrezzo(chiave);
		posa.setParametro("spada");
		
		posa.esegui(partita, io);
		assertFalse(stanza.hasAttrezzo("chiave"));
		assertTrue(borsa.hasAttrezzo("chiave"));
		
	}
	
	
	@Test
	public void esegui_Valido () {
		
		ComandoPosa posa = new ComandoPosa();
		
		Attrezzo chiave = new Attrezzo("chiave", 1);
		
		partita.getGiocatore().getBorsa().addAttrezzo(chiave);
		posa.setParametro("chiave");
		
		posa.esegui(partita, io);
		
		assertTrue(partita.getGiocatore().getBorsa().addAttrezzo(chiave));
		assertFalse(borsa.hasAttrezzo("chiave"));

	}
	
}
