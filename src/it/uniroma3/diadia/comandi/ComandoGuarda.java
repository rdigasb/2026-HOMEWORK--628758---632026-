package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
/**
* Classe che implementa l'interfaccia Comando,
* questa classe serve per l'esecuzione del comando "Guarda"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.1
*/
public class ComandoGuarda implements Comando{
	@Override
	public void esegui(Partita partita,IO io) {
		io.mostraMessaggio("Sei qui: " + partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	@Override
	public void setParametro(String parametro) {
		/*Non serve*/
	}
}
