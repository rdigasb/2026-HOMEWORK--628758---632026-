package it.uniroma3.diadia.ambienti;
/**
 *Classe che estende la classe Stanza, differisce da essa
 *poichè se non si ha nella stanza un determinato oggetto
 *se si utilizza il comando "Guarda" da tastiera non si 
 *potrà avere la descrizione della stanza.
 *L'oggetto che serve per vedere nella stanza è deciso
 *dal compilatore del Labirinto
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026 
 * @version 1.0
 */
public class StanzaBuia extends Stanza{
	private String attrezzoPerVedere;
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.attrezzoPerVedere=nomeAttrezzo;
	}
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoPerVedere)) {
			return super.getDescrizione();
			}
		else {
			return "Non Posso vedere";
		}
	}
}
