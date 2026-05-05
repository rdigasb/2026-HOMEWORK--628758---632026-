package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
/**
* Classe che implementa l'interfaccia Comando,
* questa classe serve per l'esecuzione del comando "Aiuto"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.1
*/
import it.uniroma3.diadia.IO;
public class ComandoAiuto implements Comando{
	static final private String[] elencoComandi={"vai", "aiuto", "prendi", "posa", "fine", "guarda"};
	@Override
	public void esegui(Partita partita,IO io) {
		for(int i=0;i<elencoComandi.length;i++) {
			io.mostraMessaggio(elencoComandi[i]+" ");
		}
	}
	@Override
	public void setParametro(String parametro) {
		/*Non serve*/
	}
}
