package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
/**
* Classe che estende la classe AbstractComando,
* questa classe serve per l'esecuzione del comando "Posa"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.2
*/
public class ComandoPosa extends AbstractComando{
	@Override
	public void eseguiSpecifico(Partita partita) {
		if(this.getParametro()==null) {
			this.getIO().mostraMessaggio("Che oggetto vuoi posare?");
			return;
		}
		partita.getGiocatore().posa(this.getParametro(), partita.getStanzaCorrente(),this.getIO());
	}
}
