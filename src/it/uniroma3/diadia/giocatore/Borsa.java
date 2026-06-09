package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import it.uniroma3.diadia.*;

/**
 * Classe Borsa - una borsa in un gioco di ruolo.
 * una borsa immagazina oggetti tramite una borsa fino al peso massimo.
 * possiamo aggiungere e rimuovere oggetti e controllare il peso della borsa.
 * 
 * @author Rocco Di Gasbarro, Matricola: 628758 // Francesco Abballe, Matricola: 632026  
 * @see Attrezzo
 * @version 1.4
*/

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
    private Map<String,Attrezzo> attrezzi;
    private int pesoMax;
 
    public Borsa() {
    	this(Configuratore.getPesoMaxBorsa());
    }

    public Borsa(int pesoMax) {
       this.pesoMax = pesoMax;
       this.attrezzi = new HashMap<>();
    }

    
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(attrezzo==null) return false;
       if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
           return false;
       
       this.attrezzi.put(attrezzo.getNome(), attrezzo);
       return true;
    }

    
    public int getPesoMax() {
       return pesoMax;
    }

    
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
       if(nomeAttrezzo==null) return null;
       return this.attrezzi.get(nomeAttrezzo);
       }

    
    public int getPeso() {
    	int peso = 0;
    	
    	for (Attrezzo a : this.attrezzi.values())
    		peso+=a.getPeso();
    	return peso;
    	}

    
    public boolean isEmpty() {
    	return this.attrezzi.isEmpty();
    	}

    
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	if(nomeAttrezzo==null) return false;
    	return this.attrezzi.containsKey(nomeAttrezzo);
    	}

    
    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        if (nomeAttrezzo == null) return null; 
        return this.attrezzi.remove(nomeAttrezzo);
    }
    
    
    public List<Attrezzo> getContenutoOrdinatoPerPeso(){
    	List<Attrezzo> listaOrdinata=new ArrayList<>(this.attrezzi.values());
    	Comparator<Attrezzo> comparatorePerPesoENome = new Comparator<Attrezzo>() {
    		@Override
    		public int compare(Attrezzo a1, Attrezzo a2) {
    			int differenzaPeso= a1.getPeso()-a2.getPeso();
    			if(differenzaPeso!=0) return differenzaPeso;
    			else {
    				return a1.getNome().compareTo(a2.getNome());    			
    				}
    		}
    	};
    	Collections.sort(listaOrdinata,comparatorePerPesoENome);
    	return listaOrdinata;
    }
    public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
    	Map<Integer,Set<Attrezzo>> mappaRaggruppata=new HashMap<>();
    	for(Attrezzo a:this.attrezzi.values()) {
    		int peso=a.getPeso();
    		if(!mappaRaggruppata.containsKey(peso)) mappaRaggruppata.put(peso,  new HashSet<>());
    		mappaRaggruppata.get(peso).add(a);
    	}
    	return mappaRaggruppata;
    }
    public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
    	return new TreeSet<>(this.attrezzi.values());
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (!this.isEmpty()) {
            s.append("Contenuto borsa (").append(this.getPeso()).append("kg/").append(this.getPesoMax()).append("kg):\n");
            
            s.append("-> Ordinato per nome: ").append(this.getContenutoOrdinatoPerNome().toString()).append("\n");
            s.append("-> Ordinato per peso: ").append(this.getContenutoOrdinatoPerPeso().toString()).append("\n");
            s.append("-> Raggruppato per peso: ").append(this.getContenutoRaggruppatoPerPeso().toString());
        } else {
            s.append("Borsa vuota");
        }
        return s.toString();
    }
    public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
    	Comparator<Attrezzo> comparatorePerPeso=new Comparator<Attrezzo>() {
    		@Override
    		public int compare(Attrezzo a1, Attrezzo a2) {
    			int diffPeso=a1.getPeso()-a2.getPeso();
    			if(diffPeso!=0) return diffPeso;
    			else {
    				return a1.getNome().compareTo(a2.getNome());
    			}
    		}
    	};
    	SortedSet<Attrezzo> setOrdinato=new TreeSet<>(comparatorePerPeso);
    	setOrdinato.addAll(this.attrezzi.values());
    	return setOrdinato;
    }
}