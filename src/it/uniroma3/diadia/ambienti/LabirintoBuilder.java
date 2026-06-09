package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Map;
import java.util.HashMap;
import it.uniroma3.diadia.personaggi.*;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Map<String, Stanza> stanzeCreate;
	private Stanza ultimaStanzaAggiunta;
	
	public LabirintoBuilder() {
		this.labirinto=new Labirinto();
		this.stanzeCreate=new HashMap<>();
	}
	
	public Labirinto getLabirnto() {
		return this.labirinto;
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza iniziale=new Stanza(nomeStanza);
		this.labirinto.setStanzaIniziale(iniziale);
		this.stanzeCreate.put(nomeStanza, iniziale);
		this.ultimaStanzaAggiunta=iniziale;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza vincente=new Stanza(nomeStanza);
		this.labirinto.setStanzaVincente(vincente);
		this.stanzeCreate.put(nomeStanza, vincente);
		this.ultimaStanzaAggiunta=vincente;
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza stanza=new Stanza(nomeStanza);
		this.stanzeCreate.put(nomeStanza, stanza);
		this.ultimaStanzaAggiunta=stanza;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
		if(this.ultimaStanzaAggiunta!=null) {
			Attrezzo attrezzo=new Attrezzo(nomeAttrezzo, peso);
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		}
		return this;
	}
	
	public LabirintoBuilder addAdiazenza(String stanzaCorrente,String stanzaAdiacente, Direzione direzione) {
		Stanza corrente=this.stanzeCreate.get(stanzaCorrente);
		Stanza adiacente=this.stanzeCreate.get(stanzaAdiacente);
		if(corrente!=null && adiacente!=null) {
			corrente.impostaStanzaAdiacente(direzione, adiacente);
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza,int soglia) {
		Stanza magica=new StanzaMagica(nomeStanza,soglia);
		this.stanzeCreate.put(nomeStanza, magica);
		this.ultimaStanzaAggiunta=magica;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza,String attrezzoPerVedere) {
		Stanza buia=new StanzaBuia(nomeStanza, attrezzoPerVedere);
		this.stanzeCreate.put(nomeStanza, buia);
		this.ultimaStanzaAggiunta=buia;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza,Direzione direzioneBloccata,String chiave) {
		Stanza bloccata=new StanzaBloccata(nomeStanza,direzioneBloccata,chiave);
		this.stanzeCreate.put(nomeStanza, bloccata);
		this.ultimaStanzaAggiunta=bloccata;
		return this;
	}
	
	public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio) {
		if (this.ultimaStanzaAggiunta != null && personaggio != null) {
			this.ultimaStanzaAggiunta.setPersonaggio(personaggio);
		}
		return this;
	}
}
