package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
/**
 * Interfaccia Comando che verrà utilizzata da tutte 
 * le classi "Comando" che serviranno per la realizzazione
 * di diadia, prima era una classe che si occupava di tutto, ora dividiamo i compiti
 *
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 * @version 1.1
 */

public interface Comando{
	/*Esecuzione del comando*/
	public void esegui(Partita partita,IO io);
	/*Set parametro del comando*/
	public void setParametro(String stringa);
}