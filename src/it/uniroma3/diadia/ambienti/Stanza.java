package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import it.uniroma3.diadia.personaggi.*;
import java.util.Set;
/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite gestite tramite mappe.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026  
 * @see Attrezzo
 * @version 1.2
*/
public class Stanza {
	
	private String nome;
	
    private Map<String,Attrezzo> attrezzi;
    
    private AbstractPersonaggio personaggio;
    
 
    private Map<Direzione, Stanza> stanzeAdiacenti; 

    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
    }

    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        this.stanzeAdiacenti.put(direzione, stanza);
    }

    public Stanza getStanzaAdiacente(Direzione direzione) {
        return this.stanzeAdiacenti.get(direzione);
    }

    public Set<Direzione> getDirezioni() {
        return this.stanzeAdiacenti.keySet();
    }

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi.values();
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if(attrezzo==null) return false;
        this.attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti.
	* toString e' stata modificata dalla sua versione originale per un errore N.P.E.
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.stanzeAdiacenti.keySet())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	
    	for (Attrezzo attrezzo : this.attrezzi.values()) {
    		if (attrezzo != null) {
    			risultato.append(attrezzo.toString()+" ");
    		}
    	}
    	if (this.getPersonaggio() != null) {
    	    risultato.append("\nIn questa stanza c'è un personaggio: " + this.getPersonaggio().getNome());
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo==null) return false;
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null) return null;
		return this.attrezzi.get(nomeAttrezzo)	;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
	    if(attrezzo == null) return false;
	    Attrezzo rimosso=this.attrezzi.remove(attrezzo.getNome());
	    return rimosso!=null;
	}

	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

}
