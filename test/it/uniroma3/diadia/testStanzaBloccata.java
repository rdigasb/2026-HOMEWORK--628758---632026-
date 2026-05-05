package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comandi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testStanzaBloccata {

	private StanzaBloccata stanza;
	private Stanza stanza2;
	private Attrezzo attrezzo;

	
	@BeforeEach
	public void SetUp () {
		stanza = new StanzaBloccata("aula", "sud", "passepartout");
		stanza2 = new Stanza("atrio");
		
		}
	
	
	@Test 
	public void getStanzaAdiacente_NoAttrezzo () {
		stanza.impostaStanzaAdiacente("sud", stanza2);
		Partita partita = new Partita();
	    IO io = new IOConsole();
	    ComandoVai vai = new ComandoVai();
		vai.setParametro("sud");
		vai.esegui(partita, io);
		
		assertFalse(stanza.getStanzaAdiacente("sud").equals(stanza2));
		
		}
	

	@Test
	public void getStanzaAdiacente_DirezioneLibera () {
		stanza.impostaStanzaAdiacente("ovest", stanza2);
		Partita partita = new Partita();
	    IO io = new IOConsole();
	    ComandoVai vai = new ComandoVai();
		vai.setParametro("ovest");
		vai.esegui(partita, io);
		
		assertTrue(stanza.getStanzaAdiacente("ovest").equals(stanza2));
	}
	
	
	@Test
	public void getStanzaAdiacente_True () {
		stanza.impostaStanzaAdiacente("sud", stanza2);
		Partita partita = new Partita();
	    IO io = new IOConsole();
	    ComandoVai vai = new ComandoVai();
	    
	    attrezzo = new Attrezzo("passepartout", 2);
	    stanza.addAttrezzo(attrezzo);
		vai.setParametro("sud");
		vai.esegui(partita, io);
		
		assertTrue(stanza.getStanzaAdiacente("sud").equals(stanza2));
		
	}

	
}
