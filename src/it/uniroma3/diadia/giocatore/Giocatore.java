package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;

/* questa classe gestisce il giocatore.
 * Giocatore gestisce i cfu, le azioni per prendere 
 * e posare gli strumenti.
 * 
 *  @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 *  @see Borsa, Stanza, Attrezzo
 *  @version 1.2
 */
public class Giocatore {

	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
   // private Stanza stanzaCorrente;
  
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	
	public void prendi(String nomeAttrezzo, Stanza stanzaCorrente,IO io) {
        
        Attrezzo a = stanzaCorrente.getAttrezzo(nomeAttrezzo);

        if (a != null) {
            
            if (this.borsa.addAttrezzo(a)) {
               
                stanzaCorrente.removeAttrezzo(a);
                io.mostraMessaggio("Hai preso: " + nomeAttrezzo);
            } else {
                io.mostraMessaggio("Borsa troppo piena! Non puoi prendere " + nomeAttrezzo);
            }
        } else {
            io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è in questa stanza.");
        }
    }
	
	
	public void posa(String nomeAttrezzo, Stanza stanzaCorrente,IO io) {
        if (this.borsa.hasAttrezzo(nomeAttrezzo)) {
            Attrezzo a = this.borsa.removeAttrezzo(nomeAttrezzo);
            stanzaCorrente.addAttrezzo(a);
            io.mostraMessaggio("Hai posato: " + nomeAttrezzo);
        } else {
            io.mostraMessaggio("Non hai " + nomeAttrezzo + " in borsa.");
        }
    }
	
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
}
