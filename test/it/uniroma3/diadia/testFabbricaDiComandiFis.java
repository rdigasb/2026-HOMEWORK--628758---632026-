package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.comandi.*;


public class testFabbricaDiComandiFis {

   private FabbricaDiComandi fabbrica;
   private IO io;

	    @BeforeEach
	    public void setUp() {
	        fabbrica = new FabbricaDiComandiFisarmonica();
	    }

	    @Test
	    public void testComandoVai() {
	        Comando comando = fabbrica.costruisciComando("vai nord",io);
	        assertTrue(comando instanceof ComandoVai);
	    }

	    @Test
	    public void testComandoPrendi() {
	        Comando comando = fabbrica.costruisciComando("prendi chiave",io);
	        assertTrue(comando instanceof ComandoPrendi);
	    }

	    @Test
	    public void testComandoPosa() {
	        Comando comando = fabbrica.costruisciComando("posa chiave",io);
	        assertTrue(comando instanceof ComandoPosa);
	    }

	    @Test
	    public void testComandoAiuto() {
	        Comando comando = fabbrica.costruisciComando("aiuto",io);
	        assertTrue(comando instanceof ComandoAiuto);
	    }

	    @Test
	    public void testComandoFine() {
	        Comando comando = fabbrica.costruisciComando("fine",io);
	        assertTrue(comando instanceof ComandoFine);
	    }

	    @Test
	    public void testComandoGuarda() {
	        Comando comando = fabbrica.costruisciComando("guarda",io);
	        assertTrue(comando instanceof ComandoGuarda);
	    }

	    @Test
	    public void testComandoNonValido() {
	        Comando comando = fabbrica.costruisciComando("salta",io);
	        assertTrue(comando instanceof ComandoNonValido);
	    }

	    @Test
	    public void testInputVuoto() {
	        Comando comando = fabbrica.costruisciComando("",io);
	        assertTrue(comando instanceof ComandoNonValido);
	    }
	}

