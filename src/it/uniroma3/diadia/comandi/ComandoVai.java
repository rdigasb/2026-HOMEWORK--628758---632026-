package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	@Override
	public void eseguiSpecifico(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.getParametro() == null) {
			this.getIO().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		
		Direzione direzione;
		try {
			direzione = Direzione.valueOf(this.getParametro().toUpperCase());
		} catch (IllegalArgumentException e) {
			this.getIO().mostraMessaggio("Direzione non valida. Usa: nord, sud, est, ovest.");
			return;
		}

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.getIO().mostraMessaggio("Di la' non ci sono porte!");
		} else {
			//partita.getStanzaCorrente().setPersonaggioVisibile(false);
			partita.setStanzaCorrente(prossimaStanza);
			this.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		}
	}
}