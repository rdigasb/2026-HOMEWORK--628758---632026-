package it.uniroma3.diadia.personaggi;
	
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.ambienti.*;
import java.util.*;

public class Strega extends AbstractPersonaggio{
	
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Collection<Direzione> direzioni = stanzaCorrente.getDirezioni();
		
		if(direzioni == null || direzioni.isEmpty()) { 
		    return "Non ci sono stanze adiacenti";
		}
		
		Stanza stanzaDestinazione=null;
		
		if(this.haSalutato()) {
			//caso in cui la strega è stata salutata
			for(Direzione direzione : direzioni) {
				Stanza adiacente=stanzaCorrente.getStanzaAdiacente(direzione);
				if(adiacente!=null) {
					if(stanzaDestinazione==null||adiacente.getAttrezzi().size()>stanzaDestinazione.getAttrezzi().size()) {
						stanzaDestinazione=adiacente;
					}
				}
			}
			partita.setStanzaCorrente(stanzaDestinazione);
			return "Sei stato educato a salutarmi! Come premio ecco la stanza con più attrezzi qui vicino: " + stanzaDestinazione.getNome();
		}else {
			//caso in cui la strega NON è stata salutata
			for (Direzione direzione : direzioni) {
				Stanza adiacente = stanzaCorrente.getStanzaAdiacente(direzione);
				if (adiacente != null) {
					if (stanzaDestinazione == null || adiacente.getAttrezzi().size() < stanzaDestinazione.getAttrezzi().size()) {
						stanzaDestinazione = adiacente;
					}
				}
			}
			partita.setStanzaCorrente(stanzaDestinazione);
			return "Che villano, non mi hai nemmeno salutata! Per punizione ti ho scaraventato nella stanza più spoglia: " + stanzaDestinazione.getNome();
		}
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
	    if (attrezzo == null) return "Non scherzare con me.";
	    
	    partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
	    
	    return "AHAHAH! Ora è SOLO MIO!";
	}
}
