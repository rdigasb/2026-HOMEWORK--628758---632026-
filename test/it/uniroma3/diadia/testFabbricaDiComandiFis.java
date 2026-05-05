package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.comandi.*;


public class testFabbricaDiComandiFis {

   private FabbricaDiComandi fabbrica;

	    @BeforeEach
	    public void setUp() {
	        fabbrica = new FabbricaDiComandiFisarmonica();
	    }

	    @Test
	    public void testComandoVai() {
	        Comando comando = fabbrica.costruisciComando("vai nord");
	        assertTrue(comando instanceof ComandoVai);
	    }

	    @Test
	    public void testComandoPrendi() {
	        Comando comando = fabbrica.costruisciComando("prendi chiave");
	        assertTrue(comando instanceof ComandoPrendi);
	    }

	    @Test
	    public void testComandoPosa() {
	        Comando comando = fabbrica.costruisciComando("posa chiave");
	        assertTrue(comando instanceof ComandoPosa);
	    }

	    @Test
	    public void testComandoAiuto() {
	        Comando comando = fabbrica.costruisciComando("aiuto");
	        assertTrue(comando instanceof ComandoAiuto);
	    }

	    @Test
	    public void testComandoFine() {
	        Comando comando = fabbrica.costruisciComando("fine");
	        assertTrue(comando instanceof ComandoFine);
	    }

	    @Test
	    public void testComandoGuarda() {
	        Comando comando = fabbrica.costruisciComando("guarda");
	        assertTrue(comando instanceof ComandoGuarda);
	    }

	    @Test
	    public void testComandoNonValido() {
	        Comando comando = fabbrica.costruisciComando("salta");
	        assertTrue(comando instanceof ComandoNonValido);
	    }

	    @Test
	    public void testInputVuoto() {
	        Comando comando = fabbrica.costruisciComando("");
	        assertTrue(comando instanceof ComandoNonValido);
	    }
	}

