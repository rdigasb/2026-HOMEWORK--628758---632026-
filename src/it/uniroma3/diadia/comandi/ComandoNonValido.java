package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
/**
* Classe che estende la classe AbstractComando,
* questa classe serve per dire all'utente che 
* il comando richiamato non Esiste
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.2
*/
public class ComandoNonValido extends AbstractComando{
	@Override
	public void eseguiSpecifico(Partita partita) {
		this.getIO().mostraMessaggio("Comando inesistente");
	}
}
