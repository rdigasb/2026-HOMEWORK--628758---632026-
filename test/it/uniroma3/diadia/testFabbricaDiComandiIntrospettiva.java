package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.comandi.*;

public class testFabbricaDiComandiIntrospettiva {
	private FabbricaDiComandiIntrospettiva fabbrica;
	private IO io;

	@BeforeEach
	public void setUp() {
		this.fabbrica = new FabbricaDiComandiIntrospettiva();
		this.io = new IOSimulator();
	}

	@Test
	public void testCostruisciComando_Valido() {
		Comando comando = this.fabbrica.costruisciComando("vai nord", io);
		
		assertNotNull(comando);
		// Verifichiamo che l'oggetto istanziato sia effettivamente un'istanza di ComandoVai
		assertEquals(ComandoVai.class, comando.getClass());
	}

	@Test
	public void testCostruisciComando_Inesistente_LanciaClassNotFoundExceptionInterna() {
		// "salta" cercherà ComandoSalta che non esiste -> ClassNotFoundException gestita
		Comando comando = this.fabbrica.costruisciComando("salta", io);
		
		assertNotNull(comando);
		assertEquals(ComandoNonValido.class, comando.getClass());
	}

	@Test
	public void testCostruisciComando_StringaVuota() {
		Comando comando = this.fabbrica.costruisciComando("", io);
		
		assertNotNull(comando);
		assertEquals(ComandoNonValido.class, comando.getClass());
	}

	@Test
	public void testCostruisciComando_MaiuscoleMinuscole() {
		// La fabbrica deve formattare bene "vAi" -> "Vai"
		Comando comando = this.fabbrica.costruisciComando("vAi ovest", io);
		
		assertNotNull(comando);
		assertEquals(ComandoVai.class, comando.getClass());
	}
}
