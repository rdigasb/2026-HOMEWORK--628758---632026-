package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import java.util.List;
import java.util.ArrayList;

/*classe che si occupa di astrarre i metodi derivanti dalla
 * Classe Comando, così da non dover avere dei metodi setParametro() "vuoti"
 * nelle sottoclassi dei vari comandi
 */
public abstract class AbstractComando implements Comando{
	private IO io;
	private String parametro;
	private static final List<String> elencoComandi=new ArrayList<>();
	public AbstractComando() {
		String nomeClasse= this.getClass().getSimpleName();
		if(nomeClasse.startsWith("Comando") && !nomeClasse.equals("ComandoNonValido")) {
			String nomeComando=nomeClasse.substring("Comando".length()).toLowerCase();
			if(!elencoComandi.contains(nomeComando)) {
				elencoComandi.add(nomeComando);
			}
		}
	}
	@Override
	public final void esegui(Partita partita, IO io){
		this.io=io;
		this.eseguiSpecifico(partita);
	}
	protected abstract void eseguiSpecifico(Partita partita);
	
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public static List<String> getElencoComandi(){
		return elencoComandi;
	}
	
	public IO getIO() {
		return this.io;
	}
}
