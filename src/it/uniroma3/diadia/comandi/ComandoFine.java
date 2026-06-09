package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
/**
* Classe che estende la classe AbstractComando,
* questa classe serve per l'esecuzione del comando "Fine"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.2
*/
public class ComandoFine extends AbstractComando{
	@Override
	public void eseguiSpecifico(Partita partita) {
		this.getIO().mostraMessaggio("Grazie per aver giocato");
		partita.setFinita();
	}
}
