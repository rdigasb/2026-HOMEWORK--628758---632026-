package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
/**
* Classe che estende la classe AbstractComando,
* questa classe serve per l'esecuzione del comando "Guarda"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.2
*/
public class ComandoGuarda extends AbstractComando{
	@Override
	public void eseguiSpecifico(Partita partita) {
		this.getIO().mostraMessaggio("Sei qui: " + partita.getStanzaCorrente().getDescrizione());
		this.getIO().mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
		this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
}
