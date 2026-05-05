package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;  
import org.junit.jupiter.api.Test;
import java.util.List;

public class testIOSimulator {

	    @Test
	    public void testPartitaCompleta() {

	        IOSimulator io = new IOSimulator ("vai sud", "prendi chiave", "vai est", "fine");

	        DiaDia gioco = new DiaDia(io);
	        gioco.gioca();

	        List<String> output = io.getOutput();

	        assertTrue(output.contains("Hai preso: chiave"));
	        assertTrue(output.contains("Grazie per aver giocato"));
	    }
	    
	    
	    @Test
	    public void testComandoNonValido() {

	        IOSimulator io = new IOSimulator("salta");

	        DiaDia gioco = new DiaDia(io);
	        gioco.gioca();

	        boolean messaggioErrore = io.getOutput().contains("Comando inesistente");

	        assertTrue(messaggioErrore);
	    }
	    
	    
	    @Test
	    public void testVittoria() {

	        IOSimulator io = new IOSimulator("vai sud", "vai nord", "prendi osso", "vai nord");

	        DiaDia gioco = new DiaDia(io);
	        gioco.gioca();

	        boolean vittoria = io.getUltimoMessaggio().contains("Hai Vinto!");

	        assertTrue(vittoria);
	    }
	}

