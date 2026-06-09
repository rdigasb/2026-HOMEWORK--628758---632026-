package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import java.util.List;
import java.util.ArrayList;

/**
* Classe che estende la classe AbstractComando,
* questa classe serve per l'esecuzione del comando "Aiuto"
* @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
* @version 1.3
*/

public class ComandoAiuto extends AbstractComando{
	private static final List<Class<?>> classiComandi = new ArrayList<>();

	static {
		classiComandi.add(ComandoVai.class);
		classiComandi.add(ComandoPrendi.class);
		classiComandi.add(ComandoPosa.class);
		classiComandi.add(ComandoGuarda.class);
		classiComandi.add(ComandoFine.class);
		classiComandi.add(ComandoAiuto.class);
		classiComandi.add(ComandoRegala.class); 
		classiComandi.add(ComandoInteragisci.class);
		classiComandi.add(ComandoSaluta.class);
		
		// Iteriamo sulla lista per forzare la registrazione in AbstractComando
		for (Class<?> classe : classiComandi) {
			try {
				classe.getConstructor().newInstance();
			} catch (Exception e) {
				
			}
		}
	}
	@Override
	public void eseguiSpecifico(Partita partita) {
		List<String> comandiDisponibili=AbstractComando.getElencoComandi();
		this.getIO().mostraMessaggio("Comandi disponibili: ");
		StringBuilder listaComandi=new StringBuilder();
		for(String comando : comandiDisponibili) {
			listaComandi.append(comando).append(" ");
		}
		this.getIO().mostraMessaggio(listaComandi.toString().trim());
	}
}
