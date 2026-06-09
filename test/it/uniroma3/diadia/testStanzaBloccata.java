package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testStanzaBloccata {

	private StanzaBloccata stanza;
	private Stanza stanza2;
	private Attrezzo attrezzo;

	@BeforeEach
	public void SetUp () {
		// La stanza è bloccata a "sud" a meno che non contenga il "passepartout"
		stanza = new StanzaBloccata("aula", Direzione.SUD, "passepartout");
		stanza2 = new Stanza("atrio");
	}
	
	@Test 
	public void getStanzaAdiacente_NoAttrezzo () {
		stanza.impostaStanzaAdiacente(Direzione.SUD, stanza2);
		// Senza attrezzo sbloccante, se provo a chiedere la stanza a sud mi restituisce la stanza stessa (rimango bloccato)
		assertEquals(stanza, stanza.getStanzaAdiacente(Direzione.SUD));
	}

	@Test
	public void getStanzaAdiacente_DirezioneLibera () {
		stanza.impostaStanzaAdiacente(Direzione.OVEST, stanza2);
		// Una direzione non bloccata ("ovest") deve comportarsi normalmente
		assertEquals(stanza2, stanza.getStanzaAdiacente(Direzione.OVEST));
	}
	
	@Test
	public void getStanzaAdiacente_True () {
		stanza.impostaStanzaAdiacente(Direzione.SUD, stanza2);
		attrezzo = new Attrezzo("passepartout", 2);
		stanza.addAttrezzo(attrezzo); // Lasciamo la chiave nella stanza per sbloccarla
		
		// Ora la direzione bloccata deve aprirsi e restituire stanza2
		assertEquals(stanza2, stanza.getStanzaAdiacente(Direzione.SUD));
	}
}