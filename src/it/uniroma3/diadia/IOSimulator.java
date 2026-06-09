package it.uniroma3.diadia;
/**
 * Questa classe implementa l'interfaccia IO per simulare l'interazione con l'utente.
 * Invece di leggere dalla console, utilizza una lista di stringhe predefinite come input
 * e memorizza tutti i messaggi prodotti dal gioco in una lista di output.
 * È particolarmente utile per l'automazione dei test di unità e di integrazione.
 *
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026
 * @version 1.2
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class IOSimulator implements IO {

	private List<String> input;
	private List<String> output;
	private int indiceInput;
	private Map<String, List<String>> riga2messaggi;
	private String chiaveRigaCorrente;

	    public IOSimulator(List<String> righeInput) {
	        this.input = new ArrayList<>(righeInput);
	        this.output = new ArrayList<>();
	        this.indiceInput = 0;
	        this.riga2messaggi=new HashMap<>();
	        this.chiaveRigaCorrente="0: BENVENUTO";
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
	        this.riga2messaggi=new HashMap<>();
	        this.chiaveRigaCorrente="0: BENVENUTO";
	    }

	    @Override
	    public void mostraMessaggio(String messaggio) {
	        this.output.add(messaggio);
	        if(!this.riga2messaggi.containsKey(this.chiaveRigaCorrente)) {
	        	this.riga2messaggi.put(this.chiaveRigaCorrente, new ArrayList<>());
	        }
	        this.riga2messaggi.get(this.chiaveRigaCorrente).add(messaggio);
	    }

	    @Override
	    public String leggiRiga() {
	        if (this.indiceInput < this.input.size()) {
	            String riga= this.input.get(this.indiceInput);
	            this.indiceInput++;
	            this.chiaveRigaCorrente=this.indiceInput+ ": " + riga;
	            return riga;
	        }
	        return null; 
	    }

	    

	    public List<String> getOutput() {
	        return this.output;
	    }

	    public String getUltimoMessaggio() {
	        if (this.output.isEmpty()) return null;
	        return this.output.get(this.output.size() - 1);
	    }

	    public List<String> getInput() {
	        return input;
	    }
	    
	    public List<String> getMessaggiDiRiga(String chiaveRiga){
	    	return this.riga2messaggi.get(chiaveRiga);
	    }
	    
	    public Map<String, List<String>> getMappaRiga2Messaggi(){
	    	return this.riga2messaggi;
	    }
}
