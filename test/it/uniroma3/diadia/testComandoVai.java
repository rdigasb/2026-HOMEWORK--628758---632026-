package it.uniroma3.diadia;



import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;

public class testComandoVai {
	
	Stanza stanza;
	Stanza aula;
	Partita partita;
	IO io;
	
	@BeforeEach
	public void SetUp() {
		stanza = new Stanza("atrio");
		aula = new Stanza("aula");
		partita = new Partita();
		io = new IOConsole();
	}

	@Test
	public void esegui_null () {
		ComandoVai vai = new ComandoVai();
		partita.setStanzaCorrente(stanza);
		vai.setParametro(null);
		
		vai.esegui(partita, io);
		assertTrue(partita.getStanzaCorrente().equals(stanza));
		}
	
	
	@Test
	public void esegui_NonEsistente () {
		
		ComandoVai vai = new ComandoVai();
		partita.setStanzaCorrente(stanza);
		stanza.impostaStanzaAdiacente("sud", aula);
		
		vai.setParametro("nord");
		
		vai.esegui(partita, io);
		
		assertFalse(partita.getStanzaCorrente().equals(aula));
		
		}
	
	 @Test
	    public void esegui_Valido() {
	        
	        ComandoVai vai = new ComandoVai();
	        partita.setStanzaCorrente(stanza);
	        vai.setParametro("nord");

	        stanza.impostaStanzaAdiacente("nord", aula);
	        vai.esegui(partita, io);
	        
	        assertTrue(partita.getStanzaCorrente().equals(aula));
	       
	 }
	
}

      