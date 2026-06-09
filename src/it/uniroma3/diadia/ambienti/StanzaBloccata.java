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
 
	private Direzione direzioneBloccata;
    private String nomeAttrezzoSbloccante;

    public StanzaBloccata(String nome, Direzione direzioneBloccata, String nomeAttrezzoSbloccante) {
        super(nome);
        this.direzioneBloccata = direzioneBloccata;
        this.nomeAttrezzoSbloccante = nomeAttrezzoSbloccante;
    }

    @Override
    public Stanza getStanzaAdiacente(Direzione direzione) {
        if (direzioneBloccata.equals(direzione) && !this.hasAttrezzo(nomeAttrezzoSbloccante)) {
            return this; 
        }
        return super.getStanzaAdiacente(direzione);
    }
	
	
	 @Override
	 public String getDescrizione() {
		StringBuilder ris = new StringBuilder();
		
		ris.append(super.toString()).append("\n");
	    ris.append("Uscita Bloccata: ").append(direzioneBloccata).append("\n");
	    ris.append("Attrezzo necessario per sbloccare: ").append("CHIAVE").append("\n");
	    return ris.toString();
    }

}
