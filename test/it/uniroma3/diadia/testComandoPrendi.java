package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.giocatore.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.attrezzi.*;
public class testComandoPrendi {

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
	public void esegui_null () {
		
		ComandoPrendi prendi = new ComandoPrendi();
		
		prendi.setParametro(null);
		prendi.esegui(partita, io);
		
		assertTrue(borsa.isEmpty());
	}
	
	
	@Test
	public void esegui_NonValido () {
		
		ComandoPrendi prendi = new ComandoPrendi();
		
		Attrezzo spada = new Attrezzo("spada", 3);
		stanza.addAttrezzo(spada);
		
		prendi.setParametro("lanterna");
		prendi.esegui(partita, io);
		
		assertTrue(borsa.isEmpty());
		
	}
	
	
	 @Test
	    public void esegui_Valido() {
		 
	        Attrezzo spada = new Attrezzo("spada", 3);

            ComandoPrendi prendi = new ComandoPrendi();
	        stanza.addAttrezzo(spada);
	        prendi.setParametro("spada");

	        prendi.esegui(partita, io);

	        assertFalse(stanza.hasAttrezzo("spada"));
	        
	 }
	
}
