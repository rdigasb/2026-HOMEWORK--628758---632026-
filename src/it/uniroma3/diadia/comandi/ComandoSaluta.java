package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
public class ComandoSaluta extends AbstractComando{
	@Override
	public void eseguiSpecifico(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if(personaggio == null) {
			this.getIO().mostraMessaggio("Non c'è nessuno con cui parlare");
			return;
		}
		String risposta=personaggio.saluta();
		this.getIO().mostraMessaggio(risposta);
	}
}
