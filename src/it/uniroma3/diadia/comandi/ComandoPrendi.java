package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
/**
* Classe che implementa l'interfaccia Comando,
* questa classe serve per l'esecuzione del comando "Prendi"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.1
*/
public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	@Override
	public void esegui(Partita partita,IO io) {
		if(this.nomeAttrezzo==null) {
			io.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		partita.getGiocatore().prendi(this.nomeAttrezzo, partita.getStanzaCorrente(),io);
	}
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}
}
