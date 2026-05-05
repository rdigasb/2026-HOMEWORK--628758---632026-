package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class testStanzaBuia {

	private StanzaBuia stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void SetUp() {
	 
		stanza = new StanzaBuia("aula", "lanterna");
	}
	
	
	@Test
	public void getDescrizione_null () {
		
		assertTrue(stanza.getDescrizione().equals("Non Posso vedere"));
	
	}
	
	
	@Test
	public void getDescrizione_AttrezzoSbagliato () {
		attrezzo = new Attrezzo("spada", 4);
		stanza.addAttrezzo(attrezzo);
		
		assertTrue(stanza.getDescrizione().equals("Non Posso vedere"));
	}
	
	
	@Test 
	public void getDescrizione_True () {
	
		attrezzo = new Attrezzo("lanterna", 4);
		stanza.addAttrezzo(attrezzo);
		
		assertFalse(stanza.getDescrizione().equals("Non Posso vedere"));
	}
}
