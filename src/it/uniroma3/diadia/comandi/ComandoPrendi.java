package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
/**
* Classe che estende la classe AbstractComando,
* questa classe serve per l'esecuzione del comando "Prendi"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.2
*/
public class ComandoPrendi extends AbstractComando{
	@Override
	public void eseguiSpecifico(Partita partita) {
		if(this.getParametro()==null) {
			this.getIO().mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		partita.getGiocatore().prendi(this.getParametro(), partita.getStanzaCorrente(),this.getIO());
	}
}
