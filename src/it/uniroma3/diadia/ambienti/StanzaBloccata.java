package it.uniroma3.diadia.ambienti;
//import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 *Classe che estende la classe Stanza, differisce da essa
 *poichè se non si ha nella stanza un determinato oggetto
 *una direzione della stanza stessa è inaccessibile.
 *L'oggetto "CHIAVE" e la direzione bloccata sono dati
 *nel compilatore del Labirinto
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 * @version 1.0
 */
public class StanzaBloccata extends Stanza {
 
	private String direzioneBloccata;
	private String CHIAVE;
	
	public StanzaBloccata (String nome, String direzioneBloccata, String CHIAVE) {	
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.CHIAVE = CHIAVE;
	}
	
	@Override
	public Stanza getStanzaAdiacente (String direzione) {
		
		if(direzione.equals(direzioneBloccata)) {
			if(this.hasAttrezzo(CHIAVE)) {
				return super.getStanzaAdiacente(direzione);
			} else 
				return this;
			
		} 
			
		return super.getStanzaAdiacente(direzione);
		
	}
	
	
	@Override
	 public String getDescrizione() {
		StringBuilder ris = new StringBuilder();
		
		ris.append(super.getDescrizione()).append("\n");
	    ris.append("Uscita Bloccata: ").append(direzioneBloccata).append("\n");
	    ris.append("Attrezzo necessario per sbloccare: ").append(CHIAVE).append("\n");

	    return ris.toString();
    }

}
