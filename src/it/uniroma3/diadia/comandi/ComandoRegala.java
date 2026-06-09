package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
* Classe che estende la classe AbstractComando,
* questa classe serve per l'esecuzione del comando "Regala"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.3
*/
public class ComandoRegala extends AbstractComando {
	
	@Override
	public void eseguiSpecifico(Partita partita) {
		
		if (this.getParametro() == null) {
			this.getIO().mostraMessaggio("Quale attrezzo vuoi regalare?"); 
			return;
		}
		
		
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			this.getIO().mostraMessaggio("Non c'è nessuno in questa stanza a cui poter fare un regalo!");
			return;
		}
		
		
		if (partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())) {
			Attrezzo daRegalare = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
			
			String rispostaPersonaggio = personaggio.riceviRegalo(daRegalare, partita);
			
			this.getIO().mostraMessaggio(rispostaPersonaggio);
			
		} else {
			this.getIO().mostraMessaggio("Non hai l'oggetto " + this.getParametro() + " nella borsa.");
		}
	}
}