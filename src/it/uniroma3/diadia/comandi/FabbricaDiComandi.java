package it.uniroma3.diadia.comandi;

/**
 * Interfaccia Fabbrica di Comandi che verrà implementata 
 * da fabbrica di comandi fisarmonica per analizzare 
 * gli effettivi comandi dati da tastiera *
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 * @version 1.0
 */

public interface FabbricaDiComandi {
    public Comando costruisciComando(String istruzione);
}
