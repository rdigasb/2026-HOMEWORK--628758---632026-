package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public class Cane extends AbstractPersonaggio{
	private static final String MESSAGGIO_MORSO = "ACCIDENTI! Il cane non ha gradito e ti ha morso. Perdi 1 CFU";
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		int cfuAttuali=partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfuAttuali-1);
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo == null) {
			return "Wof? Non mi stai dando nulla.";
		}
		
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		if (attrezzo.getNome().equalsIgnoreCase("osso")) {
			Attrezzo regaloDelCane = new Attrezzo("chiave", 1);
			partita.getStanzaCorrente().addAttrezzo(regaloDelCane);
			
			return "WOF WOF! Il cane scodinzola felicissimo per l'osso e lascia cadere una chiave per terra!";
		}
		
		int cfuAttuali = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfuAttuali - 1);
		
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
		return "GRRR... Al cane non interessa affatto " + attrezzo.getNome() + "! Ti morde per il fastidio (perdi 1 CFU) e lascia l'oggetto per terra.";
	}
}
