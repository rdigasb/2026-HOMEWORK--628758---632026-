package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version 1.4
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private Partita partita;
	private IO inputoutput;

	public DiaDia(IO console) {
		this.inputoutput=console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		inputoutput.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do {		
			istruzione = inputoutput.leggiRiga();
			if(istruzione==null) {
				break;
			}
		}while (!processaIstruzione(istruzione));
		
	/*	scannerDiLinee.close(); PER IL MOMENTO TENIAMOLO COME COMMENTO */
	}   


	/**
	 * Processa una istruzione
	 */
	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandi factory=new FabbricaDiComandiFisarmonica();
		Comando comando=factory.costruisciComando(istruzione);
		comando.esegui(this.partita,this.inputoutput);
		if(this.partita.vinta()) {
			inputoutput.mostraMessaggio("Hai Vinto!");
			return true;
		}
		if(this.partita.isFinita()) {
			return true;
		}
		return false;
	}   

	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}