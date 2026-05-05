package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
/**
* Classe che implementa l'interfaccia Comando,
* questa classe serve per l'esecuzione del comando "Vai"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.1
*/
public class ComandoVai implements Comando{
	private String direzione;
	@Override
	public void esegui(Partita partita,IO io) {
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		Stanza prossimaStanza=stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(this.direzione==null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare la direzione");
			return;
		}
		if(prossimaStanza==null) {
			io.mostraMessaggio("Direzione Inesistente");
			return;
		}
		else {
			partita.setStanzaCorrente(prossimaStanza);
			io.mostraMessaggio(partita.getStanzaCorrente().getNome());
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		}
	}
	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}
}
