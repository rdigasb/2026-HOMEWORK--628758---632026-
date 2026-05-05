package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
/**
* Classe che implementa l'interfaccia Comando,
* questa classe serve per l'esecuzione del comando "Fine"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.1
*/
public class ComandoFine implements Comando{
	@Override
	public void esegui(Partita partita,IO io) {
		io.mostraMessaggio("Grazie per aver giocato");
		partita.setFinita();
	}
	@Override
	public void setParametro(String parametro) {
		/*Non serve*/
	}
}
