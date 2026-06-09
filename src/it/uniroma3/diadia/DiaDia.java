package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiIntrospettiva;
import it.uniroma3.diadia.ambienti.Labirinto;
//import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Direzione;
import java.util.Scanner;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version 1.5
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
	
	public DiaDia(Labirinto labirinto, IO io) {
		this.inputoutput=io;
		this.partita=new Partita(labirinto);
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
		FabbricaDiComandi factory=new FabbricaDiComandiIntrospettiva();
		Comando comando=factory.costruisciComando(istruzione, this.inputoutput);
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

	public static void main(String[] argc) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) { 
			IO io = new IOConsole(scanner);
			
			
			Labirinto labirinto = Labirinto.newBuilder()
					.addStanzaIniziale("LabCampusOne")
					.addStanzaVincente("Biblioteca")
					.addStanza("AulaN11")
					.addStanza("AulaN10")
					.addAttrezzo("lanterna", 3)
					.addAttrezzo("osso", 1)
					.addAdiacenza("LabCampusOne", "AulaN11", Direzione.NORD)
					.addAdiacenza("LabCampusOne", "AulaN10", Direzione.SUD)
					.addAdiacenza("AulaN11", "Biblioteca", Direzione.EST)
					.addAdiacenza("AulaN11", "LabCampusOne", Direzione.SUD)
					.addAdiacenza("AulaN10", "LabCampusOne", Direzione.NORD)
					.addAdiacenza("Biblioteca", "AulaN11", Direzione.OVEST)
					.getLabirinto();
					
			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		}
	}
}