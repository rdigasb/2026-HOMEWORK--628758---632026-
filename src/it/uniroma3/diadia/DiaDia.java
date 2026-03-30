package it.uniroma3.diadia;


//import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine"};

	private Partita partita;
	private IOConsole inputoutput;

	public DiaDia(IOConsole console) {
		this.inputoutput= console;
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
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
				this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
				this.posa(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			inputoutput.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			inputoutput.mostraMessaggio("Hai vinto!");
			return true;
		}
		if (this.partita.isFinita() && !this.partita.vinta()) {
			inputoutput.mostraMessaggio("Hai esaurito i CFU! Hai perso.");
	        return true; 
	    }
	    
	    return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			inputoutput.mostraMessaggio(elencoComandi[i]+" ");
	}
	
	/**
	 * Prende un attrezzo dalla stanza e lo mette nella borsa del giocatore.
	 */
	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			inputoutput.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		
		this.partita.getGiocatore().prendi(nomeAttrezzo, this.partita.getStanzaCorrente());
	}

	/**
	 * Toglie un attrezzo dalla borsa e lo lascia nella stanza corrente.
	 */
	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			inputoutput.mostraMessaggio("Cosa vuoi posare?");
			return;
		}
		
		this.partita.getGiocatore().posa(nomeAttrezzo, this.partita.getStanzaCorrente());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			inputoutput.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			inputoutput.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		inputoutput.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		inputoutput.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console =new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}