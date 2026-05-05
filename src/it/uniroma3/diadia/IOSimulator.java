package it.uniroma3.diadia;
/**
 * Questa classe implementa l'interfaccia IO per simulare l'interazione con l'utente.
 * Invece di leggere dalla console, utilizza una lista di stringhe predefinite come input
 * e memorizza tutti i messaggi prodotti dal gioco in una lista di output.
 * È particolarmente utile per l'automazione dei test di unità e di integrazione.
 *
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {

	private List<String> input;
	private List<String> output;
	private int indiceInput;

	    public IOSimulator(List<String> righeInput) {
	        this.input = new ArrayList<>(righeInput);
	        this.output = new ArrayList<>();
	        this.indiceInput = 0;
	    }
        /* usiamo un costruttore variabile (String...)
         * perch�?
         * perch� l'ho scoperto adesso ed � comodo
         * 
         * e senza non si pu� neanche fare 
         */
	    public IOSimulator(String... righeInput) {
	        this.input = new ArrayList<>();
	        for (String riga : righeInput) {
	            this.input.add(riga);
	        }
	        this.output = new ArrayList<>();
	        this.indiceInput = 0;
	    }

	    @Override
	    public void mostraMessaggio(String messaggio) {
	        output.add(messaggio);
	    }

	    @Override
	    public String leggiRiga() {
	        if (indiceInput < input.size()) {
	            return input.get(indiceInput++);
	        }
	        return null; 
	    }

	    

	    public List<String> getOutput() {
	        return output;
	    }

	    public String getUltimoMessaggio() {
	        if (output.isEmpty()) return null;
	        return output.get(output.size() - 1);
	    }

	    public List<String> getInput() {
	        return input;
	    }
}
